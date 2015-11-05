package io.jschneider.graphql.gremlin;

import lombok.Data;
import lombok.NonNull;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphQLEntity {
    @NonNull String relationName;

    List<String> fields = new ArrayList<>();
    List<GraphQLEntity> childEntities = new ArrayList<>();
    List<Traversal<?, ?>> matchClauses = new ArrayList<>();
    List<String> selects = new ArrayList<>();
}
