package io.jschneider.graphql.gremlin.variable;

import lombok.Data;
import lombok.NonNull;

@Data
public class GraphQLValue implements GraphQLValueOrVariable {
    @NonNull String key;
    @NonNull Object value;
}
