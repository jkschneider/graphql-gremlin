package io.jschneider.graphql.gremlin;

import spock.lang.Specification;

class GraphQLEntitySpec extends Specification {
    def 'flatten fields through nested fragments'() {
        when:
        def entity = relation('r')
        entity.fields.add(field('field1'))

        def f1 = fragment('f')
        entity.childEntities.add(f1)
        f1.fields.add(field('field2'))

        def f2 = fragment('f2')
        f1.childEntities.add(f2)
        f2.fields.add(field('field3'))

        then:
        entity.flattenedFields().collect { it.fieldName } ==
                ['field1', 'field2', 'field3']
    }

    def 'flatten relationships through nested fragment'() {
        when:
        def entity = relation('r')
        entity.childEntities.add(relation('r1'))

        def f1 = fragment('f')
        entity.childEntities.add(f1)
        f1.childEntities.add(relation('r2'))

        def f2 = fragment('f2')
        f1.childEntities.add(f2)
        f2.childEntities.add(relation('r3'))

        then:
        entity.flattenedChildEntities().collect { it.relationName } ==
                ['r1', 'r2', 'r3']
    }

    def relation(String name) { new GraphQLRelationEntity(name, name) }
    def field(String name) { new GraphQLField(name, name, name) }
    def fragment(String name) { new GraphQLFragmentEntity(name) }
}
