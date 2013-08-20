package com.intuit.cg.lang.simplexslt;

public class EqualOp extends EqualityOp{
	 
    public EqualOp(String oper) {
        super(oper);
        // TODO Auto-generated constructor stub
    }
 
/*
    public STO checkOperands(STO a,STO b){
        STO sto;
        FuncSTO funcSTO=this.getFunc();
        MyCodeGen myCodeGen=this.getMyCodeGen();
        FuncSTO preMainSTO=getPreMain();
        int compareOpCount=myCodeGen.getCompareOpCount();
         
        boolean isGlobal=false;
         
        int nextFrameCount=0;
        if(funcSTO==null){ //get framecount of pre main for global initializations
            funcSTO=getPreMain();
            isGlobal=true;
        }
        if(funcSTO!=null)
            nextFrameCount=funcSTO.getFrameCount()+4;
 
         
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
                 
         
 
            //Both Ints
        if((aType instanceof IntType) && (bType instanceof IntType)){
                if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                 
                    int aVal=((ConstSTO) a).getIntValue();
                    int bVal=((ConstSTO) b).getIntValue();
 
                     
                    boolean bool=doConstEqOp(aVal,bVal);
                    int res=0;
                    if(bool)
                        res=1;
                 
                    ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
 
                    //pass funcSTO containing framecount
                    constSTO.setFunc(funcSTO);              
                      
                    //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                    //just change funcSTO back, is all
                    //pass preMain containining framecount
                    constSTO.setPreMain(preMainSTO);
 
                    return constSTO;
                     
                }
                else if(a instanceof ConstSTO){ //int
                    int aVal=((ConstSTO) a).getIntValue();
                    ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                    exprSTO.setBase("%fp");
                    exprSTO.setOffset("-"+nextFrameCount);
                     
                    String str=
                        "! "+exprSTO.getName()+"\n"+
                        SEPARATOR+"set "+aVal+", %l1\n"+
                        myCodeGen.setAddress(b)+
                        SEPARATOR+"ld [%l0],%l2\n"+
                        doComparison(compareOpCount,exprSTO.getAddress());
                        ;
                     
                    //save write for initializing globals till main
                    if(isGlobal){
                        exprSTO.setAddInstructions(a.getInstructions());
                        exprSTO.setAddInstructions(b.getInstructions());
                        exprSTO.setAddInstructions(str);
                    }else{
                        myCodeGen.write(str);
                    }
                    //pass funcSTO containing framecount
                    if(funcSTO!=null){
                        funcSTO.addFrameCount(4);
                    }
 
                    //return funcSTO as original currently parsed function. 
                    //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                    //just change funcSTO back, is all
                    exprSTO.setPreMain(preMainSTO);
                    funcSTO=this.getFunc();
                    exprSTO.setFunc(funcSTO);
                     
                    return exprSTO;
                         
                }else if(b instanceof ConstSTO){//int
                    int bVal=((ConstSTO) b).getIntValue();
                    ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                    exprSTO.setBase("%fp");
                    exprSTO.setOffset("-"+nextFrameCount);
                     
                    String str=
                        "! "+exprSTO.getName()+"\n"+
                        myCodeGen.setAddress(a)+
                        SEPARATOR+"ld [%l0],%l1\n"+
                        SEPARATOR+"set "+bVal+", %l2\n"+
                        doComparison(compareOpCount,exprSTO.getAddress());
                     
                    //save write for initializing globals till main
                    if(isGlobal){
                        exprSTO.setAddInstructions(a.getInstructions());
                        exprSTO.setAddInstructions(b.getInstructions());
                        exprSTO.setAddInstructions(str);
                    }else{
                        myCodeGen.write(str);
                    }       
                     
                    if(funcSTO!=null){
                        funcSTO.addFrameCount(4);
                    }
                    //return funcSTO as original currently parsed function. 
                    //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                    //just change funcSTO back, is all
                    exprSTO.setPreMain(preMainSTO);
                    funcSTO=this.getFunc();
                    exprSTO.setFunc(funcSTO);
 
                    return exprSTO;
                }//int
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                exprSTO.setBase("%fp");
                exprSTO.setOffset("-"+nextFrameCount);
 
                 
                String str=
                    "! "+exprSTO.getName()+"\n"+
                    myCodeGen.setAddress(a)+
                    SEPARATOR+"ld [%l0],%l1\n"+
                    myCodeGen.setAddress(b)+
                    SEPARATOR+"ld [%l0],%l2\n"+
                    doComparison(compareOpCount,exprSTO.getAddress());
                 
                 
                //save write for initializing globals till main
                if(isGlobal){
                    exprSTO.setAddInstructions(a.getInstructions());
                    exprSTO.setAddInstructions(b.getInstructions());
                    exprSTO.setAddInstructions(str);
                }else{
                    myCodeGen.write(str);
                }
                 
 
                if(funcSTO!=null){
                    funcSTO.addFrameCount(4);
                }
             
                //return funcSTO as original currently parsed function. 
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                exprSTO.setPreMain(preMainSTO);
                funcSTO=this.getFunc();
                exprSTO.setFunc(funcSTO);
 
                 
//              exprSTO.setInstruction(str);
                return exprSTO;         
            }//end if Both INTs
         
        //both are numeric--combo of ints/float or both floats
        if ((aType instanceof NumericType) && (bType instanceof NumericType)){
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                 
                 
                float aVal=((ConstSTO) a).getFloatValue();
                float bVal=((ConstSTO) b).getFloatValue();
                 
                boolean equ=doConstEqOp(aVal,bVal);
                 
                int res=0;
                if(equ)
                    res=1; //true=1
                 
                ConstSTO constSTO= new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"),new Double(res));
 
                //pass funcSTO containing framecount
                constSTO.setFunc(funcSTO);              
                  
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                //pass preMain containining framecount
                constSTO.setPreMain(preMainSTO);
 
                return constSTO;
 
            }//end if ConstSTO
            else if(a instanceof ConstSTO){//Float
                float aVal=((ConstSTO) a).getFloatValue();
                 
                String floatLabel=myCodeGen.makeFloatLabel(aVal);
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                 
                exprSTO.setBase("%fp");
                exprSTO.setOffset("-"+nextFrameCount);
                 
                 
                String str=
                    "! "+exprSTO.getName()+"\n"+
                     
                    SEPARATOR+"set "+floatLabel+", %l0\n"+
                    SEPARATOR+"ld [%l0],%f1\n"+
                    myCodeGen.setAddress(b)+
                     
                    SEPARATOR+"ld [%l0],%f2\n"+
                    myCodeGen.intToFloat(b, "%f2")+  //change bit pattern to float
                    doComparisonFloat(compareOpCount,exprSTO.getAddress()); 
                 
                 
                //save write for initializing globals till main
                if(isGlobal){
                    exprSTO.setAddInstructions(a.getInstructions());
                    exprSTO.setAddInstructions(b.getInstructions());
                    exprSTO.setAddInstructions(str);
                }else{
                    myCodeGen.write(str);
                }
                 
                //pass funcSTO containing framecount
 
                if(funcSTO!=null){
                    funcSTO.addFrameCount(4);
                }
                //return funcSTO as original currently parsed function. 
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                exprSTO.setPreMain(preMainSTO);
                funcSTO=this.getFunc();
                exprSTO.setFunc(funcSTO);
                 
//              exprSTO.setInstruction(str);
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){//Float
                float bVal=((ConstSTO) b).getFloatValue();
                 
                String floatLabel=myCodeGen.makeFloatLabel(bVal);
                 
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                 
                exprSTO.setBase("%fp");
                exprSTO.setOffset("-"+nextFrameCount);
                 
                String str=
                    "! "+exprSTO.getName()+"\n"+
                    myCodeGen.setAddress(a)+
                    SEPARATOR+"ld [%l0],%f1\n"+
                    myCodeGen.intToFloat(a, "%f1")+  //change bit pattern to float
                    SEPARATOR+"set "+floatLabel+", %l0\n"+
                    SEPARATOR+"ld [%l0],%f2\n"+
                    doComparisonFloat(compareOpCount,exprSTO.getAddress()); 
                     
                 
                //save write for initializing globals till main
                if(isGlobal){
                    exprSTO.setAddInstructions(a.getInstructions());
                    exprSTO.setAddInstructions(b.getInstructions());
                    exprSTO.setAddInstructions(str);
                }else{ //write because it's being parsed in function
                    myCodeGen.write(str);
                }
                 
             
                if(funcSTO!=null){
                    funcSTO.addFrameCount(4);
                }
 
                //return funcSTO as original currently parsed function. 
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                exprSTO.setPreMain(preMainSTO);
                funcSTO=this.getFunc();
                exprSTO.setFunc(funcSTO);
 
                return exprSTO;
            }//a run time, b const numeric
            ExprSTO exprSTO= new ExprSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"));
             
            exprSTO.setBase("%fp");
            exprSTO.setOffset("-"+nextFrameCount);
 
             
            String str=
                "! "+exprSTO.getName()+"\n"+
                myCodeGen.setAddress(a)+
                SEPARATOR+"ld [%l0],%f1\n"+
                myCodeGen.intToFloat(a, "%f1")+  //change bit pattern to float
                myCodeGen.setAddress(b)+
                SEPARATOR+"ld [%l0],%f2\n"+
                myCodeGen.intToFloat(b, "%f2")+  //change bit pattern to float
                doComparisonFloat(compareOpCount,exprSTO.getAddress()); 
                 
             
            //save write for initializing globals till main
            if(isGlobal){
                exprSTO.setAddInstructions(a.getInstructions());
                exprSTO.setAddInstructions(b.getInstructions());
                exprSTO.setAddInstructions(str);
            }else{ //write because it's being parsed in function
                myCodeGen.write(str);
            }
 
            if(funcSTO!=null){
                funcSTO.addFrameCount(4);
            }
             
            //return funcSTO as original currently parsed function. 
            //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
            //just change funcSTO back, is all
            exprSTO.setPreMain(preMainSTO);
            funcSTO=this.getFunc();
            exprSTO.setFunc(funcSTO);
 
            return exprSTO;         
                 
        }//end ExprSTO=Float
        //either float/numeric
         
         
        //both are bool
            else if(((aType instanceof BoolType) && (bType instanceof BoolType))){
             
            if((a instanceof ConstSTO)&&(b instanceof ConstSTO)){
                boolean aVal=((ConstSTO) a).getBoolValue();
                boolean bVal=((ConstSTO) b).getBoolValue();
 
                boolean equ=doConstEqOp(aVal,bVal);
             
                int res=0;
                if(equ)
                    res=1; //true=1
             
                ConstSTO constSTO=new ConstSTO(a.getName()+" "+this.toString()+" "+b.getName(),new BoolType("bool"), new Double(res));
 
                //pass funcSTO containing framecount
                constSTO.setFunc(funcSTO);              
                  
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                //pass preMain containining framecount
                constSTO.setPreMain(preMainSTO);
 
                return constSTO;
            }//end if ConstSTO
            else if(a instanceof ConstSTO){
                int aVal=((ConstSTO) a).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
                exprSTO.setBase("%fp");
                exprSTO.setOffset("-"+nextFrameCount);
                 
                String str=
                    "! "+exprSTO.getName()+"\n"+
                    SEPARATOR+"set "+aVal+", %l1\n"+
                    myCodeGen.setAddress(b)+
                    SEPARATOR+"ld [%l0],%l2\n"+
                    doComparison(compareOpCount,exprSTO.getAddress());
                    ;
                 
                //save write for initializing globals till main
                if(isGlobal){
                    exprSTO.setAddInstructions(a.getInstructions());
                    exprSTO.setAddInstructions(b.getInstructions());
                    exprSTO.setAddInstructions(str);
                }else{
                    myCodeGen.write(str);
                }
                //pass funcSTO containing framecount
                if(funcSTO!=null){
                    funcSTO.addFrameCount(4);
                }
 
                //return funcSTO as original currently parsed function. 
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                exprSTO.setPreMain(preMainSTO);
                funcSTO=this.getFunc();
                exprSTO.setFunc(funcSTO);
                 
                return exprSTO;
                     
            }else if(b instanceof ConstSTO){
                int bVal=((ConstSTO) b).getIntValue();
                ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
                exprSTO.setBase("%fp");
                exprSTO.setOffset("-"+nextFrameCount);
                 
                String str=
                    "! "+exprSTO.getName()+"\n"+
                    myCodeGen.setAddress(a)+
                    SEPARATOR+"ld [%l0],%l1\n"+
                    SEPARATOR+"set "+bVal+", %l2\n"+
                    doComparison(compareOpCount,exprSTO.getAddress());
                 
                //save write for initializing globals till main
                if(isGlobal){
                    exprSTO.setAddInstructions(a.getInstructions());
                    exprSTO.setAddInstructions(b.getInstructions());
                    exprSTO.setAddInstructions(str);
                }else{
                    myCodeGen.write(str);
                }       
                 
                if(funcSTO!=null){
                    funcSTO.addFrameCount(4);
                }
                //return funcSTO as original currently parsed function. 
                //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
                //just change funcSTO back, is all
                exprSTO.setPreMain(preMainSTO);
                funcSTO=this.getFunc();
                exprSTO.setFunc(funcSTO);
 
                return exprSTO;
            }
            ExprSTO exprSTO=new ExprSTO("("+a.getName()+" "+this.toString()+" "+b.getName()+")",new BoolType("bool"));
 
            exprSTO.setBase("%fp");
            exprSTO.setOffset("-"+nextFrameCount);
 
             
            String str=
                "! "+exprSTO.getName()+"\n"+
                myCodeGen.setAddress(a)+
                SEPARATOR+"ld [%l0],%l1\n"+
                myCodeGen.setAddress(b)+
                SEPARATOR+"ld [%l0],%l2\n"+
                doComparison(compareOpCount,exprSTO.getAddress());
             
             
            //save write for initializing globals till main
            if(isGlobal){
                exprSTO.setAddInstructions(a.getInstructions());
                exprSTO.setAddInstructions(b.getInstructions());
                exprSTO.setAddInstructions(str);
            }else{
                myCodeGen.write(str);
            }
             
 
            if(funcSTO!=null){
                funcSTO.addFrameCount(4);
            }
         
            //return funcSTO as original currently parsed function. 
            //funcSTO=preMain is to keep the operations funcSTO.addFrameCount the same
            //just change funcSTO back, is all
            exprSTO.setPreMain(preMainSTO);
            funcSTO=this.getFunc();
            exprSTO.setFunc(funcSTO);
 
             
//          exprSTO.setInstruction(str);
            return exprSTO;         
             
        //else if BOTH NULL-returns true
        }else if(aType instanceof NullType && bType instanceof NullType){
            return new ConstSTO("BoolConstSTO",new BoolType("bool"),new Double(1));
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
        else if(((aType instanceof PointerType) && (bType instanceof NullType))
                ||((bType instanceof PointerType) && (aType instanceof NullType))
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
     
     
    public String doComparison(int cmpOpCount,String stoAddress){
        String str=
            SEPARATOR+"cmp %l1,%l2\n"+
            SEPARATOR+getArithInstr()+" compareOpF"+cmpOpCount+"\n"+//branch if wrong
            SEPARATOR+"set 1,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            SEPARATOR+"ba compareOpEnd"+cmpOpCount+"\n"+
            "compareOpF"+cmpOpCount+":\n"+
            SEPARATOR+"set 0,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            "compareOpEnd"+cmpOpCount+":\n";
        return str;
 
    }//end doComparison
     
    public String doComparisonFloat(int cmpOpCount,String stoAddress){
        String str=
            SEPARATOR+"fcmps %f1,%f2\n"+
            SEPARATOR+"nop\n"+
            SEPARATOR+getArithInstrFloat()+" compareOpF"+cmpOpCount+"\n"+//branch if wrong
            SEPARATOR+"nop\n"+
            SEPARATOR+"set 1,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            SEPARATOR+"ba compareOpEnd"+cmpOpCount+"\n"+
            "compareOpF"+cmpOpCount+":\n"+
            SEPARATOR+"set 0,%l3\n"+
            SEPARATOR+"st %l3,["+stoAddress+"]\n"+
            "compareOpEnd"+cmpOpCount+":\n";
        return str;
 
    }//end doComparisonFloat
    public String getArithInstr(){
        String op=this.toString();
 
        if(op.equals("==")) //==
            return "bne ";
        return "be "; //!= 
    }//getArithInstr()
     
    public String getArithInstrFloat(){
        String op=this.toString();
         
        if(op.equals("==")) //==
            return "fbne ";
        return "fbe "; //!= 
    }//end getArithInstrFloat() 
 
    public boolean doConstEqOp(float a,float b){
        String op=this.toString();
         
        if(op.equals("=="))
            return (a==b);
        return (a!=b);
    }//end doConstOp (float, float)
 
    public boolean doConstEqOp(boolean a,boolean b){
        String op=this.toString();
         
        if(op.equals("=="))
            return (a==b);
        return (a!=b);
    }//end doConstOp (
     
    */
}//end EqualOp
