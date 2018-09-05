package com.cmft.net.CoreJava.bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Shows and animate bouncing ball.
 * @author 13954
 *
 */
public class Bounce {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new BounceFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

// The frame with ball and buttons
class BounceFrame extends JFrame {
	private BallComponent component;
	public static final int STEPS = 1000;
	public static final int DELAYS = 3;

	/**
	 * Constructs the frame with component for showing bouncing ball and Start and
	 * Close buttons
	 */
	public BounceFrame() {
		setTitle("Bounce");
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
	 * 
	 * @param c        the container
	 * @param title    the button title
	 * @param listener the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	/**
	 * Adds a bouncing ball to the panel and makes it bounce 1,000 times
	 */
	public void addBall() {
		try {
			Ball ball = new Ball();
			component.add(ball);

			for (int i = 0; i < STEPS; i++) {
				ball.move(component.getBounds());
				component.paint(component.getGraphics());
				Thread.sleep(DELAYS);
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}