package io.jschneider.graphql.gremlin.directive;

import java.util.List;

public interface GraphQLDirectiveAware {
    List<GraphQLDirective> getDirectives();

    default boolean isSkipped() {
        return getDirectives().stream()
                .filter(d -> d instanceof SkipDirective)
                .findAny()
                .map(d -> ((SkipDirective) d).skip())
                .orElse(false);
    }
}
