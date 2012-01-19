package bavaria.hightech.time;

import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author mrochow
 */
@SuppressWarnings("serial")
public class Clock extends JFrame {
	
	private static final int TICK_PERIOD = 1;
	private Timer ticker;
	private JPanel panel;
	
	/**
	 * @author mrochow
	 */
	private class TickerTask extends TimerTask {
		
		/**
		 * Rebuilds the clock
		 */
		@Override
		public void run() {
			panel.repaint();
		}
		
	}
	
	/**
	 * @author mrochow
	 */
	private static class ClockDisplay extends JPanel {
		
		/**
		 * Prints the clock in a <code>JPanel</code>
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paint(g);
			Calendar cal = Zeitgeber.getZeitgeber().getCalendar();
			Date date = cal.getTime();
			DateFormat dateFormatter = DateFormat.getTimeInstance();
			g.setFont(new Font(null, Font.PLAIN, 20));
			g.drawString(dateFormatter.format(date), 20, 50);
			dateFormatter = DateFormat.getDateInstance();
			g.drawString(dateFormatter.format(date), 10, 30);
		}
		
	}
	
	/**
	 * Default constructor
	 */
	public Clock() {
		panel = new ClockDisplay();
		add("Center", panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(100, 100);
		setVisible(true);
	}
	
	/**
	 * Starts a new timer
	 */
	public void start() {
		ticker = new Timer(true);
		ticker.scheduleAtFixedRate(new TickerTask(), 0, TICK_PERIOD);
	}
	
	/**
	 * Stops the timer
	 */
	public void stop() {
		ticker.cancel();
		setVisible(false);
	}
	
	/**
	 * Sets the visibility to <code>true</code>
	 */
	public void showMe() {
		setVisible(true);
	}
	
}
