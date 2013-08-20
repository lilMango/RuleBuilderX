package com.intuit.cg.lang.simplexslt;

public class StringType extends BasicType{
    public StringType(String strName){
        super(strName);
    }   
    public boolean isString(){return true;}
     
    public boolean isAssignable(Type t){ //float <=String, String is assignable TO Decimal
        if((t instanceof StringType)|| (t instanceof FloatType))
            return true;
        return false;
             
    }//end isAssignable
     
    public boolean isEquivalent(Type t){
        if(t instanceof StringType)
            return true;
        return false;
         
    }//end isEquivalent(Type)

    public boolean isCastable(Type t){
    	if((t instanceof DateType)||(t instanceof TimeType)||(t instanceof FloatType)){
    		return true;
    	}
    	return false;
    }//end isCastable(Type)
    
    public Type clone(){
        return new StringType(getName());
    }
}//end class StringType
