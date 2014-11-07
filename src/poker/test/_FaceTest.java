package poker.test;
import poker.base.Face;


public class _FaceTest {
	private static void check(String message, boolean value)
	{
		if(!value){
			System.err.println("FAILED: " + message);
		}
		else {
			System.err.println("PASSED: " + message);
		}
	}
	
	public static void main(String[] args) {
		check("ACE == 14", Face.ACE.getValue() == 14);
	}
}
