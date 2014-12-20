/**
 * 
 */
package poker.player;

import poker.base.Card;
import poker.server.Server;

/**
 * @author luciofranco
 *
 */
public class MultiPlayer extends Player {
	public Server server;

	public MultiPlayer(String name, Server server, int startingchips) {
		super(name, startingchips);
		this.server = server;
	}


	@Override
	public void update(Card[] table, int phaseNumber) {
		
	}
	
	

}
