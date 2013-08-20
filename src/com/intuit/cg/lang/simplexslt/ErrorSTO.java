package com.intuit.cg.lang.simplexslt;


public class ErrorSTO extends STO
{
   //----------------------------------------------------------------
   //
   //----------------------------------------------------------------
   public
   ErrorSTO (String strName)
   {
       super (strName);
   }
 
   //----------------------------------------------------------------
   //  There are times where it is an error if the STO is not a 
   //  constant or adddressable or something else. However, if
   //  the STO is already an error, nothing should be said. To
   //  supress that error, we would have to check if the STO is
   //  not an ErrorSTO as well as what we want it to be.  Rather
   //  than 2 checks we'll have the ErrorSTO always return true
   //  for every check.  (This is an example of where the
   //  instanceof operator would not have been appropriate.)
   //----------------------------------------------------------------
       public boolean  isVar ()              { return true; }
       public boolean  isConst ()            { return true; }
       public boolean  isExpr ()             { return true; }
       public boolean  isError ()            { return true; }
 }//end class ErrorSTO
