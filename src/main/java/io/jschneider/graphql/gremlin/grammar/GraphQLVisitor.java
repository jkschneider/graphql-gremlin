// Generated from /Users/joschneider/Projects/github/jkschneider/graphql-gremlin/src/main/antlr4/GraphQL.g4 by ANTLR 4.2.2
package io.jschneider.graphql.gremlin.grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GraphQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GraphQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GraphQLParser#fragmentDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFragmentDefinition(@NotNull GraphQLParser.FragmentDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(@NotNull GraphQLParser.ArgumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(@NotNull GraphQLParser.FieldNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#fragmentSpread}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFragmentSpread(@NotNull GraphQLParser.FragmentSpreadContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#inlineFragment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineFragment(@NotNull GraphQLParser.InlineFragmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(@NotNull GraphQLParser.DefaultValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(@NotNull GraphQLParser.DocumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#fieldRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(@NotNull GraphQLParser.TypeNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull GraphQLParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#fieldValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldValue(@NotNull GraphQLParser.FieldValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#listType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListType(@NotNull GraphQLParser.ListTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#selectionSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionSet(@NotNull GraphQLParser.SelectionSetContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#stringValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(@NotNull GraphQLParser.StringValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#variableDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinitions(@NotNull GraphQLParser.VariableDefinitionsContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#fragmentName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFragmentName(@NotNull GraphQLParser.FragmentNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(@NotNull GraphQLParser.ArrayContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(@NotNull GraphQLParser.AliasContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(@NotNull GraphQLParser.DefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#numberValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberValue(@NotNull GraphQLParser.NumberValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#directives}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectives(@NotNull GraphQLParser.DirectivesContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#variableDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinition(@NotNull GraphQLParser.VariableDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#operationDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationDefinition(@NotNull GraphQLParser.OperationDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#arrayValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayValue(@NotNull GraphQLParser.ArrayValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirective(@NotNull GraphQLParser.DirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#valueOrVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueOrVariable(@NotNull GraphQLParser.ValueOrVariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#nonNullType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonNullType(@NotNull GraphQLParser.NonNullTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#selection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(@NotNull GraphQLParser.SelectionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#typeCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeCondition(@NotNull GraphQLParser.TypeConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(@NotNull GraphQLParser.VariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#operationType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationType(@NotNull GraphQLParser.OperationTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(@NotNull GraphQLParser.ArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link GraphQLParser#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(@NotNull GraphQLParser.BooleanValueContext ctx);
}