package poker.base;

/**
 * The ranks are as follows: 9 - Royal Flush; 8 - Straight
 *   Flush; 7 - Four of a Kind; 6 - Full House; 5 - Flush; 4 - Straight;
 *   3 - Three of a Kind; 2 - Two Pair; 1 - Pair; 0 - Bust (nothing)
 */
public enum Ranks {
	BUST, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRIAGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH;
}
