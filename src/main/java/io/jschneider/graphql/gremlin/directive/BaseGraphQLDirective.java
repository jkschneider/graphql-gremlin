package io.jschneider.graphql.gremlin.directive;

import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;

public abstract class BaseGraphQLDirective implements GraphQLDirective {
    GraphQLValueOrVariable arg;

    public BaseGraphQLDirective(GraphQLValueOrVariable arg) {
        this.arg = arg;
    }

    @Override
    public <T extends Traversal<?, ?>> T modifyFieldTraversal(T traversal) {
        return traversal;
    }

    @Override
    public <T extends Traversal<?, ?>> T modifyRelationTraversal(T traversal) {
        return traversal;
    }
}
