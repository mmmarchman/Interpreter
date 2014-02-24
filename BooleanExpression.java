
public class BooleanExpression 
{
	private RelationalOperator rOp;
	private Expression expr1;
	private Expression expr2;
	
	/**
	 * @param rOp
	 * @param expr1 - cannot be null
	 * @param expr2 - cannot be null
	 * @throws IllegalArgumentException if any precondition is false
	 */
	public BooleanExpression(RelationalOperator rOp, Expression expr1, Expression expr2)
	{
		if (expr1 == null || expr2 == null)
			throw new IllegalArgumentException ("null expression argument in BooleanExpression");
		
		this.rOp = rOp;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	
	/**
	 * @return result of boolean expression
	 */
	public boolean evaluate()
	{
		
		boolean result = false;
		
		switch (rOp)
		{
			case LE:
				result = (expr1.evaluate() <= expr2.evaluate());
				break;
			case LT:
				result = (expr1.evaluate() < expr2.evaluate());
				break;
			case GE:
				result = (expr1.evaluate() >= expr2.evaluate());
				break;
			case GT:
				result = (expr1.evaluate() > expr2.evaluate());
				break;
			case EQ:
				result = (expr1.evaluate() == expr2.evaluate());
				break;
			case NE:
				result = (expr1.evaluate() != expr2.evaluate());
				break;
				
		}
		return result;
	}

	
	
	
}
