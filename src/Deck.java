import java.util.Random;

public class Deck {
	private final static Random rand = new Random();
	private final static Face[] valuesOfFace = Face.values();
	private final static Suit[] valuesOfSuit = Suit.values();
	
	public static Card nextCard() {
		return new Card(valuesOfFace[rand.nextInt(valuesOfFace.length)], valuesOfSuit[rand.nextInt(valuesOfSuit.length)]);
	}
}
