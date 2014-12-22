/**
 * 
 */
package poker.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import poker.base.Card;
import poker.base.Hand;
import poker.player.HumanPlayer;
import poker.player.Player;
import poker.server.packets.Packet;

/**
 * @author luciofranco
 *
 */
public class PokerClient extends Thread {
	private DatagramSocket socket;
	private InetAddress ip;
	private int port;
	public boolean connected;
	private Player player;
	private Player[] players;
	private Card[] tableCards;
	
	public PokerClient(HumanPlayer humanPlayer, InetAddress ip, int port) {
		super("PokerClient");
		this.player = humanPlayer;
		this.players = new Player[6];
		this.tableCards = new Card[5];
		this.connected = false;
		this.ip = ip;
		this.port = port;
		
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.start();
	}
	
	public PokerClient(HumanPlayer humanPlayer, Server server) {
		super("PokerClient");
		this.player = humanPlayer;
		this.players = new Player[6];
		this.connected = false;
		this.ip = server.getIp();
		this.port = server.getPort();
		
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.start();
	}

	/**
	 * Thread for waiting for incoming packets from the server and then does something when it is received
	 */
	public void run() {
		while(true) {
			try {
				byte[] buf = new byte[1024];
				byte[] callback;
				
				//receive packet
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				//receive packet
				Packet p = Packet.parsePacket(packet);
				p.proccess(this);
				
				callback = p.callback();
				
				if(callback != null) {
					this.sendData(callback);
				}

				String message = p.cout();

				if(message != null) {
					System.out.println("CLIENT <[" + ip + ":" + port + "]> " + message);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * connects the player to the server
	 */
	public void connect() {
		this.sendData(("00" + this.player.getName()).getBytes());
	}
	
	/**
	 * @return true or false if player is connected to the server or not
	 */
	public boolean getConnected() {
		return this.connected;
	}
	
	/**
	 * 
	 * @param data
	 * to send a packet to the server
	 * 
	 */
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return list of players ordered for the client Player list that is displayed
	 */
	public Player[] getPlayers() {
		//TODO Re Do getPlayers on poker client
		Player[] tempplayers = new Player[6];
		tempplayers[0] = this.players[0];
		for(int i = 1; i < tempplayers.length; i++) {
			if(this.players[i] != null && this.players[i].getName().equals(this.player.getName())) {
				tempplayers[i] = tempplayers[0];
				tempplayers[0] = this.players[i];
			}else {
				tempplayers[i] = this.players[i];
			}
		}
		
		return tempplayers;
	}
	
	/**
	 * update players in client class
	 * @param players
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public void setTableCards(Card[] tableCards) {
		this.tableCards = tableCards;
	}

	/**
	 * @return the current table cards on the server
	 */
	public Card[] getTableCards() {
		return this.tableCards;
	}
	
	/**
	 * @return gets the port of the client socket
	 */
	public int getPort() {
		return this.socket.getLocalPort();
	}

	/**
	 * @param username of player
	 * @return the hand of the player that is inputed
	 */
	public Hand getPlayerHand(String name) {
		for(Player p : players) {
			if(p != null && p.getName().equals(name)) {
				return p.getHand();
			}
		}
 		return new Hand(null, null);
	}
	
	/**
	 * @param player object
	 * @return the hand of the player object that is passed in
	 */
	public Hand getPlayerHand(Player player) {
		for(Player p : players) {
			if(p != null && p.getName().equals(player.getName())) {
				return p.getHand();
			}
		}
 		return new Hand(null, null);
	}
	

	public void finalize() {
		this.socket.close();
	}
}
