
public class AIPlayer extends Player{
	public AIPlayer() {
		super("computer");
	}
	
	public void update() {
		if(hand.getBestRank().ordinal() < 4) {
			this.hasFolded = true;
		}
	}

}
