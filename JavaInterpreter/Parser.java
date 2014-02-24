

import java.io.FileNotFoundException;

public class Parser 
{
	private LexicalAnalyzer lex;
	/**
	 * @param fileName cannot be null
	 * @throws FileNotFoundException if file was not found
	 * @throws LexException 
	 * @throws IllegalArgumentException if fileName is null
	 * Check to see if fileName is empty if not create a LexicalAnalyzer object
	 */
	
	public Parser(String fileName) throws FileNotFoundException, LexException
	{
		if (fileName == null)
			throw new IllegalArgumentException("fileName is Empty in Parser");
		lex = new LexicalAnalyzer(fileName);
	}
	
	public Program parse() throws ParserException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.PROGRAM);
		tok = lex.getNextToken();
		match (tok, TokenType.IDENT);
		String id = tok.getLexeme();
		StatementList sList = getStatementList ();
		tok = lex.getNextToken();
		match (tok, TokenType.END);
		tok = lex.getNextToken();
		match (tok, TokenType.PROGRAM);
		tok = lex.getNextToken();
		match (tok, TokenType.IDENT);
		if (!id.equals(tok.getLexeme()))
			throw new ParserException ("invalid id", tok.getRowNumber(), tok.getColumnNumber());
		return new Program (sList);
	}
	
	
	
	public Expression parseExpression() throws ParserException
	{
		return getExpression();
	}

	private Expression getExpression() throws ParserException
	{
		Expression expr;
		Token tok = lex.getLookaheadToken();
		if (tok.getTokenType() == TokenType.IDENT)
		{
			tok = lex.getNextToken();		
			expr = new VariableExpression (tok.getLexeme().charAt(0));
		}
		else if (tok.getTokenType() == TokenType.INT_LIT)
		{
			tok = lex.getNextToken();
			expr = new ConstantExpression (Integer.parseInt(tok.getLexeme()));
		}
		else
		{
			ArithmeticOperator aOp = getArithmeticOperator();
			Expression expr1 = getExpression();
			Expression expr2 = getExpression();
			expr = new BinaryExpression (aOp, expr1, expr2);
		}
		return expr;
	}

	private ArithmeticOperator getArithmeticOperator() throws ParserException
	{
		ArithmeticOperator arOp;
		Token tok = lex.getNextToken();
		switch (tok.getTokenType())
		{
			case ADD_OP:
				arOp = ArithmeticOperator.ADD;
				break;
			case SUB_OP:
				arOp = ArithmeticOperator.SUB;
				break;
			case MULT_OP:
				arOp = ArithmeticOperator.MUL;
				break;
			case DIV_OP:
				arOp = ArithmeticOperator.DIV;
				break;
			default:
				throw new ParserException ("arithmetic operator expected", tok.getRowNumber(),
					tok.getColumnNumber());
		}
		return arOp;
	}


	
	private StatementList getStatementList() throws ParserException
	{
		StatementList sList = new StatementList();
		Statement s = getStatement();
		sList.add (s);
		Token tok = lex.getLookaheadToken();
		while (isValidStartOfStatement (tok))
		{
			s = getStatement();
			sList.add(s);
			tok = lex.getLookaheadToken();
		}
		return sList;
		
	}
	
	private Statement getStatement() throws ParserException
	{
		Statement s;
		Token tok = lex.getLookaheadToken();
		if (tok.getTokenType() == TokenType.IF_OP)
			s = getIfStatement();
		else if (tok.getTokenType() == TokenType.IDENT)
			s = getAssignmentStatement();
		else if (tok.getTokenType() == TokenType.DO_OP)
			s = getDoStatement();
		else if (tok.getTokenType() == TokenType.PRINT_OP)
			s = getPrintStatement();
		else
			throw new ParserException ("statement expected", tok.getRowNumber(), tok.getColumnNumber());
		return s;
	}
	private IfStatement getIfStatement() throws ParserException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.IF_OP);
		tok = lex.getNextToken();
		match (tok, TokenType.LEFT_PAREN);
		BooleanExpression expr = getBooleanExpression ();
		tok = lex.getNextToken();
		match (tok, TokenType.RIGHT_PAREN);
		tok = lex.getNextToken();
		match (tok, TokenType.THEN_OP);
		StatementList sList1 = getStatementList();
		tok = lex.getNextToken();
		match (tok, TokenType.ELSE_OP);
		StatementList sList2 = getStatementList();
		tok = lex.getNextToken();
		match (tok, TokenType.END);
		tok = lex.getNextToken();
		match (tok, TokenType.IF_OP);
		return new IfStatement (expr, sList1, sList2);
		
		
	}
	private BooleanExpression getBooleanExpression() throws ParserException
	{
		RelationalOperator rOp = getRelationalOperator();
		Expression expr1 = getExpression();
		Expression expr2 = getExpression();
		return new BooleanExpression (rOp, expr1, expr2);
	}
	private DoStatement getDoStatement() throws ParserException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.DO_OP);
		tok = lex.getNextToken();
		match (tok, TokenType.IDENT);
		char ch = tok.getLexeme().charAt(0);
		VariableExpression var = new VariableExpression (ch);
		tok = lex.getNextToken();
		match (tok, TokenType.ASSIGN_OP);
		tok = lex.getNextToken();
		match (tok, TokenType.INT_LIT);
		int first = Integer.parseInt(tok.getLexeme());
		tok = lex.getNextToken();
		match (tok, TokenType.COMMA_OP);
		tok = lex.getNextToken();
		match (tok, TokenType.INT_LIT);
		int last = Integer.parseInt(tok.getLexeme());
		StatementList sList = getStatementList();
		tok = lex.getNextToken();
		match (tok, TokenType.END);
		tok = lex.getNextToken();
		match(tok, TokenType.DO_OP);
		return new DoStatement (var, first, last, sList);
			
	}
		

	private PrintStatement getPrintStatement() throws ParserException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.PRINT_OP);
		tok = lex.getNextToken();
		match (tok, TokenType.LEFT_PAREN);
		Expression expr = getExpression();
		tok = lex.getNextToken();
		match (tok, TokenType.RIGHT_PAREN);
		return new PrintStatement (expr);
		
	}

	private AssignmentStatement getAssignmentStatement() throws ParserException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.IDENT);
		char ch = tok.getLexeme().charAt(0);
		VariableExpression var = new VariableExpression (ch);
		tok = lex.getNextToken();
		match (tok, TokenType.ASSIGN_OP);
		Expression expr = getExpression();
		return new AssignmentStatement (var, expr);
	}
	
	private RelationalOperator getRelationalOperator() throws ParserException
	{
		RelationalOperator rOp;
		
		Token tok = lex.getNextToken();
		switch (tok.getTokenType())
		{
			case LT_OP:
				rOp = RelationalOperator.LT;
				break;
			case LE_OP:
				rOp = RelationalOperator.LE;
				break;
			case GT_OP:
				rOp = RelationalOperator.GT;
				break;
			case GE_OP:
				rOp = RelationalOperator.GE;
				break;
			case EQ_OP:
				rOp = RelationalOperator.EQ;
				break;
			case NE_OP:
				rOp = RelationalOperator.NE;
				break;
			default:
				throw new ParserException ("relational operator expected", tok.getRowNumber(),
					tok.getColumnNumber());
		}
		return rOp;
	}
	
	//Check to see if tok is of the TokenType expected
	private void match(Token tok, TokenType expected) throws ParserException
	{
		assert tok != null;
		if (tok.getTokenType() != expected)
			throw new ParserException (expected.toString() + " expected", tok.getRowNumber(),
				tok.getColumnNumber());		
	}
	
	private boolean isValidStartOfStatement(Token tok)
	{
		assert tok != null;
		return tok.getTokenType() == TokenType.IDENT || tok.getTokenType() == TokenType.DO_OP ||
			tok.getTokenType() == TokenType.IF_OP || tok.getTokenType() == TokenType.PRINT_OP;
	}
}
