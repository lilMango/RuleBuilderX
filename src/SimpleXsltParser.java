


//Generated from SimpleXslt.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleXsltParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, RESERVED_WORDS=5, T_OR=6, T_AND=7, ID=8, 
		NUMBER=9, DIGIT=10, T_LBRACE=11, T_RBRACE=12, T_LPAREN=13, T_RPAREN=14, 
		T_LSQUIGBRACE=15, T_RSQUIGBRACE=16, T_PLUS=17, T_MINUS=18, T_STAR=19, 
		T_SLASH=20, T_EQ=21, T_NEQ=22, T_LTE=23, T_LT=24, T_GTE=25, T_GT=26, WS=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'and'", "'or'", "'hello'", "'var'", "RESERVED_WORDS", "T_OR", 
		"T_AND", "ID", "NUMBER", "DIGIT", "'['", "']'", "'('", "')'", "'{'", "'}'", 
		"'+'", "'-'", "'*'", "'/'", "'='", "'!='", "'<='", "'<'", "'>='", "'>'", 
		"WS"
	};
	public static final int
		RULE_mystart = 0, RULE_block = 1, RULE_assignment = 2, RULE_expr = 3, 
		RULE_expr1 = 4, RULE_expr2 = 5, RULE_expr3 = 6, RULE_expr4 = 7, RULE_expr5 = 8, 
		RULE_equality = 9, RULE_relation = 10, RULE_addOp = 11, RULE_mulOp = 12, 
		RULE_atom = 13;
	public static final String[] ruleNames = {
		"mystart", "block", "assignment", "expr", "expr1", "expr2", "expr3", "expr4", 
		"expr5", "equality", "relation", "addOp", "mulOp", "atom"
	};

	@Override
	public String getGrammarFileName() { return "SimpleXslt.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleXsltParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MystartContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SimpleXsltParser.ID); }
		public TerminalNode NUMBER(int i) {
			return getToken(SimpleXsltParser.NUMBER, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(SimpleXsltParser.NUMBER); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ID(int i) {
			return getToken(SimpleXsltParser.ID, i);
		}
		public MystartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mystart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterMystart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitMystart(this);
		}
	}

	public final MystartContext mystart() throws RecognitionException {
		MystartContext _localctx = new MystartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_mystart);
		int _la;
		try {
			setState(41);
			switch (_input.LA(1)) {
			case 3:
				enterOuterAlt(_localctx, 1);
				{
				setState(28); match(3);
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(29); match(ID);
					}
					}
					setState(32); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NUMBER) {
					{
					{
					setState(34); match(NUMBER);
					}
					}
					setState(39);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case EOF:
			case 4:
			case RESERVED_WORDS:
			case ID:
			case NUMBER:
			case T_LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(40); block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		try {
			setState(46);
			switch (_input.LA(1)) {
			case EOF:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 2);
				{
				setState(44); assignment();
				}
				break;
			case RESERVED_WORDS:
			case ID:
			case NUMBER:
			case T_LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(45); expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode T_EQ() { return getToken(SimpleXsltParser.T_EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimpleXsltParser.ID, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); match(4);
			setState(49); match(ID);
			setState(50); match(T_EQ);
			setState(51); expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_OR() { return getToken(SimpleXsltParser.T_OR, 0); }
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(54); expr1(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(56);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(57);
					_la = _input.LA(1);
					if ( !(_la==2 || _la==T_OR) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(58); expr1(0);
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr1Context extends ParserRuleContext {
		public int _p;
		public Expr2Context expr2() {
			return getRuleContext(Expr2Context.class,0);
		}
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public TerminalNode T_AND() { return getToken(SimpleXsltParser.T_AND, 0); }
		public Expr1Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr1Context(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitExpr1(this);
		}
	}

	public final Expr1Context expr1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr1Context _localctx = new Expr1Context(_ctx, _parentState, _p);
		Expr1Context _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, RULE_expr1);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(65); expr2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr1Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr1);
					setState(67);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(68);
					_la = _input.LA(1);
					if ( !(_la==1 || _la==T_AND) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(69); expr2(0);
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr2Context extends ParserRuleContext {
		public int _p;
		public Expr2Context expr2() {
			return getRuleContext(Expr2Context.class,0);
		}
		public EqualityContext equality() {
			return getRuleContext(EqualityContext.class,0);
		}
		public Expr3Context expr3() {
			return getRuleContext(Expr3Context.class,0);
		}
		public Expr2Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr2Context(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitExpr2(this);
		}
	}

	public final Expr2Context expr2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr2Context _localctx = new Expr2Context(_ctx, _parentState, _p);
		Expr2Context _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, RULE_expr2);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(76); expr3(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr2Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr2);
					setState(78);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(79); equality();
					setState(80); expr3(0);
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr3Context extends ParserRuleContext {
		public int _p;
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public Expr4Context expr4() {
			return getRuleContext(Expr4Context.class,0);
		}
		public Expr3Context expr3() {
			return getRuleContext(Expr3Context.class,0);
		}
		public Expr3Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr3Context(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterExpr3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitExpr3(this);
		}
	}

	public final Expr3Context expr3(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr3Context _localctx = new Expr3Context(_ctx, _parentState, _p);
		Expr3Context _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, RULE_expr3);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(88); expr4(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr3Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr3);
					setState(90);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(91); relation();
					setState(92); expr4(0);
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr4Context extends ParserRuleContext {
		public int _p;
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
		public AddOpContext addOp() {
			return getRuleContext(AddOpContext.class,0);
		}
		public Expr4Context expr4() {
			return getRuleContext(Expr4Context.class,0);
		}
		public Expr4Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr4Context(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterExpr4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitExpr4(this);
		}
	}

	public final Expr4Context expr4(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr4Context _localctx = new Expr4Context(_ctx, _parentState, _p);
		Expr4Context _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, RULE_expr4);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(100); expr5(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr4Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr4);
					setState(102);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(103); addOp();
					setState(104); expr5(0);
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr5Context extends ParserRuleContext {
		public int _p;
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
		public TerminalNode RESERVED_WORDS() { return getToken(SimpleXsltParser.RESERVED_WORDS, 0); }
		public MulOpContext mulOp() {
			return getRuleContext(MulOpContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Expr5Context(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr5Context(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterExpr5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitExpr5(this);
		}
	}

	public final Expr5Context expr5(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr5Context _localctx = new Expr5Context(_ctx, _parentState, _p);
		Expr5Context _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, RULE_expr5);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			switch (_input.LA(1)) {
			case ID:
			case NUMBER:
			case T_LPAREN:
				{
				setState(112); atom();
				}
				break;
			case RESERVED_WORDS:
				{
				setState(113); match(RESERVED_WORDS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(122);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr5Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr5);
					setState(116);
					if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
					setState(117); mulOp();
					setState(118); atom();
					}
					} 
				}
				setState(124);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EqualityContext extends ParserRuleContext {
		public TerminalNode T_EQ() { return getToken(SimpleXsltParser.T_EQ, 0); }
		public TerminalNode T_NEQ() { return getToken(SimpleXsltParser.T_NEQ, 0); }
		public EqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitEquality(this);
		}
	}

	public final EqualityContext equality() throws RecognitionException {
		EqualityContext _localctx = new EqualityContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_equality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_la = _input.LA(1);
			if ( !(_la==T_EQ || _la==T_NEQ) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationContext extends ParserRuleContext {
		public TerminalNode T_LT() { return getToken(SimpleXsltParser.T_LT, 0); }
		public TerminalNode T_GTE() { return getToken(SimpleXsltParser.T_GTE, 0); }
		public TerminalNode T_GT() { return getToken(SimpleXsltParser.T_GT, 0); }
		public TerminalNode T_LTE() { return getToken(SimpleXsltParser.T_LTE, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitRelation(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_LTE) | (1L << T_LT) | (1L << T_GTE) | (1L << T_GT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddOpContext extends ParserRuleContext {
		public TerminalNode T_PLUS() { return getToken(SimpleXsltParser.T_PLUS, 0); }
		public TerminalNode T_MINUS() { return getToken(SimpleXsltParser.T_MINUS, 0); }
		public AddOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterAddOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitAddOp(this);
		}
	}

	public final AddOpContext addOp() throws RecognitionException {
		AddOpContext _localctx = new AddOpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_addOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_la = _input.LA(1);
			if ( !(_la==T_PLUS || _la==T_MINUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulOpContext extends ParserRuleContext {
		public TerminalNode T_SLASH() { return getToken(SimpleXsltParser.T_SLASH, 0); }
		public TerminalNode T_STAR() { return getToken(SimpleXsltParser.T_STAR, 0); }
		public MulOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterMulOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitMulOp(this);
		}
	}

	public final MulOpContext mulOp() throws RecognitionException {
		MulOpContext _localctx = new MulOpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_mulOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_la = _input.LA(1);
			if ( !(_la==T_STAR || _la==T_SLASH) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_LPAREN() { return getToken(SimpleXsltParser.T_LPAREN, 0); }
		public TerminalNode ID() { return getToken(SimpleXsltParser.ID, 0); }
		public TerminalNode T_RPAREN() { return getToken(SimpleXsltParser.T_RPAREN, 0); }
		public TerminalNode NUMBER() { return getToken(SimpleXsltParser.NUMBER, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleXsltListener ) ((SimpleXsltListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_atom);
		try {
			setState(139);
			switch (_input.LA(1)) {
			case T_LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(133); match(T_LPAREN);
				setState(134); expr(0);
				setState(135); match(T_RPAREN);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(137); match(NUMBER);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(138); match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3: return expr_sempred((ExprContext)_localctx, predIndex);

		case 4: return expr1_sempred((Expr1Context)_localctx, predIndex);

		case 5: return expr2_sempred((Expr2Context)_localctx, predIndex);

		case 6: return expr3_sempred((Expr3Context)_localctx, predIndex);

		case 7: return expr4_sempred((Expr4Context)_localctx, predIndex);

		case 8: return expr5_sempred((Expr5Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr3_sempred(Expr3Context _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr4_sempred(Expr4Context _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr1_sempred(Expr1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr2_sempred(Expr2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr5_sempred(Expr5Context _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return 3 >= _localctx._p;
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\35\u0090\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\6\2!\n\2\r\2\16\2\""+
		"\3\2\7\2&\n\2\f\2\16\2)\13\2\3\2\5\2,\n\2\3\3\3\3\3\3\5\3\61\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\7\6I\n\6\f\6\16\6L\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7"+
		"\7U\n\7\f\7\16\7X\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\ba\n\b\f\b\16\bd"+
		"\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\tm\n\t\f\t\16\tp\13\t\3\n\3\n\3\n"+
		"\5\nu\n\n\3\n\3\n\3\n\3\n\7\n{\n\n\f\n\16\n~\13\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008e\n\17\3\17\2"+
		"\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\b\4\2\4\4\b\b\4\2\3\3\t\t\3"+
		"\2\27\30\3\2\31\34\3\2\23\24\3\2\25\26\u008f\2+\3\2\2\2\4\60\3\2\2\2\6"+
		"\62\3\2\2\2\b\67\3\2\2\2\nB\3\2\2\2\fM\3\2\2\2\16Y\3\2\2\2\20e\3\2\2\2"+
		"\22t\3\2\2\2\24\177\3\2\2\2\26\u0081\3\2\2\2\30\u0083\3\2\2\2\32\u0085"+
		"\3\2\2\2\34\u008d\3\2\2\2\36 \7\5\2\2\37!\7\n\2\2 \37\3\2\2\2!\"\3\2\2"+
		"\2\" \3\2\2\2\"#\3\2\2\2#\'\3\2\2\2$&\7\13\2\2%$\3\2\2\2&)\3\2\2\2\'%"+
		"\3\2\2\2\'(\3\2\2\2(,\3\2\2\2)\'\3\2\2\2*,\5\4\3\2+\36\3\2\2\2+*\3\2\2"+
		"\2,\3\3\2\2\2-\61\3\2\2\2.\61\5\6\4\2/\61\5\b\5\2\60-\3\2\2\2\60.\3\2"+
		"\2\2\60/\3\2\2\2\61\5\3\2\2\2\62\63\7\6\2\2\63\64\7\n\2\2\64\65\7\27\2"+
		"\2\65\66\5\b\5\2\66\7\3\2\2\2\678\b\5\1\289\5\n\6\29?\3\2\2\2:;\6\5\2"+
		"\3;<\t\2\2\2<>\5\n\6\2=:\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\t\3\2"+
		"\2\2A?\3\2\2\2BC\b\6\1\2CD\5\f\7\2DJ\3\2\2\2EF\6\6\3\3FG\t\3\2\2GI\5\f"+
		"\7\2HE\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\13\3\2\2\2LJ\3\2\2\2MN\b"+
		"\7\1\2NO\5\16\b\2OV\3\2\2\2PQ\6\7\4\3QR\5\24\13\2RS\5\16\b\2SU\3\2\2\2"+
		"TP\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\r\3\2\2\2XV\3\2\2\2YZ\b\b\1"+
		"\2Z[\5\20\t\2[b\3\2\2\2\\]\6\b\5\3]^\5\26\f\2^_\5\20\t\2_a\3\2\2\2`\\"+
		"\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\17\3\2\2\2db\3\2\2\2ef\b\t\1\2"+
		"fg\5\22\n\2gn\3\2\2\2hi\6\t\6\3ij\5\30\r\2jk\5\22\n\2km\3\2\2\2lh\3\2"+
		"\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\21\3\2\2\2pn\3\2\2\2qr\b\n\1\2ru\5"+
		"\34\17\2su\7\7\2\2tq\3\2\2\2ts\3\2\2\2u|\3\2\2\2vw\6\n\7\3wx\5\32\16\2"+
		"xy\5\34\17\2y{\3\2\2\2zv\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\23\3\2"+
		"\2\2~|\3\2\2\2\177\u0080\t\4\2\2\u0080\25\3\2\2\2\u0081\u0082\t\5\2\2"+
		"\u0082\27\3\2\2\2\u0083\u0084\t\6\2\2\u0084\31\3\2\2\2\u0085\u0086\t\7"+
		"\2\2\u0086\33\3\2\2\2\u0087\u0088\7\17\2\2\u0088\u0089\5\b\5\2\u0089\u008a"+
		"\7\20\2\2\u008a\u008e\3\2\2\2\u008b\u008e\7\13\2\2\u008c\u008e\7\n\2\2"+
		"\u008d\u0087\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\35"+
		"\3\2\2\2\16\"\'+\60?JVbnt|\u008d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}