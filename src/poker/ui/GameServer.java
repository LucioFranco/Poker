/**
 * 
 */
package poker.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import poker.base.Card;
import poker.player.HumanPlayer;
import poker.player.MultiPlayer;
import poker.player.Player;
import poker.server.PokerClient;
import poker.server.PokerServer;
import poker.server.Server;

/**
 * @author luciofranco
 *
 */
public class GameServer extends Canvas implements Runnable, ButtonUpdatable {
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private Graphics g;
	private Point point;
	private JFrame frame;
	private Player[] players;
	private Card[] tableCards;
	private HumanPlayer humanPlayer;
	private int phaseNumber;
	private PokerServer server;
	
	public static void main(String[] args) throws UnknownHostException {
		new GameServer(800, 800, 25565);
	}
	
	public GameServer(int width, int height, int port) {
		frame = new JFrame();
		frame.setTitle("Poker Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(null);
		frame.setSize(width, height);
		frame.add(this);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		
		this.setSize(width, height);
		this.setVisible(true);
		g = this.getGraphics();
		this.players = new Player[6];
		this.tableCards = new Card[5];
		this.humanPlayer = new HumanPlayer("serverplayer", 25000);
		this.server = new PokerServer(this.humanPlayer, port);
		this.start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.addMouseListener(new ButtonListener(this));
		
		while(true) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 800);
			g.setColor(new Color(0, 150, 5));
			g.fillOval(140, 40, 520, 720);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("res/assets/pokerlogo.png"), 315, 200, this);
			
			
			this.draw();
			try {
				Thread.sleep(150);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void draw() {
		if(tableCards == null) {
			tableCards = new Card[5];
		}
		
		this.players = this.server.getPlayers();
		
		g.drawImage(CardManager.getCardImage(this.humanPlayer.getHand().card1), 325, 650, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.humanPlayer.getHand().card1), 402, 650, 72, 96, null);
		
		//table cards
		int x = 210;
		g.drawImage(CardManager.getCardImage(this.tableCards[0]), x, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[1]), x + 77, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[2]), x + 154, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[3]), x + 231, 450, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.tableCards[4]), x + 308, 450, 72, 96, null);
		
		//player 2
		if(players[1] != null) {
			this.drawHand(players[1], 638, 364);
		}
		
		//player 3
		if(players[2] != null) {
			this.drawHand(players[2], 564, 98);
		}
		
		//player 4
		if(players[3] != null) {
			this.drawHand(players[3], 88, 98);
		}
		
		//player 5
		if(players[4] != null) {
			this.drawHand(players[4], 28, 364);
		}
		
		//buttons
		g.setColor(Color.ORANGE);
		g.fillRect(670, 630, 100, 40);
		g.fillRect(670, 680, 100, 40);
		g.setColor(Color.black);
		g.setFont(new Font("Veranda", Font.PLAIN, 28));
		g.drawString("Check", 675, 660);
		g.drawString("Fold", 690, 710);
		
		//TODO Fix phase number
		if(phaseNumber == 4) {
			//buttons
			g.setColor(Color.RED);
			g.fillRect(15, 690, 115, 40);
			g.setColor(Color.black);
			g.setFont(new Font("Veranda", Font.PLAIN, 28));
			g.drawString("Re-Deal", 20, 720);
		}
		
		String str = "Connected Players: " + this.humanPlayer.getName() + " ";
		for(MultiPlayer p : this.server.connectedplayers) {
			str += p.getName() + " ";
		}
		
		g.setColor(Color.white);
		g.drawString(str, 50, 50);
	}
	
	public void buttonUpdate() {
		for(Player c : this.players) {
			System.out.println(c);
		}
/*		Point point = MouseInfo.getPointerInfo().getLocation();
		
		if(point.x > 670 && point.x < 770 && point.y > 677 && point.y < 715) {
			
			Card[] tempcards = table.nextPhase();
			
			if(tempcards == null) {
				Player tempplayer = table.determineWinner();
				bestplayer = tempplayer;
				for(int i = 0; i < players.length; i++) {
					String temp = players[i].getName() + " is " + players[i].getHand().getBestRank();
					if(players[i].hasFolded()) {
						temp += " folded";
					}
					if(players[i].equals(tempplayer)) {
						temp += " is winner";
					}
					System.out.println(temp);
				}
			}else {
				this.tableCards = tempcards;
			}
			
			
		}else if(point.x > 670 && point.y > 725 && point.x < 770 && point.y < 765) {
			players[0].fold();
		}
		
		if(table.getPhaseNum() == 4 && point.x > 15 && point.y > 690 && point.x < 130 && point.y < 775) {
			this.tableCards = table.deal();
		}*/
	}
	
	private void drawHand(Player player, int x, int y) {
		g.drawImage(CardManager.getCardImage(null), x, y, 72, 96, null);
		g.drawImage(CardManager.getCardImage(null), x + 77, y, 72, 96, null);
		if(players.length > 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 24));
			g.drawString(player.getName(), x + 12, y + 120);
			
			//TODO Fix player hand showing add to PokerClient
			if(true) {
				g.drawImage(CardManager.getCardImage(player.getHand().card1), x, y, 72, 96, null);
				g.drawImage(CardManager.getCardImage(player.getHand().card2), x + 77, y, 72, 96, null);
			}
		}
	}

}
