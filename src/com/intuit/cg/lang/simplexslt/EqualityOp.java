package com.intuit.cg.lang.simplexslt;

public class EqualityOp extends BinaryOp {
	 
    public EqualityOp(String oper) {
        super(oper);
        // TODO Auto-generated constructor stub
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
                 
         
 
        //Both Ints
        if((aType instanceof IntType) && (bType instanceof IntType)){
                if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                 
                    int aVal=((ConstSTO) a).getIntValue();
                    int bVal=((ConstSTO) b).getIntValue();
 
                     
                    boolean bool=doConstEqOp(aVal,bVal);
                    int res=0;
                    if(bool)
                        res=1;
                 
                    ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
 
                    return constSTO;
                     
                }
                else if(a instanceof ConstSTO){ //int
                    int aVal=((ConstSTO) a).getIntValue();
                    ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                    return exprSTO;
                         
                }else if(b instanceof ConstSTO){//int
 
                    int bVal=((ConstSTO) b).getIntValue();
                    ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                    return exprSTO;
                }//int
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                return exprSTO;         
            }//end if Both INTs
         
        //both are numeric--combo of ints/float or both floats
        if ((aType instanceof NumericType) && (bType instanceof NumericType)){
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                 
                 
                float aVal=((ConstSTO) a).getFloatValue();
                float bVal=((ConstSTO) b).getFloatValue();
                 
                boolean equ=doConstEqOp(aVal,bVal);
                 
                int res=0;
                if(equ)
                    res=1; //true=1
                 
                ConstSTO constSTO= new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"),new Double(res));
 
                return constSTO;
 
            }//end if ConstSTO
            else if(a instanceof ConstSTO){//Float
                float aVal=((ConstSTO) a).getFloatValue();
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){//Float
                float bVal=((ConstSTO) b).getFloatValue();
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                 
                return exprSTO;
            }//a run time, b const numeric
            ExprSTO exprSTO= new ExprSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"));
             
            return exprSTO;         
                 
        }//end ExprSTO=Float
        //either float/numeric
         
         
        //both are bool
        else if(((aType instanceof BoolType) && (bType instanceof BoolType))){
             
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                boolean aVal=((ConstSTO) a).getBoolValue();
                boolean bVal=((ConstSTO) b).getBoolValue();
 
                boolean equ=doConstEqOp(aVal,bVal);
             
                int res=0;
                if(equ)
                    res=1; //true=1
             
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
 
                return constSTO;
            }//end if ConstSTO
            else if(a instanceof ConstSTO){//bool
                int aVal=((ConstSTO) a).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                 
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){//bool
                int bVal=((ConstSTO) b).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                return exprSTO;
            }//bool
            ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
            return exprSTO;         
             
         
        }//end if BOTH bool
         
        //else if BOTH NULL-returns true
        else if(aType instanceof NullType && bType instanceof NullType){
            return new ConstSTO("BoolConstSTO",new BoolType("bool"),new Double(1));
        }
       else{
            //%T %O %T
            sto= new ErrorSTO("EqualityOPelse");
             
            sto.setErrorMsgType(aType); //%T
            sto.setErrorMsgType2(bType); //%T
            return sto;
        }
         
    }//end checkOperands()
 
    public String doComparison(String stoAddress){
        String str=
            SEPARATOR+"cmp %l1,%l2\n"+
            SEPARATOR+getArithInstr()+" compareOpFTMP\n"+//branch if wrong
            SEPARATOR+"set 1,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            SEPARATOR+"ba compareOpEndTMP\n"+
            "compareOpFTMP:\n"+
            SEPARATOR+"set 0,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            "compareOpEndTMP:\n";
        return str;
 
    }//end doComparison
     
    public String doComparisonFloat(String stoAddress){
        String str=
            SEPARATOR+"fcmps %f1,%f2\n"+
            SEPARATOR+"nop\n"+
            SEPARATOR+getArithInstrFloat()+" compareOpFTMP\n"+//branch if wrong
            SEPARATOR+"nop\n"+
            SEPARATOR+"set 1,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            SEPARATOR+"ba compareOpEndTMP\n"+
            "compareOpFTMP:\n"+
            SEPARATOR+"set 0,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            "compareOpEndTMP:\n";
        return str;
 
    }//end doComparisonFloat
     
    /*
     
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
 
    }//end doComparisonFloat
*/
     
    public String getArithInstr(){
        String op=this.toString();
 
        if(op.equals("==")) //==
            return "bne ";
        return "be "; //!= 
    }//getArithInstr()
     
    public String getArithInstrFloat(){
        String op=this.toString();
         
        if(op.equals("==")) //==
            return "fbne ";
        return "fbe "; //!= 
    }//end getArithInstrFloat() 
 
    public boolean doConstEqOp(float a,float b){
        String op=this.toString();
         
        if(op.equals("=="))
            return (a==b);
        return (a!=b);
    }//end doConstOp (float, float)
 
    public boolean doConstEqOp(boolean a,boolean b){
        String op=this.toString();
         
        if(op.equals("=="))
            return (a==b);
        return (a!=b);
    }//end doConstOp (
 
}//end class EqualityOp
