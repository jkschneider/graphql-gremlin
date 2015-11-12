package io.jschneider.graphql.gremlin.entity;

import io.jschneider.graphql.gremlin.GraphQLField;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphQLFragmentEntity implements GraphQLEntity, Cloneable {
    @NonNull String fragmentName;
    String type;
    List<GraphQLField> fields = new ArrayList<>();
    List<GraphQLEntity> childEntities = new ArrayList<>();

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    protected Object clone() throws CloneNotSupportedException {
        GraphQLFragmentEntity clone = new GraphQLFragmentEntity(fragmentName);
        clone.setType(type);
        clone.setFields(fields);
        clone.setChildEntities(childEntities);
        return clone;
    }
}
