

public class LexException extends Exception 
{
	private int rowNumber;
	private int columnNumber;
	
	//validate input column number and row number
	public LexException(String error, int rowNumber, int columnNumber)
	{
		super (error);
		if (error == null)
			throw new IllegalArgumentException ("String Input is Null");
		if (columnNumber <= 0)
			throw new IllegalArgumentException ("Invalid Column Number");
		if (rowNumber <= 0)
			throw new IllegalArgumentException ("Invalid Row Number");
		
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}

	@Override
	public String toString()
	{
		return getMessage() + " at line number " + rowNumber + " column number " + columnNumber;
	}
}
