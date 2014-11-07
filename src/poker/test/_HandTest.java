package poker.test;
import poker.base.Card;
import poker.base.Face;
import poker.base.Hand;
import poker.base.Suit;


public class _HandTest extends _Test{
	public static void main(String[] args) {
		Hand hand = new Hand();
		Card[] cards = new Card[5];
		cards[0] = new Card(Face.ACE, Suit.CLUBS);
		cards[1] = new Card(Face.KING, Suit.CLUBS);
		cards[2] = new Card(Face.TWO, Suit.CLUBS);
		System.out.println(hand.calculateScore(cards));
	}
}
