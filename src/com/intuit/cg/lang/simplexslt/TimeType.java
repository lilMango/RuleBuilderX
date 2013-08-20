package com.intuit.cg.lang.simplexslt;

public class TimeType extends NumericType{
	public TimeType(String strName){
	    super(strName);
	}   
	public boolean isTime(){return true;}
	 
	 
	public boolean isAssignable(Type t){
	        return isEquivalent(t);
	         
	}//end isAssignable
	 
	public boolean isEquivalent(Type t){
	    if(t instanceof TimeType)
	        return true;
	    return false;
	     
	}//end isEquivalent
	public Type clone(){
	    return new TimeType(getName());
	}
}//end class FloatType
