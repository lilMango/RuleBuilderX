package com.intuit.cg.lang.simplexslt;

public class ArithmeticOp extends BinaryOp{
	 
    public ArithmeticOp(String oper) {
 
        // TODO Auto-generated constructor stub
        super(oper);
    }//end Constructor
 
    public STO checkOperands(STO a,STO b){
 
        STO sto;

         
        if(a==null || b==null ){
            return new ErrorSTO("WNBT-Null STO");
        }       
        if (a instanceof ErrorSTO)
            return a;
        else if( b instanceof ErrorSTO)
            return b;
         
        Type aType=a.getType();
        Type bType=b.getType();
         
         
        if( aType==null || bType==null){
            return new ErrorSTO("WNBT-Null Types");
        }
         
 
        //neither is numeric
        if (!(aType instanceof NumericType) || !(bType instanceof NumericType)){
            sto= new ErrorSTO("Neither number is numeric");
            if(!(aType instanceof NumericType))
                sto.setErrorMsgType(aType);
            else
                sto.setErrorMsgType(bType);
            return sto;
        }
        // both ints
        else if((aType instanceof IntType) && (bType instanceof IntType)){
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
 
             
                int aVal=((ConstSTO) a).getIntValue();
                int bVal=((ConstSTO) b).getIntValue();
 
                 
                int res=doConstOp(aVal,bVal);
             
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new IntType("int"), new Double(res));
 
 
                return constSTO;
                 
            }
            else if(a instanceof ConstSTO){
                int aVal=((ConstSTO) a).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new IntType("int"));
                 
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){
                int bVal=((ConstSTO) b).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new IntType("int"));
                 
                return exprSTO;
            }
            ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new IntType("int"));
        
            return exprSTO;         
        }//end if Both INTs
 
         
        /**result is FLOAT if EITHER is float
         * 
         */
        else{
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){ //Float
                float aVal=((ConstSTO) a).getFloatValue();
                float bVal=((ConstSTO) b).getFloatValue();
                 
                //divide by zero, return error
                if(this.toString().equals("/")&& bVal==(float)0.00)
                    return new ErrorSTO("Y U Divide by Zero?!");
 
                 
                float res=doConstOp(aVal,bVal);
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new FloatType("float"), new Double(res));
              
                return constSTO;
            }
            else if(a instanceof ConstSTO){//Float
                float aVal=((ConstSTO) a).getFloatValue();
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new FloatType("float"));
     
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){//Float
                float bVal=((ConstSTO) b).getFloatValue();
                 
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new FloatType("float"));
 
                return exprSTO;
            }
            ExprSTO exprSTO= new ExprSTO(a.getName()+" "+this.toString()+" "+b.getName(),new FloatType("float"));
             
            return exprSTO;         
                 
        }//end ExprSTO=Float
 
    }//end checkOperands
     
     
    public float doConstOp(float a,float b){
        String op=this.toString();
         
        if(op.equals("+"))
            return a+b;
        else if(op.equals("-"))
            return a-b;
        else if(op.equals("*"))
            return a*b;
         // div
        return a/b;
         
         
    }//end doConstOp (float, float)
     
     
    public int doConstOp(int a,int b){
        String op=this.toString();
         
        if(op.equals("+"))
            return a+b;
        else if(op.equals("-"))
            return a-b;
        else if(op.equals("*"))
            return a*b;
        else if(op.equals("/"))
            return a/b;
         
        return a%b;
    }//end doConstOp(int,int)
     
    public String getArithInstr(){
        String op=this.toString();
         
        if(op.equals("+"))
            return "add";
        else if(op.equals("-"))
            return "sub";
        else if(op.equals("*"))
            return "MulOP(.mul)"; //overridden
        else if(op.equals("/"))
            return "DivOp(.div)"; //overridden
        return "ModOp(.rem)"; //ModOp uses its own method 
    }//getArithInstr()
     

}//end ArithmeticOp
