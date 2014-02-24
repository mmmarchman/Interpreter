
public class AssignmentStatement  implements Statement
{
	private VariableExpression var;
	private Expression expr1;
	
	

	
	public AssignmentStatement(VariableExpression var, Expression expr1)
	{
		if (var == null || expr1 == null)
			throw new IllegalArgumentException("null expression argument in AssignmentStatement");
		this.var = var;
		this.expr1 = expr1;
		
	}

	@Override
	public void execute()
	{
		var.setValue(expr1.evaluate());
	}
	

}
