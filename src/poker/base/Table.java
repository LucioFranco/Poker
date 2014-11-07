package poker.base;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import poker.player.Player;

public class Table {
	private Player[] players;
	private Card[] tableCards = new Card[5];
	/*
	 *  round one with three cards is phase 1
	 *  round two with four cards it phase 2
	 *  round three with five cards is phase 3
	 */
	private int phaseNumber;
		
	/**
	 * 
	 * @param Player[] players
	 * Takes in an array of players so that you can set the amout of AI players and ONE Human Player
	 */
	public Table(Player[] players) {
		this.phaseNumber = 1;
		this.players = Arrays.copyOf(players, players.length);
		
		this.deal();
	}
	
	@SuppressWarnings("unused")
	private Table() {
		
	}
	
	/**
	 *  Goes to next phase of game i.e. not card unvield on the table
	 */
	public void nextPhase() {
		System.out.println("Round: " + phaseNumber);
		if(phaseNumber == 4) {
			System.out.println("must re deal");
		}else if(phaseNumber == 2) {
			tableCards[4] = Deck.nextCard();
			for(int i = 0; i < players.length; i++) {
				players[i].getHand().calculateScore(tableCards);
			}
			phaseNumber++;
			
		}else if(phaseNumber == 1){
			tableCards[3] = Deck.nextCard();
			phaseNumber++;
		}
		
		for(int i = 0; i < players.length; i++) {
			if(!players[i].hasFolded()) {
				players[i].update(tableCards, phaseNumber);
			}
		}
	}
	
	/**
	 * 	deals new cards to the table and deals new cards to the players
	 */
	public void deal() {
		
		phaseNumber = 1;
		
		for(int i = 0; i < 3; ++i) {
			tableCards[i] = Deck.nextCard();
		}
		
		for(int i = 0; i < players.length; ++i) {
			players[i].deal();
		}
	}
	
	/**
	 * determines winner of all the players
	 * @return the winner
	 */
	public Player determineWinner() {
		Player bestplayer = this.players[0];
		
		for(int i = 1; this.players.length > 2 && i < this.players.length; i++) {
			if(this.players[i].getHand().getBestRank().ordinal() > bestplayer.getHand().getBestRank().ordinal()) {
				bestplayer = this.players[i];
			}
		}
		
		logWinner(bestplayer);
		return bestplayer;
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
	
	public String toStringWithPhase() {
		String total = "";
		
		for(int i = 0; i < phaseNumber + 2; ++i) {
			total +=  "Table card #" + (i + 1) + " ";
			total += tableCards[i].toString() + "\n";
		}
		
		for(int i = 0; i < players.length; ++i) {
			total += "Player #" + (i + 1) + " ";
			total += players[i].toString() + "\n";
		}
		return total;
	}
}
