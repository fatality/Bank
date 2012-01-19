package bavaria.hightech.banking;

import java.text.DateFormat;
import java.util.Calendar;

import bavaria.hightech.time.Zeitgeber;

/**
 * @author mrochow
 */
public final class Buchung {
	
	private String reason;
	private Money amount;
	private String transaction;
	public Calendar createDate;
	
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	
	/**
	 * Default constructor
	 * 
	 * @param reason: why
	 * @param amount: the money
	 * @param transaction: "+" or "-"
	 */
	public Buchung(String reason, Money amount, String transaction) {
		this.reason = reason;
		this.amount = amount;
		this.transaction = transaction;
		createDate = Zeitgeber.getZeitgeber().getCalendar();
	}
	
	/**
	 * Returns the creation date
	 * 
	 * @return
	 */
	public Calendar getCreationDate() {
		return createDate;
	}
	
	/**
	 * Prints desired informations
	 * 
	 * @return <code>String</code>
	 */
	public String toString() {
		return dateFormat.format(getCreationDate().getTime()) + "  " + reason + "         " + amount + " " + transaction;
	}
	
}
