package io.jschneider.graphql.gremlin.directive;

import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;

public abstract class BaseGraphQLDirective implements GraphQLDirective {
    GraphQLValueOrVariable arg;

    public BaseGraphQLDirective(GraphQLValueOrVariable arg) {
        this.arg = arg;
    }
}
