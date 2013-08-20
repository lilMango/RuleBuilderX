package com.intuit.cg.lang.simplexslt;

public class IntType extends NumericType{
    public IntType(String strName){
        super(strName);
    }   
    public boolean isInt(){return true;}
     
    public boolean isAssignable(Type t){ //float <=int, int is assignable TO Decimal
        if((t instanceof IntType)|| (t instanceof FloatType))
            return true;
        return false;
             
    }//end isAssignable
     
    public boolean isEquivalent(Type t){
        if(t instanceof IntType)
            return true;
        return false;
         
    }//end isEquivalent
     
    public Type clone(){
        return new IntType(getName());
    }
}//end class IntType
