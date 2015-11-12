package io.jschneider.graphql.gremlin.variable;

public interface GraphQLValueOrVariable {
    Object getValue();
    String getKey();
}
