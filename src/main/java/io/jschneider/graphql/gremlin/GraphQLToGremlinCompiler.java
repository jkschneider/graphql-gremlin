package io.jschneider.graphql.gremlin;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import io.jschneider.graphql.gremlin.grammar.GraphQLLexer;
import io.jschneider.graphql.gremlin.grammar.GraphQLParser;

public class GraphQLToGremlinCompiler {
    private GraphTraversal<Vertex, ?> traversal;

    private GraphQLToGremlinCompiler(final GraphTraversal<Vertex, ?> traversal) {
        this.traversal = traversal;
    }

    public static GraphTraversal<Vertex, ?> convertToGremlinTraversal(final Graph g, final String query) {
        return new GraphQLToGremlinCompiler(g.traversal().V()).convertToGremlinTraversal(query);
    }

    public static GraphTraversal<Vertex, ?> convertToGremlinTraversal(final GraphTraversalSource gts, final String query) {
        return new GraphQLToGremlinCompiler(gts.V()).convertToGremlinTraversal(query);
    }

    GraphTraversal<Vertex, ?> convertToGremlinTraversal(String graphql) {
        GraphQLLexer lexer = new GraphQLLexer(new ANTLRInputStream(graphql));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GraphQLParser parser = new GraphQLParser(tokens);
        ParseTree tree = parser.document();

        GraphQLCompilerListener listener = new GraphQLCompilerListener(traversal);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        return listener.result();
    }
}
