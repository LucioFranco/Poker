package poker.player;
import java.util.Scanner;

import poker.base.Card;


public class HumanPlayer extends Player{
	private Scanner scan;
	
	public HumanPlayer(String name) {
		super(name);
		scan = new Scanner(System.in);
	}
	
	public void update(Card[] table, int phaseNumber) {
		
	}
}
