
public class _HandTest {
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
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		
		System.err.println("Card1: " + hand1.toString() + "\nCard2: " + hand2.toString());
		System.err.println("hand1.equals(hand2) " + hand1.equals(hand2));
		
	}
}
