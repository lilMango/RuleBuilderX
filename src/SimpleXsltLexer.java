// Generated from SimpleXslt.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleXsltLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, RESERVED_WORDS=4, T_OR=5, T_AND=6, StringLiteral=7, 
		ID=8, NUMBER=9, DIGIT=10, T_LBRACE=11, T_RBRACE=12, T_LPAREN=13, T_RPAREN=14, 
		T_LSQUIGBRACE=15, T_RSQUIGBRACE=16, T_PLUS=17, T_MINUS=18, T_STAR=19, 
		T_SLASH=20, T_EQ=21, T_NEQ=22, T_LTE=23, T_LT=24, T_GTE=25, T_GT=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'and'", "'or'", "'var'", "RESERVED_WORDS", "T_OR", "T_AND", "StringLiteral", 
		"ID", "NUMBER", "DIGIT", "'['", "']'", "'('", "')'", "'{'", "'}'", "'+'", 
		"'-'", "'*'", "'/'", "'='", "'!='", "'<='", "'<'", "'>='", "'>'", "WS"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "RESERVED_WORDS", "T_OR", "T_AND", "StringLiteral", 
		"ID", "NAMECHAR", "LETTER", "NUMBER", "DIGIT", "DECIMAL", "T_LBRACE", 
		"T_RBRACE", "T_LPAREN", "T_RPAREN", "T_LSQUIGBRACE", "T_RSQUIGBRACE", 
		"T_PLUS", "T_MINUS", "T_STAR", "T_SLASH", "T_EQ", "T_NEQ", "T_LTE", "T_LT", 
		"T_GTE", "T_GT", "WS"
	};


	public SimpleXsltLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimpleXslt.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 29: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\35\u00d7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5W\n\5\3\6\3\6\3\6\3\6\5\6]\n\6\3\7\3\7\3\7\3\7\3"+
		"\7\5\7d\n\7\3\b\3\b\7\bh\n\b\f\b\16\bk\13\b\3\b\3\b\3\b\7\bp\n\b\f\b\16"+
		"\bs\13\b\3\b\5\bv\n\b\3\t\3\t\3\t\3\t\7\t|\n\t\f\t\16\t\177\13\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\5\n\u0087\n\n\3\13\3\13\3\f\7\f\u008c\n\f\f\f\16\f"+
		"\u008f\13\f\3\f\3\f\6\f\u0093\n\f\r\f\16\f\u0094\3\f\6\f\u0098\n\f\r\f"+
		"\16\f\u0099\3\f\3\f\7\f\u009e\n\f\f\f\16\f\u00a1\13\f\3\f\6\f\u00a4\n"+
		"\f\r\f\16\f\u00a5\5\f\u00a8\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\35\3\36\3\36\3\37\6\37\u00d2\n\37\r\37\16\37\u00d3\3\37\3\37\2 \3\3"+
		"\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\2\1\25\2\1\27\13\1"+
		"\31\f\1\33\2\1\35\r\1\37\16\1!\17\1#\20\1%\21\1\'\22\1)\23\1+\24\1-\25"+
		"\1/\26\1\61\27\1\63\30\1\65\31\1\67\32\19\33\1;\34\1=\35\2\3\2\7\4\2$"+
		"$^^\4\2^^aa\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u00e7\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5C\3\2\2\2\7F\3\2\2\2\tV\3"+
		"\2\2\2\13\\\3\2\2\2\rc\3\2\2\2\17u\3\2\2\2\21w\3\2\2\2\23\u0086\3\2\2"+
		"\2\25\u0088\3\2\2\2\27\u00a7\3\2\2\2\31\u00a9\3\2\2\2\33\u00ab\3\2\2\2"+
		"\35\u00ad\3\2\2\2\37\u00af\3\2\2\2!\u00b1\3\2\2\2#\u00b3\3\2\2\2%\u00b5"+
		"\3\2\2\2\'\u00b7\3\2\2\2)\u00b9\3\2\2\2+\u00bb\3\2\2\2-\u00bd\3\2\2\2"+
		"/\u00bf\3\2\2\2\61\u00c1\3\2\2\2\63\u00c3\3\2\2\2\65\u00c6\3\2\2\2\67"+
		"\u00c9\3\2\2\29\u00cb\3\2\2\2;\u00ce\3\2\2\2=\u00d1\3\2\2\2?@\7c\2\2@"+
		"A\7p\2\2AB\7f\2\2B\4\3\2\2\2CD\7q\2\2DE\7t\2\2E\6\3\2\2\2FG\7x\2\2GH\7"+
		"c\2\2HI\7t\2\2I\b\3\2\2\2JK\7v\2\2KL\7t\2\2LM\7w\2\2MW\7g\2\2NO\7h\2\2"+
		"OP\7c\2\2PQ\7n\2\2QR\7u\2\2RW\7g\2\2ST\7x\2\2TU\7c\2\2UW\7t\2\2VJ\3\2"+
		"\2\2VN\3\2\2\2VS\3\2\2\2W\n\3\2\2\2XY\7q\2\2Y]\7t\2\2Z[\7~\2\2[]\7~\2"+
		"\2\\X\3\2\2\2\\Z\3\2\2\2]\f\3\2\2\2^_\7c\2\2_`\7p\2\2`d\7f\2\2ab\7(\2"+
		"\2bd\7(\2\2c^\3\2\2\2ca\3\2\2\2d\16\3\2\2\2ei\7$\2\2fh\n\2\2\2gf\3\2\2"+
		"\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lv\7$\2\2mq\7)\2\2"+
		"np\n\2\2\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2"+
		"tv\7)\2\2ue\3\2\2\2um\3\2\2\2v\20\3\2\2\2wx\5%\23\2x}\5\23\n\2y|\5\23"+
		"\n\2z|\5\31\r\2{y\3\2\2\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~"+
		"\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\5\'\24\2\u0081\22\3\2\2\2\u0082"+
		"\u0087\5\25\13\2\u0083\u0087\t\3\2\2\u0084\u0087\5/\30\2\u0085\u0087\4"+
		"/\60\2\u0086\u0082\3\2\2\2\u0086\u0083\3\2\2\2\u0086\u0084\3\2\2\2\u0086"+
		"\u0085\3\2\2\2\u0087\24\3\2\2\2\u0088\u0089\t\4\2\2\u0089\26\3\2\2\2\u008a"+
		"\u008c\5\31\r\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3"+
		"\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090"+
		"\u0092\5\33\16\2\u0091\u0093\5\31\r\2\u0092\u0091\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u00a8\3\2\2\2\u0096"+
		"\u0098\5\31\r\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009f\5\33\16\2\u009c"+
		"\u009e\5\31\r\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3"+
		"\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a8\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2"+
		"\u00a4\5\31\r\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3"+
		"\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u008d\3\2\2\2\u00a7"+
		"\u0097\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a8\30\3\2\2\2\u00a9\u00aa\t\5\2"+
		"\2\u00aa\32\3\2\2\2\u00ab\u00ac\7\60\2\2\u00ac\34\3\2\2\2\u00ad\u00ae"+
		"\7]\2\2\u00ae\36\3\2\2\2\u00af\u00b0\7_\2\2\u00b0 \3\2\2\2\u00b1\u00b2"+
		"\7*\2\2\u00b2\"\3\2\2\2\u00b3\u00b4\7+\2\2\u00b4$\3\2\2\2\u00b5\u00b6"+
		"\7}\2\2\u00b6&\3\2\2\2\u00b7\u00b8\7\177\2\2\u00b8(\3\2\2\2\u00b9\u00ba"+
		"\7-\2\2\u00ba*\3\2\2\2\u00bb\u00bc\7/\2\2\u00bc,\3\2\2\2\u00bd\u00be\7"+
		",\2\2\u00be.\3\2\2\2\u00bf\u00c0\7\61\2\2\u00c0\60\3\2\2\2\u00c1\u00c2"+
		"\7?\2\2\u00c2\62\3\2\2\2\u00c3\u00c4\7#\2\2\u00c4\u00c5\7?\2\2\u00c5\64"+
		"\3\2\2\2\u00c6\u00c7\7>\2\2\u00c7\u00c8\7?\2\2\u00c8\66\3\2\2\2\u00c9"+
		"\u00ca\7>\2\2\u00ca8\3\2\2\2\u00cb\u00cc\7@\2\2\u00cc\u00cd\7?\2\2\u00cd"+
		":\3\2\2\2\u00ce\u00cf\7@\2\2\u00cf<\3\2\2\2\u00d0\u00d2\t\6\2\2\u00d1"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\b\37\2\2\u00d6>\3\2\2\2\23\2V\\c"+
		"iqu{}\u0086\u008d\u0094\u0099\u009f\u00a5\u00a7\u00d3";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}