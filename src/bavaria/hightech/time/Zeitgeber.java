package bavaria.hightech.time;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author mrochow
 */
public final class Zeitgeber {
	
	private Calendar calendar;
	private static Zeitgeber zeitgeber;
	
	/**
	 * Default constructor, starts a new <code>Calendar</code> instance
	 */
	private Zeitgeber() {
		calendar = Calendar.getInstance();
	}
	
	public void elapsTime(int time) {
		Quarz quarz = new Quarz();
		calendar.add(Calendar.DATE, time);
		quarz.startTimeing();
		while (calendar.before(calendar));
		quarz.stopTimeing();
	}
	
	/**
	 * If <code>zeitgeber</code> isn't set, we make a new instance
	 * 
	 * @return zeitgeber
	 */
	public static Zeitgeber getZeitgeber() {
		if (zeitgeber == null) zeitgeber = new Zeitgeber();
		return zeitgeber;
	}
	
	/**
	 * Gets an instance of <code>Calendar</strong>
	 * 
	 * @return calender
	 */
	public Calendar getCalendar() {
		return (Calendar) calendar.clone();
	}
	
	/**
	 * @author mrochow
	 */
	public class Quarz {
		
		private Timer timer;
		private Clock clock;
		
		/**
		 * Gets the clock
		 * 
		 * @return clock
		 */
		public Clock getClock() {
			return clock;
		}
		
		/**
		 * Adds a new value to the calendar
		 * 
		 * @param value
		 */
		private void timewarper(int value) {
			calendar.add(Calendar.SECOND, value);
		}
		
		/**
		 * Starts the clock and set up a new instance of <code>Timer()</code> and <code>Clock()</code>
		 */
		public void startTimeing() {
			timer = new Timer();
			clock = new Clock();
			clock.start();
			
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					Quarz.this.timewarper(600);
				}
			}, 0, 1);
		}
		
		/**
		 * Stops the procedure
		 */
		public void stopTimeing() {
			timer.cancel();
			clock.stop();
		}
	}
	
}
