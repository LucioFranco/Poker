package poker.player;
import poker.base.Card;
import poker.base.Hand;

/**
 * @author luciofranco
 *
 */
public abstract class Player{
	protected boolean isDealer;
	protected boolean isBigBlind;
	protected boolean isSmallBlind;
	protected Hand hand;
	protected String name;
	protected boolean hasFolded;
	protected int chips;
	
	public Player(String name, int startingchips) {
		this.isDealer = false;
		this.isBigBlind = false;
		this.isSmallBlind = false;
		this.name = name;
		this.hasFolded = false;
		this.hand = new Hand(null, null);
		this.chips = startingchips;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public String getName() {
		return this.name.trim();
	}
	
	public boolean hasFolded() {
		return this.hasFolded;
	}
	
	public boolean isDealer() {
		return isDealer;
	}

	public void setDealer(boolean isDealer) {
		this.isDealer = isDealer;
	}

	public boolean isBigBlind() {
		return isBigBlind;
	}

	public void setBigBlind(boolean isBigBlind) {
		this.isBigBlind = isBigBlind;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public boolean isSmallBlind() {
		return isSmallBlind;
	}

	public void setSmallBlind(boolean isSmallBlind) {
		this.isSmallBlind = isSmallBlind;
	}
	
	public void fold() {
		this.hasFolded = true;
	}

	public void deal() {
		hand = new Hand(false);
		this.hasFolded = false;
	}
	
	public String toString() {
		if(this.hand == null || (this.hand.card1 == null && this.hand.card2 == null)) {
			return name + " has no cards";
		}else {
			return name + " has " + this.hand.toString() + " folded: " + this.hasFolded();
		}
	}
	
	public abstract void update(Card[] table, int phaseNumber);

	/**
	 * @return
	 */
	public int getChips() {
		return this.chips;
	}
}
