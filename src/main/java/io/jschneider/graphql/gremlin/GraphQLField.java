package io.jschneider.graphql.gremlin;

import lombok.Data;
import lombok.NonNull;

@Data
public class GraphQLField {
    @NonNull String fieldName;
    @NonNull String fieldAlias;
}
