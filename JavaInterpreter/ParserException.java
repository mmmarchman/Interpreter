

public class ParserException extends Exception 
{
	private int rowNum;
	private int columnNum;
	
	/**
	 * @param message - cannot be null
	 * @param rowNum - must be >= 1
	 * @param columnNum - must be >= 1
	 * @throws IllegalArgumentException if any precondition is not true
	 */
	public ParserException(String message, int rowNum, int columnNum)
	{
		super (message);
		if (message == null)
			throw new IllegalArgumentException ("null message argument");
		if (rowNum <= 0)
			throw new IllegalArgumentException ("invalid row number argument");
		if (columnNum <= 0)
			throw new IllegalArgumentException ("invalid column number argument");
		this.rowNum = rowNum;
		this.columnNum = columnNum;
	}

	@Override
	public String toString()
	{
		return getMessage() + " at row: " + rowNum + " column: " + columnNum;
	}

}

