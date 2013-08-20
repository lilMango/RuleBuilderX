package com.intuit.cg.lang.simplexslt;

public class FloatType extends NumericType{

    public FloatType(String strName){
        super(strName);
    }   
    public boolean isDecimal(){return true;}
     
     
    public boolean isAssignable(Type t){
            return isEquivalent(t);             
    }//end isAssignable(Type)
     
    public boolean isEquivalent(Type t){
        if(t instanceof FloatType)
            return true;
        return false;         
    }//end isEquivalent(Type)
    
    public Type clone(){
        return new FloatType(getName());
    }
}//end class FloatType
