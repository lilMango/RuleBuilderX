package com.intuit.cg.tools.rules.utils;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mpaysan
 */
public class XsltBuilder {
    private ArrayList<Query> queryList;
    private RuleTemplate template;
    private String ruleName="";
    private String agency="";
    private String formName="";
    public XsltBuilder(String ruleName,String agency){
        template=new RuleTemplate();
        this.ruleName=ruleName;
        this.agency=agency;
        queryList=new ArrayList<Query>();
    }//end constructor(a,b)
    

    
    public XsltBuilder(){
        template = new RuleTemplate();
        queryList=new ArrayList<Query>();
    }//end constructor()
    //using 
    public XsltBuilder(String str){
    	this(); //calls XsltBuilder()
    	queryList.add(new Query(str));
    	
    }//end XsltBuilder(String)
    
    public void setRulename(String ruleName){
        this.ruleName = ruleName;
    }//end setRulename
    
    public String getRulename(){
        return this.ruleName;
    }//end getRulename

    public void setAgency(String agency){
        this.agency=agency;
    }//end setAgency
    
    public String getAgency(){
        return this.agency;
    }//end getAgency()

    public void setFormname(String formName){
    	this.formName=formName;
    }//end setFormname(String)
    
    public String getFormname(){
    	return this.formName;
    }//end getFormname()
    public String getXSLT(){
        String result = template.getTemplate();
        
        result = result.replace("$ruleName", ruleName);
        result = result.replace("$agency", agency);
        StringBuffer queries = new StringBuffer();
        
        boolean flag=false;
        for (Query q : queryList){
            if(!flag){
                flag=true;
                queries.append(q.statement);
            }else{
                queries.append(" "+q.conjunction+" "+ q.statement);
            }
        }//end for Query
        result = result.replace("$test",queries.toString());
        return result;
    }
    
    public void addQuery(boolean isAnd,String query){
        if(queryList.isEmpty()){//first item on list
            queryList.add(new Query(query));
        }else{
            if(isAnd){
                queryList.add(new Query("and",query));
            }else{
                queryList.add(new Query("or",query));
            }
        }
    }//end addQuery
    

    
    /*
     * findBeginTag
     * returns int: > 0 if it finds the nearest begin tag, else -1
     */    		
    private boolean findTag(String str,String line){
    	if(str!=null && str.trim().equalsIgnoreCase("")){
    		
    	}
    	
    	return false;
    }//end findTag(String)
  }//end class XSLTbuilder
