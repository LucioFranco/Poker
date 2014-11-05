
public class _TableTest {
	public static void main(String[] args) {
		Player[] players = new Player[1];
		players[0] = new HumanPlayer("Lucio");
		
		
		
		Table table = new Table(players);
		table.deal();
		System.out.println(table.toStringWithPhase());
		table.nextPhase();
		System.out.println(table.toStringWithPhase());
		table.nextPhase();
		System.out.println(table.toStringWithPhase());
		Player tempplayer = table.determineWinner();
		System.out.println(tempplayer.getHand().getBestRank());
		System.out.println(tempplayer.getHand().toStringBestCards());
		
	}
}
