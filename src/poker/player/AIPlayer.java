package poker.player;
import poker.base.*;


public class AIPlayer extends Player{
	public AIPlayer() {
		super("computer", 0);
	}
	
	public AIPlayer(String name) {
		super(name, 0);
	}
	
	public void update(Card[] table, int phaseNumber) {
		if(Math.random() > .95) {
			System.out.println(this.name + " has folded");
			this.fold();
		}
	}
}
