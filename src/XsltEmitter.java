import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;


public class XsltEmitter extends SimpleXsltBaseListener{
	static ParseTreeProperty<String> xslt = new ParseTreeProperty<String>();

	public String getXslt(ParseTree ctx){
		return xslt.get(ctx);
	}//end getXslt(ParseTree)
	
	public void setXslt(ParseTree ctx, String s){
		xslt.put(ctx, s);
	}//end setXslt(ParseTree,String)
	
	/*
	 * atom:
		T_LPAREN expr T_RPAREN 
		| NUMBER 				
		| ID 
		| StringLiteral
		| RESERVED_WORDS
	 */
	public void enterAtom(@NotNull SimpleXsltParser.AtomContext ctx) { }	
	public void exitAtom(SimpleXsltParser.AtomContext ctx){
		if(ctx.getChildCount()==3){// ( expr )
			 String expr=getXslt(ctx.getChild(1));
			 String translation="("+expr+")";
			 setXslt(ctx,translation);
		}
		else if(ctx.ID()!=null){
			String id = prepXPath(stripBraces(ctx.getText()));
			
			setXslt(ctx,id);				
		}
		else{
			setXslt(ctx,ctx.getText());
		}
			
		System.out.println("@exitAtom\n"+getXslt(ctx));
	}//end exitAtom()
	
	public void enterMystart(@NotNull SimpleXsltParser.MystartContext ctx) { }
	public void exitMystart(SimpleXsltParser.MystartContext ctx){
		setXslt(ctx,getXslt(ctx.getChild(0)));

		 System.out.println("@exitMystart\n"+getXslt(ctx)+"\t|\t"+ctx.getChildCount());
	}//end exitMystart()
	


	 public void enterAssignment(@NotNull SimpleXsltParser.AssignmentContext ctx) { }
	 public void exitAssignment(@NotNull SimpleXsltParser.AssignmentContext ctx) { }

 
	 //Logical OR
	 public void enterExpr(@NotNull SimpleXsltParser.ExprContext ctx) { }	 
	 public void exitExpr(@NotNull SimpleXsltParser.ExprContext ctx) { 
		 if(ctx.getChildCount()==3){//expr OR expr1
			 setCtxChildren(ctx);
		 }
		 else{	
			 setXslt(ctx,getXslt(ctx.getChild(0))); 
		 }
	 }//end exitExpr()

	 //Logical AND
	 public void enterExpr1(@NotNull SimpleXsltParser.Expr1Context ctx) { }
	 public void exitExpr1(@NotNull SimpleXsltParser.Expr1Context ctx) { 
		 if(ctx.getChildCount()==3){//expr OR expr1
			 setCtxChildren(ctx);
		 }
		 else{	
			 setXslt(ctx,getXslt(ctx.getChild(0))); 
		 }
	 }//end exitExpr1()

	 //Equality ==, !=
	 public void enterExpr2(@NotNull SimpleXsltParser.Expr2Context ctx) { }
	 public void exitExpr2(@NotNull SimpleXsltParser.Expr2Context ctx) {
		 if(ctx.getChildCount()==3){//expr OR expr1
			 setCtxChildren(ctx);
		 }
		 else{	
			 setXslt(ctx,getXslt(ctx.getChild(0))); 
		 }
	 }//end exitExpr2()

	 //Relation <,<=,>,>=
	 public void enterExpr3(@NotNull SimpleXsltParser.Expr3Context ctx) { }
	 public void exitExpr3(@NotNull SimpleXsltParser.Expr3Context ctx) {
		 if(ctx.getChildCount()==3){//expr3 Relation expr4
			 String left=getXslt(ctx.getChild(0));
			 String op=ctx.getChild(1).getText();
			 String right=getXslt(ctx.getChild(2));
			 
			 if("<".equals(op)){
				 op="&lt;";
			 }else if("<=".equals(op)){
				 op="&lte;";
			 }else if(">".equals(op)){
				 op="&gt;";
			 }else{//>=
				 op="&gte;";
			 }
			 setXslt(ctx,left+" "+op+" "+right);
			 
			 System.out.println(op+"\t"+getXslt(ctx));
		 }
		 else{	
			 setXslt(ctx,getXslt(ctx.getChild(0))); 
		 }
	 }//end exitExpr3

	 //AddOp
	 public void enterExpr4(@NotNull SimpleXsltParser.Expr4Context ctx) { }
	 public void exitExpr4(@NotNull SimpleXsltParser.Expr4Context ctx) {
		 if(ctx.getChildCount()==3){//expr + expr1
			 String left=getXslt(ctx.getChild(0));
			 String op=ctx.getChild(1).getText();
			 String right=getXslt(ctx.getChild(2));
			 
			 if("+".equals(op)){
				 op="sum";
			 } else {
				 op="difference";
			 }
			 String translation=op+"("+left+","+right+")";
			 setXslt(ctx,translation);
			 
			 System.out.println(op+"\t"+getXslt(ctx));
		 }
		 else{	
			 setXslt(ctx,getXslt(ctx.getChild(0))); 
		 }
	 }//end exitExpr4

	 //MulOp
	 public void enterExpr5(@NotNull SimpleXsltParser.Expr5Context ctx) { }
	 public void exitExpr5(@NotNull SimpleXsltParser.Expr5Context ctx) {
		 if(ctx.getChildCount()==3){//expr + expr1
			 String left=getXslt(ctx.getChild(0));
			 String op=ctx.getChild(1).getText();
			 String right=getXslt(ctx.getChild(2));
			 
			 if("*".equals(op)){
				 op="sum";
			 } 
			 String translation=left+" "+op+" "+right+")";
			 setXslt(ctx,translation);
			 
			 System.out.println(op+"\t"+getXslt(ctx));
		 }
		 else{	
			 setXslt(ctx,getXslt(ctx.getChild(0))); 
		 }
	 }//end exitExpr5()

	 public void enterEquality(@NotNull SimpleXsltParser.EqualityContext ctx) { }
	 public void exitEquality(@NotNull SimpleXsltParser.EqualityContext ctx) {

		 setXslt(ctx,getXslt(ctx.getChild(0))); 
	 }//end exitEquality()

	 public void enterRelation(@NotNull SimpleXsltParser.RelationContext ctx) { }
	 public void exitRelation(@NotNull SimpleXsltParser.RelationContext ctx) {
		 setXslt(ctx,getXslt(ctx.getChild(0))); 
	 }//end exitRelation()

	 public void enterAddOp(@NotNull SimpleXsltParser.AddOpContext ctx) { }	
	 public void exitAddOp(@NotNull SimpleXsltParser.AddOpContext ctx) {
		 setXslt(ctx,getXslt(ctx.getChild(0))); 
	 }//end exitAddOp

	 public void enterMulOp(@NotNull SimpleXsltParser.MulOpContext ctx) { }
	 public void exitMulOp(@NotNull SimpleXsltParser.MulOpContext ctx) {
		 setXslt(ctx,getXslt(ctx.getChild(0))); 
	 }//end exitMulOp





////////////////////////////////////////////////////////////////////////////
	/*
	 * Helpers for translating IDs (XPaths)
	 */
	private String stripBraces(String str){
		return str.substring(1,str.length()-1);
	}//end stripBraces(String)
	private String prepXPath(String str){
		return str.replace("/","/*:");
	}//end prepXPath
	

	private String setCtxChildren(ParseTree ctx){
		 String left=getXslt(ctx.getChild(0));
		 String op=ctx.getChild(1).getText();
		 String right=getXslt(ctx.getChild(2));
		 setXslt(ctx,left+" "+op+" "+right);
		 System.out.println(op+"\t"+getXslt(ctx));
		 
		 return left+" "+op+" "+right;
	}//end setCtxChildren(ParseTree)

}//end class SimpleXsltWalker
