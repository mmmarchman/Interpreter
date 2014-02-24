
import java.io.FileNotFoundException;



public class Interpreter 
{
	public static void main(String[] args)
	{
			try
			{
				Parser p = new Parser("test3.for");
				Program prog = p.parse();
				prog.execute();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("source file not found");
				e.printStackTrace();
			}
			catch (LexException e)
			{
				System.out.println(e.toString());
				
			}			
			catch (ParserException e)
			{
				System.out.println(e.toString());
				
			}
			catch (IllegalArgumentException e)
			{
				System.out.println (e);
				e.printStackTrace();
			}
			catch (Exception e)
			{
				System.out.println("unknown error occurred - terminating");
				e.printStackTrace();
			}
		}
	
}


