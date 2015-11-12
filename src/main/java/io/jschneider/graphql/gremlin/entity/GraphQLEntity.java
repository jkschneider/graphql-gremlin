package io.jschneider.graphql.gremlin.entity;

import io.jschneider.graphql.gremlin.GraphQLField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface GraphQLEntity {
    List<GraphQLField> getFields();
    List<GraphQLEntity> getChildEntities();

    /**
     * @return all fields, including those defined directly and in nested fragments
     */
    default List<GraphQLField> flattenedFields() {
        List<GraphQLField> fieldsIncludingFragments = new ArrayList<>(getFields());

        fieldsIncludingFragments.addAll(getChildEntities().stream()
                .filter(c -> c instanceof GraphQLFragmentEntity)
                .flatMap(c -> c.flattenedFields().stream())
                .collect(Collectors.toList()));

        return fieldsIncludingFragments;
    }

    /**
     * @return all children, including those defined directly and in nested fragments
     */
    default List<GraphQLRelationEntity> flattenedChildEntities() {
        List<GraphQLRelationEntity> children = getChildEntities().stream()
                .filter(c -> c instanceof GraphQLRelationEntity)
                .map(c -> (GraphQLRelationEntity) c)
                .collect(Collectors.toList());

        children.addAll(getChildEntities().stream()
                .filter(c -> c instanceof GraphQLFragmentEntity)
                .flatMap(c -> c.flattenedChildEntities().stream())
                .collect(Collectors.toList()));

        return children;
    }
}
