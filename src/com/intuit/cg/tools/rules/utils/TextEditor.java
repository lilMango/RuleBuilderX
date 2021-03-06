package com.intuit.cg.tools.rules.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.javafx.sg.PGShape.Mode;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * TextEditor contains the syntax highlighted Text Area and save/write functionality
 */
public class TextEditor {
	static final String BEGIN_RULE="<xsl:if test=";//"<!-- Begin";
	static final String END_RULE="</xsl:if>";//"<!-- End"; 
	static final String END_COMMENT="</xsl:if>";//"-->";
	static final String BEGIN_COMMENT_RULE="<!-- Begin ";
	static final String END_COMMENT_RULE="<!-- End ";

    File file;
    private RSyntaxTextArea textArea;
    private RTextScrollPane rScrollPane;

    public boolean isSaved;
    
    public TextEditor(File file){
    	this.isSaved=true;
        this.file=file;
        textArea = new RSyntaxTextArea(60, 60);
        setSyntax();
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);
        rScrollPane = new RTextScrollPane(textArea);              
        rScrollPane.setFoldIndicatorEnabled(true);  
        
        textArea.addKeyListener(new KeyAdapter(){
         public void keyPressed(KeyEvent evt){
             if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S){
                 System.out.println("Saved called inside textArea!");
             }else{
                 isSaved=false;System.out.println("typed in textArea");
             }
         
         }//end keyPressed(KeyEvent)
         
         public void keyReleased(KeyEvent evt){
        	 System.out.println("Key Released!");
         }

     });//end textArea.addKeyListener()
    
    }//end constructor(File)
    
    public RSyntaxTextArea getTextArea(){
    	return this.textArea;
    }//end getTextArea()
    /*
     * TODO
     */
    public void setText(String str){
        textArea.setText(str);
    }//end setText(String)
   
    /*
     * updateRuleProps(String)
     * Update the closest rule to change the file name and or agency name
     */
    public void updateRuleProps(String argRuleName , String argAgency,boolean changeRuleName){
    	int prevCaretPos=this.textArea.getCaretPosition();
    	
    	int currLine=-1;
    	String lines[]=this.textArea.getText().split("\n");
    	
    	try {
			currLine=this.textArea.getLineOfOffset(this.textArea.getCaretPosition());

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			currLine=-1;
			return;
		}//end try-catch
    	
    	int beginTag=findBeginTag(lines,currLine);
    	int endTag=findEndTag(lines,currLine+1);
    	StringBuffer resultText=new StringBuffer();	

    	//test to see if cursor is within a rule already, if so try and append to its list of conditions
    	if(beginTag!=-1 && endTag!=-1){

    		//convert to string
    		if(changeRuleName){
    			if(lines[beginTag-1].contains("<!--"))
    				lines[beginTag-1]="<!-- Begin "+argRuleName+"-->";
    			if(lines[endTag+1].contains("<!--"));
    				lines[endTag+1]="<!-- End "+argRuleName+"-->";
    		}
    		
    		StringBuffer tempRule=new StringBuffer();
    		for(int i=beginTag;i<=endTag;i++){
    			tempRule.append(lines[i]+"\n");
			}//end for i
    		
 
    		
    		String updatedRule=tempRule.toString();
    		if(changeRuleName)
    			updatedRule=updatedRule.replaceAll("<xsl:attribute name=\"errorCode\">(.?)*</xsl:attribute>", "<xsl:attribute name=\"errorCode\">"+argRuleName+"</xsl:attribute>");
    		else
    			updatedRule=updatedRule.replaceAll("<xsl:attribute name=\"RejectingAgency\">(.?)*</xsl:attribute>", "<xsl:attribute name=\"RejectingAgency\">"+argAgency+"</xsl:attribute>");
    		
    		System.err.println("Updated:\n"+updatedRule);
            
   
				//=====================================================================/

			//Store previous stuff to a buffer
    		for(int i=0;i<beginTag;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//add new content
    		//resultText.append(lines[beginTag]);
    		resultText.append(updatedRule);
    		//resultText
    		//resultText.append(tempRule);
    		
    		//store latter stuff to buffer
    		for(int i=endTag+1;i<lines.length;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//print out buffer to textArea
    		this.textArea.setText(resultText.toString());
    		System.out.println("textWidth:"+this.textArea.getColumns()+"*"+lines.length+" = "+lines.length*this.textArea.getColumns());
    		System.out.println("prevCaretPos:"+prevCaretPos);
    		this.textArea.setCaretPosition(prevCaretPos);
    	}else{ //in a completely new area
    		int curPos=this.textArea.getCaretPosition();
    		XsltBuilder xbTemp=new XsltBuilder("");
    		xbTemp.setRulename(argRuleName);
    		this.textArea.insert(xbTemp.getXSLT(), curPos); 
    		this.textArea.setCaretPosition(curPos+100);
    	}//end if beginTag!=-1 && endTag!=-1
    }//end updateRuleProps(String)

    
    /*
     *  appendRule(String)
     *  Will find the closest rule to append the new test condition to
     *  if not, it will create a new rule from a template.
     *  args0:and/or condition
     *  args1:the new XSLT condition string to add
     *	returns void
     */
    public void appendRule(boolean isAnd,String argStr,String ruleName, String agency,String formName){
    	
    	String conjunction=isAnd?"and":"or";
    	int prevCaretPos=this.textArea.getCaretPosition();
    	
    	int currLine=-1;
    	String lines[]=this.textArea.getText().split("\n");
    	
    	try {
			currLine=this.textArea.getLineOfOffset(this.textArea.getCaretPosition());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			currLine=-1;
			return;
		}//end try-catch
    	int beginTag=findBeginTag(lines,currLine);
    	int endTag=findEndTag(lines,currLine+1);
    	StringBuffer resultText=new StringBuffer();
    	
    	//test to see if cursor is within a rule already, if so try and append to its list of conditions
    	if(beginTag!=-1 && endTag!=-1){
    		System.out.println("INSIDE a rule!");
     		StringBuffer tempRule=new StringBuffer();
    		for(int i=beginTag;i<=endTag;i++){
    			tempRule.append(lines[i]+"\n");
			}//end for i
    		
 
    		
    		String updatedRule=tempRule.toString();

    		Pattern p = Pattern.compile("<xsl:if test=\"(.*?)*\"");
            Matcher m = p.matcher(updatedRule);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
            	String test=m.group().substring(0,m.group().length()-1);
            	test=test.replace("$", "\\$");
            	
            	System.err.println("test:"+test);
            	//test=test.substring(1,test.length()-2);
                if(m.group().contains("test=\"\""))
                	m.appendReplacement(sb, test+argStr+"\"");
                else
                	m.appendReplacement(sb, test+" "+conjunction+" "+argStr+"\"");
            }
            m.appendTail(sb);
            updatedRule=sb.toString();
			
    		
			//=====================================================================/

			//Store previous stuff to a buffer
    		for(int i=0;i<beginTag;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//add new content
    		resultText.append(updatedRule);
    		
    		//store latter stuff to buffer
    		for(int i=endTag+1;i<lines.length;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//print out buffer to textArea
    		this.textArea.setText(resultText.toString());
    		this.textArea.setCaretPosition(prevCaretPos);
    	}else{ //in a completely new area
    		int curPos=this.textArea.getCaretPosition();
        	System.out.println("OUT!");
        	XsltBuilder xb= new XsltBuilder(argStr);
        	xb.setAgency(agency);
        	xb.setRulename(ruleName);
        	xb.setFormname(formName);
    		this.textArea.insert(xb.getXSLT(), curPos);         		
    		this.textArea.setCaretPosition(curPos+50);
    	}//end if beginTag!=-1 && endTag!=-1
    	    	
    }//appendRule(String)
    
   
    
    public void setIsSaved(boolean arg){
        isSaved=arg;
    }//end setIsSaved(bool)
    public boolean isSaved(){
        return this.isSaved;
    }
    /*
     * Write file to path and boldens the string name of the tab
     * @TODO
     */
    public void saveFile(){
        if(!isSaved){
            try {
            	textArea.setText(XsltEncoder.encode(textArea.getText()));
            	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(textArea.getText());
			bw.close();
 
			System.out.println("Done Saving...");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
            isSaved=true;
        }
    }//end saveFile()
    
    public void setCursorAtLine(int line){
    	try {
			this.textArea.setCaretPosition(this.textArea.getLineStartOffset(line));
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public RTextScrollPane getRTextScrollPane(){
        return this.rScrollPane;
    }//end getRTextScrollPane()
    
    public String getName(){
        return file.getName();
    }//end getName()
      

    
    /*
     * Helper for constructor
     */
    private String getFileExtension(){
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if(i>0){
            extension= file.getName().substring(i+1);
        }
        return extension;
    }//end getFileExtension()
    
    private void setSyntax(){
        String ext=getFileExtension();
        
        if("c".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
        }else if("cpp".equals(ext)||"hpp".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        }else if("css".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
        }else if("html".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);   
        }else if("java".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        }else if(".js".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        }else if("py".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        }else if("php".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
        }else if("rb".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
        }else {//if("xml".equals(ext)||"xslt".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        }
    
    }//end setSyntax()

    
    
    /*
     * Helper for appendRule
     * Finds the closest Begin Tag for a rule w.r.t. cursor
     */
    private int findBeginTag(final String[] lines,int currLine){
    	if(currLine==-1)return -1;
    	
    	int i=currLine;
    	if(i>=lines.length){
    		i=lines.length-1;
    	}
    	
		//find beginning of xml rule
		for(	;i>=0;i--){
			if(lines[i].contains(END_RULE)){
				return -1;
			}else if(lines[i].contains(BEGIN_RULE)){
				System.out.println("<!-- Begin @ line:"+i);
				//argTA.replaceRange("I WILL WAIT\t",10,250);
				
				return i;
			}
		}//end for i
    	
    	return -1;
    }//end findBeginTag(String[],int)
    
    /*
     * Helper for appendRule
     * Finds the closest End Tag for a rule w.r.t. cursor
     */
    private int findEndTag(final String[] lines, int currLine){    
       	if(currLine==-1)return -1;
    	
    	int j=currLine;
    	
		//Find the end of the xmlrule
		for(	; j<lines.length;j++){
			if(lines[j].contains(BEGIN_RULE)){
				return -1;
			}else if(lines[j].contains(END_RULE)){
				System.out.println("End--> @ line:"+j);
				return j;
			}
		}//end for j
    	System.out.println("----");
    	return -1;
    }//end findEndTag(String[],int)
    
  
}//end class Editor
