package com.intuit.cg.lang.simplexslt;

public class DateType extends NumericType {
    public DateType(String strName){
        super(strName);
    }   
    public boolean isDate(){return true;}
     
     
    public boolean isAssignable(Type t){
            return isEquivalent(t);
             
    }//end isAssignable
     
    public boolean isEquivalent(Type t){
        if(t instanceof DateType)
            return true;
        return false;
         
    }//end isEquivalent
    public Type clone(){
        return new DateType(getName());
    }
}//end class FloatType
