/**
 * 
 */
package poker.server.packets;

import java.net.DatagramPacket;

import poker.base.Card;
import poker.server.PokerClient;
import poker.server.PokerServer;
import poker.util.Util;

/**
 * @author luciofranco
 *
 */
public class TableCardUpdatePacket extends Packet {

	/**
	 * @param packet
	 */
	public TableCardUpdatePacket(DatagramPacket packet) {
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
		String[] tempstr = Util.trimTopOfMessageArray(this.message.split(":"));
		Card[] tableCards = new Card[5];
		int count = 0;
		
		for(int i = 0; i < tempstr.length; i += 2) {
			int face = Integer.parseInt(tempstr[i]);
			int suit = Integer.parseInt(tempstr[i + 1]);
			
			if(face == 0) {
				tableCards[count] = null;
			}else {
				tableCards[count] = new Card(face, suit);
			}
			
			count++;
		}
		
		client.setTableCards(tableCards);
	}

	/**
	 * @return
	 */
	public static String updatePlayers(Card[] tableCards) {
		String message = "";
		for(int i = 0; i < tableCards.length; i++) {
			if(tableCards[i] == null) {
				message += ":0:0";
			}else {
				message += ":" + tableCards[i].getFace().getValue() + ":" + tableCards[i].getSuit().getValue();
			}
		}
 		return message;
	}

}
