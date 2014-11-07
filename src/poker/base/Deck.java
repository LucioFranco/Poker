package poker.base;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private final static Random rand = new Random();
	private final static Face[] valuesOfFace = Face.values();
	private final static Suit[] valuesOfSuit = Suit.values();
	private static ArrayList<Card> deck;
	
	public static Card nextCard() {
		Card tempcard = new Card(valuesOfFace[rand.nextInt(valuesOfFace.length)], valuesOfSuit[rand.nextInt(valuesOfSuit.length)]);
		if(deck == null || deck.size() == 52) {
			deck = new ArrayList<Card>();
			deck.add(tempcard);
		}else if(deck != null) {
			boolean cardExsists = false;
			while(true) {
				cardExsists = false;
				for(Card a : deck) {
					if(tempcard.equalsWithSuit(a)) {
						cardExsists = true;
						tempcard = new Card(valuesOfFace[rand.nextInt(valuesOfFace.length)], valuesOfSuit[rand.nextInt(valuesOfSuit.length)]);
					}
				}
				
				if(!cardExsists) {
					deck.add(tempcard);
					break;
				}
			}
		}
		return tempcard;
	}
	
	/*
	 * create a linked list that expands after every card is drawn
	 */
}
