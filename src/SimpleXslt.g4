grammar SimpleXslt;

//basically work like enums, since token assignments are deprevated in v4.  tokens { OR='or'; AND, TRUE, FALSE, VAR}	


mystart	:
	| assignment
	| expr

	;
	
assignment	:
	'var' ID T_EQ expr
	;


/*designed this way so that "A+B+C+D" is parsed as "((A+B)+C)+D" aka left associative or left recursive*/
expr	:
	expr ('or'|T_OR) expr1
	|	expr1
	;

expr1	:
	expr1 ('and'| T_AND) expr2
	|	expr2
	;

expr2	:
	expr2 equality expr3
	|	expr3
	;
	
expr3 :
	expr3 relation expr4
	|	expr4
	;
	
expr4 :
	expr4 addOp expr5
	| expr5
	;

expr5 : 
	expr5 mulOp atom
	| atom
//	| RESERVED_WORDS
	;	
	
equality	:
	T_EQ | T_NEQ
	;

relation	:
	T_LTE | T_LT | T_GTE | T_GT
	;

addOp	:
	T_PLUS 
	| T_MINUS
	;

mulOp	:
	T_STAR
	|  T_SLASH
	;
	
atom:
	T_LPAREN expr T_RPAREN 
	| NUMBER 				
	| ID 
	| StringLiteral
	| RESERVED_WORDS
	;

/*DECLARED HERE EARLY TO NOT BE CONFUSED AS KEYWORDS
tokens written earlier, won't be interpreted as a simple string but as the keyword
*/
RESERVED_WORDS:
	'true' | 'false' | 'var'
	;
T_OR : 'or' | '||' ;
T_AND: 'and' | '&&';
	
StringLiteral	:
	'"' (~('\\'|'"'))* '"'
	|'\'' (~('\\'|'"'))* '\''
	
	;
	
/*Naming conventions */
ID : T_LSQUIGBRACE (NAMECHAR)(NAMECHAR | DIGIT)* T_RSQUIGBRACE ;// ([^'-']$) ,not ending with '-'


fragment NAMECHAR : 
	  LETTER 
	| '\\' | '_' | T_SLASH | '-' | '.'
	;

fragment LETTER : [a-zA-Z];

NUMBER :
     (DIGIT*)DECIMAL(DIGIT)+
   | (DIGIT)+DECIMAL(DIGIT*)
   | (DIGIT)+ 
   ;
   
DIGIT: [0-9];
fragment DECIMAL: '.';


T_LBRACE :'[';
T_RBRACE :']';
T_LPAREN :'(';
T_RPAREN : ')';
T_LSQUIGBRACE : '{';
T_RSQUIGBRACE : '}';


T_PLUS  : '+' ;
T_MINUS : '-' ;
T_STAR : '*' ;
T_SLASH : '/' ;

T_EQ : '=' ;
T_NEQ : '!=' ;
T_LTE : '<=' ;
T_LT : '<' ;
T_GTE : '>=' ;
T_GT : '>' ;



WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
