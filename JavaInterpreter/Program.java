public class Program
{

	private StatementList sList;
	
	/**
	 * @param sList - cannot be null
	 * @throws IllegalArgumentException if sList is null
	 */
	public Program(StatementList sList)
	{
		if (sList == null)
			throw new IllegalArgumentException ("null statement list argument");
		this.sList = sList;
	}
	
	
	public void execute ()
	{
		sList.execute();
	}

}