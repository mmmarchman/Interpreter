import java.util.ArrayList;
import java.util.List;

public class StatementList 
{
	private List<Statement> stmts;


	public StatementList() 
	{
		stmts = new ArrayList<Statement>();
	}


	public void add(Statement s) 
	{
		stmts.add(s);
	}

	public void execute() 
	{
		for (int i = 0; i < stmts.size(); i++)
			stmts.get(i).execute();
	}

}
