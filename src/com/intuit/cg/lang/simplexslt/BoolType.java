package com.intuit.cg.lang.simplexslt;

public class BoolType extends BasicType{
	 
    public BoolType(String strName) {
        super(strName);
    }
    public boolean isAssignable(Type t){
        return isEquivalent(t);
    }//end isAssignable
     
    public boolean isEquivalent(Type t){
        if(t instanceof BoolType)
            return true;
        return false;
         
    }//end isEquivalent
     
    // (int|decimal|ALIASFLOAT) <=(int|decimal|ALIASFLOAT) bool
    public boolean isCastable(Type t){
        if((t instanceof IntType)||(t instanceof FloatType)||(t instanceof BoolType) || (t instanceof StringType))
            return true;
        return false;
    }//end isCastable()
     
    public Type clone(){
        return new BoolType(getName());
    }
}//end class BoolType
