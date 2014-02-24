
public class VariableExpression implements Expression
{

	private char ch;
	
	public VariableExpression(char ch)
	{
		this.ch = ch;
	}

	@Override
	public int evaluate()
	{
		return Memory.fetch(ch);
	}
	
	public void setValue (int value)
	{
		Memory.store(ch, value);
	}

}
