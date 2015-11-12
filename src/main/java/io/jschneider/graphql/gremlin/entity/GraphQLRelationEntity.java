package io.jschneider.graphql.gremlin.entity;

import io.jschneider.graphql.gremlin.field.GraphQLField;
import io.jschneider.graphql.gremlin.directive.GraphQLDirective;
import io.jschneider.graphql.gremlin.variable.GraphQLValueOrVariable;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

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
    List<GraphQLValueOrVariable> whereClauses = new ArrayList<>();

    List<GraphQLDirective> directives = new ArrayList<GraphQLDirective>();
}
