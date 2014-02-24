


public class Token 
{
	private TokenType tok;
	private String lexeme;
	private int rowNum;
	private int columnNum;
	
	
//check if token type is empty , lexeme , rowNum, columNum invalid
	public Token(TokenType tok, String lexeme, int rowNum, int columnNum)
	{
		this.tok = tok;
		if (lexeme == null)
			throw new IllegalArgumentException ("null string argument");
		if (lexeme.length() == 0)
			throw new IllegalArgumentException ("empty lexeme argument");
		this.lexeme = lexeme;
		if (rowNum <= 0)
			throw new IllegalArgumentException ("invalid line number");
		this.rowNum = rowNum;
		if (columnNum <= 0)
			throw new IllegalArgumentException ("invalid column number");
		this.lexeme = lexeme;
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.tok = tok;
	}


	public TokenType getTokenType()
	{
		return tok;
	}


	public String getLexeme()
	{
		return lexeme;
	}


	public int getRowNumber()
	{
		return rowNum;
	}


	public int getColumnNumber()
	{
		return columnNum;
	}


	

}
