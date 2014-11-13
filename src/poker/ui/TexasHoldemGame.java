/**
 * 
 */
package poker.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import poker.base.Card;
import poker.base.Table;
import poker.execptions.NullPlayerListException;
import poker.player.AIPlayer;
import poker.player.HumanPlayer;
import poker.player.Player;

public class TexasHoldemGame implements Runnable {
	private Table table;
	private Player[] players;
	private Thread thread;
	private Card[] tableCards;
	private boolean mousepressed;
	
	
	public TexasHoldemGame() {
		this.mousepressed = false;
		players = new Player[4];
		players[0] = new HumanPlayer("Lucio");
		players[1] = new AIPlayer("Bob");
		players[2] = new AIPlayer("Jones");
		players[3] = new AIPlayer("Jain");
		
		
		try {
			table = new Table(players);
		} catch (NullPlayerListException e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		
		tableCards = table.deal();
		
	
		
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(new Color(0, 150, 5));
		g.fillOval(140, 40, 520, 720);
		g.drawImage(CardManager.getCardImage(players[0].getHand().card1), 325, 650, 72, 96, null);
		g.drawImage(CardManager.getCardImage(players[0].getHand().card2), 402, 650, 72, 96, null);
		
		//table cards
		int x = 210;
		g.drawImage(CardManager.getCardImage(this.tableCards[0]), x, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[1]), x + 77, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[2]), x + 154, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[3]), x + 231, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[4]), x + 308, 450, 72, 96, null);
		
		//player 2
		g.drawImage(CardManager.getCardImage(null), 638, 364, 72, 96, null);
		g.drawImage(CardManager.getCardImage(null), 715, 364, 72, 96, null);
		if(players.length > 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 24));
			g.drawString(players[1].getName(), 638 + 12, 364 + 120);
			
			if(table.getPhaseNum() == 4 && table.determineWinner().equals(players[1])) {
				g.drawImage(CardManager.getCardImage(players[1].getHand().card1), 638, 364, 72, 96, null);
				g.drawImage(CardManager.getCardImage(players[1].getHand().card2), 715, 364, 72, 96, null);
			}
		}
		
		//player 3
		g.drawImage(CardManager.getCardImage(null), 564, 98, 72, 96, null);
		g.drawImage(CardManager.getCardImage(null), 641, 98, 72, 96, null);
		if(players.length > 2) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 24));
			g.drawString(players[2].getName(), 564 + 12, 98 + 120);
			
			if(table.getPhaseNum() == 4 && table.determineWinner().equals(players[2])) {
				g.drawImage(CardManager.getCardImage(players[2].getHand().card1), 564, 98, 72, 96, null);
				g.drawImage(CardManager.getCardImage(players[2].getHand().card2), 641, 98, 72, 96, null);
			}
		}
		
		//player 4
		g.drawImage(CardManager.getCardImage(null), 88, 147, 72, 96, null);
		g.drawImage(CardManager.getCardImage(null), 165, 147, 72, 96, null);
		if(players.length > 3) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 24));
			g.drawString(players[3].getName(), 88 + 12, 147 + 120);
			
			if(players.length > 3 && table.getPhaseNum() == 4 && table.determineWinner().equals(players[3])) {
				g.drawImage(CardManager.getCardImage(players[3].getHand().card1), 88, 147, 72, 96, null);
				g.drawImage(CardManager.getCardImage(players[3].getHand().card2), 165, 147, 72, 96, null);
			}
		}
		
		//buttons
		g.setColor(Color.ORANGE);
		g.fillRect(670, 630, 100, 40);
		g.fillRect(670, 680, 100, 40);
		g.setColor(Color.black);
		g.setFont(new Font("Veranda", Font.PLAIN, 28));
		g.drawString("Check", 675, 660);
		g.drawString("Fold", 690, 710);
		
		if(table.getPhaseNum() == 4) {
			//buttons
			g.setColor(Color.RED);
			g.fillRect(15, 690, 115, 40);
			g.setColor(Color.black);
			g.setFont(new Font("Veranda", Font.PLAIN, 28));
			g.drawString("Re-Deal", 20, 720);
		}
	}
	
	public void buttonUpdate() {
		Point point = MouseInfo.getPointerInfo().getLocation();
		System.out.println(point.x + ", " + point.y);
		if(point.x > 670 && point.x < 770 && point.y > 677 && point.y < 715) {
			

			Card[] tempcards = table.nextPhase();
			
			
			
			if(tempcards == null) {
				Player tempplayer = table.determineWinner();
				for(int i = 0; i < players.length; i++) {
					String temp = players[i].getName() + " is " + players[i].getHand().getBestRank();
					if(players[i].equals(tempplayer)) {
						temp += " is winner";
					}
					System.out.println(temp);
				}
			}else {
				this.tableCards = tempcards;
			}
			
			
		}
		
		if(table.getPhaseNum() == 4 && point.x > 15 && point.y > 690 && point.x < 130 && point.y < 775) {
			this.tableCards = table.deal();
		}
	}
}
