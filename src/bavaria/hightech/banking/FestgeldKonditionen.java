package bavaria.hightech.banking;

/**
 * @author mrochow
 */
public class FestgeldKonditionen extends Konditionen {
	
	private int duration;
	private int interestRate;
	
	/**
	 * Default constructor
	 * 
	 * @param duration
	 * @param interestRate
	 */
	public FestgeldKonditionen(int duration, int interestRate) {
		this.duration = duration;
		this.interestRate = interestRate;
	}
	
	/**
	 * Returns the duration of the account
	 * 
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Returns the interests of this account
	 * 
	 * @return interestRate
	 */
	public int getInterestRate() {
		return interestRate;
	}
	
	/**
	 * Prints the desired information
	 * 
	 * @return <code>String</code>
	 */
	public String toString() {
		return "Laufzeit: " + this.duration + ", Zinssatz: " + this.interestRate;
	}
	
}
