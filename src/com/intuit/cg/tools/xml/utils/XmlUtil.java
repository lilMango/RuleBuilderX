package com.intuit.cg.tools.xml.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/*
 * Class is used to parse all Xml like files (including XSLT,XSLT,) that has start and end tags
 * and can be converted to a node tree
 */
public class XmlUtil {
	
	 /**
	   * This will parse an XML stream and create a DOM document.
	   *
	   * @param is The stream to get the XML from.
	   * @return The DOM document.
	   * @throws IOException It there is an error creating the dom.
	   */
    public static Document parse( InputStream is ) throws IOException
	{
	      try
	      {
	          DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	          DocumentBuilder builder = builderFactory.newDocumentBuilder();
	          return builder.parse( is );
	      }
	      catch( Exception e )
	      {
	          IOException thrown = new IOException( e.getMessage() );
	          throw thrown;
	      }
	 }//end parse(InputStream)
    
    
}//end class XmlUtil
