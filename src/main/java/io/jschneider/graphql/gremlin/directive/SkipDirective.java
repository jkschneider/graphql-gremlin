package io.jschneider.graphql.gremlin.directive;

import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;

public class SkipDirective extends BaseGraphQLDirective {
    public SkipDirective(GraphQLValueOrVariable arg) {
        super(arg);
    }

    public boolean skip() {
        return arg == null || (arg.getValue() instanceof Boolean && (Boolean) arg.getValue()) || arg.getValue() != null;
    }
}
