

public class BinaryExpression implements Expression 
{
	private ArithmeticOperator arOp;
	private Expression expr1;
	private Expression expr2;

	

	//provides binary expressions +,-,*,/
	public BinaryExpression(ArithmeticOperator arOp, Expression expr1, Expression expr2)
	{
		if (expr1 == null || expr2 == null)
			throw new IllegalArgumentException ("null expression argument");
		this.arOp = arOp;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	//return value of binary expresion
	@Override
	public int evaluate()
	{
		int value = 0;
		switch (arOp)
		{
			
			case MUL:
				value = expr1.evaluate() * expr2.evaluate();
				break;
			case DIV:
				value = expr1.evaluate() / expr2.evaluate();
				break;
				
			case ADD:
				value = expr1.evaluate() + expr2.evaluate();
				break;
				
			case SUB:
				value = expr1.evaluate() - expr2.evaluate();
				break;
		}
		return value;
	}

	

}
