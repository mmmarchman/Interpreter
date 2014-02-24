
public class PrintStatement implements Statement
{
	
	private Expression expr;
	
	/**
	 * @param expr - cannot be null
	 * @throws IllegalArgumentException if expr is null
	 */
	public PrintStatement(Expression expr)
	{
		if (expr == null)
			throw new IllegalArgumentException ("null expression argument");
		this.expr = expr;
	}

	@Override
	public void execute()
	{
		System.out.println (expr.evaluate());
	}

	
	
	
}
