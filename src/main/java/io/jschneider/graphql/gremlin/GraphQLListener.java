package io.jschneider.graphql.gremlin;

import io.jschneider.graphql.gremlin.grammar.GraphQLBaseListener;
import io.jschneider.graphql.gremlin.grammar.GraphQLParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.*;

public class GraphQLListener extends GraphQLBaseListener {
    private GraphTraversal<Vertex, ?> traversal;

    private Stack<String> relationStack = new Stack<>();
    private Stack<Map<String, Traversal<?, ?>>> selectionSetClausesStack = new Stack<>();
    private Stack<GraphTraversal<?, ?>> whereClauseStack = new Stack<>();

    private Map<String, GraphQLEntity> matchSelectClausesByRelation = new HashMap<>();

    public GraphQLListener(GraphTraversal<Vertex, ?> traversal) {
        this.traversal = traversal;
        relationStack.push("document");
    }

    public GraphTraversal<Vertex, ?> result() {
        return traversal;
    }

    @Override
    public void enterFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx) {
        String fieldName = ctx.fieldName().getText();

        GraphTraversal<?, ?> whereClause = __.as("__" + relationStack.peek()).barrier(1);
        for (GraphQLParser.ArgumentContext arg : ctx.arguments().argument()) {
            whereClause = whereClause.has(arg.NAME().getText(), extractValue(arg.valueOrVariable().value()));
        }
        whereClause.as("__" + fieldName);
        whereClauseStack.push(whereClause);

        relationStack.push(fieldName);
    }

    private Object extractValue(GraphQLParser.ValueContext valueContext) {
        String val = valueContext.getText().replaceAll("\"", "");
        if(valueContext instanceof GraphQLParser.StringValueContext)
            return val;
        if(valueContext instanceof GraphQLParser.BooleanValueContext)
            return Boolean.parseBoolean(val);
        if(valueContext instanceof GraphQLParser.NumberValueContext) {
            if(val.contains("."))
                return Double.parseDouble(val);
            else
                return Integer.parseInt(val);
        }
        return null;
        // FIXME deal with ArrayValueContext
    }

    @Override
    public void exitFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx) {
        Map<String, Traversal<?, ?>> selectionSetClauses = selectionSetClausesStack.pop();
        List<Traversal<?, ?>> matchClauses = new ArrayList<>(selectionSetClauses.size() + 1);

        matchClauses.add(whereClauseStack.pop());
        matchClauses.addAll(selectionSetClauses.values());

        String myRelation = relationStack.pop();
        matchSelectClausesByRelation.put(myRelation, new GraphQLEntity(matchClauses, new ArrayList<>(selectionSetClauses.keySet()),
                myRelation));

        String parentRelation = relationStack.peek();
        matchSelectClausesByRelation.get(parentRelation).getMatchClauses()
                .add(__.as("__" + parentRelation).match(matchClauses));
    }

    @Override
    public void enterSelectionSet(@NotNull GraphQLParser.SelectionSetContext ctx) {
        selectionSetClausesStack.push(new LinkedHashMap<>());
    }

    @Override
    public void enterFieldValue(@NotNull GraphQLParser.FieldValueContext ctx) {
        Map<String, Traversal<?, ?>> selectionSetClauses = selectionSetClausesStack.peek();
        String fieldName = ctx.fieldName().getText();
        selectionSetClauses.put(fieldName, __.as("__" + relationStack.peek()).values(fieldName).as(fieldName));
    }

    @Override
    public void exitDocument(@NotNull GraphQLParser.DocumentContext ctx) {
        for (GraphQLEntity matchSelectClause : matchSelectClausesByRelation) {
            List<Traversal<?, ?>> matchClauses = matchSelectClause.getMatchClauses();
            traversal = traversal.match(matchClauses.toArray(new Traversal[matchClauses.size()]));

            List<String> selectKeys = matchSelectClause.getSelects();
            switch(selectKeys.size()) {
                case 0:
                    throw new IllegalStateException("Is this possible?");
                case 1:
                    traversal = traversal.select(selectKeys.get(0));
                    break;
                case 2:
                    traversal = traversal.select(selectKeys.get(0), selectKeys.get(1));
                    break;
                default:
                    List<String> moreKeys = selectKeys.subList(2, selectKeys.size());
                    traversal = traversal.select(selectKeys.get(0), selectKeys.get(1),
                            moreKeys.toArray(new String[moreKeys.size()]));
            }

            traversal = traversal.as(matchSelectClause.getRelation());
        }
    }

//    g.traversal().V()
//        .as('__document') // special case where there is no match predicate
//        .match(
//            __.as('__document').barrier(1).has('person', 'name', 'marko').as('__person'),
//            __.as('__person').match(
//                __.as('__person').values('name').as('name'),
//                __.as('__person').values('age').as('age'),
//                __.as('__person').match(
//                        __.as('__person')
//                                .outE('created').has('weight', 0.4).inV()
//                                .as('__created'),
//                        __.as('__created').values('name').as('name2'),
//                        __.as('__created').values('lang').as('lang')
//                    )
//                    .select('name2', 'lang')
//                    .map { Traverser<Map> t ->
//                        def m = t.get()
//                        m['name'] = m['name2']
//                        m.remove('name2')
//                        m
//                    }
//                    .as('created')
//            )
//        )
//        .select('name', 'age', 'created')
//        .as('person')
//        .next()
}
