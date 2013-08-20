// Generated from SimpleXslt.g4 by ANTLR 4.1
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
		T__2=1, T__1=2, T__0=3, RESERVED_WORDS=4, T_BOOLS=5, T_OR=6, T_AND=7, 
		T_STRING_LITERAL=8, T_CHAR_LITERAL=9, ID=10, NUMBER=11, DIGIT=12, T_LBRACE=13, 
		T_RBRACE=14, T_LPAREN=15, T_RPAREN=16, T_LSQUIGBRACE=17, T_RSQUIGBRACE=18, 
		T_PLUS=19, T_MINUS=20, T_STAR=21, T_SLASH=22, T_EQ=23, T_NEQ=24, T_LTE=25, 
		T_LT=26, T_GTE=27, T_GT=28, WS=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'and'", "'or'", "'var'", "RESERVED_WORDS", "T_BOOLS", "T_OR", 
		"T_AND", "T_STRING_LITERAL", "T_CHAR_LITERAL", "ID", "NUMBER", "DIGIT", 
		"'['", "']'", "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", 
		"'='", "'!='", "'<='", "'<'", "'>='", "'>'", "WS"
	};
	public static final int
		RULE_mystart = 0, RULE_assignment = 1, RULE_expr = 2, RULE_expr1 = 3, 
		RULE_expr2 = 4, RULE_expr3 = 5, RULE_expr4 = 6, RULE_expr5 = 7, RULE_equality = 8, 
		RULE_relation = 9, RULE_addOp = 10, RULE_mulOp = 11, RULE_atom = 12;
	public static final String[] ruleNames = {
		"mystart", "assignment", "expr", "expr1", "expr2", "expr3", "expr4", "expr5", 
		"equality", "relation", "addOp", "mulOp", "atom"
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitMystart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MystartContext mystart() throws RecognitionException {
		MystartContext _localctx = new MystartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_mystart);
		try {
			setState(29);
			switch (_input.LA(1)) {
			case EOF:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 2);
				{
				setState(27); assignment();
				}
				break;
			case RESERVED_WORDS:
			case T_STRING_LITERAL:
			case T_CHAR_LITERAL:
			case ID:
			case NUMBER:
			case T_LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(28); expr(0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); match(3);
			setState(32); match(ID);
			setState(33); match(T_EQ);
			setState(34); expr(0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(37); expr1(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(39);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(40);
					_la = _input.LA(1);
					if ( !(_la==2 || _la==T_OR) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(41); expr1(0);
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitExpr1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr1Context expr1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr1Context _localctx = new Expr1Context(_ctx, _parentState, _p);
		Expr1Context _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, RULE_expr1);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(48); expr2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(55);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr1Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr1);
					setState(50);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(51);
					_la = _input.LA(1);
					if ( !(_la==1 || _la==T_AND) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(52); expr2(0);
					}
					} 
				}
				setState(57);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitExpr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr2Context expr2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr2Context _localctx = new Expr2Context(_ctx, _parentState, _p);
		Expr2Context _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, RULE_expr2);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(59); expr3(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr2Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr2);
					setState(61);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(62); equality();
					setState(63); expr3(0);
					}
					} 
				}
				setState(69);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitExpr3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr3Context expr3(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr3Context _localctx = new Expr3Context(_ctx, _parentState, _p);
		Expr3Context _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, RULE_expr3);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(71); expr4(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr3Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr3);
					setState(73);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(74); relation();
					setState(75); expr4(0);
					}
					} 
				}
				setState(81);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitExpr4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr4Context expr4(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr4Context _localctx = new Expr4Context(_ctx, _parentState, _p);
		Expr4Context _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, RULE_expr4);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(83); expr5(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(91);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr4Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr4);
					setState(85);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(86); addOp();
					setState(87); expr5(0);
					}
					} 
				}
				setState(93);
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

	public static class Expr5Context extends ParserRuleContext {
		public int _p;
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitExpr5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr5Context expr5(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr5Context _localctx = new Expr5Context(_ctx, _parentState, _p);
		Expr5Context _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, RULE_expr5);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(95); atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(103);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr5Context(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr5);
					setState(97);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(98); mulOp();
					setState(99); atom();
					}
					} 
				}
				setState(105);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityContext equality() throws RecognitionException {
		EqualityContext _localctx = new EqualityContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_equality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitAddOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddOpContext addOp() throws RecognitionException {
		AddOpContext _localctx = new AddOpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_addOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitMulOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulOpContext mulOp() throws RecognitionException {
		MulOpContext _localctx = new MulOpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_mulOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
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
		public TerminalNode RESERVED_WORDS() { return getToken(SimpleXsltParser.RESERVED_WORDS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode T_LPAREN() { return getToken(SimpleXsltParser.T_LPAREN, 0); }
		public TerminalNode ID() { return getToken(SimpleXsltParser.ID, 0); }
		public TerminalNode T_CHAR_LITERAL() { return getToken(SimpleXsltParser.T_CHAR_LITERAL, 0); }
		public TerminalNode T_RPAREN() { return getToken(SimpleXsltParser.T_RPAREN, 0); }
		public TerminalNode T_STRING_LITERAL() { return getToken(SimpleXsltParser.T_STRING_LITERAL, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleXsltVisitor ) return ((SimpleXsltVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_atom);
		try {
			setState(123);
			switch (_input.LA(1)) {
			case T_LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(114); match(T_LPAREN);
				setState(115); expr(0);
				setState(116); match(T_RPAREN);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(118); match(NUMBER);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(119); match(ID);
				}
				break;
			case T_STRING_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(120); match(T_STRING_LITERAL);
				}
				break;
			case T_CHAR_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(121); match(T_CHAR_LITERAL);
				}
				break;
			case RESERVED_WORDS:
				enterOuterAlt(_localctx, 6);
				{
				setState(122); match(RESERVED_WORDS);
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
		case 2: return expr_sempred((ExprContext)_localctx, predIndex);

		case 3: return expr1_sempred((Expr1Context)_localctx, predIndex);

		case 4: return expr2_sempred((Expr2Context)_localctx, predIndex);

		case 5: return expr3_sempred((Expr3Context)_localctx, predIndex);

		case 6: return expr4_sempred((Expr4Context)_localctx, predIndex);

		case 7: return expr5_sempred((Expr5Context)_localctx, predIndex);
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
		case 5: return 2 >= _localctx._p;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\37\u0080\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\5\2 \n\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\58\n\5\f\5\16\5;\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6D\n\6\f"+
		"\6\16\6G\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7P\n\7\f\7\16\7S\13\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\7\b\\\n\b\f\b\16\b_\13\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16~\n\16\3\16\2\17\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\2\b\4\2\4\4\b\b\4\2\3\3\t\t\3\2\31\32\3\2\33"+
		"\36\3\2\25\26\3\2\27\30\177\2\37\3\2\2\2\4!\3\2\2\2\6&\3\2\2\2\b\61\3"+
		"\2\2\2\n<\3\2\2\2\fH\3\2\2\2\16T\3\2\2\2\20`\3\2\2\2\22l\3\2\2\2\24n\3"+
		"\2\2\2\26p\3\2\2\2\30r\3\2\2\2\32}\3\2\2\2\34 \3\2\2\2\35 \5\4\3\2\36"+
		" \5\6\4\2\37\34\3\2\2\2\37\35\3\2\2\2\37\36\3\2\2\2 \3\3\2\2\2!\"\7\5"+
		"\2\2\"#\7\f\2\2#$\7\31\2\2$%\5\6\4\2%\5\3\2\2\2&\'\b\4\1\2\'(\5\b\5\2"+
		"(.\3\2\2\2)*\6\4\2\3*+\t\2\2\2+-\5\b\5\2,)\3\2\2\2-\60\3\2\2\2.,\3\2\2"+
		"\2./\3\2\2\2/\7\3\2\2\2\60.\3\2\2\2\61\62\b\5\1\2\62\63\5\n\6\2\639\3"+
		"\2\2\2\64\65\6\5\3\3\65\66\t\3\2\2\668\5\n\6\2\67\64\3\2\2\28;\3\2\2\2"+
		"9\67\3\2\2\29:\3\2\2\2:\t\3\2\2\2;9\3\2\2\2<=\b\6\1\2=>\5\f\7\2>E\3\2"+
		"\2\2?@\6\6\4\3@A\5\22\n\2AB\5\f\7\2BD\3\2\2\2C?\3\2\2\2DG\3\2\2\2EC\3"+
		"\2\2\2EF\3\2\2\2F\13\3\2\2\2GE\3\2\2\2HI\b\7\1\2IJ\5\16\b\2JQ\3\2\2\2"+
		"KL\6\7\5\3LM\5\24\13\2MN\5\16\b\2NP\3\2\2\2OK\3\2\2\2PS\3\2\2\2QO\3\2"+
		"\2\2QR\3\2\2\2R\r\3\2\2\2SQ\3\2\2\2TU\b\b\1\2UV\5\20\t\2V]\3\2\2\2WX\6"+
		"\b\6\3XY\5\26\f\2YZ\5\20\t\2Z\\\3\2\2\2[W\3\2\2\2\\_\3\2\2\2][\3\2\2\2"+
		"]^\3\2\2\2^\17\3\2\2\2_]\3\2\2\2`a\b\t\1\2ab\5\32\16\2bi\3\2\2\2cd\6\t"+
		"\7\3de\5\30\r\2ef\5\32\16\2fh\3\2\2\2gc\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij"+
		"\3\2\2\2j\21\3\2\2\2ki\3\2\2\2lm\t\4\2\2m\23\3\2\2\2no\t\5\2\2o\25\3\2"+
		"\2\2pq\t\6\2\2q\27\3\2\2\2rs\t\7\2\2s\31\3\2\2\2tu\7\21\2\2uv\5\6\4\2"+
		"vw\7\22\2\2w~\3\2\2\2x~\7\r\2\2y~\7\f\2\2z~\7\n\2\2{~\7\13\2\2|~\7\6\2"+
		"\2}t\3\2\2\2}x\3\2\2\2}y\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~\33\3\2"+
		"\2\2\n\37.9EQ]i}";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}