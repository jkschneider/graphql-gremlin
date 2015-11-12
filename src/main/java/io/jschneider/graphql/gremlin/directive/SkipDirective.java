package io.jschneider.graphql.gremlin.directive;

import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;

public class SkipDirective extends BaseGraphQLDirective {
    public SkipDirective(GraphQLValueOrVariable arg) {
        super(arg);
    }

    @Override
    public <T extends Traversal<?, ?>> T modifyFieldTraversal(T traversal) {
        return conditionallySkip(traversal);
    }

    @Override
    public <T extends Traversal<?, ?>> T modifyRelationTraversal(T traversal) {
        return conditionallySkip(traversal);
    }

    private <T extends Traversal<?, ?>> T conditionallySkip(T travesral) {
        boolean shouldSkip = arg == null || (arg.getValue() instanceof Boolean && (Boolean) arg.getValue()) || arg.getValue() != null;
        if(shouldSkip)
            return (T) __.identity();
        return travesral;
    }
}
