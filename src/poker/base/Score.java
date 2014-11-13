package poker.base;

public class Score {
	public Ranks evalRank(Card[] cards) {
		//for the test class of score test
		Hand hand = new Hand(false);
		return hand.evalRank(cards);
	}
}
