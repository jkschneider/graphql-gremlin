package io.jschneider.graphql.gremlin;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import io.jschneider.graphql.gremlin.directive.GraphQLDirectiveAware;
import io.jschneider.graphql.gremlin.directive.SkipDirective;
import io.jschneider.graphql.gremlin.entity.GraphQLEntity;
import io.jschneider.graphql.gremlin.entity.GraphQLEntitySelectStep;
import io.jschneider.graphql.gremlin.entity.GraphQLFragmentEntity;
import io.jschneider.graphql.gremlin.entity.GraphQLRelationEntity;
import io.jschneider.graphql.gremlin.field.GraphQLField;
import io.jschneider.graphql.gremlin.grammar.GraphQLBaseListener;
import io.jschneider.graphql.gremlin.grammar.GraphQLParser;
import io.jschneider.graphql.gremlin.variable.GraphQLValue;
import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;
import io.jschneider.graphql.gremlin.variable.GraphQLVariable;
import io.jschneider.graphql.gremlin.variable.VariableResolver;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.*;
import java.util.stream.Collectors;

public class GraphQLCompilerListener extends GraphQLBaseListener {
    private GraphTraversal<Vertex, ?> traversal;
    private VariableResolver variableResolver;

    /**
     * .as(R) names must have unique R across all fields and relations throughout the traversal
     */
    private Multiset<String> asNames = TreeMultiset.create();

    private GraphQLRelationEntity queryEntity = new GraphQLRelationEntity("query", "query");
    private Stack<GraphQLEntity> entityStack = new Stack<>();
    private Map<String, GraphQLFragmentEntity> fragmentsByName = new HashMap<>();

    public GraphQLCompilerListener(GraphTraversal<Vertex, ?> traversal, VariableResolver variableResolver) {
        this.traversal = traversal;
        this.variableResolver = variableResolver;
    }

    public GraphTraversal<Vertex, ?> result() {
        return traversal;
    }

    @Override
    public void enterDocument(@NotNull GraphQLParser.DocumentContext ctx) {
        entityStack.push(queryEntity);
    }

    @Override
    public void enterOperationType(@NotNull GraphQLParser.OperationTypeContext ctx) {
        if(ctx.getText().equals("mutation"))
            throw new UnsupportedOperationException("GraphQL mutation is not supported");
    }

    @Override
    public void enterFragmentDefinition(@NotNull GraphQLParser.FragmentDefinitionContext ctx) {
        GraphQLFragmentEntity fragment = fragmentsByName.computeIfAbsent(ctx.fragmentName().getText(),
                GraphQLFragmentEntity::new);
        GraphQLParser.TypeNameContext typeName = ctx.typeCondition().typeName();
        if(typeName != null)
            fragment.setType(typeName.getText());

        addDirectives(fragment, ctx.directives());

        entityStack.push(fragment);
    }

    @Override
    public void exitFragmentDefinition(@NotNull GraphQLParser.FragmentDefinitionContext ctx) {
        entityStack.pop();
    }

    @Override
    public void enterFragmentSpread(@NotNull GraphQLParser.FragmentSpreadContext ctx) {
        GraphQLFragmentEntity fragment = fragmentsByName.computeIfAbsent(ctx.fragmentName().getText(),
                GraphQLFragmentEntity::new);
        addDirectives(fragment, ctx.directives());

        GraphQLEntity parentEntity = entityStack.peek();
        parentEntity.getChildEntities().add(fragment);
    }

    @Override
    public void enterInlineFragment(@NotNull GraphQLParser.InlineFragmentContext ctx) {
        String name = "inlineFragment" + asNames.count("inlineFragment");
        asNames.add("inlineFragment");
        GraphQLFragmentEntity fragment = fragmentsByName.computeIfAbsent(name, GraphQLFragmentEntity::new);
        addDirectives(fragment, ctx.directives());

        GraphQLEntity parentEntity = entityStack.peek();
        parentEntity.getChildEntities().add(fragment);

        entityStack.push(fragment);
    }

    @Override
    public void exitInlineFragment(@NotNull GraphQLParser.InlineFragmentContext ctx) {
        entityStack.pop();
    }

    @Override
    public void enterFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx) {
        String relationName = ctx.fieldName().getText();
        String relationAlias = relationName + asNames.count(relationName);

        GraphQLRelationEntity entity = new GraphQLRelationEntity(relationName, relationAlias);
        GraphQLEntity parentEntity = entityStack.peek();
        parentEntity.getChildEntities().add(entity);

        for (GraphQLParser.ArgumentContext arg : ctx.arguments().argument())
            entity.getWhereClauses().add(valueOrVariable(arg));

        addDirectives(entity, ctx.directives());

        entityStack.push(entity);
        asNames.add(relationName);
    }

    private Object value(GraphQLParser.ValueContext valueContext) {
        String val = valueContext.getText().replaceAll("\"", "");
        if (valueContext instanceof GraphQLParser.StringValueContext)
            return val;
        if (valueContext instanceof GraphQLParser.BooleanValueContext)
            return Boolean.parseBoolean(val);
        if (valueContext instanceof GraphQLParser.NumberValueContext) {
            if (val.contains("."))
                return Double.parseDouble(val);
            else
                return Integer.parseInt(val);
        }
        return null;
        // FIXME deal with ArrayValueContext
    }

    private GraphQLValueOrVariable valueOrVariable(GraphQLParser.ArgumentContext ctx) {
        if(ctx == null)
            return null;

        String key = ctx.NAME().getText();

        if(ctx.valueOrVariable().value() != null) {
            return new GraphQLValue(key, value(ctx.valueOrVariable().value()));
        }
        else {
            return new GraphQLVariable(key, ctx.valueOrVariable().variable().NAME().getText(), variableResolver);
        }
    }

    @Override
    public void exitFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx) {
        entityStack.pop();
    }

    @Override
    public void enterVariableDefinition(@NotNull GraphQLParser.VariableDefinitionContext ctx) {
        if(ctx.defaultValue() != null)
            variableResolver.defaultValue(ctx.variable().NAME().getText(), value(ctx.defaultValue().value()));
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

        GraphQLField field = new GraphQLField(fieldName, fieldAlias, queryAlias);
        addDirectives(field, ctx.directives());

        entityStack.peek().getFields().add(field);
    }

    @Override
    public void exitDocument(@NotNull GraphQLParser.DocumentContext ctx) {
        // there should be precisely one entity left on the stack, the document entity
        Preconditions.checkState(entityStack.size() == 1);
        recurseBuildMatch(queryEntity, null, traversal);
    }

    private GraphTraversal<?, ?> recurseBuildMatch(GraphQLRelationEntity entity, GraphQLRelationEntity parent, GraphTraversal<?, ?> at) {
        List<GraphQLField> fields = entity.flattenedFields();
        List<Traversal<?, ?>> matchClauses = new ArrayList<>(fields.size() + entity.getChildEntities().size() + 1);

        if(!entity.getWhereClauses().isEmpty())
            matchClauses.add(buildWhereClause(entity, parent));

        matchClauses.addAll(fields.stream()
            .filter(f -> !f.isSkipped())
            .map(field -> __.as(entity.getPrivateRelationAlias()).values(field.getFieldName()).as(field.getFieldAlias()))
            .collect(Collectors.toList()));

        matchClauses.addAll(entity.flattenedChildEntities().stream()
            .map(c -> recurseBuildMatch((GraphQLRelationEntity) c, entity, __.as(entity.getPrivateRelationAlias())))
            .collect(Collectors.toList()));

        GraphTraversal<?, ?> match = at.match(matchClauses.toArray(new Traversal<?, ?>[matchClauses.size()]));
        match.asAdmin().addStep(new GraphQLEntitySelectStep<>(match.asAdmin(), entity));

        if(!entity.getRelationAlias().equals(entity.getRelationName()))
            match = match.as(entity.getRelationAlias());

        return match;
    }

    private GraphTraversal<?, ?> buildWhereClause(GraphQLRelationEntity entity, GraphQLRelationEntity parent) {
        GraphTraversal<?, ?> whereClause;
        if(parent == queryEntity) {
            whereClause = buildHasChain(entity, __.as(parent.getRelationName()).barrier(1));
        }
        else {
            //noinspection unchecked
            whereClause = __.as(parent.getPrivateRelationAlias()).union(
                buildHasChain(entity, __.outE(entity.getRelationName())).inV(),
                buildHasChain(entity, __.out(entity.getRelationName()))
            ).dedup();
        }

        return whereClause.as(entity.getPrivateRelationAlias());
    }

    private <E> GraphTraversal<?, E> buildHasChain(GraphQLRelationEntity entity, GraphTraversal<?, E> whereClause) {
        for (GraphQLValueOrVariable valueOrVariable : entity.getWhereClauses()) {
            whereClause = whereClause.has(valueOrVariable.getKey(), valueOrVariable.getValue());
        }
        return whereClause;
    }

    private void addDirectives(GraphQLDirectiveAware directiveAware, GraphQLParser.DirectivesContext ctx) {
        if(ctx != null) {
            for (GraphQLParser.DirectiveContext directiveContext : ctx.directive()) {
                String directiveName = directiveContext.NAME().getText();
                if("skip".equals(directiveName)) {
                    directiveAware.getDirectives().add(new SkipDirective(valueOrVariable(directiveContext.argument()), false));
                }
                else if("include".equals(directiveName)) {
                    directiveAware.getDirectives().add(new SkipDirective(valueOrVariable(directiveContext.argument()), true));
                }
            }
        }
    }
}
