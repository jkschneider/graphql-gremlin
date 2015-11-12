package io.jschneider.graphql.gremlin.variable;

import lombok.Data;
import lombok.NonNull;

@Data
public class GraphQLVariable implements GraphQLValueOrVariable {
    @NonNull String key;
    @NonNull String name;
    @NonNull VariableResolver resolver;

    @Override
    public Object getValue() {
        return resolver.value(name);
    }
}
