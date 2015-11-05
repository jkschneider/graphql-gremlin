package io.jschneider.graphql.gremlin
import org.apache.tinkerpop.gremlin.process.traversal.Traverser
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory
import spock.lang.Specification

class GraphQLToGremlinCompilerSpec extends Specification {
    def 'match a vertex with a property'() {
        when:
        def g = TinkerFactory.createModern()

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
                    __.as('__document').barrier(1).has('name', 'marko').as('__person'),
                    __.as('__person').values('name').as('name'),
                    __.as('__person').values('age').as('age')
                )
                .select('name', 'age')
                .as('person')

        then:
        expected == actual
        actual.next() == [name: 'marko', age: 29]
    }

    def 'match a vertex with a single relation'() {
        when:
        def g = TinkerFactory.createModern()

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
                    __.as('__document').barrier(1).has('name', 'marko').as('__person'),
                    __.as('__person').values('name').as('name'),
                    __.as('__person').match(
                            __.as('__person').out('created').has('name', 'lop').as('__created'),
                            __.as('__created').values('lang').as('lang')
                        )
                        .select('lang')
                        .as('created')
                )
                .select('name', 'created')
                .as('person')

        then:
        expected == actual
        actual.next() == [name: 'marko', created: [lang: 'java']]
    }

    def 'complex match'() {
        when:
        def g = TinkerFactory.createModern()

        def result = g.traversal().V()
            .match(
                __.as('__document').barrier(1).has('person', 'name', 'marko').as('person'),
                __.as('person').match(
                    __.as('person').values('name').as('name'),
                    __.as('person').match(
                            __.as('person')
                                    .outE('created').has('weight', 0.4).inV()
                                    .as('__created'),
                            __.as('__created').values('name').as('name2'),
                            __.as('__created').values('lang').as('lang')
                        )
                        .select('name2', 'lang')
                        .map { Traverser<Map> t ->
                            def m = t.get()
                            m['name'] = m['name2']
                            m.remove('name2')
                            m
                        }
                        .as('created')
                )
            )
            .select('name', 'age', 'created')
            .as('document')
            .next()

        println(result)

        then:
        result.name == 'marko'
        result.age == 29
        result.created.name == 'lop'
        result.created.lang == 'java'
    }
}
