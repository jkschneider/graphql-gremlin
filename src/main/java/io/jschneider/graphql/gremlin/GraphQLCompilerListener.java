package io.jschneider.graphql.gremlin;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import io.jschneider.graphql.gremlin.grammar.GraphQLBaseListener;
import io.jschneider.graphql.gremlin.grammar.GraphQLParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class GraphQLCompilerListener extends GraphQLBaseListener {
    private GraphTraversal<Vertex, ?> traversal;

    /**
     * .as(R) names must have unique R across all fields and relations throughout the traversal
     */
    Multiset<String> asNames = TreeMultiset.create();

    GraphQLEntity documentEntity = new GraphQLEntity("document", "document");
    Stack<GraphQLEntity> entityStack = new Stack<>();

    public GraphQLCompilerListener(GraphTraversal<Vertex, ?> traversal) {
        this.traversal = traversal;
    }

    public GraphTraversal<Vertex, ?> result() {
        return traversal;
    }

    @Override
    public void enterDocument(@NotNull GraphQLParser.DocumentContext ctx) {
        entityStack.push(documentEntity);
    }

    @Override
    public void enterFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx) {
        String relationName = ctx.fieldName().getText();
        String relationAlias = relationName + asNames.count(relationName);

        GraphQLEntity entity = new GraphQLEntity(relationName, relationAlias);
        GraphQLEntity parentEntity = entityStack.peek();
        parentEntity.getChildEntities().add(entity);

        GraphTraversal<?, ?> whereClause;
        if(parentEntity == documentEntity)
            whereClause = __.as(parentEntity.getRelationName()).barrier(1);
        else
            whereClause = __.as(parentEntity.getPrivateRelationAlias()).out(entity.getRelationName());

        for (GraphQLParser.ArgumentContext arg : ctx.arguments().argument()) {
            whereClause = whereClause.has(arg.NAME().getText(), extractValue(arg.valueOrVariable().value()));
        }
        whereClause.as(entity.getPrivateRelationAlias());
        entity.setWhereClause(whereClause);

        entityStack.push(entity);
        asNames.add(relationName);
    }

    private Object extractValue(GraphQLParser.ValueContext valueContext) {
        String val = valueContext.getText().replaceAll("\"", "");
        if(valueContext instanceof GraphQLParser.StringValueContext)
            return val;
        if(valueContext instanceof GraphQLParser.BooleanValueContext)
            return Boolean.parseBoolean(val);
        if(valueContext instanceof GraphQLParser.NumberValueContext) {
            if(val.contains("."))
                return Double.parseDouble(val);
            else
                return Integer.parseInt(val);
        }
        return null;
        // FIXME deal with ArrayValueContext
    }

    @Override
    public void exitFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx) {
        entityStack.pop();
    }

    @Override
    public void enterFieldValue(@NotNull GraphQLParser.FieldValueContext ctx) {
        GraphQLParser.FieldNameContext fieldNameContext = ctx.fieldName();

        String fieldName = fieldNameContext.getText();
        String queryAlias = null;

        if(fieldNameContext.alias() != null) {
            List<TerminalNode> aliasParts = fieldNameContext.alias().NAME();
            fieldName = aliasParts.get(1).getText();
            queryAlias = aliasParts.get(0).getText();
        }

        String fieldAlias = fieldName + asNames.count(fieldName);
        asNames.add(fieldName);

        entityStack.peek().getFields().add(new GraphQLField(fieldName, fieldAlias, queryAlias));
    }

    @Override
    public void exitDocument(@NotNull GraphQLParser.DocumentContext ctx) {
        // there should be precisely one entity left on the stack, the document entity
        Preconditions.checkState(entityStack.size() == 1);
        recurseBuildMatch(documentEntity, traversal);
    }

    private GraphTraversal<?, ?> recurseBuildMatch(GraphQLEntity entity, GraphTraversal<?, ?> at) {
        List<GraphQLField> fields = entity.getFields();

        List<Traversal<?, ?>> matchClauses = new ArrayList<>(fields.size() + entity.getChildEntities().size() + 1);

        if(entity.getWhereClause() != null)
            matchClauses.add(entity.getWhereClause());

        matchClauses.addAll(fields.stream()
            .map(field -> __.as(entity.getPrivateRelationAlias()).values(field.getFieldName()).as(field.getFieldAlias()))
            .collect(Collectors.toList()));

        matchClauses.addAll(entity.getChildEntities().stream()
            .map(childEntity -> recurseBuildMatch(childEntity, __.as(entity.getPrivateRelationAlias())))
            .collect(Collectors.toList()));

        GraphTraversal<?, ?> match = at.match(matchClauses.toArray(new Traversal<?, ?>[matchClauses.size()]));
        match.asAdmin().addStep(new GraphQLEntitySelectStep<>(match.asAdmin(), entity));

        if(!entity.getRelationAlias().equals(entity.getRelationName()))
            match = match.as(entity.getRelationAlias());

        return match;
    }
}
