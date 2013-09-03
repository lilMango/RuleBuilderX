import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import com.intuit.cg.lang.simplexslt.*;


public class XsltVisitor extends SimpleXsltBaseVisitor<STO>{
	static ParseTreeProperty<STO> xslt = new ParseTreeProperty<STO>();
	
	public STO getXslt(ParseTree ctx){
		return xslt.get(ctx);
	}//end getXslt(ParseTree)
	
	public void setXslt(ParseTree ctx, STO s){
		xslt.put(ctx, s);
	}//end setXslt(ParseTree,STO)
	
	
	public STO visitAtom(SimpleXsltParser.AtomContext ctx){
		System.out.println("~~~~~~~~From visitor\t"+ctx.getText());
		if(ctx.T_LPAREN()!=null && ctx.T_RPAREN()!=null){// ( expr )
			 STO expr=getXslt(ctx.getChild(1));
			 String translation="("+expr.getName()+")";
			 setXslt(ctx,new ExprSTO(translation,new IntType("expr int")));

		}else if(ctx.NUMBER()!=null){
			System.out.println("is a number!");
			return new ConstSTO(ctx.getText(),new FloatType("decimal"),new Double(ctx.getText()));

		}else if(ctx.ID()!=null){
			System.out.println("is an ID!");
			//Check if ID exists in XSL!!
			//TODO
			String id = prepXPath(stripBraces(ctx.getText()));
			return new VarSTO(id,new FloatType("IDENT"));
			
		}else if(ctx.T_STRING_LITERAL()!=null){
			System.out.println("T_STRING_LITERAL");
			return new ConstSTO("'"+ctx.getText().substring(1,ctx.getText().length()-1)+"'",new StringType("String"));
		
		}else if(ctx.T_CHAR_LITERAL()!=null){
			System.out.println("T_CHAR_LITERAL");
			return new ConstSTO(ctx.getText().substring(1,ctx.getText().length()-1),new StringType("char"));
		
		}else if(ctx.RESERVED_WORDS()!=null){
			System.out.println("QQQ\t"+ctx.RESERVED_WORDS());
			if(ctx.RESERVED_WORDS().toString().equals( "false")){
				return new ConstSTO("false()",new BoolType("bool"),new Double(0));
			}else if(ctx.RESERVED_WORDS().toString().equals( "true")){
				return new ConstSTO("true()",new BoolType("bool"),new Double(1));
			}
		}
		return new ErrorSTO("FWFWF");
	}//end visitAtom(ctx)
	
	public STO visitMystart(SimpleXsltParser.MystartContext ctx){
		if(ctx==null) return new ErrorSTO("myStartErr");
		STO expr=visitExpr(ctx.expr());
		if(expr!=null){
			return expr;
		}
		System.out.println("MYstart!");
		if (expr!=null && expr.isVar()){
			expr.setName("exists("+expr.getName()+")");
		}
		return expr;
	}//end visitMystart(ctx)
	
	
	//Logical OR
	public STO visitExpr(SimpleXsltParser.ExprContext ctx){
		if(ctx==null)return new ErrorSTO("expr is null");
	 
		STO b = visitExpr1(ctx.expr1());
		if(b instanceof ErrorSTO)
			return b;
		
		if(ctx.getChildCount()==3){//expr OR expr1
			STO a = visitExpr(ctx.expr());
			if(a instanceof ErrorSTO)return a;

			return new ExprSTO(a.getName()+" or "+b.getName());//TODO
		 }
		
			//single child
		return b;
}//end visitExpr(ctx)
	
	//Logical AND
	public STO visitExpr1(SimpleXsltParser.Expr1Context ctx){
		if(ctx==null)
			return new ErrorSTO("expr1 is null");
		
		STO b = visitExpr2(ctx.expr2());
		if(b instanceof ErrorSTO)
			return b;
		

		if(ctx.getChildCount()==3){//expr1 AND expr2
			STO a = visitExpr1(ctx.expr1());
			if(a instanceof ErrorSTO)return a;

			return new ExprSTO(a.getName()+" and "+b.getName());//TODO
		}//end if getChildCount()
		
			//single child
		return b;
	}//end visitExpr1(ctx)
	
	//Equality ==,!=
	public STO visitExpr2(SimpleXsltParser.Expr2Context ctx){
		if(ctx==null)
			return new ErrorSTO("expr2 is null");
		
		STO b = visitExpr3(ctx.expr3());
		if(b instanceof ErrorSTO)
			return b;
		
	 
		if(ctx.getChildCount()==3){//expr2 OR expr3
			STO a = visitExpr2(ctx.expr2());
			if(a instanceof ErrorSTO)return a;

			String op=ctx.getChild(1).getText();
			if("!=".equals(op)){
				op="ne";
			}
			return new ExprSTO(a.getName()+" "+op+" "+b.getName());
		 }
		
			//single child
		return b;
	}//end visitExpr2(ctx)
	
	//Relation <,<=,>,>=
	public STO visitExpr3(SimpleXsltParser.Expr3Context ctx){
		if(ctx==null)
			return new ErrorSTO("expr3 is null");
		
		STO b = visitExpr4(ctx.expr4());
		if(b instanceof ErrorSTO)
			return b;
		

		if(ctx.getChildCount()==3){//expr2 OR expr3
			STO a = visitExpr3(ctx.expr3());
			if(a instanceof ErrorSTO)return a;

			
			String op=ctx.getChild(1).getText();
			 if("<".equals(op)){
				 op="&lt;";
			 }else if("<=".equals(op)){
				 op="&lte;";
			 }else if(">".equals(op)){
				 op="&gt;";
			 }else {//>=
				 op="&gte;";
			 }
			 
			 if(a instanceof ConstSTO && b instanceof VarSTO){
				 return new ExprSTO(a.getName()+" "+op+" "+"xs:decimal("+b.getName()+")");
			 }else if(a instanceof VarSTO && b instanceof ConstSTO){
				 return new ExprSTO("xs:decimal("+a.getName()+") "+op+" "+b.getName());
			 }else if(a instanceof VarSTO && b instanceof VarSTO){
				 return new ExprSTO("xs:decimal("+a.getName()+") "+op+" xs:decimal("+b.getName()+")");
			 }
			return new ExprSTO(a.getName()+" "+op+" "+b.getName());
		 }//end if childCount
		
			//single child
		return b;
	}//end visitExpr3()
	
	//AddOp
	public STO visitExpr4(SimpleXsltParser.Expr4Context ctx){
		if(ctx==null)
			return new ErrorSTO("expr4 is null");
		
		STO b = visitExpr5(ctx.expr5());
		if(b instanceof ErrorSTO)
			return b;
		
		
		if(ctx.getChildCount()==3){//expr3 OR expr4
			STO a = visitExpr4(ctx.expr4());
			if(a instanceof ErrorSTO)return a;

			//TODO more constant folding
			if(a instanceof ConstSTO && b instanceof ConstSTO){
				float op1 = ((ConstSTO)a).getFloatValue();
				float op2 = ((ConstSTO)b).getFloatValue();
				
				String oper=ctx.getChild(1).getText();
				if("-".equals(oper))
					return new ConstSTO((op1-op2)+"",new FloatType("float"),new Double(op1-op2));
				return new ConstSTO((op1+op2)+"",new FloatType("float"),new Double(op1+op2));
			}
			
			return new ExprSTO(a.getName()+" "+ctx.getChild(1).getText()+" "+b.getName());
		 }//end if childCount
		
			//single child
		return b;	
	}//end visitExpr4()
	
	//MulOp
	public STO visitExpr5(SimpleXsltParser.Expr5Context ctx){
		if(ctx==null)
			return new ErrorSTO("expr5 is null");
		
		STO b = visitAtom(ctx.atom());
		if(b instanceof ErrorSTO)
			return b;
		
		
		if(ctx.getChildCount()==3){
			STO a = visitExpr5(ctx.expr5());
			if(a instanceof ErrorSTO)return a;

			if(a instanceof ConstSTO && b instanceof ConstSTO){
				float op1 = ((ConstSTO)a).getFloatValue();
				float op2 = ((ConstSTO)b).getFloatValue();
				
				String oper=ctx.getChild(1).getText();
				if("/".equals(oper))
					return new ConstSTO((op1/op2)+"",new FloatType("float"),new Double(op1/op2));
				return new ConstSTO((op1*op2)+"",new FloatType("float"),new Double(op1*op2));
			}
			
			return new ExprSTO(a.getName()+" "+ctx.mulOp().toString()+" "+b.getName());			
		}
		return b;
	}//end visitExpr5()
	///////////////////////////////////////////////////////////////////////
	/*
	 * Helpers for translating IDs (XPaths)
	 */
	private String stripBraces(String str){
		return str.substring(1,str.length()-1);
	}//end stripBraces(String)
	private String prepXPath(String str){
		return str.replace("/","/*:");
	}//end prepXPath(String)
	
}//end class XsltVisitor
