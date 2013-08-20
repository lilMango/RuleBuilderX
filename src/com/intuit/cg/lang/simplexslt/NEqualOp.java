package com.intuit.cg.lang.simplexslt;

public class NEqualOp extends EqualityOp{
	 
    public NEqualOp(String oper) {
        super(oper);
        // TODO Auto-generated constructor stub
    }
 
/*
    public STO checkOperands(STO a,STO b){
        STO sto;
 
        if(a==null || b==null ){
            return new ErrorSTO("WNBT-Null STO");
        }       
        if (a instanceof ErrorSTO)
            return a;
        else if( b instanceof ErrorSTO)
            return b;
         
        Type aType=a.getType();
        Type bType=b.getType();
         
        if ((a instanceof FuncSTO)&& ((FuncSTO) a).isNormalFunc()){
            aType=((FuncSTO) a).getReturnType();
        }
        if ((b instanceof FuncSTO)&& ((FuncSTO) b).isNormalFunc()){
            bType=((FuncSTO) b).getReturnType();
        }
                 
        if( aType==null || bType==null){
            return new ErrorSTO("WNBT-Null Types");
        }
                 
         
        //both are numeric
        if ((aType instanceof NumericType) && (bType instanceof NumericType)){
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                 
                 
                float aVal=((ConstSTO) a).getFloatValue();
                float bVal=((ConstSTO) b).getFloatValue();
                 
                boolean equ=(aVal!=bVal);
                 
                int res=0;
                if(equ)
                    res=1; //true=1
                 
                return new ConstSTO("BoolConstSTO",new BoolType("bool"),new Double(res));
            }//end if ConstSTO
            return new ExprSTO("BoolExpr",new BoolType("bool"));
        //both are bool
        }else if((aType instanceof BoolType) && (bType instanceof BoolType)){
             
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                boolean aVal=((ConstSTO) a).getBoolValue();
                boolean bVal=((ConstSTO) b).getBoolValue();
             
                boolean equ=(aVal!=bVal);
             
                int res=0;
                if(equ)
                    res=1; //true=1
             
                return new ConstSTO("BoolConstSTO",new BoolType("bool"),new Double(res));
            }//end if ConstSTO
            return new ExprSTO("BoolExpr",new BoolType("bool"));
             
        //else if BOTH NULL-returns true
        }else if(aType instanceof NullType && bType instanceof NullType){
            return new ConstSTO("BoolConstSTO",new BoolType("bool"),new Double(0));
        }
        //Check 17 . Tests if both operands are pointers to the same type
        else if((aType instanceof PointerType) && (bType instanceof PointerType)){
            if (aType.isEquivalent(bType))
                return new ExprSTO("BoolExpr",new BoolType("bool"));
             
            //pointers to different types
            sto= new ErrorSTO(ErrorMsg.error17_Expr);
            sto.setErrorMsgType(aType); //%T
            sto.setErrorMsgType2(bType); //%T
            return sto;
 
             
        }
        //Both function pointer types
        else if((aType instanceof FuncPtrType) && (bType instanceof FuncPtrType)){
            if (aType.isEquivalent(bType))
                return new ExprSTO("BoolExpr",new BoolType("bool"));
             
            //pointers to different types
            sto= new ErrorSTO(ErrorMsg.error17_Expr);
            sto.setErrorMsgType(aType); //%T
            sto.setErrorMsgType2(bType); //%T
            return sto;
 
             
        }
 
        //Check 17. If types are NULL and pointer, or pointer and null
        else if(((aType instanceof PointerType) && (b.getName().equals("NULL")))
                ||((bType instanceof PointerType) && (a.getName().equals("NULL")))
                ){
            return new ExprSTO("BoolExpr",new BoolType("bool"));
        }
 
        //Check 18a
        else if((aType instanceof FuncPtrType) &&(bType instanceof NullType)){
            return new ExprSTO("BoolExpr",new BoolType("bool"));
        }       
        //if either type is null or  pointer
        else if((aType instanceof PointerType) || (aType instanceof NullType)||
                (bType instanceof PointerType) ||(bType instanceof NullType)){
 
                    //pointers to different types
                    sto= new ErrorSTO(ErrorMsg.error17_Expr);
                    sto.setErrorMsgType(aType); //%T
                    sto.setErrorMsgType2(bType); //%T
                    return sto;
                     
                }
        else{
            //%T %O %T
            sto= new ErrorSTO(ErrorMsg.error1b_Expr);
             
            sto.setErrorMsgType(aType); //%T
            sto.setErrorMsgType2(bType); //%T
            return sto;
        }
         
    }//end checkOperands()
*/
}//ends ComparisonOp
