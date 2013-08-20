// Generated from SimpleXslt.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleXsltParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleXsltVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#mulOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOp(@NotNull SimpleXsltParser.MulOpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(@NotNull SimpleXsltParser.AtomContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull SimpleXsltParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(@NotNull SimpleXsltParser.EqualityContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#expr3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr3(@NotNull SimpleXsltParser.Expr3Context ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#expr4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr4(@NotNull SimpleXsltParser.Expr4Context ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr1(@NotNull SimpleXsltParser.Expr1Context ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr2(@NotNull SimpleXsltParser.Expr2Context ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(@NotNull SimpleXsltParser.RelationContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#expr5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr5(@NotNull SimpleXsltParser.Expr5Context ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull SimpleXsltParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#mystart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMystart(@NotNull SimpleXsltParser.MystartContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimpleXsltParser#addOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOp(@NotNull SimpleXsltParser.AddOpContext ctx);
}