




import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.print.PrintException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.TestRig;

import com.intuit.cg.lang.simplexslt.ErrorSTO;
import com.intuit.cg.lang.simplexslt.STO;

public class SimpleXsltCompiler {

	public static final String LEXER_START_RULE_NAME = "tokens";

	protected String grammarName = "SimpleXslt"; //*.g4 grammar file
	protected String startRuleName = "mystart";  //the first rule to start with in that *.g4 grammar file
	protected String inputString;
	protected final List<String> inputFiles = new ArrayList<String>();
	protected boolean printTree = false;
	protected boolean gui = false;
	protected String psFile = null;
	protected boolean showTokens = false;
	protected boolean trace = false;
	protected boolean diagnostics = false;
	protected String encoding = null;
	protected boolean SLL = false;
	
	public SimpleXsltCompiler(String[] args) throws Exception {

		int i=0;
		while ( i<args.length ) {
			String arg = args[i];
			i++;

			if ( arg.equals("-tree") ) {
				printTree = true;
			}
			if ( arg.equals("-gui") ) {
				gui = true;
			}
			if ( arg.equals("-tokens") ) {
				showTokens = true;
			}
			else if ( arg.equals("-trace") ) {
				trace = true;
			}
			else if ( arg.equals("-SLL") ) {
				SLL = true;
			}
			else if ( arg.equals("-diagnostics") ) {
				diagnostics = true;
			}
			else if ( arg.equals("-encoding") ) {
				if ( i>=args.length ) {
					System.err.println("missing encoding on -encoding");
					return;
				}
				encoding = args[i];
				i++;
			}
			else if ( arg.equals("-ps") ) {
				if ( i>=args.length ) {
					System.err.println("missing filename on -ps");
					return;
				}
				psFile = args[i];
				i++;
			}
		}//end while
	}//end constructor	
	


	/*parsing a single string*/
	public void processString(String arg) throws Exception {
//		System.out.println("exec "+grammarName+"."+startRuleName);
		String lexerName = grammarName+"Lexer";
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Class<? extends Lexer> lexerClass = null;
		try {
			lexerClass = cl.loadClass(lexerName).asSubclass(Lexer.class);
		}
		catch (java.lang.ClassNotFoundException cnfe) {
			// might be pure lexer grammar; no Lexer suffix then
			lexerName = grammarName;
			try {
				lexerClass = cl.loadClass(lexerName).asSubclass(Lexer.class);
			}
			catch (ClassNotFoundException cnfe2) {
				System.err.println("Can't load "+lexerName+" as lexer or parser");
				return;
			}
		}

		Constructor<? extends Lexer> lexerCtor = lexerClass.getConstructor(CharStream.class);
		Lexer lexer = lexerCtor.newInstance((CharStream)null);

		Class<? extends Parser> parserClass = null;
		Parser parser = null;
		if ( !startRuleName.equals(LEXER_START_RULE_NAME) ) {
			String parserName = grammarName+"Parser";
			parserClass = cl.loadClass(parserName).asSubclass(Parser.class);
			if ( parserClass==null ) {
				System.err.println("Can't load "+parserName);
			}
			Constructor<? extends Parser> parserCtor = parserClass.getConstructor(TokenStream.class);
			parser = parserCtor.newInstance((TokenStream)null);
		}

		if(arg.length()>0){
			InputStream is = new ByteArrayInputStream(arg.getBytes());
			Reader r;
			
			if ( encoding!=null ) {
				r = new InputStreamReader(is, encoding);
			}
			else {
				r = new InputStreamReader(is);
			}

			process(lexer, parserClass, parser, is, r);
			return;
		}//end if inputString.length>0
	
	}//end processString(String)
	
	
	public void processFile(String argFile) throws Exception {
//		System.out.println("exec "+grammarName+"."+startRuleName);
		String lexerName = grammarName+"Lexer";
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Class<? extends Lexer> lexerClass = null;
		try {
			lexerClass = cl.loadClass(lexerName).asSubclass(Lexer.class);
		}
		catch (java.lang.ClassNotFoundException cnfe) {
			// might be pure lexer grammar; no Lexer suffix then
			lexerName = grammarName;
			try {
				lexerClass = cl.loadClass(lexerName).asSubclass(Lexer.class);
			}
			catch (ClassNotFoundException cnfe2) {
				System.err.println("Can't load "+lexerName+" as lexer or parser");
				return;
			}
		}

		Constructor<? extends Lexer> lexerCtor = lexerClass.getConstructor(CharStream.class);
		Lexer lexer = lexerCtor.newInstance((CharStream)null);

		Class<? extends Parser> parserClass = null;
		Parser parser = null;
		if ( !startRuleName.equals(LEXER_START_RULE_NAME) ) {
			String parserName = grammarName+"Parser";
			parserClass = cl.loadClass(parserName).asSubclass(Parser.class);
			if ( parserClass==null ) {
				System.err.println("Can't load "+parserName);
			}
			Constructor<? extends Parser> parserCtor = parserClass.getConstructor(TokenStream.class);
			parser = parserCtor.newInstance((TokenStream)null);
		}
		
		if(argFile.length()>0){
			
			InputStream is = new FileInputStream(argFile);
			Reader r;
			
			if ( encoding!=null ) {
				r = new InputStreamReader(is, encoding);
			}
			else {
				r = new InputStreamReader(is);
			}

			process(lexer, parserClass, parser, is, r);
			return;
			
		}
		
	}//end processFile(String argFile)
	
	
	protected void process(Lexer lexer, Class<? extends Parser> parserClass, Parser parser, InputStream is, Reader r) throws IOException, IllegalAccessException, InvocationTargetException, PrintException {
		try {
			ANTLRInputStream input = new ANTLRInputStream(r);
			lexer.setInputStream(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			tokens.fill();

			if ( showTokens ) {
				for (Object tok : tokens.getTokens()) {
					System.out.println(tok);
				}
			}

			if ( startRuleName.equals(LEXER_START_RULE_NAME) ) return;

			if ( diagnostics ) {
				parser.addErrorListener(new DiagnosticErrorListener());
				parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
			}

			if ( printTree || gui || psFile!=null ) {
				parser.setBuildParseTree(true);
			}

			if ( SLL ) { // overrides diagnostics
				parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
			}

			parser.setTokenStream(tokens);
			parser.setTrace(trace);

			try {
				Method startRule = parserClass.getMethod(startRuleName);
				ParserRuleContext tree = (ParserRuleContext)startRule.invoke(parser, (Object[])null);

				if ( printTree ) {
					System.out.println(tree.toStringTree(parser));
				}
				if ( gui ) {
					tree.inspect(parser);
				}
				if ( psFile!=null ) {
					tree.save(parser, psFile); // Generate postscript
				}
			}
			catch (NoSuchMethodException nsme) {
				System.err.println("No method for rule "+startRuleName+" or it has arguments");
				System.err.println("ZOMGORZS");
			}
		}
		finally {
			if ( r!=null ) r.close();
			if ( is!=null ) is.close();
		}
	}//end process(Lexer,parser,is,r)
	
	/*
	 * Parameters: String - Simple Xslt string or anything that goes into query box
	 * Returns: String - XSLT translated
	 */
	public String translateToXslt(String arg)throws Exception{
		//getting CharStream of input string
		InputStream is = new ByteArrayInputStream(arg.getBytes());
		Reader r;		
		r = new InputStreamReader(is);
		
		ANTLRInputStream input=null;
		try {
			input = new ANTLRInputStream(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleXsltLexer lexer= new SimpleXsltLexer(input);
		//lexer.setInputStream(input);
		
		//Parser feeds off tokens to make of language constructs
		SimpleXsltParser parser= new SimpleXsltParser(new CommonTokenStream(lexer));
		parser.setErrorHandler(new BailErrorStrategy());
		parser.addErrorListener(new DiagnosticErrorListener());
		
		//Create subtree starting at rule 'mystart'
		ParseTree tree = parser.mystart();
		XsltEmitter xsltEmitter=new XsltEmitter();
		/*ParseTreeWalker.DEFAULT.walk(xsltEmitter,tree);//**USES LISTENERS
		return xsltEmitter.getXslt(tree);
		*/
		XsltVisitor v = new XsltVisitor();
		STO fw=v.visit(tree);
		System.out.println("===@translateToXslt "+fw.getName());
		if(!(fw instanceof ErrorSTO))
			return fw.getName();
		else
			return "";
	}//end translateToXslt(String)
	
	/*
	public static void main(String[] args) throws Exception{
		
		SimpleXsltCompiler myTestRig= new SimpleXsltCompiler(args);
		if(args.length >=1){
			try{
			//myTestRig.processFile("input.txt");
			myTestRig.processString("{reddfe-fve540}-{hi}+{/4342-yoammoma/CA-Return540}");
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("ffef");
			}//end try-catch
			
		}
	}//end main
	*/
}//end class SimpleXsltCompiler