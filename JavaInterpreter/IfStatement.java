
public class IfStatement implements Statement
{
	private BooleanExpression bool;
	private StatementList list1;
	private StatementList list2;
	
	public IfStatement(BooleanExpression bool, StatementList list1, StatementList list2)
	{
		if (list1 == null || list2 == null)
			throw new IllegalArgumentException("null expression argument in IfStatement");
		
		this.bool = bool;
		this.list1 = list1;
		this.list2 = list2;
		
	}

	
	@Override
	public void execute() 
	{
		if(bool.evaluate() == true)
		{
			list1.execute();
			
		}
		else
		{
			list2.execute();
		}
		
		
	}

}
