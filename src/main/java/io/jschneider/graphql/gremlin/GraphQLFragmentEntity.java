package io.jschneider.graphql.gremlin;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphQLFragmentEntity implements GraphQLEntity {
    @NonNull String fragmentName;
    String type;
    List<GraphQLField> fields = new ArrayList<>();
    List<GraphQLEntity> childEntities = new ArrayList<>();
}
