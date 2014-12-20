/**
 * 
 */
package poker.server;

import java.net.InetAddress;

/**
 * @author luciofranco
 *
 */
public class Server {
	private InetAddress ip;
	private int port;
	
	public Server(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}	
}
