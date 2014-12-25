/**
 * 
 */
package poker.ui.server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import poker.server.PokerServer;

/**
 * @author luciofranco
 *
 */
public class ApplicationServer extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterTextHere;
	private JTextArea consoleTextArea;
	private PokerServer server;
	private ConsoleUpdateThread consolethread;
	
	private class EnterButtonListener implements ActionListener {
		private JTextField textArea;
		
		public EnterButtonListener(JTextField textArea) {
			this.textArea = textArea;
		}
		
		public void actionPerformed(ActionEvent e) {
			Console.getInstance().say(new ConsoleMessage("Server", this.textArea.getText()));
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationServer frame = new ApplicationServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ApplicationServer() {
		setTitle("Poker Server");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 6, 434, 521);
		contentPane.add(scrollPane);
		
		this.consoleTextArea = new JTextArea();
		scrollPane.setViewportView(this.consoleTextArea);
		this.consoleTextArea.setEditable(false);
		this.consoleTextArea.setColumns(40);
		
		txtEnterTextHere = new JTextField();
		txtEnterTextHere.setText("Enter text here");
		txtEnterTextHere.setBounds(6, 530, 340, 28);
		contentPane.add(txtEnterTextHere);
		txtEnterTextHere.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new EnterButtonListener(this.txtEnterTextHere));
		btnEnter.setBounds(345, 531, 99, 29);
		contentPane.add(btnEnter);
		
		init();	
	}
	
	private void init() {
		//create new object
		this.server = new PokerServer(Integer.parseInt(JOptionPane.showInputDialog("Enter port")));
		this.consolethread = new ConsoleUpdateThread(this.consoleTextArea, this);
		this.consolethread.start();
	}	
}
