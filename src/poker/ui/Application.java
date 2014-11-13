/**
 * 
 */
package poker.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Application extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Thread thread;
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private Graphics g;
	private Point point;
	
	public Application() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(null);
		frame.setSize(this.WIDTH, this.HEIGHT);
		this.setSize(this.WIDTH, this.HEIGHT);
		frame.add(this);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		this.start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		g = this.getGraphics();

		final TexasHoldemGame game = new TexasHoldemGame();
		
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			
			}

			@Override
			public void mousePressed(MouseEvent e) { 
				
			}

			@Override
			public void mouseReleased(MouseEvent e) { 
				game.buttonUpdate();
				e.consume();
			}

			@Override
			public void mouseEntered(MouseEvent e) { 
				
			}

			@Override
			public void mouseExited(MouseEvent e) {	}
		});
		
		while(true) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 800);
			game.draw(g);
			try {
				Thread.sleep(200);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	public static void main(String[] args) {
		new Application();
	}
}
