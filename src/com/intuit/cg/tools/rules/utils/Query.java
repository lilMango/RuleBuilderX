package com.intuit.cg.tools.rules.utils;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mpaysan
 */
public class Query {
    public String conjunction="";
    
    public String statement="";
    
    
    public Query(String statement){
        this.conjunction="";
        this.statement=statement;
    }//end Query(statement)
    
    public Query(String conjunction, String statement){
        this.conjunction = conjunction;
        this.statement = statement;
    }//end Query(conjunction, statement)
    
}//end class Query
