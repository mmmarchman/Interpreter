

public class Memory 
{
	private static int[] mem = new int[26];
	
	
	/**
	 * @param ch - must be a letter
	 * @return value stored in memory location for ch
	 * @throws RuntimeException if ch is not a letter
	 * Returns value at memory location ch
	 */
	public static int fetch(char ch)
	{
		if (!Character.isLetter(ch))
			throw new RuntimeException ("invalid memory access");
		if (Character.isUpperCase(ch))
			ch = Character.toLowerCase(ch);
		return mem[ch - 'a'];
		
	}
	
	/**
	 * @param ch - must be a letter
	 * @param value
	 * @throws RuntimeException if ch is not a letter
	 * Stores value into @ location ch
	 */
	public static void store (char ch, int value)
	{
		if (!Character.isLetter(ch))
			throw new RuntimeException ("invalid memory access");
		if (Character.isUpperCase(ch))
			ch = Character.toLowerCase(ch);
		mem[ch - 'a'] = value;
	}
}
