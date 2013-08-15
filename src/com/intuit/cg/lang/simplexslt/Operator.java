package com.intuit.cg.lang.simplexslt;

public abstract class Operator {
    protected static final String SEPARATOR = "\t\t\t";
    String opString;
    String expectedType;
   
    public Operator(String oper){
        this.opString=oper;
    }
     
    public String getOpString(){
        return this.opString;
    }
     
     
    public STO checkOperands(STO a,STO b){
        STO sto=null;
         
        return sto;
    }//end checkOperands
     
    public STO checkOperand(STO a){
        STO sto=null;
         
        return sto;
    }
     
    public String toString(){
        return opString;
    }
 
    public String getExpectedType() {
        // TODO Auto-generated method stub
        return this.expectedType;
    }
     
    public float doConstOp(float a,float b){
        return (float) 123456789.00;
    }//end doConstOp (float, float)
     
    public int doConstOp(int a,int b){
        return 123456789;
    }//end doConstOp (float, float)
 

}//end class Operator