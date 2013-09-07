==================================================================================================================
Simple Xslt: THe simplified xslt language
==================================================================================================================

Installation:
1) download the ANTL4-complete.jar
2) setup eclipse IDE by just adding an "External JAR" under Project > Properties > Java Build Path
3) run Hello example grammar


Grammar Rules (*.g4 files)
-It's a left recursive grammar, meaning equal ranked syntaxes will parse left to right. For example A+B+C+D  is parsed as ((A+B)+C)+D

-The more parents a node has, generally will have higher precedence. In other words, generally, the bottom of the file has more atomic pieces or atoms of higher precedence. This is where you see something wrapped in parenthesis, or the ID of a variable/field name since these are high precedence pieces.
 As you progress upward you get more complex rules and lower precedence rules. 
Notice how PEMDAS is written from the bottom-up.

-rerun Antlr on *.g4 every time you change the grammar file.
Arguments: "antlr4 -package <package route> -visitor"


The Language:
ID's are wrapped in {},squiggly braces.

---------------------------------------------------------
Quick use of translator (returns XSLT String):<SimpleXSLTCompiler.java>
-processString(String simpleXsltString)
-processFile(String filename)
	-load the Run configuration to have the Program arguments "-gui" or "-tree"
-translateToXslt(String simpleXsltString) - returns translated XSLT code as String

Where the XsltTranslations take place: <TheSimpleXsltWalker.java>
-Used to define the action code after the input has been compiled as valid syntax	

-------------------------------------------------------------------------

Quick Start example of Antlr Grammar

1)Create a grammar file  (*.g4)
2)run "antlr4 <filename>.g4" at the command line. (assuming you did doskey for "java org.antlr.v4.Tool")
3)javac <filename>*.java
4)grun <filename> <first rule name in the grammar file> <-gui>
5)type standard in
6)EOF by pressing ctrl-z followed by enter


Problems
 The SimpleXslt* files and grammar can't be in a package


----------------------------------------------------------
STOs (Symbol Table Objects) are what the simple Xslt expressions are broken down into. 
Ex. "{varA} < 2" will be broken down into "ExprSTO operator ConstantSTO". Check TheSimpleXsltWalker.java for more info

-------------------------------------------------------------------------------
What needs to be done:
-Add more action code (TheSimpleXsltWalker.java) to do casting inferences to some variables
-modify grammar so you don't have to wrap squiggly braces({) around variable names. 
	It was done this way because some variable names had backslash directory names that conflicted with the division operator
-Term Helper feature:
	-Will auto suggested business rule related terms and give simple xslt suggestions

==================================================================================================================
Code Repository:	https://github.com/lilMango/RuleBuilderX
==================================================================================================================


==================================================================================================================
RuleBuilder app features:
1) A simplified Xslt language and parser that will convert into XSLT code
2) Working directory dropdowns
3) File navigator
4) Xslt Rule navigator
5) Editable textAreas with closable tabs
6) Syntax highlighting for most languages( XML,Xslt, Java, HTML,CSS, etc..)
7) Agency rule validations on Turbo Tax form input
==================================================================================================================

 