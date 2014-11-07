package poker.test;
import poker.base.Deck;


public class _DeckTest {
	public static void main(String[] args) {
		//CHECK IF DECK outputs all 52 unique cards
		for(int i = 0; i < 52; i++) {
			System.out.println("#" + (i + 1) + " " + Deck.nextCard().toString());
		}
	}
}
