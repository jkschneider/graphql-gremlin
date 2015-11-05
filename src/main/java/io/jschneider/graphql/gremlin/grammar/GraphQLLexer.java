// Generated from /Users/joschneider/Projects/github/jkschneider/graphql-gremlin/src/main/antlr4/GraphQL.g4 by ANTLR 4.2.2
package io.jschneider.graphql.gremlin.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GraphQLLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, NAME=18, STRING=19, BOOLEAN=20, NUMBER=21, WS=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'mutation'", "'...'", "'fragment'", "':'", "'['", "'{'", "'on'", "']'", 
		"'='", "'}'", "'@'", "'!'", "'$'", "'('", "')'", "','", "'query'", "NAME", 
		"STRING", "BOOLEAN", "NUMBER", "WS"
	};
	public static final String[] ruleNames = {
		"T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", 
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"NAME", "STRING", "BOOLEAN", "ESC", "UNICODE", "HEX", "NUMBER", "INT", 
		"EXP", "WS"
	};


	public GraphQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GraphQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u00c9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3"+
		"\23\7\23s\n\23\f\23\16\23v\13\23\3\24\3\24\3\24\7\24{\n\24\f\24\16\24"+
		"~\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u008b"+
		"\n\25\3\26\3\26\3\26\5\26\u0090\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\31\5\31\u009b\n\31\3\31\3\31\3\31\6\31\u00a0\n\31\r\31\16\31\u00a1"+
		"\3\31\5\31\u00a5\n\31\3\31\5\31\u00a8\n\31\3\31\3\31\3\31\3\31\5\31\u00ae"+
		"\n\31\3\31\5\31\u00b1\n\31\3\32\3\32\3\32\7\32\u00b6\n\32\f\32\16\32\u00b9"+
		"\13\32\5\32\u00bb\n\32\3\33\3\33\5\33\u00bf\n\33\3\33\3\33\3\34\6\34\u00c4"+
		"\n\34\r\34\16\34\u00c5\3\34\3\34\2\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\2"+
		"-\2/\2\61\27\63\2\65\2\67\30\3\2\f\5\2C\\aac|\6\2\62;C\\aac|\4\2$$^^\n"+
		"\2$$\61\61^^ddhhppttvv\5\2\62;CHch\3\2\62;\3\2\63;\4\2GGgg\4\2--//\5\2"+
		"\13\f\17\17\"\"\u00d3\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2\61\3\2"+
		"\2\2\2\67\3\2\2\2\39\3\2\2\2\5B\3\2\2\2\7F\3\2\2\2\tO\3\2\2\2\13Q\3\2"+
		"\2\2\rS\3\2\2\2\17U\3\2\2\2\21X\3\2\2\2\23Z\3\2\2\2\25\\\3\2\2\2\27^\3"+
		"\2\2\2\31`\3\2\2\2\33b\3\2\2\2\35d\3\2\2\2\37f\3\2\2\2!h\3\2\2\2#j\3\2"+
		"\2\2%p\3\2\2\2\'w\3\2\2\2)\u008a\3\2\2\2+\u008c\3\2\2\2-\u0091\3\2\2\2"+
		"/\u0097\3\2\2\2\61\u00b0\3\2\2\2\63\u00ba\3\2\2\2\65\u00bc\3\2\2\2\67"+
		"\u00c3\3\2\2\29:\7o\2\2:;\7w\2\2;<\7v\2\2<=\7c\2\2=>\7v\2\2>?\7k\2\2?"+
		"@\7q\2\2@A\7p\2\2A\4\3\2\2\2BC\7\60\2\2CD\7\60\2\2DE\7\60\2\2E\6\3\2\2"+
		"\2FG\7h\2\2GH\7t\2\2HI\7c\2\2IJ\7i\2\2JK\7o\2\2KL\7g\2\2LM\7p\2\2MN\7"+
		"v\2\2N\b\3\2\2\2OP\7<\2\2P\n\3\2\2\2QR\7]\2\2R\f\3\2\2\2ST\7}\2\2T\16"+
		"\3\2\2\2UV\7q\2\2VW\7p\2\2W\20\3\2\2\2XY\7_\2\2Y\22\3\2\2\2Z[\7?\2\2["+
		"\24\3\2\2\2\\]\7\177\2\2]\26\3\2\2\2^_\7B\2\2_\30\3\2\2\2`a\7#\2\2a\32"+
		"\3\2\2\2bc\7&\2\2c\34\3\2\2\2de\7*\2\2e\36\3\2\2\2fg\7+\2\2g \3\2\2\2"+
		"hi\7.\2\2i\"\3\2\2\2jk\7s\2\2kl\7w\2\2lm\7g\2\2mn\7t\2\2no\7{\2\2o$\3"+
		"\2\2\2pt\t\2\2\2qs\t\3\2\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u&\3"+
		"\2\2\2vt\3\2\2\2w|\7$\2\2x{\5+\26\2y{\n\4\2\2zx\3\2\2\2zy\3\2\2\2{~\3"+
		"\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0080\7$\2\2\u0080"+
		"(\3\2\2\2\u0081\u0082\7v\2\2\u0082\u0083\7t\2\2\u0083\u0084\7w\2\2\u0084"+
		"\u008b\7g\2\2\u0085\u0086\7h\2\2\u0086\u0087\7c\2\2\u0087\u0088\7n\2\2"+
		"\u0088\u0089\7u\2\2\u0089\u008b\7g\2\2\u008a\u0081\3\2\2\2\u008a\u0085"+
		"\3\2\2\2\u008b*\3\2\2\2\u008c\u008f\7^\2\2\u008d\u0090\t\5\2\2\u008e\u0090"+
		"\5-\27\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090,\3\2\2\2\u0091"+
		"\u0092\7w\2\2\u0092\u0093\5/\30\2\u0093\u0094\5/\30\2\u0094\u0095\5/\30"+
		"\2\u0095\u0096\5/\30\2\u0096.\3\2\2\2\u0097\u0098\t\6\2\2\u0098\60\3\2"+
		"\2\2\u0099\u009b\7/\2\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\5\63\32\2\u009d\u009f\7\60\2\2\u009e\u00a0"+
		"\t\7\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a5\5\65\33\2\u00a4\u00a3\3"+
		"\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00b1\3\2\2\2\u00a6\u00a8\7/\2\2\u00a7"+
		"\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\5\63"+
		"\32\2\u00aa\u00ab\5\65\33\2\u00ab\u00b1\3\2\2\2\u00ac\u00ae\7/\2\2\u00ad"+
		"\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\5\63"+
		"\32\2\u00b0\u009a\3\2\2\2\u00b0\u00a7\3\2\2\2\u00b0\u00ad\3\2\2\2\u00b1"+
		"\62\3\2\2\2\u00b2\u00bb\7\62\2\2\u00b3\u00b7\t\b\2\2\u00b4\u00b6\t\7\2"+
		"\2\u00b5\u00b4\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8"+
		"\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00b2\3\2\2\2\u00ba"+
		"\u00b3\3\2\2\2\u00bb\64\3\2\2\2\u00bc\u00be\t\t\2\2\u00bd\u00bf\t\n\2"+
		"\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1"+
		"\5\63\32\2\u00c1\66\3\2\2\2\u00c2\u00c4\t\13\2\2\u00c3\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2"+
		"\2\2\u00c7\u00c8\b\34\2\2\u00c88\3\2\2\2\22\2tz|\u008a\u008f\u009a\u00a1"+
		"\u00a4\u00a7\u00ad\u00b0\u00b7\u00ba\u00be\u00c5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}