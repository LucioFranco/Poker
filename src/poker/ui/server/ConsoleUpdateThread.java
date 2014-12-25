/**
 * 
 */
package poker.ui.server;

import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * @author luciofranco
 *
 */
public class ConsoleUpdateThread extends Thread {
	private JTextArea textArea;
	private ApplicationServer gui;
	
	public ConsoleUpdateThread(JTextArea textArea, ApplicationServer gui) {
		this.textArea = textArea;
		this.gui = gui;
	}
	
	public void run() {
		while(true) {
			ArrayList<ConsoleMessage> messages = Console.getInstance().readQueue();
			if(!messages.isEmpty()) {
				for(ConsoleMessage m : messages) {
					this.textArea.append(m.toString()+ "\n");
				}
			}
			
			try {
				this.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
