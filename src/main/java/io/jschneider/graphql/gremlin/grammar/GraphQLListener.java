// Generated from /Users/joschneider/Projects/github/jkschneider/graphql-gremlin/src/main/antlr4/GraphQL.g4 by ANTLR 4.2.2
package io.jschneider.graphql.gremlin.grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GraphQLParser}.
 */
public interface GraphQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GraphQLParser#fragmentDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFragmentDefinition(@NotNull GraphQLParser.FragmentDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#fragmentDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFragmentDefinition(@NotNull GraphQLParser.FragmentDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(@NotNull GraphQLParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(@NotNull GraphQLParser.ArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void enterFieldName(@NotNull GraphQLParser.FieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void exitFieldName(@NotNull GraphQLParser.FieldNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#fragmentSpread}.
	 * @param ctx the parse tree
	 */
	void enterFragmentSpread(@NotNull GraphQLParser.FragmentSpreadContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#fragmentSpread}.
	 * @param ctx the parse tree
	 */
	void exitFragmentSpread(@NotNull GraphQLParser.FragmentSpreadContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#inlineFragment}.
	 * @param ctx the parse tree
	 */
	void enterInlineFragment(@NotNull GraphQLParser.InlineFragmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#inlineFragment}.
	 * @param ctx the parse tree
	 */
	void exitInlineFragment(@NotNull GraphQLParser.InlineFragmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(@NotNull GraphQLParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(@NotNull GraphQLParser.DefaultValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(@NotNull GraphQLParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(@NotNull GraphQLParser.DocumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#fieldRelation}.
	 * @param ctx the parse tree
	 */
	void enterFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#fieldRelation}.
	 * @param ctx the parse tree
	 */
	void exitFieldRelation(@NotNull GraphQLParser.FieldRelationContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(@NotNull GraphQLParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(@NotNull GraphQLParser.TypeNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull GraphQLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull GraphQLParser.TypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#fieldValue}.
	 * @param ctx the parse tree
	 */
	void enterFieldValue(@NotNull GraphQLParser.FieldValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#fieldValue}.
	 * @param ctx the parse tree
	 */
	void exitFieldValue(@NotNull GraphQLParser.FieldValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#listType}.
	 * @param ctx the parse tree
	 */
	void enterListType(@NotNull GraphQLParser.ListTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#listType}.
	 * @param ctx the parse tree
	 */
	void exitListType(@NotNull GraphQLParser.ListTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#selectionSet}.
	 * @param ctx the parse tree
	 */
	void enterSelectionSet(@NotNull GraphQLParser.SelectionSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#selectionSet}.
	 * @param ctx the parse tree
	 */
	void exitSelectionSet(@NotNull GraphQLParser.SelectionSetContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(@NotNull GraphQLParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(@NotNull GraphQLParser.StringValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#variableDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinitions(@NotNull GraphQLParser.VariableDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#variableDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinitions(@NotNull GraphQLParser.VariableDefinitionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#fragmentName}.
	 * @param ctx the parse tree
	 */
	void enterFragmentName(@NotNull GraphQLParser.FragmentNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#fragmentName}.
	 * @param ctx the parse tree
	 */
	void exitFragmentName(@NotNull GraphQLParser.FragmentNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(@NotNull GraphQLParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(@NotNull GraphQLParser.ArrayContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(@NotNull GraphQLParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(@NotNull GraphQLParser.AliasContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(@NotNull GraphQLParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(@NotNull GraphQLParser.DefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#numberValue}.
	 * @param ctx the parse tree
	 */
	void enterNumberValue(@NotNull GraphQLParser.NumberValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#numberValue}.
	 * @param ctx the parse tree
	 */
	void exitNumberValue(@NotNull GraphQLParser.NumberValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#directives}.
	 * @param ctx the parse tree
	 */
	void enterDirectives(@NotNull GraphQLParser.DirectivesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#directives}.
	 * @param ctx the parse tree
	 */
	void exitDirectives(@NotNull GraphQLParser.DirectivesContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#variableDefinition}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinition(@NotNull GraphQLParser.VariableDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#variableDefinition}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinition(@NotNull GraphQLParser.VariableDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#operationDefinition}.
	 * @param ctx the parse tree
	 */
	void enterOperationDefinition(@NotNull GraphQLParser.OperationDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#operationDefinition}.
	 * @param ctx the parse tree
	 */
	void exitOperationDefinition(@NotNull GraphQLParser.OperationDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#arrayValue}.
	 * @param ctx the parse tree
	 */
	void enterArrayValue(@NotNull GraphQLParser.ArrayValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#arrayValue}.
	 * @param ctx the parse tree
	 */
	void exitArrayValue(@NotNull GraphQLParser.ArrayValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(@NotNull GraphQLParser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(@NotNull GraphQLParser.DirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#valueOrVariable}.
	 * @param ctx the parse tree
	 */
	void enterValueOrVariable(@NotNull GraphQLParser.ValueOrVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#valueOrVariable}.
	 * @param ctx the parse tree
	 */
	void exitValueOrVariable(@NotNull GraphQLParser.ValueOrVariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#nonNullType}.
	 * @param ctx the parse tree
	 */
	void enterNonNullType(@NotNull GraphQLParser.NonNullTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#nonNullType}.
	 * @param ctx the parse tree
	 */
	void exitNonNullType(@NotNull GraphQLParser.NonNullTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(@NotNull GraphQLParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(@NotNull GraphQLParser.SelectionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#typeCondition}.
	 * @param ctx the parse tree
	 */
	void enterTypeCondition(@NotNull GraphQLParser.TypeConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#typeCondition}.
	 * @param ctx the parse tree
	 */
	void exitTypeCondition(@NotNull GraphQLParser.TypeConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull GraphQLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull GraphQLParser.VariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#operationType}.
	 * @param ctx the parse tree
	 */
	void enterOperationType(@NotNull GraphQLParser.OperationTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#operationType}.
	 * @param ctx the parse tree
	 */
	void exitOperationType(@NotNull GraphQLParser.OperationTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull GraphQLParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull GraphQLParser.ArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GraphQLParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(@NotNull GraphQLParser.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(@NotNull GraphQLParser.BooleanValueContext ctx);
}