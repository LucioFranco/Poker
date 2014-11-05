import java.util.Arrays;

public class Table {
	private Player[] players;
	private Card[] tableCards = new Card[5];
	private int phaseNumber;
	
	public Table(int numOfPlayers, String[] playernames) {
		
		this.phaseNumber = 1;
		players = new Player[numOfPlayers];
		
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player(playernames[i]);
		}
		
		this.deal();
	}
	
	
	/**
	 * 
	 * @param Player[] players
	 * Takes in an array of players so that you can set the amout of AI players and ONE Human Player
	 */
	public Table(Player[] players) {
		this.phaseNumber = 1;
		this.players = Arrays.copyOf(players, players.length);
		
		this.deal();
	}
	
	@SuppressWarnings("unused")
	private Table() {
		
	}
	
	/**
	 *  Goes to next phase of game i.e. not card unvield on the table
	 */
	public void nextPhase() {
		if(phaseNumber == 3) {
			System.out.println("must re deal");
		}else if(phaseNumber == 2) {
			for(int i = 0; i < players.length; i++) {
				players[i].getHand().calculateScore(tableCards);
			}
			
			phaseNumber++;
			
		}else {
			phaseNumber++;
		}
		System.out.println(phaseNumber);
		for(int i = 0; i < players.length; i++) {
			if(!players[i].hasFolded()) {
				players[i].update();
			}
		}
	}
	
	/**
	 * 	deals new cards to the table and deals new cards to the players
	 */
	public void deal() {
		
		phaseNumber = 1;
		
		for(int i = 0; i < 3; ++i) {
			tableCards[i] = Deck.nextCard();
		}
		
		for(int i = 0; i < players.length; ++i) {
			players[i].deal();
		}
		
		for(int i = 3; i < 5; i++) {
			tableCards[i] = Deck.nextCard();
		}
	}
	
	/**
	 * determines winner of all the players
	 * @return the winner
	 */
	public Player determineWinner() {
		Player bestplayer = this.players[0];
		
		for(int i = 1; this.players.length > 2 && i < this.players.length; i++) {
			if(this.players[i].getHand().getBestRank().ordinal() > bestplayer.getHand().getBestRank().ordinal()) {
				bestplayer = this.players[i];
			}
		}
		
		return bestplayer;
	}

	public String toString() {
		String total = "";
		
		for(int i = 0; i < tableCards.length; ++i) {
			total +=  "Table card #" + (i + 1) + " ";
			total += tableCards[i].toString() + "\n";
		}
		
		for(int i = 0; i < players.length; ++i) {
			total += "Player #" + (i + 1) + " ";
			total += players[i].toString() + "\n";
		}
		return total;
	}
	
	public String toStringWithPhase() {
		String total = "";
		
		for(int i = 0; i < phaseNumber + 2; ++i) {
			total +=  "Table card #" + (i + 1) + " ";
			total += tableCards[i].toString() + "\n";
		}
		
		for(int i = 0; i < players.length; ++i) {
			total += "Player #" + (i + 1) + " ";
			total += players[i].toString() + "\n";
		}
		return total;
	}
}
