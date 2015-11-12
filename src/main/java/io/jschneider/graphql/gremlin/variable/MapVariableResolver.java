package io.jschneider.graphql.gremlin.variable;

import java.util.Map;

public class MapVariableResolver implements VariableResolver {
    private Map<String, Object> variableRegistry;

    public MapVariableResolver(Map<String, Object> variableRegistry) {
        this.variableRegistry = variableRegistry;
    }

    @Override
    public Object value(String variableName) {
        return variableRegistry.get(variableName);
    }

    @Override
    public void defaultValue(String variableName, Object value) {
        variableRegistry.put(variableName, value);
    }
}
