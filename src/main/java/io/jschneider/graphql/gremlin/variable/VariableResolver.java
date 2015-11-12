package io.jschneider.graphql.gremlin.variable;

public interface VariableResolver {
    Object value(String variableName);
    void defaultValue(String variableName, Object value);
}
