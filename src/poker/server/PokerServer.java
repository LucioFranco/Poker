/**
 * 
 */
package poker.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import poker.base.Table;
import poker.player.MultiPlayer;
import poker.player.Player;
import poker.server.packets.HandUpdatePacket;
import poker.server.packets.Packet;
import poker.server.packets.TableCardUpdatePacket;
import poker.ui.server.Console;

/**
 * @author luciofranco
 *
 */
public class PokerServer extends Thread {
	private DatagramSocket socket;
	public ArrayList<MultiPlayer> connectedplayers = new ArrayList<MultiPlayer>();
	public Table table;
	public Console out;
	
	public PokerServer(int port) {
		super("PokerServer");
		
		try {
			this.table = new Table(this);
			
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		} 
		this.start();
	}

	public void run() {
		while(true) {
			try {
				byte[] buf = new byte[1024];
				byte[] callback;
				
				//receive packet
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
			
				Packet p = Packet.parsePacket(packet);
				p.proccess(this);
				callback = p.callback();

				if(callback != null) {
					this.sendData(callback, packet.getAddress(), packet.getPort());
				}

				String message = p.cout();

				if(message != null) {
					System.out.println("SEVER <[" + this.socket.getInetAddress().toString() +":" + this.socket.getPort() + "]> " + message);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void sendData(byte[] data, InetAddress ip, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printPlayerList() {
		System.out.println("Player List (" + this.connectedplayers.size() + "/6):");
		for(Player p : this.connectedplayers) {
			System.out.println(p.getName());
		}
	}
	
	public void finalize() {
		this.socket.close();
	}

	/**
	 * 
	 * @return list of players ordered for the client Player list that is displayed
	 */
	public ArrayList<MultiPlayer> getPlayers() {
		return this.connectedplayers;
	}

	/**
	 * update the players hands
	 */
	public void updatePlayersHands() {
		String message = "01" + HandUpdatePacket.updatePlayers(this.table.players);
		for(MultiPlayer p : this.connectedplayers) {
			this.sendData(message.getBytes(), p.server.getIp(), p.server.getPort());
		}
	}
	
	/**
	 * update the clients with the current table/common cards
	 */
	public void updateTableCards() {
		String message = "02" + TableCardUpdatePacket.updatePlayers(this.table.tableCards);
		for(MultiPlayer p : this.connectedplayers) {
			this.sendData(message.getBytes(), p.server.getIp(), p.server.getPort());
		}
	}
}
