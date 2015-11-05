package io.jschneider.graphql.gremlin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class GraphQLField {
    @NonNull String fieldName;
    @NonNull String fieldAlias;

    /**
     * An alias specified in the query itself, e.g.:
     * {
     *   user(id: 4) {
     *     name: fullName
     *   }
     * }
     *
     * name is the alias, fullName is the fieldName, fullName0 is the fieldAlias for the traversal
     */
    String queryAlias;
}
