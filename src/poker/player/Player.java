package poker.player;
import poker.base.Card;
import poker.base.Hand;

/**
 * @author luciofranco
 *
 */
public class Player {
	protected Hand hand;
	protected String name;
	protected boolean hasFolded;
	
	public Player(String name) {
		this.name = name;
		this.hasFolded = false;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean hasFolded() {
		return this.hasFolded;
	}

	public void deal() {
		hand = new Hand(false);
		this.hasFolded = false;
	}
	
	public String toString() {
		return name + " has " + this.hand.toString() + " folded: " + this.hasFolded();
	}
	
	public void update(Card[] table, int phaseNumber) {
		//add exception for no updated method
	}
	
}
