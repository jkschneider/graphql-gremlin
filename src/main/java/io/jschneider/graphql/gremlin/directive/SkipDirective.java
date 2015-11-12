package io.jschneider.graphql.gremlin.directive;

import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;

public class SkipDirective extends BaseGraphQLDirective {
    boolean inverted;

    public SkipDirective(GraphQLValueOrVariable arg, boolean inverted) {
        super(arg);
        this.inverted = inverted;
    }

    public boolean skip() {
        boolean skip = arg == null;
        if(!skip) {
            if (arg.getValue() instanceof Boolean)
                skip = (Boolean) arg.getValue();
            else
                skip = arg.getValue() != null;
        }

        return inverted != skip;
    }
}
