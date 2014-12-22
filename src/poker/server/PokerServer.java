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
import java.util.List;

import poker.base.Table;
import poker.execptions.NullPlayerListException;
import poker.player.HumanPlayer;
import poker.player.MultiPlayer;
import poker.player.Player;
import poker.server.packets.HandUpdatePacket;
import poker.server.packets.Packet;

/**
 * @author luciofranco
 *
 */
public class PokerServer extends Thread {
	private DatagramSocket socket;
	public List<MultiPlayer> connectedplayers = new ArrayList<MultiPlayer>();
	private HumanPlayer humanplayer; 
	public Table table;
	
	public PokerServer(HumanPlayer player, int port) {
		super("PokerServer");
		
		this.humanplayer = player;
		
		try {
			this.table = new Table(this.humanplayer, this);
			
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch(NullPlayerListException e) {
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
	public Player[] getPlayers() {
		//TODO Re Do getPlayers on poker server
		Player[] tempplayers = new Player[6];
		tempplayers[0] = this.table.players[0];
		for(int i = 1; i < tempplayers.length; i++) {
			if(this.table.getPlayer(i) != null && this.table.getPlayer(i).getName().equals(this.humanplayer.getName())) {
				tempplayers[i] = tempplayers[0];
				tempplayers[0] = this.table.getPlayer(i);
			}else {
				tempplayers[i] = this.table.getPlayer(i);
			}
		}
		
		return tempplayers;
	}

	/**
	 * update the players hands
	 */
	public void updatePlayersHands() {
		String message = "01" + HandUpdatePacket.updatePlayers(this.table.players);
		System.out.println("Server - " + message);
		for(MultiPlayer p : this.connectedplayers) {
			this.sendData(message.getBytes(), p.server.getIp(), p.server.getPort());
		}
	}
}
