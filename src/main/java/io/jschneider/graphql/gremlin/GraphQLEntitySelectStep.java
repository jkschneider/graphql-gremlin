package io.jschneider.graphql.gremlin;

import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.step.PathProcessor;
import org.apache.tinkerpop.gremlin.process.traversal.step.Scoping;
import org.apache.tinkerpop.gremlin.process.traversal.step.TraversalParent;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.MapStep;
import org.apache.tinkerpop.gremlin.process.traversal.traverser.TraverserRequirement;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalHelper;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalRing;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalUtil;
import org.apache.tinkerpop.gremlin.structure.util.StringFactory;

import java.util.*;
import java.util.stream.Collectors;

public final class GraphQLEntitySelectStep<S, E> extends MapStep<S, Map<String, E>> implements Scoping, TraversalParent, PathProcessor {

    @SuppressWarnings("unchecked")
    private TraversalRing<Object, E> traversalRing = new TraversalRing<>();
    private GraphQLEntity entity;

    public GraphQLEntitySelectStep(final Traversal.Admin traversal, GraphQLEntity entity) {
        super(traversal);
        this.entity = entity;
    }

    @Override
    protected Map<String, E> map(final Traverser.Admin<S> traverser) {
        final Map<String, E> bindings = new LinkedHashMap<>();

        for (final GraphQLField field : entity.getFields()) {
            final E end = this.getNullableScopeValue(null, field.getFieldAlias(), traverser);
            if (null != end)
                bindings.put(field.getQueryAlias() == null ? field.getFieldName() : field.getQueryAlias(),
                        TraversalUtil.apply(end, traversalRing.next()));
            else {
                traversalRing.reset();
                return null;
            }
        }

        for (final GraphQLEntity child : entity.getChildEntities()) {
            final E end = this.getNullableScopeValue(null, child.getRelationAlias(), traverser);
            if (null != end) {
                bindings.put(child.getRelationName(), TraversalUtil.apply(end, traversalRing.next()));
            }
            else {
                traversalRing.reset();
                return null;
            }
        }

        traversalRing.reset();
        return bindings;
    }

    @Override
    public void reset() {
        super.reset();
        traversalRing.reset();
    }

    @Override
    public String toString() {
        List<String> selectKeys = new ArrayList<>();

        selectKeys.addAll(this.entity.getFields().stream()
                .map(field -> field.getFieldAlias() + " as " + field.getFieldName() +
                    (field.getQueryAlias() != null ? " a.k.a. " + field.getQueryAlias() : ""))
                .collect(Collectors.toList()));

        selectKeys.addAll(entity.getChildEntities().stream()
                .map(child -> child.getRelationAlias() + " as " + child.getRelationName())
                .collect(Collectors.toList()));

        return StringFactory.stepString(this, null, selectKeys, traversalRing);
    }

    @Override
    public GraphQLEntitySelectStep<S, E> clone() {
        final GraphQLEntitySelectStep<S, E> clone = (GraphQLEntitySelectStep<S, E>) super.clone();
        clone.traversalRing = this.traversalRing.clone();
        clone.getLocalChildren().forEach(clone::integrateChild);
        return clone;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.traversalRing.hashCode() ^ this.getScopeKeys().hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Traversal.Admin<Object, E>> getLocalChildren() {
        return this.traversalRing.getTraversals();
    }

    @Override
    public void addLocalChild(final Traversal.Admin<?, ?> selectTraversal) {
        this.traversalRing.addTraversal(this.integrateChild(selectTraversal));
    }

    @Override
    public Set<TraverserRequirement> getRequirements() {
        Set<String> selectKeys = getScopeKeys();
        return this.getSelfAndChildRequirements(TraversalHelper.getLabels(TraversalHelper.getRootTraversal(this.traversal))
                    .stream()
                    .filter(selectKeys::contains)
                    .findAny().isPresent() ? TYPICAL_GLOBAL_REQUIREMENTS_ARRAY : TYPICAL_LOCAL_REQUIREMENTS_ARRAY);
    }

    @Override
    public Set<String> getScopeKeys() {
        Set<String> scopeKeys = new TreeSet<>();

        scopeKeys.addAll(this.entity.getFields().stream()
                .map(GraphQLField::getFieldName)
                .collect(Collectors.toList()));

        scopeKeys.addAll(this.entity.getChildEntities().stream()
                .map(GraphQLEntity::getRelationName)
                .collect(Collectors.toList()));

        return scopeKeys;
    }
}
