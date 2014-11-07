package poker.base;

public class Card {
	private Face face;
	private Suit suit;
	
	public Card(Face card, Suit suit) {
		this.face = card;
		this.suit = suit;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Face getFace() {
		return this.face;
	}

	public String toString() {
		return this.face + " of " + this.suit;
	}
	
	/**
	 * @param Card that
	 * @return comapreTo method to compare cards without Suits
	 */
	public int compareTo(Card that) {
		if(that.getFace().equals(this.face)) {
			return 0;
		}else if(that.getFace().ordinal() < this.face.ordinal()) {
			return 1;
		}else if(that.getFace().ordinal() > this.face.ordinal()) {
			return -1;
		}else {
			System.err.println("Error: something went wrong with Card.compareTo()");
			return -2;
		}
	}
	
	/**
	 * 
	 * @param Card that
	 * @return
	 */
	public int compareToWithSuit(Card that) {
		if(that.getFace().equals(this.face) && that.getSuit().equals(this.suit)) {
			return 0;
		}else if(that.getFace().ordinal() < this.face.ordinal() && that.getSuit().ordinal() < this.suit.ordinal()) {
			return 1;
		}else if(that.getFace().ordinal() > this.face.ordinal() && that.getSuit().ordinal() > this.suit.ordinal()) {
			return -1;
		}else {
			System.err.println("Error: something went wrong with Card.compareTo()");
			return -2;
		}
	}

	public boolean equals(Card that) {
		return this.face.equals(that.getFace());
	}
	
	public boolean equalsWithSuit(Card that) {
		return this.face.equals(that.getFace()) && this.suit.equals(that.getSuit());
	}
}
