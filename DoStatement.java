
public class DoStatement implements Statement
{
	private VariableExpression var;
	private int first;
	private int last;
	
	private StatementList sList;
	/**
	 * @param var - cannot be null
	 * @param first - initial value of loop index
	 * @param last - final value of loop index - first <= last
	 * @param sList - cannot be null
	 * @throws IllegalArgumentException if any precondition is false
	 */
	public DoStatement(VariableExpression var, int first, int last, StatementList sList)
	{
		if (var == null)
			throw new IllegalArgumentException ("invalid variable argument");
		if (first > last)
			throw new IllegalArgumentException ("invalid loop indices arguments");
		if (sList == null)
			throw new IllegalArgumentException ("null statement list argument");
		this.var = var;
		this.first = first;
		this.last = last;
		this.sList = sList;
	}

	@Override
	public void execute()
	{
		var.setValue(first);
		while (var.evaluate() <= last)
		{
			sList.execute();
			var.setValue(var.evaluate() + 1);
		}
	}

}