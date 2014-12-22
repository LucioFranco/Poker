/**
 * 
 */
package poker.server.packets;

import java.net.DatagramPacket;

import poker.server.PokerClient;
import poker.server.PokerServer;

/**
 * @author luciofranco
 *
 */
public abstract class Packet {
	protected DatagramPacket packet;
	protected String callback;
	protected String message;
	protected String cout;
	
	public Packet(DatagramPacket packet) {
		this.packet = packet;
		this.callback = null;
		this.message = new String(packet.getData()).substring(2).trim();
		this.cout = null;
	}
	
	public abstract void proccess(PokerServer server);
	public abstract void proccess(PokerClient client);
	
	public byte[] callback() {
		if(this.callback == null) {
			return null;
		}else {
			return this.callback.getBytes();
		}
	}
	
	public String cout() {
		return cout;
	}
	
	public static Packet parsePacket(DatagramPacket packet) {
		switch(Integer.parseInt((new String(packet.getData()).substring(0, 2)))) {
			default:
				throw new RuntimeException("SECURITY BREACH!!!");
			case 00:
				return new ConnectPacket(packet);
			case 01:
				return new HandUpdatePacket(packet);
			case 02:
				return new TableCardUpdatePacket(packet);
		}
	}
	
}
