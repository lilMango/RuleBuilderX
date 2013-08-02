
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mpaysan
 */
public class XSLTbuilder {
    private ArrayList<Query> queryList;
    private RuleTemplate template;
    private String ruleName="";
    private String agency="";
    public XSLTbuilder(String ruleName,String agency){
        template=new RuleTemplate();
        this.ruleName=ruleName;
        this.agency=agency;
        queryList=new ArrayList<Query>();
    }//end constructor(a,b)
    
    public XSLTbuilder(){
        template = new RuleTemplate();
        queryList=new ArrayList<Query>();
    }//end constructor()

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
    
    public String getXSLT(){
        String result = template.getTemplate();
        
        result = result.replace("$ruleName", ruleName);
        
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
    
}//end class XSLTbuilder
