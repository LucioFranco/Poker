/**
 * 
 */
package poker.ui.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import poker.base.Card;
import poker.player.HumanPlayer;
import poker.player.Player;
import poker.server.PokerClient;
import poker.server.Server;
import poker.ui.ButtonListener;
import poker.ui.ButtonUpdatable;
import poker.ui.CardManager;

public class GameClient extends JPanel implements Runnable, ButtonUpdatable {
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private Graphics g;
	private Point point;
	private JFrame frame;
	private Player[] players;
	private Card[] tableCards;
	private HumanPlayer humanPlayer;
	private int phaseNumber;
	private PokerClient client;
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	
	public static void main(String[] args) throws UnknownHostException {
		new GameClient(800, 800, new Server(InetAddress.getByName("localhost"), 25565), "testname");
	}
	
	public GameClient(int width, int height, Server server, String username) {
		frame = new JFrame();
		frame.setTitle("Poker Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	
		this.setBackground(Color.black);
		
		frame.add(this);
		frame.pack();
		frame.setSize(this.WIDTH, this.HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);
		
		this.setSize(width, height);
		this.setVisible(true);
		g = this.getGraphics();
		this.players = new Player[6];
		this.tableCards = new Card[5];
		this.humanPlayer = new HumanPlayer(JOptionPane.showInputDialog(frame, "Enter Username"), 25000);
		this.client = new PokerClient(this.humanPlayer, server);
		this.start();
		this.client.connect();
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
			g.setColor(new Color(0, 150, 5));
			g.fillOval(140, 40, 520, 720);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("res/assets/pokerlogo.png"), 315, 200, this);
			
			this.updateFromServer();
			this.draw();
			try {
				Thread.sleep(150);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void updateFromServer() {
		this.players = client.getPlayers();
		this.humanPlayer.setHand(client.getPlayerHand(humanPlayer.getName()));
		this.tableCards = client.getTableCards();
	}

	public void draw() {
		if(tableCards == null) {
			tableCards = new Card[5];
		}
		
		this.players = this.client.getPlayers();
		
		g.drawImage(CardManager.getCardImage(this.humanPlayer.getHand().card1), 325, 650, 72, 96, null);
		g.drawImage(CardManager.getCardImage(this.humanPlayer.getHand().card2), 402, 650, 72, 96, null);
		
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
	}
	
	public void buttonUpdate() {
		//TODO draw buttons
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
