package poker.test;

public class _Test {
	public static void check(String message, boolean value)
	{
		if(!value){
			System.err.println("FAILED: " + message);
		}
		else {
			System.err.println("PASSED: " + message);
		}
	}
}
