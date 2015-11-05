package io.jschneider.graphql.gremlin;

import lombok.Data;
import lombok.NonNull;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphQLEntity {
    @NonNull String relationName;

    /**
     * Because every as(R) step in gremlin needs a unique R and the same relation name can occur in multiple nested
     * levels of the entity hierarchy
     */
    @NonNull String relationAlias;

    String getPrivateRelationAlias() {
        return "__" + relationAlias;
    }

    List<GraphQLField> fields = new ArrayList<>();
    List<GraphQLEntity> childEntities = new ArrayList<>();

    /**
     * May be null, as in the case of the root document, which is a faux entity anyway
     */
    Traversal<?, ?> whereClause;
}
