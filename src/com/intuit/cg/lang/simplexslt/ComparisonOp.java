package com.intuit.cg.lang.simplexslt;

public class ComparisonOp extends BinaryOp{
    public boolean result=false;
     
    public ComparisonOp(String oper) {
        super(oper);
 
    }
     
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
            sto= new ErrorSTO("Neither Numeric");
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
 
                 
                boolean bool=doConstCmpOp(aVal,bVal);
                int res=0;
                if(bool)
                    res=1;
             
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
 
                return constSTO;
                 
            }//end both ConstSTO Ints
            else if(a instanceof ConstSTO){
                int aVal=((ConstSTO) a).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                 
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){
                int bVal=((ConstSTO) b).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                return exprSTO;
            }
            ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
            return exprSTO;         
        }//end if Both INTs
 
         
        /**result is FLOAT if EITHER is float
         * 
         */
        else{
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){ //Float
                float aVal=((ConstSTO) a).getFloatValue();
                float bVal=((ConstSTO) b).getFloatValue();
                 
         
                boolean bool=doConstCmpOp(aVal,bVal);
                int res=0;
                if(bool)
                    res=1;
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
 
                return constSTO;
            }
            else if(a instanceof ConstSTO){//Float
                float aVal=((ConstSTO) a).getFloatValue();
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){//Float
                float bVal=((ConstSTO) b).getFloatValue();
                 
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                
                return exprSTO;
            }
            ExprSTO exprSTO= new ExprSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"));
            return exprSTO;         
                 
        }//end ExprSTO=Float
 
    }//end checkOperands
 
     
    public String doComparison(int cmpOpCount,String stoAddress){
        String str=
            SEPARATOR+"cmp %l1,%l2\n"+
            SEPARATOR+getArithInstr()+" compareOpF"+cmpOpCount+"\n"+//branch if wrong
            SEPARATOR+"set 1,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            SEPARATOR+"ba compareOpEnd"+cmpOpCount+"\n"+
            "compareOpF"+cmpOpCount+":\n"+
            SEPARATOR+"set 0,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            "compareOpEnd"+cmpOpCount+":\n";
        return str;
 
    }//end doComparison
     
    public String doComparisonFloat(int cmpOpCount,String stoAddress){
        String str=
            SEPARATOR+"fcmps %f1,%f2\n"+
            SEPARATOR+"nop\n"+
            SEPARATOR+getArithInstrFloat()+" compareOpF"+cmpOpCount+"\n"+//branch if wrong
            SEPARATOR+"nop\n"+
            SEPARATOR+"set 1,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            SEPARATOR+"ba compareOpEnd"+cmpOpCount+"\n"+
            "compareOpF"+cmpOpCount+":\n"+
            SEPARATOR+"set 0,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            "compareOpEnd"+cmpOpCount+":\n";
        return str;
 
    }//end doComparison
 
    public String getArithInstr(){
        String op=this.toString();
 
        if(op.equals("<")) //<
            return "bge ";
        else if(op.equals("<=")) //<=
            return "bg ";
        else if(op.equals(">")) //>
            return "ble "; //overridden
        return "bl "; //>= 
    }//getArithInstr()
     
    public String getArithInstrFloat(){
        String op=this.toString();
         
        if(op.equals("<")) //<
            return "fbge ";
        else if(op.equals("<=")) //<=
            return "fbg ";
        else if(op.equals(">")) //>
            return "fble "; //overridden
        return "fbl "; //>= 
    }//end getArithInstrFloat() 
 
    public boolean doConstCmpOp(float a,float b){
        String op=this.toString();
         
        if(op.equals("<"))
            return (a<b);
        else if(op.equals("<="))
            return (a<=b);
        else if(op.equals(">"))
            return(a>b);
        else //>=
            return (a>=b);
    }//end doConstOp (float, float)
     
 
}//end ComparisonOp
