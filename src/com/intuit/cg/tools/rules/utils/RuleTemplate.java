package com.intuit.cg.tools.rules.utils;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mpaysan
 */
public class RuleTemplate {
    
    private static final String ERRORS_XSLT="\t<Error>\n"+
                                                "\t   <xsl:attribute name=\"errorCode\">$ruleName</xsl:attribute>\n"+
                                                "\t   <xsl:attribute name=\"type\">rejectToCustomer</xsl:attribute>\n"+
                                                "\t   <xsl:attribute name=\"RejectingAgency\">$agency</xsl:attribute>\n"+
                                             "\t</Error>\n";
    
    //parse using $
    private static final String IF_QUERY = 
                                        "<!-- Begin $ruleName -->\n"+
                                           "   <xsl:if test=\"$test\">\n"+
                                                ERRORS_XSLT+//"$"+
                                           "   </xsl:if>\n"+
                                                "<!-- End $ruleName -->"; 
    
    //parse strings at ? for errorCode and rejectingAgency

    public RuleTemplate(){
        
    }//end constructor()
    
    public void setErrors(String arg0,String arg1, String arg2){
        
    }//end setErrors
    
    public String getTemplate(){
        return IF_QUERY;
    }
}//end class RuleTemplate
