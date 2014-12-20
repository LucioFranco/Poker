/**
 * 
 */
package poker.server.packets;

import java.net.DatagramPacket;

import poker.execptions.NullPlayerListException;
import poker.player.MultiPlayer;
import poker.server.PokerClient;
import poker.server.PokerServer;
import poker.server.Server;



/**
 * @author luciofranco
 *
 */
public class ConnectPacket extends Packet {


	public ConnectPacket(DatagramPacket packet) {
		super(packet);
	}

	@Override
	public void proccess(PokerServer server) {
		if(server.connectedplayers.size() > 6) {
			super.callback = "000";
		}else {
			MultiPlayer player = new MultiPlayer(new String(super.packet.getData()).substring(2), new Server(super.packet.getAddress(), super.packet.getPort()), 25000);
			server.connectedplayers.add(player);
			try {
				server.table.connectPlayer(player);
			} catch (NullPlayerListException e) {
				e.printStackTrace();
			}
			super.callback = "001";
			server.printPlayerList();
		}
	}

	@Override
	public void proccess(PokerClient client) {
		if(message.equals("1")){
			this.cout = "connected to the server";
			client.connected = true;
		}else {
			this.cout = "Not connected to the server";
			client.connected = false;
		}
		
	}
}
