/**
 * 
 */
package poker.server.packets;

import java.net.DatagramPacket;

import poker.player.Player;
import poker.server.PokerClient;
import poker.server.PokerServer;

/**
 * @author luciofranco
 *	
 * 
 *
 */
public class HandUpdatePacket extends Packet {

	/**
	 * @param packet
	 */
	public HandUpdatePacket(DatagramPacket packet) {
		super(packet);
	}

	/* (non-Javadoc)
	 * @see poker.server.packets.Packet#proccess(poker.server.PokerServer)
	 */
	@Override
	public void proccess(PokerServer server) {

	}

	/* (non-Javadoc)
	 * @see poker.server.packets.Packet#proccess(poker.server.PokerClient)
	 */
	@Override
	public void proccess(PokerClient client) {
		String[] tempstr = this.message.split(":");
		for(int i = 0; i < tempstr.length; i++) {
			System.out.println(tempstr[i]);
		}
	}

	/**
	 * @param players
	 */
	public static String updatePlayers(Player[] players) {
		String message = "";
		
		for(int i = 0; i < players.length; i++) {
			if(players[i] != null) { 
				message += ":" + players[i].getName();
				if (players[i].getHand().isPlayerHandEmpty()) {
					message += ":0:0:0:0";
				} else {
					message += ":"
							+ players[i].getHand().card1.getFace().getValue() + ":"
							+ players[i].getHand().card1.getSuit().ordinal();
					message += ":"
							+ players[i].getHand().card2.getFace().getValue() + ":"
							+ players[i].getHand().card2.getSuit().ordinal();
				}
			}
		}
		
		return message;
	}
}
