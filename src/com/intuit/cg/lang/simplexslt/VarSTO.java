package com.intuit.cg.lang.simplexslt;

public class VarSTO extends STO
{
    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public
    VarSTO (String strName)
    {
        super (strName);
         
        // fields as necessary
    }
  
    public
    VarSTO (String strName, Type typ)
    {
        super (strName, typ);
        // You may want to change the isModifiable and isAddressable 
        // fields as necessary
    }
  
 
    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public boolean  
    isVar () 
    {
        return true;
    }
     
}//end class VarSTO
