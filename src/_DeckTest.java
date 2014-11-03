
public class _DeckTest {
	public static void main(String[] args) {
		//CHECK IF DECK RANDOMLY OUTPUTS RANDOM CARDS
		for(int i = 0; i < 12; i++) {
			System.out.println(Deck.nextCard().toString());
		}
	}
}
