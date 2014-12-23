/**
 * 
 */
package poker.server.packets;

import java.net.DatagramPacket;

import poker.base.Card;
import poker.base.Hand;
import poker.player.HumanPlayer;
import poker.player.Player;
import poker.server.PokerClient;
import poker.server.PokerServer;
import poker.util.Util;

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
		System.out.println(this.message);
		String[] tempstr = Util.trimTopOfMessageArray(this.message.split(":"));
		Player[] tempplr = new Player[6];
		
		//TODO FIX ERROR FOR NETWORK MESSAGE FAIL
		if((tempstr.length % 6) != 0) {
			System.err.println("ERROR: Network Message failure");
		}
		
		int count = 0;
		for(int i = 0; i < tempstr.length; i += 6) {
			Player player = new HumanPlayer(tempstr[i], Integer.parseInt(tempstr[i + 1]));
			player.setHand(new Hand(new Card(Integer.parseInt(tempstr[i + 2]), Integer.parseInt(tempstr[i + 3])), new Card(Integer.parseInt(tempstr[i + 4]), Integer.parseInt(tempstr[i + 5]))));
			tempplr[count] = player;
			count++;
		}
		
		client.setPlayers(tempplr);
	}

	/**
	 * 01:(player name):(chips):(face id):(suit id):(face id):(suit id)
	 * @param players
	 */
	public static String updatePlayers(Player[] players) {
		String message = "";
		
		for(int i = 0; i < players.length; i++) {
			if(players[i] != null) { 
				message += ":" + players[i].getName() + ":" + players[i].getChips();
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
