package com.intuit.fsapp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.intuit.cg.fs.common.FileUtil;
import com.intuit.cg.fs.tools.fsapp.ui.RulesDialog;
import com.intuit.cg.fs.tools.fsapp.util.RulesObjectList;
import com.intuit.cg.fs.tools.fsapp.util.XslTransformer;

public class Validator {

public static RulesDialog validate(){
	List agencyFilePath = new ArrayList();
	List agencyList = new ArrayList();
	List agencySchemaPath = new ArrayList();
	List agencyErrors = new ArrayList();
	List<RulesObjectList> rulesObj = new ArrayList<RulesObjectList>();
    String xmlOutput = "";        

     

        //String filename = FileUtil.getFileNameWithoutExtension(input.getName());
    String rulesPath ="c:\\ty13\\mpaysan_V07C294E23E1_3892_ty13\\content\\services\\ef\\rules\\trunk\\mef\\src\\main\\resources\\rules\\";// localRulesBrowse.getFileLocation();
    File fcsDir=new File("C:\\13work\\13per\\output");
    File targetFolder = new File("C:\\13work\\13per\\output");//fcsDir;// TurboTax files-ish
    File[] listOfFiles = targetFolder.listFiles();

    //Figure out what state agency to select/test
  //Figure out what state agency to select/test
	for(int i=0; i < listOfFiles.length; i++)
	{
		File file = listOfFiles[i];
		if(file.isFile() && (file.getName().endsWith("FILING.xml") || (file.getName().endsWith("EXTENSION.xml"))))
		{
			String strFile = file.toString();
			//JOptionPane.showMessageDialog(null, strFile);
			String[] strParts = strFile.split("-");
			
			agencyFilePath.add(strFile);
			//Add the agency to agencyList
			agencyList.add(strParts[2]);
			
			//Read the file and extract applicable schema name
	    	try 
	    	{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new File(strFile));
				
				doc.getDocumentElement().normalize();
				
				Element parentNode = doc.getDocumentElement();
				String schemaLocation = parentNode.getAttribute("xsi:schemaLocation");
				String[] schemaLocationArray = schemaLocation.split("\\\\");
				
				String transformFileName = schemaLocationArray[schemaLocationArray.length - 1];
				transformFileName = transformFileName.replace(".xsd", ".xsl");//xsl is form the rule is
				agencySchemaPath.add(transformFileName);
			} 
	    	catch (ParserConfigurationException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	catch (SAXException e) 
	    	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//end for i
		
	for(int x=0; x < agencyList.size(); x++)
	{
		XslTransformer transformObject = new XslTransformer();    			
        ByteArrayOutputStream transformOutput = new ByteArrayOutputStream();
		File xslFilePath = new File(rulesPath + "\\" + agencyList.get(x).toString() + "\\" + agencySchemaPath.get(x).toString());
		File xmlFilePath = new File(agencyFilePath.get(x).toString());
		
		if(xslFilePath.exists() && xmlFilePath.exists())
		{
			System.out.println("Transformer Path: " + xslFilePath.toString());
			System.out.println("Transform File Path: " + xmlFilePath.toString());
			try 
        	{	
	    transformObject.process(xslFilePath, xmlFilePath, transformOutput); //Magic happens here. EXTRACT THIS BARE FUNCTIONALITY AND PRESTO!! TODO
				
				agencyErrors.add(transformOutput.toString());

				System.out.println("Transformed Output: " + transformOutput.toString());
				xmlOutput += "<File path=\"" + agencyFilePath.get(x).toString() + "\" agency=\"" + agencyList.get(x).toString() + "\" appliedSchema=\"" 
								+ agencySchemaPath.get(x).toString() + "\">\n" + transformOutput.toString() + "\n</File>\n";  
			} catch (TransformerException e) {
				//e.printStackTrace();
				System.out.println("Transformation Error");
				agencyErrors.add(transformObject.getError());    				
			}	    				
		}
		else if(!xslFilePath.exists())
		{
			System.err.println("Could not find the transformer file:" + xslFilePath.toString());
			agencyErrors.add("Transformer File" + xslFilePath.toString() + "does not exist.");
		}
	}
	

	for(int y=0; y < agencyFilePath.size(); y++)
	{
		rulesObj.add(new RulesObjectList(agencyFilePath.get(y).toString(), agencyList.get(y).toString(), agencySchemaPath.get(y).toString(), agencyErrors.get(y).toString()));
	}//end for y
	
	RulesDialog rulesDialog = new RulesDialog(rulesObj);
	rulesDialog.pack();
	rulesDialog.setLocation(10,10);
	rulesDialog.setSize(800, 600);
	rulesDialog.setVisible(true);
	return rulesDialog;
}//end validate()

}//end class Validator
