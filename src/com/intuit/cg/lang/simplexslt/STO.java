package com.intuit.cg.lang.simplexslt;

/*
 * STOs are objects produced after traversing the parse tree
 */

public abstract class STO
{
  //----------------------------------------------------------------
  //
  //----------------------------------------------------------------
  public
  STO (String strName)
  {
      this(strName, null);
  }

  public
  STO (String strName, Type typ)
  {
      setName(strName);
      setType(typ);
  }
  

  //----------------------------------------------------------------
  //
  //----------------------------------------------------------------
  public String
  getName ()
  {
      return m_strName;
  }

  void
  setName (String str)
  {
      m_strName = str;
  }

  //----------------------------------------------------------------
  //
  //----------------------------------------------------------------
  public Type
  getType ()
  {
      return  m_type;
  }

  protected void
  setType (Type type)
  {
      m_type = type;
  }



  //----------------------------------------------------------------
  //  It will be helpful to ask a STO what specific STO it is.
  //  The Java operator instanceof will do this, but these methods 
  //  will allow more flexibility (ErrorSTO is an example of the
  //  flexibility needed).
  //----------------------------------------------------------------
  public boolean  isVar ()    { return false; }
  public boolean  isConst ()  { return false; }
  public boolean  isExpr ()   { return false; }
  public boolean  isFunc ()   { return false; }

  public boolean  isError ()  { return false; }
  public boolean  isNullSTO (){return false;}
   

   
  public void setErrorMsgType(Type t) {
       
      this.m_ErrorMsgType=t;
  }
  public void setErrorMsgType2(Type t) {
      this.m_ErrorMsgType2=t;     
  }

   
  public Type getErrorMsgType() {

      return m_ErrorMsgType;
  }

  public Type getErrorMsgType2() {
      // TODO Auto-generated method stub
      return m_ErrorMsgType2;
  }
   
  public void setRightSTO(STO rightSTO){
      this.rightSTO=rightSTO;
  }//end setRightSTO()
   
  public STO getRightSTO(){
      return this.rightSTO;
  }//end getRightSTO
   
  private STO rightSTO;
  //----------------------------------------------------------------
  // 
  //----------------------------------------------------------------
  private String      m_strName;
  private Type        m_type;
  private Type        m_ErrorMsgType;
  private Type        m_ErrorMsgType2;
   
   
  private String assignInstructions="";
   
  public void setAssignInstructions(String str){
      this.assignInstructions=str;
  }//end setAssignInstructions()
   
  public String getAssignInstructions(){
      return this.assignInstructions;
  }//end getAssignInstructions()

   
  private String operatorInstructions="";// for arithm, comparison, operators
   
  public void setOperatorInstructions(String str){
      this.operatorInstructions=str;
  }//end setCompareInstructions()

   
  public String getOperatorInstructions(){
      return this.operatorInstructions;
  }//end getCompareInstructions()
   

   
}//end class STO