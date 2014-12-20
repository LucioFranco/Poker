package poker.base;

public class Card {
	private Face face;
	private Suit suit;
	private boolean isNull;
	
	public Card(Face card, Suit suit) {
		this.face = card;
		this.suit = suit;
		isNull = false;
	}
	
	public Card() {
		this.face = Face.ACE;
		this.suit = Suit.CLUBS;
		isNull = true;
	}
	
	public Card(Object object) {
		
	}

	public Suit getSuit() {
		return this.suit;
	}
	
	public Face getFace() {
		return this.face;
	}
	
	public boolean isNull() {
		return this.isNull;
	}

	public String toString() {
		if(this.face == null && this.suit == null) {
			return "null card";
		}else {
			return this.face + " of " + this.suit;
		}
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
		if(that.getFace().equals(this.face)) {
			if(that.getSuit().ordinal() < this.getSuit().ordinal()) {
				return 1;
			}else if(that.getSuit().ordinal() > this.getSuit().ordinal()) {
				return -1;
			}else {
				return 0;
			}
		}else if(that.getFace().ordinal() < this.face.ordinal()) {
			return 1;
		}else if(that.getFace().ordinal() > this.face.ordinal()) {
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
