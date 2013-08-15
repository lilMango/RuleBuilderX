package com.intuit.cg.lang.simplexslt;

public class NullType extends Type {
	 
    public NullType() {
        super("NULL");
        // TODO Auto-generated constructor stub
    }
 
    public boolean isEquivalent(Type t){
        if(t instanceof NullType)
            return true;
        return false;
    }//end isEquivalent
     
    public boolean isAssignable(Type t){
        return isEquivalent(t);
    }//end isAssignable
}//end class NullType
