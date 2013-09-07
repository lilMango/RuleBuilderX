package com.intuit.cg.tools.filesystem;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/*
 * Is used to define Xslt Rule Navigator Tree
 */
public class RuleIndex {
	private String ruleName;
	private int lineNum;
	
	public RuleIndex(String ruleName,int lineNum){
		this.ruleName=ruleName;
		this.lineNum=lineNum;
	}
	
	public String getName(){
		return this.ruleName;
	}
	
	public int getLine(){
		return this.lineNum;
	}
	
	public String toString(){
		return "["+(getLine()+1)+"] "+getName();
	}
	public static Vector<RuleIndex> listRules(String input){

		Vector<RuleIndex> vec=new Vector<RuleIndex>();
		String lines[]=input.split("\n");
		
		for(int i=0;i<lines.length;i++){
			//System.out.println(lines[i]);
			if(lines[i].trim().matches("<xsl:attribute name=\"errorCode\">(.?)*</xsl:attribute>")){
				
				String tmp=lines[i].trim();
				String tmpA[]=tmp.split("<xsl:attribute name=\"errorCode\">");
				String name=tmpA[1].split("</xsl:attribute>")[0];
				//System.out.println("found: "+name);
				vec.addElement(new RuleIndex(name,i));
			
			}
		}
		
		
		return vec;
	}
}//end class XsltTreeModel
