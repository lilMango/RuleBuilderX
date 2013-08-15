package com.intuit.cg.lang.simplexslt;

public class NullSTO extends STO{
	 
    public NullSTO(String strName) {
        super(strName,new NullType());
        // TODO Auto-generated constructor stub
    }
     
    public boolean isNullSTO ()
    {
        return true;
    }//isNullSTO()
 
}//end class NullSTO
