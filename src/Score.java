
public class Score {
	
	private enum Ranks {
		RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, OnePai, HighCard;
	}
	
	/**
	 * 
	 * @param Hand handOne
	 * @param Hand handTwo
	 * @param Card[] card
	 * @return Hand that has the better score
	 */
	public int calculateScore(Hand handOne, Hand handTwo, Card[] table) {
		Card[] cards = this.verisonsOfHands(handOne, table);
		
		
		return 0;
	}
	
	private void evalRank(Card[] cards) {
		
	}
	
	/**
	 * 
	 * @param hand
	 * @param table
	 * @return the cards of the hand class and the first three cards in the table array. 
	 * It is set like this for testing
	 */
	private Card[] verisonsOfHands(Hand hand, Card[] table) {
		Card[] cards = new Card[5];
		cards[0] = hand.card1;
		cards[1] = hand.card2;
		cards[2] = table[0];
		cards[3] = table[1];
		cards[4] = table[2];
		return cards;
	}

}
