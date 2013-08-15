package com.intuit.cg.lang.simplexslt;

public class DecimalType extends NumericType{

    public DecimalType(String strName){
        super(strName);
    }   
    public boolean isDecimal(){return true;}
     
     
    public boolean isAssignable(Type t){
            return isEquivalent(t);
             
    }//end isAssignable
     
    public boolean isEquivalent(Type t){
        if(t instanceof DecimalType)
            return true;
        return false;
         
    }//end isEquivalent
    public Type clone(){
        return new DecimalType(getName());
    }
}//end class FloatType
