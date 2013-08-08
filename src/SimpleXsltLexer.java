


//Generated from SimpleXslt.g4 by ANTLR 4.1
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
		T__3=1, T__2=2, T__1=3, T__0=4, RESERVED_WORDS=5, T_OR=6, T_AND=7, ID=8, 
		NUMBER=9, DIGIT=10, T_LBRACE=11, T_RBRACE=12, T_LPAREN=13, T_RPAREN=14, 
		T_LSQUIGBRACE=15, T_RSQUIGBRACE=16, T_PLUS=17, T_MINUS=18, T_STAR=19, 
		T_SLASH=20, T_EQ=21, T_NEQ=22, T_LTE=23, T_LT=24, T_GTE=25, T_GT=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'and'", "'or'", "'hello'", "'var'", "RESERVED_WORDS", "T_OR", "T_AND", 
		"ID", "NUMBER", "DIGIT", "'['", "']'", "'('", "')'", "'{'", "'}'", "'+'", 
		"'-'", "'*'", "'/'", "'='", "'!='", "'<='", "'<'", "'>='", "'>'", "WS"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "RESERVED_WORDS", "T_OR", "T_AND", "ID", 
		"NAMECHAR", "LETTER", "NUMBER", "DIGIT", "DECIMAL", "T_LBRACE", "T_RBRACE", 
		"T_LPAREN", "T_RPAREN", "T_LSQUIGBRACE", "T_RSQUIGBRACE", "T_PLUS", "T_MINUS", 
		"T_STAR", "T_SLASH", "T_EQ", "T_NEQ", "T_LTE", "T_LT", "T_GTE", "T_GT", 
		"WS"
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\35\u00cb\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6]\n\6\3\7\3\7\3\7\3\7\5"+
		"\7c\n\7\3\b\3\b\3\b\3\b\3\b\5\bj\n\b\3\t\3\t\3\t\3\t\7\tp\n\t\f\t\16\t"+
		"s\13\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n{\n\n\3\13\3\13\3\f\7\f\u0080\n\f\f"+
		"\f\16\f\u0083\13\f\3\f\3\f\6\f\u0087\n\f\r\f\16\f\u0088\3\f\6\f\u008c"+
		"\n\f\r\f\16\f\u008d\3\f\3\f\7\f\u0092\n\f\f\f\16\f\u0095\13\f\3\f\6\f"+
		"\u0098\n\f\r\f\16\f\u0099\5\f\u009c\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3"+
		"\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3"+
		"\35\3\35\3\35\3\36\3\36\3\37\6\37\u00c6\n\37\r\37\16\37\u00c7\3\37\3\37"+
		"\2 \3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\2\1\25\2\1\27"+
		"\13\1\31\f\1\33\2\1\35\r\1\37\16\1!\17\1#\20\1%\21\1\'\22\1)\23\1+\24"+
		"\1-\25\1/\26\1\61\27\1\63\30\1\65\31\1\67\32\19\33\1;\34\1=\35\2\3\2\6"+
		"\4\2^^aa\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u00d8\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5C\3\2\2\2\7F\3\2\2\2\tL\3\2\2\2"+
		"\13\\\3\2\2\2\rb\3\2\2\2\17i\3\2\2\2\21k\3\2\2\2\23z\3\2\2\2\25|\3\2\2"+
		"\2\27\u009b\3\2\2\2\31\u009d\3\2\2\2\33\u009f\3\2\2\2\35\u00a1\3\2\2\2"+
		"\37\u00a3\3\2\2\2!\u00a5\3\2\2\2#\u00a7\3\2\2\2%\u00a9\3\2\2\2\'\u00ab"+
		"\3\2\2\2)\u00ad\3\2\2\2+\u00af\3\2\2\2-\u00b1\3\2\2\2/\u00b3\3\2\2\2\61"+
		"\u00b5\3\2\2\2\63\u00b7\3\2\2\2\65\u00ba\3\2\2\2\67\u00bd\3\2\2\29\u00bf"+
		"\3\2\2\2;\u00c2\3\2\2\2=\u00c5\3\2\2\2?@\7c\2\2@A\7p\2\2AB\7f\2\2B\4\3"+
		"\2\2\2CD\7q\2\2DE\7t\2\2E\6\3\2\2\2FG\7j\2\2GH\7g\2\2HI\7n\2\2IJ\7n\2"+
		"\2JK\7q\2\2K\b\3\2\2\2LM\7x\2\2MN\7c\2\2NO\7t\2\2O\n\3\2\2\2PQ\7v\2\2"+
		"QR\7t\2\2RS\7w\2\2S]\7g\2\2TU\7h\2\2UV\7c\2\2VW\7n\2\2WX\7u\2\2X]\7g\2"+
		"\2YZ\7x\2\2Z[\7c\2\2[]\7t\2\2\\P\3\2\2\2\\T\3\2\2\2\\Y\3\2\2\2]\f\3\2"+
		"\2\2^_\7q\2\2_c\7t\2\2`a\7~\2\2ac\7~\2\2b^\3\2\2\2b`\3\2\2\2c\16\3\2\2"+
		"\2de\7c\2\2ef\7p\2\2fj\7f\2\2gh\7(\2\2hj\7(\2\2id\3\2\2\2ig\3\2\2\2j\20"+
		"\3\2\2\2kl\5%\23\2lq\5\23\n\2mp\5\23\n\2np\5\31\r\2om\3\2\2\2on\3\2\2"+
		"\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\5\'\24\2u\22\3"+
		"\2\2\2v{\5\25\13\2w{\t\2\2\2x{\5/\30\2y{\7/\2\2zv\3\2\2\2zw\3\2\2\2zx"+
		"\3\2\2\2zy\3\2\2\2{\24\3\2\2\2|}\t\3\2\2}\26\3\2\2\2~\u0080\5\31\r\2\177"+
		"~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0086\5\33\16\2\u0085\u0087\5"+
		"\31\r\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u009c\3\2\2\2\u008a\u008c\5\31\r\2\u008b\u008a\3"+
		"\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u0093\5\33\16\2\u0090\u0092\5\31\r\2\u0091\u0090"+
		"\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u009c\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\5\31\r\2\u0097\u0096\3"+
		"\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009c\3\2\2\2\u009b\u0081\3\2\2\2\u009b\u008b\3\2\2\2\u009b\u0097\3\2"+
		"\2\2\u009c\30\3\2\2\2\u009d\u009e\t\4\2\2\u009e\32\3\2\2\2\u009f\u00a0"+
		"\7\60\2\2\u00a0\34\3\2\2\2\u00a1\u00a2\7]\2\2\u00a2\36\3\2\2\2\u00a3\u00a4"+
		"\7_\2\2\u00a4 \3\2\2\2\u00a5\u00a6\7*\2\2\u00a6\"\3\2\2\2\u00a7\u00a8"+
		"\7+\2\2\u00a8$\3\2\2\2\u00a9\u00aa\7}\2\2\u00aa&\3\2\2\2\u00ab\u00ac\7"+
		"\177\2\2\u00ac(\3\2\2\2\u00ad\u00ae\7-\2\2\u00ae*\3\2\2\2\u00af\u00b0"+
		"\7/\2\2\u00b0,\3\2\2\2\u00b1\u00b2\7,\2\2\u00b2.\3\2\2\2\u00b3\u00b4\7"+
		"\61\2\2\u00b4\60\3\2\2\2\u00b5\u00b6\7?\2\2\u00b6\62\3\2\2\2\u00b7\u00b8"+
		"\7#\2\2\u00b8\u00b9\7?\2\2\u00b9\64\3\2\2\2\u00ba\u00bb\7>\2\2\u00bb\u00bc"+
		"\7?\2\2\u00bc\66\3\2\2\2\u00bd\u00be\7>\2\2\u00be8\3\2\2\2\u00bf\u00c0"+
		"\7@\2\2\u00c0\u00c1\7?\2\2\u00c1:\3\2\2\2\u00c2\u00c3\7@\2\2\u00c3<\3"+
		"\2\2\2\u00c4\u00c6\t\5\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\b\37"+
		"\2\2\u00ca>\3\2\2\2\20\2\\bioqz\u0081\u0088\u008d\u0093\u0099\u009b\u00c7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}