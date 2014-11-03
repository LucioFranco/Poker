public class Table {
	private Hand[] players;
	private Card[] tableCards = new Card[5];
	
	public Table(int numOfPlayers) {
		players = new Hand[numOfPlayers];
		this.Deal();
	}
	
	private Table() {
	}
	
	public void Deal() {
		for(int i = 0; i < players.length; ++i) {
			players[i] = new Hand();
		}
		
		for(int i = 0; i < tableCards.length; ++i) {
			tableCards[i] = Deck.nextCard();
		}
	}

	public String toString() {
		String total = "";
		for(int i = 0; i < players.length; ++i) {
			total += "Player #" + (i + 1);
			total += players[i].toString() + "\n";
		}
		
		for(int i = 0; i < tableCards.length; ++i) {
			total +=  "Table card #" + (i + 1);
			total += tableCards[i].toString() + "\n";
		}
		return total;
	}
	/**
	 * 
	 * @param a takes in first in
	 * @param b
	 * @param c
	 * @return
	 */
	public String writes(int a, int b, int c) {
		return "" + a + b + c;
	}
}
