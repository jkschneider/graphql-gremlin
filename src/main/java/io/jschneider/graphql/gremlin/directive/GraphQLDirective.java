package io.jschneider.graphql.gremlin.directive;

import org.apache.tinkerpop.gremlin.process.traversal.Traversal;

public interface GraphQLDirective {
    <T extends Traversal<?, ?>> T modifyFieldTraversal(T traversal);
    <T extends Traversal<?, ?>> T modifyRelationTraversal(T traversal);
}
