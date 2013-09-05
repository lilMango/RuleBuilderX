package com.intuit.cg.lang.simplexslt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Used to define and set all strings that must be encoded to their respective xslt encoding
 */
public class XsltEncoder {
	public static String encode(String input){
		Pattern p = Pattern.compile("xsl:if test=\"(.*?)(>=|>|<=|<)+(.*?)\"");
        Matcher m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
        	String test=m.group();
        	//test=test.substring(1,test.length()-2);
        	test=doReplacements(test);
            m.appendReplacement(sb, test);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
        
		
		return sb.toString();
	}
	
	/*
	 * Define all xslt encodings heerre.
	 * Order matters
	 */
	private static String doReplacements(String preEnc){
		String tmp=preEnc;
		tmp=tmp.replaceAll("<=","&lte;");
		tmp=tmp.replaceAll("<","&lt;");
		tmp=tmp.replaceAll(">=","&gte;");
		tmp=tmp.replaceAll(">","&gt;");
		
		return tmp;
	}
	
}//end class XsltEncoder
