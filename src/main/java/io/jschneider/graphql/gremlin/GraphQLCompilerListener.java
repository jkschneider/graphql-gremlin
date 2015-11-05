package io.jschneider.graphql.gremlin;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import io.jschneider.graphql.gremlin.grammar.GraphQLBaseListener;
import io.jschneider.graphql.gremlin.grammar.GraphQLParser;
import org.antlr.v4.runtime.misc.NotNull;
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

    Multiset<String> relationNames = TreeMultiset.create();
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
        Integer relationCount = relationNames.count(relationName);
//        String relationAlias = relationName + (relationCount == 0 ? "" : relationCount);
        String relationAlias = relationName + relationCount;

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
        relationNames.add(relationName);
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
        entityStack.peek().getFields().add(ctx.fieldName().getText());
    }

    @Override
    public void exitDocument(@NotNull GraphQLParser.DocumentContext ctx) {
        // there should be precisely one entity left on the stack, the document entity
        Preconditions.checkState(entityStack.size() == 1);
        GraphQLEntity rootEntity = documentEntity.getChildEntities().get(0);
        recurseBuildMatch(rootEntity, traversal);
    }

    private GraphTraversal<?, ?> recurseBuildMatch(GraphQLEntity entity, GraphTraversal<?, ?> at) {
        List<String> fields = entity.getFields();
        List<String> selects = new ArrayList<>(fields);

        List<Traversal<?, ?>> matchClauses = new ArrayList<>(fields.size() + entity.getChildEntities().size() + 1);

        if(entity.getWhereClause() != null)
            matchClauses.add(entity.getWhereClause());

        // FIXME deconflict field names -- we probably need a global field alias construct
        matchClauses.addAll(fields.stream()
            .map(field -> __.as(entity.getPrivateRelationAlias()).values(field).as(field))
            .collect(Collectors.toList()));

        for (GraphQLEntity childEntity : entity.getChildEntities()) {
            matchClauses.add(recurseBuildMatch(childEntity, __.as(entity.getPrivateRelationAlias())));
            selects.add(childEntity.getRelationName());
        }

        GraphTraversal<?, ?> match = at.match(matchClauses.toArray(new Traversal<?, ?>[matchClauses.size()]));
        match.asAdmin().addStep(new GraphQLEntitySelectStep<>(match.asAdmin(), entity));

        return match.as(entity.getRelationAlias());
    }
}
