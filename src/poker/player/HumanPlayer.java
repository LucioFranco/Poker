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
		String input;
		System.out.println("Would you like to fold or check? (fold / check)");
		while(true) {
			input = scan.nextLine();
			
			if(input.equalsIgnoreCase("check")) {
				break;
			}else if(input.equalsIgnoreCase("fold")) {
				this.hasFolded = true;
				break;
			}else {
				System.err.println("wrong input");
			}
		}
	}
}