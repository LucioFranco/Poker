package poker.base;
public class Hand {
	public Card card1;
	public Card card2;
	
	private Card[] bestcards;
	private Ranks bestrank;

	//generate two cards and toString and Equals
	public Hand(boolean isNull) {
		if(isNull) {
			this.card1 = new Card();
			this.card2 = new Card();
		}else {
			this.card1 = Deck.nextCard();
			this.card2 = Deck.nextCard();
		}
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
		return card1.toString() + " and " + card2.toString();
	}
	
	public boolean equals(Hand that) {
		return (this.card1.equals(that.card1) && this.card2.equals(that.card2)) || (this.card1.equals(that.card2) && this.card2.equals(that.card1));
	}
	
	public Card[] getBestCards() {
		return this.bestcards;
	}
	
	public Card getBestCard(int index) {
		return this.bestcards[index];
	}
	
	public String toStringBestCards() {
		String temp = "";
		for(int i = 0; i < bestcards.length; i++) {
			temp += bestcards[i].toString() + "\n";
		}
		return temp;
	}
	
	public Ranks getBestRank() {
		return this.bestrank;
	}
	
	/**
	 * 
	 * @param Hand hand
	 * @param Card[] card
	 * @return Hand that has the better score
	 */
	public Ranks calculateScore(Card[] table) {
		Card[][] cards = new Card[5][5];
		Card[] bestcards = new Card[5];
		for(int i = 0; i < cards.length; i++) {
			cards[i][0] = this.card1;
			cards[i][1] = this.card2;
		}
		
		cards[0][2] = table[0];
		cards[0][3] = table[1];
		cards[0][4] = table[2];
		
		cards[1][2] = table[0];
		cards[1][3] = table[3];
		cards[1][4] = table[4];
		
		cards[2][2] = table[1];
		cards[2][3] = table[2];
		cards[2][4] = table[3];
		
		cards[3][2] = table[1];
		cards[3][3] = table[3];
		cards[3][4] = table[4];
		
		cards[4][2] = table[2];
		cards[4][3] = table[3];
		cards[4][4] = table[4];
		
		Ranks bestrank = Ranks.BUST;
		bestcards = cards[0];
		Ranks temprank;
		boolean nullCard = false;
		for(int i = 0; i < cards.length; i++) {
			nullCard = false;
			for(int j = 0; j < cards[i].length; j++) {
				if(cards[i][j] == null) {
					nullCard = true;
				}
			}
			
			if(!nullCard) {
				temprank = this.evalRank(cards[i]);
				if(temprank.ordinal() > bestrank.ordinal()) {
					bestrank = temprank;
					bestcards = this.sortHand(cards[i]);
				}
			}
		}
		
		this.bestcards = bestcards;
		this.bestrank = bestrank;
		return bestrank;
	}
	/**
	 * 
	 * @param Card[] cards
	 * @return the best possible hand
	 */
	protected Ranks evalRank(Card[] cards) {
		Card[] tempcards = this.sortHand(cards);
		//assume that hand is a bust
		Ranks rank = Ranks.BUST;
		
		//check for pair
		int pairIndex = 0;
		for(int i = 0; i < tempcards.length - 1; i++) {
			if(tempcards[i].equals(tempcards[i + 1])) {
				rank = Ranks.ONE_PAIR;
				pairIndex = i;
				break;
			}
		}

		//check for two pair
		if(rank.equals(Ranks.ONE_PAIR)) {
			for(int i = pairIndex + 2; i < tempcards.length - 1; i++) {
				if(tempcards[i].equals(tempcards[i + 1])) {
					rank = Ranks.TWO_PAIR;
				}
			}
		}
		
		//check for three of a kind OR flush
		for(int i = 0; i < 3; i++) {
			if(tempcards[i].equals(tempcards[i + 1]) && tempcards[i + 1].equals(tempcards[i + 2])) {
				if(tempcards[0].equals(tempcards[1]) && tempcards[3].equals(tempcards[4])) {
					rank = Ranks.FULL_HOUSE;
				}else {
					rank = Ranks.THREE_OF_A_KIND;
				}
			}
		}
		
		//check for four of a kind
		for(int i = 0; i < 2; i++) {
			if(tempcards[i].equals(tempcards[i + 1]) && tempcards[i + 1].equals(tempcards[i + 2]) && tempcards[i + 2].equals(tempcards[i + 3])) {
				rank = Ranks.FOUR_OF_A_KIND;
			}
		}
		
		//check for straight
		if(rank.equals(Ranks.BUST)) {
			if(tempcards[0].getFace().getValue() - tempcards[1].getFace().getValue() == 1 &&
					tempcards[1].getFace().getValue() - tempcards[2].getFace().getValue() == 1 &&
					tempcards[2].getFace().getValue() - tempcards[3].getFace().getValue() == 1 &&
					tempcards[3].getFace().getValue() - tempcards[4].getFace().getValue() == 1) {
				rank = Ranks.STRIAGHT;
			}
		}
		
		//check for flush and straight flush
		boolean isflush;
		if(rank.equals(Ranks.BUST) || rank.equals(Ranks.STRIAGHT)) {
			isflush = true;
			for(int i = 0; i < 4; i++) {
				if (!(tempcards[i].getSuit().equals(tempcards[i + 1].getSuit()))) {
					isflush = false;
				}
			}
			if (rank.equals(Ranks.STRIAGHT) && isflush) {
				rank = Ranks.STRAIGHT_FLUSH;
			} else if (isflush) {
				rank = Ranks.FLUSH;
			}
		}
		
		//check for royal flush
		if(rank.equals(Ranks.STRAIGHT_FLUSH) && tempcards[0].getFace().equals(Face.ACE) && tempcards[1].getFace().equals(Face.KING)) {
			rank = Ranks.ROYAL_FLUSH;
		}
		return rank;
	}
	
	/**
	 * 
	 * @param Card[] a
	 * Sort the Card[] from high to low
	 */
	private Card[] sortHand(Card[] cards) {
		Card[] a = new Card[5];
		for(int i = 0; i < a.length; i++) {
			a[i] = cards[i];
		}
		Card temp;
		int minIndex;
		for(int i = 0; i < a.length; i++) {
			minIndex = i;
			for(int j = i; j < a.length; j++) {

				if(a[minIndex].compareTo(a[j]) == -1)
					minIndex = j;
			}
			//swap the elements at i and j
			temp = a[minIndex];
			a[minIndex] = a[i];
			a[i] = temp;
		}
		return a;
	}
}
