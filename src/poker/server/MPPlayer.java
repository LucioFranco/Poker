/**
 * 
 */
package poker.server;

import java.net.InetAddress;

import poker.player.Player;

/**
 * @author luciofranco
 *
 */
public class MPPlayer {
	private Player player;
	private InetAddress ip;
	private int port;
	
	public MPPlayer(Player player, InetAddress ip, int port) {
		this.player = player;
		this.ip = ip;
		this.port = port;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

}
