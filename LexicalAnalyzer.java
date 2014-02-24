
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer 
{
	private List<Token> tokens;
	
	/**
	 * @param fileName - cannot be null
	 * @throws FileNotFoundException if file was not found
	 * @throws LexException 
	 * @throws IllegalArgumentException if fileName is null
	 */
	
	public LexicalAnalyzer(String fileName) throws LexException, FileNotFoundException
	{
		//Check for filename empty
		if (fileName == null)
			throw new IllegalArgumentException ("fileName is Empty");
		
		tokens = new LinkedList<Token>();
		Scanner scan = new Scanner(new File(fileName));
		
		
		int rowNum = 0;
		while (scan.hasNext())
		{
			String line = scan.nextLine();
			rowNum++;
			processLine(line, rowNum);
			
		}
		
		tokens.add(new Token (TokenType.EOF, "@", rowNum + 1, 1));
		scan.close();
	}
	
	//traverse input and create tokens
	private void processLine(String line, int rowNum) throws LexException
	{
		assert line != null;
				
		if(line == null || rowNum < 0)
			throw new IllegalArgumentException ("Either line or rowNum is illegal in processLine");
		
		int index = 0;
		index = skipWhiteSpace(line, index);
		while(index < line.length())
		{
			String lexeme = getLexeme (line, index, rowNum);
			TokenType tokType = getTokenType (lexeme, rowNum, index);
			tokens.add(new Token (tokType, lexeme, rowNum,index+1));
			index += lexeme.length();
			index = skipWhiteSpace(line, index);
			
		}
		
		
	}
	
	private TokenType getTokenType(String lexeme, int rowNum, int columnNum) throws LexException
	{
		assert lexeme != null;
		assert lexeme.length() > 0;
		assert rowNum >= 1;
		assert columnNum >= 1;
		TokenType tokType = TokenType.EOF;
		lexeme = lexeme.toLowerCase();
		if (Character.isLetter(lexeme.charAt(0)))
			if (lexeme.length() == 1)
				tokType = TokenType.IDENT;
			else
				switch (lexeme)
				{
					case "program":
						tokType = TokenType.PROGRAM;
						break;
					case "end":
						tokType = TokenType.END;
						break;
					case "if":
						tokType = TokenType.IF_OP;
						break;
					case "then":
						tokType = TokenType.THEN_OP;
						break;
					case "else":
						tokType = TokenType.ELSE_OP;
						break;
					case "do":
						tokType = TokenType.DO_OP;
						break;
					case "write":
						tokType = TokenType.PRINT_OP;
						break;
					default:
						throw new LexException ("invalid lexeme", rowNum, columnNum);
				}
		else if (Character.isDigit(lexeme.charAt(0)))
		{
			int i = 0;
			while (i < lexeme.length() && Character.isDigit(lexeme.charAt(i)))
				i++;
			if (i == lexeme.length())
				tokType = TokenType.INT_LIT;
			else
				throw new LexException ("invalid integer constant", rowNum, columnNum);
		}
		else
			switch (lexeme)
			{
				case "(":
					tokType = TokenType.LEFT_PAREN;
					break;
				case ")":
					tokType = TokenType.RIGHT_PAREN;
					break;
				case "=":
					tokType = TokenType.ASSIGN_OP;
					break;
				case "<=":
					tokType = TokenType.LE_OP;
					break;
				case "<":
					tokType = TokenType.LT_OP;
					break;
				case ">=":
					tokType = TokenType.GE_OP;
					break;
				case ">":
					tokType = TokenType.GT_OP;
					break;
				case "==":
					tokType = TokenType.EQ_OP;
					break;
				case "/=":
					tokType = TokenType.NE_OP;
					break;
				case "+":
					tokType = TokenType.ADD_OP;
					break;
				case "-":
					tokType = TokenType.SUB_OP;
					break;
				case "*":
					tokType = TokenType.MULT_OP;
					break;
				case "/":
					tokType = TokenType.DIV_OP;
					break;
				case ",":
					tokType = TokenType.COMMA_OP;
					break;
				default:
					throw new LexException ("invalid lexeme", rowNum, columnNum);
			}
		return tokType;
	}

	//Do not count white space as token
	private int skipWhiteSpace(String line, int index)
	{
		assert line != null;
		assert index >= 0;
		while (index < line.length() && Character.isWhitespace(line.charAt(index)))
			index++;
		return index;
	}
	


	//get next token but do not remove it
	public Token getLookaheadToken()
	{
		if (tokens.isEmpty())
			throw new RuntimeException ("There are no more tokens.");
		return tokens.get(0);
	}
	//return nextToken then remove it from list
	public Token getNextToken()
	{
		if (tokens.isEmpty())
			throw new RuntimeException ("There are no more tokens.");
		return tokens.remove(0);
	}
	
	private String getLexeme(String line, int index, int lineNum)
	{
		assert line != null;
		assert index >= 0;
		assert lineNum > 0;
		int i = index;
		while (i < line.length() && !Character.isWhitespace(line.charAt(i)))
			i++;
		return line.substring(index, i);
	}
	
	
}
	


