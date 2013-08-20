package com.intuit.cg.lang.simplexslt;

public abstract class NumericType extends BasicType{
    public NumericType(String strName){
        super(strName);
    }

    public boolean isCastable(Type t){
        if((t instanceof IntType)||(t instanceof FloatType)||(t instanceof BoolType))
            return true;
        return false;
    }//end isCastable()
}//end class NumericType
