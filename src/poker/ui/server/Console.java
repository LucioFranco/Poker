/**
 * 
 */
package poker.ui.server;

import java.util.ArrayList;

/**
 * @author luciofranco
 *
 */
public class Console {
	private ArrayList<ConsoleMessage> outputqueue;
	private static Console instance = null;
	
	
	private Console() {
		this.outputqueue = new ArrayList<ConsoleMessage>();
	}
	
	public void say(String who, String message) {
		this.outputqueue.add(new ConsoleMessage(who, message));
	}
	
	public void say(ConsoleMessage message) {
		this.outputqueue.add(message);
	}
	
	public ArrayList<ConsoleMessage> readQueue() {
		ArrayList<ConsoleMessage> temp = this.outputqueue;
		this.flushQueue();
		return temp;
	}
	
	public void flushQueue() {
		this.outputqueue = new ArrayList<ConsoleMessage>();
	}
	
	public boolean isEmpty() {
		return this.outputqueue.isEmpty();
	}
	
	public static Console getInstance() {
		if(instance == null) {
			instance = new Console();
		}
		return instance;
	}
	
}
