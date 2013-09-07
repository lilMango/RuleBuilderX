Welcome to the user's guide to RuleBuilder: The interface for writing business rules to validate TurboTax forms.
For developers, please view the README_PROGRAMMER

Variables:
All field names must be wrapped by squiggly braces. ie. {var}, {folder1/folder2}

Operations:
Can use comparative and arithematic operations on variables or constants
ie. {varA} < {varB/derp}   translates to			varA &lt; varB/:*derp
ie.	2 < 3
if you simply type in a variable and compile, it is checking the existance of that variable
ie. {varA}				translates to		exists(varA)


Some operations can automatically detect when to properly cast a variable
ie. {varA} >= 2			translates to 		xs:decimal(varA) &gte; 2

There is constant folding, where constant numbers will be evaluated
ie. {varA} < (3+7)/2		translates to	 xs:decimal(varA) &lt; 5




Smart cursor: 
Will automatically append the new rule to the closest business rule that the cursor
Can also edit the closest rule's name or agency.
If you enter a rule name or agency in the top editor, it will automatically create boilerplate XSLT code

