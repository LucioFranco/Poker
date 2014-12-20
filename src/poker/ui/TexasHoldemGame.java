//TODO Delete this class
///**
// * 
// */
//package poker.ui;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.MouseInfo;
//import java.awt.Point;
//import java.net.InetAddress;
//
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//import poker.base.Card;
//import poker.base.Table;
//import poker.execptions.NullPlayerListException;
//import poker.player.AIPlayer;
//import poker.player.HumanPlayer;
//import poker.player.Player;
//import poker.server.PokerClient;
//import poker.server.Server;
//
//public class TexasHoldemGame implements Runnable {
//	private Table table;
//	private Player[] players;
//	private Thread thread;
//	private Card[] tableCards;
//	private boolean mousepressed;
//	private Player bestplayer;
//	private PokerClient client;
//	
//	public TexasHoldemGame(JFrame frame) {
//		this.mousepressed = false;
//		
//		
//		
//		
//		
//		
//		players = new Player[5];
//		//players[0] = new HumanPlayer("Lucio");
//		players[1] = new AIPlayer("Bob");
//		players[2] = new AIPlayer("Jones");
//		players[3] = new AIPlayer("Jain");
//		players[4] = new AIPlayer("Sean");
//		
//	
//		this.bestplayer = null;
//		thread = new Thread(this);
//		thread.start();
//	}
//	
//	public void run() {
//		
//		tableCards = table.deal();
//		
//	
//		
//		
//	}
//	
//	public void draw(Graphics g) {
//	
//		g.drawImage(CardManager.getCardImage(players[0].getHand().card1), 325, 650, 72, 96, null);
//		g.drawImage(CardManager.getCardImage(players[0].getHand().card2), 402, 650, 72, 96, null);
//		
//		//table cards
//		int x = 210;
//		g.drawImage(CardManager.getCardImage(this.tableCards[0]), x, 450, 72, 96, null);
//		g.drawImage(CardManager.getCardImage(this.tableCards[1]), x + 77, 450, 72, 96, null);
//		g.drawImage(CardManager.getCardImage(this.tableCards[2]), x + 154, 450, 72, 96, null);
//		g.drawImage(CardManager.getCardImage(this.tableCards[3]), x + 231, 450, 72, 96, null);
//		g.drawImage(CardManager.getCardImage(this.tableCards[4]), x + 308, 450, 72, 96, null);
//		
//		//player 2
//		this.drawHand(g, players[1], 638, 364);
//		
//		//player 3
//		this.drawHand(g, players[2], 564, 98);
//		
//		//player 4
//		this.drawHand(g, players[3], 88, 98);
//		
//		//player 5
//		this.drawHand(g, players[4], 28, 364);
//		
//		//buttons
//		g.setColor(Color.ORANGE);
//		g.fillRect(670, 630, 100, 40);
//		g.fillRect(670, 680, 100, 40);
//		g.setColor(Color.black);
//		g.setFont(new Font("Veranda", Font.PLAIN, 28));
//		g.drawString("Check", 675, 660);
//		g.drawString("Fold", 690, 710);
//		
//		]
//	}
//	
//	private void drawHand(Graphics g, Player player, int x, int y) {
//		g.drawImage(CardManager.getCardImage(null), x, y, 72, 96, null);
//		g.drawImage(CardManager.getCardImage(null), x + 77, y, 72, 96, null);
//		if(players.length > 1) {
//			g.setColor(Color.WHITE);
//			g.setFont(new Font("Veranda", Font.PLAIN, 24));
//			g.drawString(player.getName(), x + 12, y + 120);
//			
//			if(table.getPhaseNum() == 4 && this.bestplayer.equals(player)) {
//				g.drawImage(CardManager.getCardImage(player.getHand().card1), x, y, 72, 96, null);
//				g.drawImage(CardManager.getCardImage(player.getHand().card2), x + 77, y, 72, 96, null);
//			}
//		}
//	}
//	
//	public void buttonUpdate() {
//		Point point = MouseInfo.getPointerInfo().getLocation();
//		
//		if(point.x > 670 && point.x < 770 && point.y > 677 && point.y < 715) {
//			
//			Card[] tempcards = table.nextPhase();
//			
//			if(tempcards == null) {
//				Player tempplayer = table.determineWinner();
//				bestplayer = tempplayer;
//				for(int i = 0; i < players.length; i++) {
//					String temp = players[i].getName() + " is " + players[i].getHand().getBestRank();
//					if(players[i].hasFolded()) {
//						temp += " folded";
//					}
//					if(players[i].equals(tempplayer)) {
//						temp += " is winner";
//					}
//					System.out.println(temp);
//				}
//			}else {
//				this.tableCards = tempcards;
//			}
//			
//			
//		}else if(point.x > 670 && point.y > 725 && point.x < 770 && point.y < 765) {
//			players[0].fold();
//		}
//		
//		if(table.getPhaseNum() == 4 && point.x > 15 && point.y > 690 && point.x < 130 && point.y < 775) {
//			this.tableCards = table.deal();
//		}
//	}
//}
