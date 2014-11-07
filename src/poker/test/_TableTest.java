package poker.test;
import poker.base.Table;
import poker.player.AIPlayer;
import poker.player.HumanPlayer;
import poker.player.Player;


public class _TableTest {
	public static void main(String[] args) {
		
		//edit this to the players that you want currently only supports one human player
		Player[] players = new Player[4];
		players[0] = new HumanPlayer("Lucio");
		players[1] = new AIPlayer("cpu1");
		players[2] = new AIPlayer("cpu2");
		players[3] = new AIPlayer("cpu3");
		
		
		
		Table table = new Table(players);
		table.deal();
		System.out.println(table.toStringWithPhase());
		table.nextPhase();
		System.out.println(table.toStringWithPhase());
		table.nextPhase();
		System.out.println(table.toStringWithPhase());
		table.nextPhase();
		Player tempplayer = table.determineWinner();
		System.out.println("Winner is " + tempplayer.getName() + " with " + tempplayer.getHand().getBestRank());
		
	}
}
