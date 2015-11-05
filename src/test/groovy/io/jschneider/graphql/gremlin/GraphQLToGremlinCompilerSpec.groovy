package io.jschneider.graphql.gremlin

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory
import spock.lang.Specification

class GraphQLToGremlinCompilerSpec extends Specification {
    static Graph g

    def setupSpec() {
        GraphTraversal.metaClass.entitySelect << { Map<String, String> fieldMappings,
                                                   Map<String, String> entityMappings = [:],
                                                   Map<String, String> queryAliases = [:] ->
            def entity = new GraphQLEntity('', '')
            entity.fields.addAll(fieldMappings.entrySet().collect { new GraphQLField(it.key, it.value, queryAliases[it.key]) })
            entity.childEntities.addAll(entityMappings.entrySet().collect { new GraphQLEntity(it.key, it.value) })
            delegate.asAdmin().addStep(new GraphQLEntitySelectStep<>(delegate.asAdmin(), entity))
        }

        g = TinkerFactory.createModern()
    }

    def 'match a vertex based on a property'() {
        when:
        def actual = GraphQLToGremlinCompiler.convertToGremlinTraversal(g, """
            {
              person(name: "marko") {
                name,
                age
              }
            }
        """)

        def expected = g.traversal().V()
            .match(
                __.as('document')
                    .match(
                        __.as('document').barrier(1).has('name', 'marko').as('__person0'),
                        __.as('__person0').values('name').as('name0'),
                        __.as('__person0').values('age').as('age0')
                    )
                    .entitySelect(name: 'name0', age: 'age0')
                    .as('person0')
            )
            .entitySelect([:], [person: 'person0'])

        then:
        expected == actual
        actual.next() == [person: [name: 'marko', age: 29]]
    }

    def 'match a relationship based on an edge property'() {
        when:
        def actual = GraphQLToGremlinCompiler.convertToGremlinTraversal(g, """
            {
              person(name: "marko") {
                name,
                created(name: "lop") {
                  lang
                }
              }
            }
        """)

        def expected = g.traversal().V()
            .match(
                __.as('document')
                    .match(
                        __.as('document').barrier(1).has('name', 'marko').as('__person0'),
                        __.as('__person0').values('name').as('name0'),
                        __.as('__person0')
                            .match(
                                __.as('__person0').out('created').has('name', 'lop').as('__created0'),
                                __.as('__created0').values('lang').as('lang0')
                            )
                            .entitySelect([lang: 'lang0'])
                            .as('created0')
                    )
                    .entitySelect([name: 'name0'], [created: 'created0'])
                    .as('person0')
            )
            .entitySelect([:], [person: 'person0'])

        then:
        expected == actual
        actual.next() == [person: [name: 'marko', created: [lang: 'java']]]
    }

    def 'nested properties with the same name'() {
        when:
        def actual = GraphQLToGremlinCompiler.convertToGremlinTraversal(g, """
            {
              person(name: "marko") {
                name,
                created(lang: "java") {
                  name
                }
              }
            }
        """)

        def expected = g.traversal().V()
            .match(
                __.as('document')
                    .match(
                        __.as('document').barrier(1).has('name', 'marko').as('__person0'),
                        __.as('__person0').values('name').as('name0'),
                        __.as('__person0')
                            .match(
                                __.as('__person0').out('created').has('lang', 'java').as('__created0'),
                                __.as('__created0').values('name').as('name1')
                            )
                            .entitySelect([name: 'name1'])
                            .as('created0')
                    )
                    .entitySelect([name: 'name0'], ['created': 'created0'])
                    .as('person0')
            )
            .entitySelect([:], [person: 'person0'])

        then:
        expected == actual
        actual.next() == [person: [name: 'marko', created: [name: 'lop']]]
    }

    def 'aliased fields'() {
        when:
        def actual = GraphQLToGremlinCompiler.convertToGremlinTraversal(g, """
            {
              person(name: "marko") {
                nameAlias: name,
                age
              }
            }
        """)

        then:
        actual.next() == [person: [nameAlias: 'marko', age: 29]]
    }

    def 'query with no fragments'() {
        when:
        def actual = GraphQLToGremlinCompiler.convertToGremlinTraversal(g, """
            query noFragments {
              person(name: "marko") {
                name,
                age
              }
            }
        """)

        then:
        actual.next() == [person: [name: 'marko', age: 29]]
    }

    def 'mutation is not supported'() {
        when:
        GraphQLToGremlinCompiler.convertToGremlinTraversal(g, 'mutation { }')

        then:
        thrown(UnsupportedOperationException)
    }
}
