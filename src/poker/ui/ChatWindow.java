/**
 * 
 */
package poker.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import poker.server.MPPlayer;
import poker.server.PokerClient;

/**
 * @author luciofranco
 *
 */
public class ChatWindow extends JFrame implements Runnable {
	private PokerClient client;
	private Thread thread;
	private InetAddress ip;
	private int port;
	
	
	public ChatWindow(InetAddress ip, int port) {
		
		this.client = client;
		this.ip = ip;
		this.port = port;
		
		JTextArea chatArea = new JTextArea(8, 40);
		chatArea.setEditable(false);
		chatArea.setFocusable(false);
		chatArea.setText("asldkfjasdlkfjasdlfkjasdflkjasdflkasdjfaslkdfjasdflkjasdflkad \n lakshdfalskdfjasldkfjasfkljasdfj \n adkfjasdflkjsdfl");
		JScrollPane chatScroll = new JScrollPane(chatArea);
		JPanel chatPanel = new JPanel(new BorderLayout());
		chatPanel.add(new JLabel("Chat:", SwingConstants.LEFT), BorderLayout.PAGE_START);
		chatPanel.add(chatScroll);

		JTextField inputField = new JTextField(40);
		JButton sendBtn = new JButton("Send");
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
		inputPanel.add(inputField);
		inputPanel.add(sendBtn);

		JPanel youLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		youLabelPanel.add(new JLabel("You:"));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(chatPanel);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(youLabelPanel);
		mainPanel.add(inputPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainPanel);
		this.setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public static void main(String[] args) throws UnknownHostException {
		new ChatWindow(InetAddress.getByName("localhost"), 25565);
	}

	public void run() {
		while(true) {
			
		}
	}

}
