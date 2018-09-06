package com.cmft.net.CoreJava.bounceThread;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cmft.net.CoreJava.bounce.Ball;
import com.cmft.net.CoreJava.bounce.BallComponent;

public class BounceThread {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new BounceFrame();
			frame.setTitle("BounceThread");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}

class BounceFrame extends JFrame {
	private BallComponent component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
	
	/**
	 * Constructs the frame with the component for showing the bouncing ball and 
	 * Start and Close buttons
	 */
	public BounceFrame() {
		component = new BallComponent();
		add(component, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event -> addBall());
		addButton(buttonPanel, "End", event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	/**
	 * Adds a button to a container
	 * @param c	the container
	 * @param title	the button title
	 * @param listener	the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * Adds a bouncing ball to the canvas and starts a thread to make it bounce
	 */
	public void addBall() {
		Ball ball = new Ball();
		component.add(ball);
		new Thread(() -> {
			try {
				for (int i = 0; i < STEPS; i++) {
					ball.move(component.getBounds());
					component.repaint();
					Thread.sleep(DELAY);
				}
			}catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}).start();
	}
}