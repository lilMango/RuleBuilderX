package com.intuit.cg.lang.simplexslt;

public class BooleanOp extends BinaryOp{
	 
    public BooleanOp(String oper) {
        super(oper);
        this.expectedType="bool";
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
                 
        //both bool
        if ((aType instanceof BoolType) && (bType instanceof BoolType)){
            if((a instanceof ConstSTO)&& (b instanceof ConstSTO)){
                boolean aVal=((ConstSTO) a).getBoolValue();
                boolean bVal=((ConstSTO) b).getBoolValue();
                 
                String op=this.toString();
                boolean eval=false;
                 
                if(op.equals("&&"))
                    eval=(aVal && bVal);
                else //||
                    eval=(aVal||bVal);
                 
                int res=0;
                if(eval)
                    res=1;
                 
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
      
                return constSTO;
            }//end if ConstSTO
 
             
            ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
            return exprSTO;
 
        }//end if both BoolType
 
        else{
            //%T %O
            sto= new ErrorSTO("DWD");
 
            //save type of which ever argument was incorrect type
            if(!(aType instanceof BoolType))
                sto.setErrorMsgType(aType);
            else
                sto.setErrorMsgType(bType);
            return sto;
             
        }
         
    }//end checkOperands()
 
     
   
     
    public String getExpectedType(){
        return this.expectedType;
    }
     
 
     
    public boolean doConstBoolOp(boolean a,boolean b){
        String op=this.toString();
         
        if(op.equals("&&"))
            return (a&&b);
        return (a||b);
    }//end doConstOp (float, float)
}//end class BooleanOp
