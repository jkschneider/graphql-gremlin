# graphql-gremlin

[![Build Status](https://travis-ci.org/jkschneider/graphql-gremlin.svg?branch=master)](https://travis-ci.org/jkschneider/graphql-gremlin)

## Purpose

This was a one-day Netflix hackday project in November 2015 to see if we could map the GraphQL spec to Apache Tinkerpop.

There are some conceptual ambiguities, e.g. is a property-based relationship defined in GraphQL related to an edge property or vertex property in Tinkerpop?

Otherwise, much of the read portion of the spec was implemented. Graph mutation has not yet been attempted.

The [tests](https://github.com/jkschneider/graphql-gremlin/blob/master/src/test/groovy/io/jschneider/graphql/gremlin/GraphQLToGremlinCompilerSpec.groovy) best demonstrate how to use.
