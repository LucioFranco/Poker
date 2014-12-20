package poker.player;
import java.util.Scanner;

import poker.base.Card;
import poker.base.Hand;


public class HumanPlayer extends Player{
	private Scanner scan;
	
	public HumanPlayer(String name, int startingchips) {
		super(name, startingchips);
		scan = new Scanner(System.in);
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	public void update(Card[] table, int phaseNumber) {
		
	}
}
