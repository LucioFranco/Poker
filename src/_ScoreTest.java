
public class _ScoreTest {
	public static void main(String[] args) {
		_Test test = new _Test();
		test.check("Enum Ranks.Royal_Flush == 9", Ranks.ROYAL_FLUSH.ordinal() == 9);
		
		Score score = new Score();
		Card[] cards = new Card[5];
		
		//one pair
		cards[0] = new Card(Face.ACE, Suit.CLUBS);
		cards[1] = new Card(Face.QUEEN, Suit.DIAMONDS);
		cards[2] = new Card(Face.TWO, Suit.HEARTS);
		cards[3] = new Card(Face.ACE, Suit.CLUBS);
		cards[4] = new Card(Face.JACK, Suit.CLUBS);
		test.check("Check rankEval for one pair", score.evalRank(cards).equals(Ranks.ONE_PAIR));
		
		cards[0] = new Card(Face.ACE, Suit.CLUBS);
		cards[1] = new Card(Face.QUEEN, Suit.DIAMONDS);
		cards[2] = new Card(Face.QUEEN, Suit.HEARTS);
		cards[3] = new Card(Face.JACK, Suit.CLUBS);
		cards[4] = new Card(Face.JACK, Suit.CLUBS);
		test.check("Check rankEval for two pair", score.evalRank(cards).equals(Ranks.TWO_PAIR));
		
		cards[0] = new Card(Face.ACE, Suit.CLUBS);
		cards[1] = new Card(Face.ACE, Suit.DIAMONDS);
		cards[2] = new Card(Face.ACE, Suit.HEARTS);
		cards[3] = new Card(Face.TWO, Suit.CLUBS);
		cards[4] = new Card(Face.JACK, Suit.CLUBS);
		test.check("Check rankEval for three of a kind of a kind", score.evalRank(cards).equals(Ranks.THREE_OF_A_KIND));
		
		cards[0] = new Card(Face.ACE, Suit.CLUBS);
		cards[1] = new Card(Face.QUEEN, Suit.HEARTS);
		cards[2] = new Card(Face.ACE, Suit.HEARTS);
		cards[3] = new Card(Face.QUEEN, Suit.CLUBS);
		cards[4] = new Card(Face.QUEEN, Suit.DIAMONDS);
		test.check("Check rankEval for a full house", score.evalRank(cards).equals(Ranks.FULL_HOUSE));
		
		cards[0] = new Card(Face.ACE, Suit.CLUBS);
		cards[1] = new Card(Face.QUEEN, Suit.DIAMONDS);
		cards[2] = new Card(Face.QUEEN, Suit.HEARTS);
		cards[3] = new Card(Face.QUEEN, Suit.CLUBS);
		cards[4] = new Card(Face.QUEEN, Suit.DIAMONDS);
		test.check("Check rankEval for four of a kind", score.evalRank(cards).equals(Ranks.FOUR_OF_A_KIND));
		
		cards[0] = new Card(Face.NINE, Suit.CLUBS);
		cards[1] = new Card(Face.QUEEN, Suit.DIAMONDS);
		cards[2] = new Card(Face.EIGHT, Suit.HEARTS);
		cards[3] = new Card(Face.TEN, Suit.CLUBS);
		cards[4] = new Card(Face.JACK, Suit.DIAMONDS);
		test.check("Check rankEval for a straight", score.evalRank(cards).equals(Ranks.STRIAGHT));
		
		cards[0] = new Card(Face.NINE, Suit.CLUBS);
		cards[1] = new Card(Face.TWO, Suit.CLUBS);
		cards[2] = new Card(Face.EIGHT, Suit.CLUBS);
		cards[3] = new Card(Face.FIVE, Suit.CLUBS);
		cards[4] = new Card(Face.KING, Suit.CLUBS);
		test.check("Check rankEval for a flush", score.evalRank(cards).equals(Ranks.FLUSH));
		
		cards[0] = new Card(Face.NINE, Suit.CLUBS);
		cards[1] = new Card(Face.QUEEN, Suit.CLUBS);
		cards[2] = new Card(Face.EIGHT, Suit.CLUBS);
		cards[3] = new Card(Face.TEN, Suit.CLUBS);
		cards[4] = new Card(Face.JACK, Suit.CLUBS);
		test.check("Check rankEval for a straight flush", score.evalRank(cards).equals(Ranks.STRAIGHT_FLUSH));
		
		cards[0] = new Card(Face.JACK, Suit.HEARTS);
		cards[1] = new Card(Face.QUEEN, Suit.HEARTS);
		cards[2] = new Card(Face.ACE, Suit.HEARTS);
		cards[3] = new Card(Face.TEN, Suit.HEARTS);
		cards[4] = new Card(Face.KING, Suit.HEARTS);
		test.check("Check rankEval for a royal flush", score.evalRank(cards).equals(Ranks.ROYAL_FLUSH));
		
		
	}
}
