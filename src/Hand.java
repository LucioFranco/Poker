public class Hand {
	public Card card1;
	public Card card2;
	private boolean hasFolded;

	//generate two cards and toString and Equals
	public Hand() {
		this.card1 = Deck.nextCard();
		this.card2 = Deck.nextCard();
		this.hasFolded = false;
	}
	
	public Hand(Card card1, Card card2) {
		this.card1 = card1;
		this.card2 = card2;
	}
	
	public Card getCard1() {
		return this.card1;
	}

	public Card getCard2() {
		return this.card2;
	}
	
	public String toString() {
		return card1.toString() + " " + card2.toString();
	}
	
	public boolean isHasFolded() {
		return hasFolded;
	}

	public void setHasFolded(boolean hasFolded) {
		this.hasFolded = hasFolded;
	}
	
	public boolean equals(Hand that) {
		return (this.card1.equals(that.card1) && this.card2.equals(that.card2)) || (this.card1.equals(that.card2) && this.card2.equals(that.card1));
	}
}
