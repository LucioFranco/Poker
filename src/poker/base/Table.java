package poker.base;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import poker.execptions.NullPlayerListException;
import poker.player.Player;
import poker.server.PokerServer;

public class Table implements Runnable {
	public Player[] players;
	public Card[] tableCards;
	public Phase phase;
	private ArrayList<Player> playerQueue;
	private Thread thread;
	private PokerServer server;
	
	/**
	 * @param takes in first player for server
	 */
	public Table(Player player, PokerServer server) throws NullPlayerListException {
		if(player == null) {
			throw new NullPlayerListException("server player is null");
		}
		
		this.server = server;
		this.players = new Player[6];
		this.tableCards = new Card[5];
		
		
		players[0] = player;
		this.phase = Phase.PRE_DEAL;
		this.playerQueue = new ArrayList<Player>();
		this.thread = new Thread(this);
		
		this.thread.start();
	}
	
	@SuppressWarnings("unused")
	private Table() {
		
	}
	
	/**
	 * Run method for Table thread
	 */
	@Override
	public void run() {
		while(this.players[1] == null) {
			//System.out.println("Waiting for second player to join the game");
		}
		
		// TODO Create run method for table
		pickDealer();
		server.updatePlayersHands();
		deal();
		server.updatePlayersHands();
		
	}
	
	/**
	 * randomly picks a dealer for the begining of the game
	 */
	private void pickDealer() {
		//TODO pick dealer
		
	}
	
	/**
	 * 
	 * @return the amount of players currently in game
	 */
	public int getNumOfPlayers() {
		int count = 0;
		for(int i = 0; i < this.players.length; i++) {
			if(this.players[i] == null) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param player
	 * @throws NullPlayerListException
	 * for server to connect player to the game
	 */
	public void connectPlayer(Player player) throws NullPlayerListException {
		if(this.phase.equals(Phase.PRE_DEAL)) {
			this.addPlayer(player);
		}else {
			this.playerQueue.add(player);
		}
	}
	
	/**
	 * 
	 * @param player
	 * @throws NullPlayerListException
	 * add player to list of players
	 */
	private void addPlayer(Player player) throws NullPlayerListException {
		for(int i = 0; i < this.players.length; i++) {
			if(this.players[i] == null) {
				this.players[i] = player;
				return;
			}
		}
		
		throw new NullPlayerListException("Game is full");
	}
	
	/**
	 * clear the playerQueue to join the game
	 */
	private void clearPlayerQueue() {
		if(this.playerQueue.size() > 0) {
			for(Player p : this.playerQueue) {
				try {
					this.addPlayer(p);
				} catch (NullPlayerListException e) {
					e.printStackTrace();
				}
			}
			
			this.playerQueue = new ArrayList<Player>();
		}
	}
	
//	/**
//	 *  Goes to next phase of game i.e. not card unvield on the table
//	 */
//	
//	public Card[] nextPhase() {
//		System.out.println("Round: " + phaseNumber);
//		if(phaseNumber == 4) {
//			return null;
//		}else if(phaseNumber == 3) {
//			phaseNumber++;
//			return null;
//		}else if(phaseNumber == 2) {
//			tableCards[4] = Deck.nextCard();
//			for(int i = 0; i < players.length; i++) {
//				players[i].getHand().calculateScore(tableCards);
//			}
//			phaseNumber++;
//			
//		}else if(phaseNumber == 1){
//			tableCards[3] = Deck.nextCard();
//			phaseNumber++;
//		}
//		
//		for(int i = 0; i < players.length; i++) {
//			if(!players[i].hasFolded()) {
//				players[i].update(tableCards, phaseNumber);
//			}
//		}
//		return tableCards.clone();
//	}
//	
//	public int getPhaseNum() {
//		return this.phase.ordinal();
//	}
	
	/**
	 * 	deals new cards to the table and deals new cards to the players
	 */
	public Card[] deal() {
		
		phase = Phase.PRE_FLOP;
		
		for(int i = 0; i < players.length; ++i) {
			players[i].deal();
		}
		return tableCards.clone();
	}
	
	/**
	 * determines winner of all the players
	 * @return the winner
	 */
	public Player determineWinner() {
		Player bestplayer;
		ArrayList<Player> bestplayers = new ArrayList<Player>();
		bestplayers.add(this.players[0]);
		bestplayer = bestplayers.get(0);
		
		for(int i = 1; this.players.length > 2 && i < this.players.length; i++) {
			//if player has not folded 
			if(!this.players[i].hasFolded()) {
				if(this.players[i].getHand().getBestRank().ordinal() > bestplayers.get(0).getHand().getBestRank().ordinal()) {
					bestplayers = new ArrayList<Player>();
					bestplayers.add(this.players[i]);
				}else if(this.players[i].getHand().getBestRank().equals(bestplayers.get(0).getHand().getBestRank())) {
					bestplayers.add(this.players[i]);
				}
			}
		}
		
		if(bestplayers.size() > 1) {
			System.out.println("before");
			bestplayer = this.compareRanks(bestplayers);
		}else {
			bestplayer = bestplayers.get(0);
		}
		
		
		logWinner(bestplayer);
		return bestplayer;
	}
	
	/**
	 * finds the best player with common rank
	 * @param bestplayers
	 */
	private Player compareRanks(ArrayList<Player> bestplayers) {
		Player best = null;
		switch(bestplayers.get(0).getHand().getBestRank()) {
		case BUST:
			for(int i = 0; i < bestplayers.size() - 1; i++) {
				if(bestplayers.get(i).getHand().getBestCard(0).compareToWithSuit(bestplayers.get(i + 1).getHand().getBestCard(0)) == 1) {
					best = bestplayers.get(i);
				}else {
					best = bestplayers.get(i + 1);
				}
			}
			return best;
		case ONE_PAIR:
			for(int i = 0; i < this.tableCards.length - 1; i++) {
				if(this.tableCards[i].equals(tableCards[i + 1])) {
					for(int j = 0; j < bestplayers.size() - 1; j++) {
						if(bestplayers.get(j).getHand().getBestCard(0).compareToWithSuit(bestplayers.get(j + 1).getHand().getBestCard(0)) == 1) {
							best = bestplayers.get(j);
						}else {
							best = bestplayers.get(j + 1);
						}
					}
					return best;
				}
			}
			for(int j = 0; j < bestplayers.size() - 1; j++) {
				Card temp1 = null;
				for(int i = 0; i < bestplayers.get(j).getHand().getBestCards().length - 1; i++) {
					if(bestplayers.get(j).getHand().getBestCard(i).equals(bestplayers.get(j).getHand().getBestCard(i + 1))) {
						temp1 = bestplayers.get(j).getHand().getBestCard(i);
						break;
					}
				}
				
				Card temp2 = null;
				for(int i = 0; i < bestplayers.get(j + 1).getHand().getBestCards().length - 1; i++) {
					if(bestplayers.get(j + 1).getHand().getBestCard(i).equals(bestplayers.get(j + 1).getHand().getBestCard(i + 1))) {
						temp2 = bestplayers.get(j + 1).getHand().getBestCard(i);
						break;
					}
				}
				
				if(temp1.compareToWithSuit(temp2) == 1) {
					best = bestplayers.get(j);
				}else {
					best = bestplayers.get(j + 1);
				}
			}
			return best;
		default:
			System.out.println("misleading player");
			return bestplayers.get(0);
		}
	}

	/**
	 * 
	 * @param player
	 * logs to the file winnerlog.txt the with the name and rank of the winning player
	 * This is my fileio
	 */
	private void logWinner(Player player) {
		/*
		 * writes who won and what the rank was to the log file
		 */
		try {
			PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("winnerlog.txt", true)));
			write.println(player.getName() + ", " + player.getHand().getBestRank());
			write.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}	
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

	/**
	 * @param i
	 * @return certain player that is requested
	 */
	public Player getPlayer(int i) {
		return this.players[i];
	}
}
