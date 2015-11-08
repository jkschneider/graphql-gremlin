package io.jschneider.graphql.gremlin;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GraphQLRelationEntity implements GraphQLEntity {
    @NonNull String relationName;

    /**
     * Because every as(R) step in gremlin needs a unique R and the same relation name can occur in multiple nested
     * levels of the entity hierarchy
     */
    @NonNull String relationAlias;

    public String getPrivateRelationAlias() {
        if(relationName.equals(relationAlias))
            return relationName;
        return "__" + relationAlias;
    }

    List<GraphQLField> fields = new ArrayList<>();
    List<GraphQLEntity> childEntities = new ArrayList<>();

    /**
     * May be empty, as in the case of the root document, which is a faux entity anyway
     */
    Map<String, Object> whereClauses = new HashMap<>();
}
