Interpreter
===========

Interpreter for a simple Fortran language written in Java, Python, and Ada


---------------------------------------------------------------------------



Interpreter-

This is a interpreter for a minimal form of FORTRAN. This minimal form of FORTRAN has only 1 data type, integer, 
and the only identifiers are single letters 

The interpreter parses the FORTAN program and builds some intermediate data structures. 
These data structures will then be interpreted to execute the program. All tokens in this language are separated by white space.
The parsing algorithm should detect any syntactical or semantic error. The first such error discovered should cause an appropriate error message to be printed, and then the interpreter should terminate. Run-time errors should also be detected with appropriate error messages being printed.

Grammar:

Lexical Analyzer
id → letter
literal_integer → digit literal_integer | digit
assignment_operator → =
le_operator → <=
lt_operator → <
ge_operator → >=
gt_operator → >
eq_operator → ==
ne_operator → /=
add_operator → +
sub_operator → -
mul_operator → *
div_operator → /
