package com.intuit.cg.lang.simplexslt;

//---------------------------------------------------------------------
//This is the top of the Type hierarchy. You most likely will need to
//create sub-classes (since this one is abstract) that handle specific
//types, such as IntType, FloatType, ArrayType, etc.
//---------------------------------------------------------------------


abstract class Type
{
 //----------------------------------------------------------------
 //
 //----------------------------------------------------------------

 public
 Type (String strName)
 {
     setName(strName);
      
 }


 //----------------------------------------------------------------
 //
 //----------------------------------------------------------------
 public String
 getName ()
 {
     return m_typeName;
 }

 public void
 setName (String str)
 {
     m_typeName = str;
 }




 //----------------------------------------------------------------
 //  It will be helpful to ask a Type what specific Type it is.
 //  The Java operator instanceof will do this, but you may
 //  also want to implement methods like isNumeric(), isInt(),
 //  etc. Below is an example of isInt(). Feel free to
 //  change this around.
 //----------------------------------------------------------------
 public boolean  isInt ()    { return false; }
 public boolean isArray()    {return false; }
 public boolean isPointer()  {return false;  }
 public boolean isStruct(){return false;}
 public boolean  isAssignable(Type t){ return false;}
 public boolean isEquivalent(Type t){ return false;}
 public boolean isCastable(Type t){ return false; }
  
 //----------------------------------------------------------------
 //  Name of the Type (e.g., int, bool, or some typedef
 //----------------------------------------------------------------
 private String      m_typeName;
 public Type clone(){
     return null;
 }
}//end class Type
