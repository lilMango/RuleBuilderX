// Generated from SimpleXslt.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleXsltParser}.
 */
public interface SimpleXsltListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#mulOp}.
	 * @param ctx the parse tree
	 */
	void enterMulOp(@NotNull SimpleXsltParser.MulOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#mulOp}.
	 * @param ctx the parse tree
	 */
	void exitMulOp(@NotNull SimpleXsltParser.MulOpContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull SimpleXsltParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull SimpleXsltParser.AtomContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull SimpleXsltParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull SimpleXsltParser.AssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(@NotNull SimpleXsltParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(@NotNull SimpleXsltParser.EqualityContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#expr3}.
	 * @param ctx the parse tree
	 */
	void enterExpr3(@NotNull SimpleXsltParser.Expr3Context ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#expr3}.
	 * @param ctx the parse tree
	 */
	void exitExpr3(@NotNull SimpleXsltParser.Expr3Context ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#expr4}.
	 * @param ctx the parse tree
	 */
	void enterExpr4(@NotNull SimpleXsltParser.Expr4Context ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#expr4}.
	 * @param ctx the parse tree
	 */
	void exitExpr4(@NotNull SimpleXsltParser.Expr4Context ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterExpr1(@NotNull SimpleXsltParser.Expr1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitExpr1(@NotNull SimpleXsltParser.Expr1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterExpr2(@NotNull SimpleXsltParser.Expr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitExpr2(@NotNull SimpleXsltParser.Expr2Context ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(@NotNull SimpleXsltParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(@NotNull SimpleXsltParser.RelationContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#expr5}.
	 * @param ctx the parse tree
	 */
	void enterExpr5(@NotNull SimpleXsltParser.Expr5Context ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#expr5}.
	 * @param ctx the parse tree
	 */
	void exitExpr5(@NotNull SimpleXsltParser.Expr5Context ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull SimpleXsltParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull SimpleXsltParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#mystart}.
	 * @param ctx the parse tree
	 */
	void enterMystart(@NotNull SimpleXsltParser.MystartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#mystart}.
	 * @param ctx the parse tree
	 */
	void exitMystart(@NotNull SimpleXsltParser.MystartContext ctx);

	/**
	 * Enter a parse tree produced by {@link SimpleXsltParser#addOp}.
	 * @param ctx the parse tree
	 */
	void enterAddOp(@NotNull SimpleXsltParser.AddOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleXsltParser#addOp}.
	 * @param ctx the parse tree
	 */
	void exitAddOp(@NotNull SimpleXsltParser.AddOpContext ctx);
}