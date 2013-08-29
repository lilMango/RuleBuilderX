package com.intuit.cg.tools.rules.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.Icon;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mpaysan
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
         
         public void keyReleased(KeyEvent evt){}

     });//end textArea.addKeyListener()
        rScrollPane.addKeyListener(new KeyAdapter(){
         public void keyPressed(KeyEvent evt){
             if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S){
                 System.out.println("Saved called inside ScrollPane!");
            }
         
         }//end keyPressed(KeyEvent)
         
         public void keyReleased(KeyEvent evt){}
         public void keyType(KeyEvent evt){System.out.println("type in ScrollArea);");}
     });//end rScrollPane.addKeyListener()
        
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
    public void updateRuleProps(String argStr){
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
    		lines[beginTag-1]="<!-- Begin "+argStr+"-->";
    		lines[endTag+1]="<!-- End "+argStr+"-->";
    		StringBuffer tempRule=new StringBuffer();
    		for(int i=beginTag;i<=endTag;i++){
    			tempRule.append(lines[i]+"\n");
			}//end for i
    		
    		
    		//string -> XML dom
    		DocumentBuilder db;
    		Document doc=null;
			try {
				db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           		InputSource is = new InputSource();
        		is.setCharacterStream(new StringReader(tempRule.toString()));
        		try {
					doc = db.parse(is);
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//end try-catch

    		//add new condition to test attribute
			//TODO Consider using Apache XmlBeans and XmlCursor
			if(doc!=null){
				doc.getDocumentElement().normalize();
				

				Node xslTag= doc.getFirstChild();
				//System.out.println("xsl : "+xslTag.getNodeName());
				Node errorTag = xslTag.getChildNodes().item(1);
				Node xAttr1Tag = errorTag.getChildNodes().item(1);
				Node errCode=xAttr1Tag.getFirstChild();
				
				if(errCode!=null){
					errCode.setNodeValue(argStr);
		
				}else{
					xAttr1Tag.setTextContent(argStr);
				}
				//TODO add argStr to errorCode!!

			}else{
				return;
			}			

			//Update rule: XML dom -> string
			String updatedRule="";
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				StringWriter writer = new StringWriter();
				
				try {
					transformer.transform(new DOMSource(doc), new StreamResult(writer));
					updatedRule= writer.getBuffer().toString();
					
				} catch (TransformerException e) {
	
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
				//=====================================================================/

			//Store previous stuff to a buffer
    		for(int i=0;i<beginTag;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//add new content
    		//resultText.append(lines[beginTag]);
    		resultText.append("   "+updatedRule+"\n");
    		//resultText
    		//resultText.append(tempRule);
    		
    		//store latter stuff to buffer
    		for(int i=endTag+1;i<lines.length;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//print out buffer to textArea
    		this.textArea.setText(resultText.toString());
    		this.textArea.setCaretPosition(prevCaretPos);
    	}else{ //in a completely new area
    		int curPos=this.textArea.getCaretPosition();
    		XsltBuilder xbTemp=new XsltBuilder("");
    		xbTemp.setRulename(argStr);
    		this.textArea.insert(xbTemp.getXSLT(), curPos);         		
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
    		//convert to string
    		StringBuffer tempRule=new StringBuffer();
    		for(int i=beginTag;i<=endTag;i++){
    			if(i==beginTag && !lines[i].startsWith(BEGIN_RULE)){//trimming the beginning line so Begin tag will be parsed only
    				System.out.println("Need to trim Beginning!");
    				int idx=lines[i].indexOf(BEGIN_RULE);
					tempRule.append(lines[i].substring(idx)+"\n");
    			
    			}else if(i==endTag && !lines[i].endsWith(END_RULE)){//trimming the end line so End tag will only be parsed
    				//int idx=lines[i].lastIndexOf(END_RULE);
    				int idx2=lines[i].lastIndexOf(END_COMMENT);
    				tempRule.append(lines[i].substring(0,idx2+END_COMMENT.length()));
    				System.out.println("Needs to trim END!");
    			}else{
    				tempRule.append(lines[i]+"\n");
    			}
			}//end for i

    		
    		//string -> XML dom
    		DocumentBuilder db;
    		Document doc=null;
			try {
				db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           		InputSource is = new InputSource();
        		is.setCharacterStream(new StringReader(tempRule.toString()));
        		try {
					doc = db.parse(is);
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//end try-catch

    		//add new condition to test attribute
			//TODO Consider using Apache XmlBeans and XmlCursor
			if(doc!=null){
				doc.getDocumentElement().normalize();
				
				String before=doc.getDocumentElement().getAttribute("test");
				if("".equals(before)){
					doc.getDocumentElement().setAttribute("test",argStr);
				}else{
					doc.getDocumentElement().setAttribute("test",before+" "+conjunction + " " + argStr );
				}

			}else{
				return;
			}			
			
			//Update rule: XML dom -> string
			String updatedRule="";
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				StringWriter writer = new StringWriter();
				
				try {
					transformer.transform(new DOMSource(doc), new StreamResult(writer));
					updatedRule= writer.getBuffer().toString();
					
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("UpdatedRule: "+updatedRule);
			System.out.println("After: "+doc.getDocumentElement().getAttribute("test"));
			//=====================================================================/

			//Store previous stuff to a buffer
    		for(int i=0;i<beginTag;i++){
    			resultText.append(lines[i]+"\n");
    		}//end for i
    		
    		//add new content
    		resultText.append("   "+updatedRule+"\n");
    		
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
    	}//end if beginTag!=-1 && endTag!=-1
    	    	
    }//appendRule(String)
    
    
    /*
     *  Open and read file contents
     */
    public void readFile(File file){
        
    }//end readFile(File)
    
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
