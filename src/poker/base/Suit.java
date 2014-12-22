package poker.base;

/**
 * 
 * Ranks: Clubs = 0, Diamonds = 1 ... etc
 *
 */

public enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES;

	/**
	 * @param suit
	 * @return
	 */
	public static Suit getSuit(int suit) {
		switch(suit) {
		default:
			break;
		case 0:
			return CLUBS;
		case 1:
			return DIAMONDS;
		case 2:
			return HEARTS;
		case 3:
			return SPADES;
		}
		return null;
	}
}
