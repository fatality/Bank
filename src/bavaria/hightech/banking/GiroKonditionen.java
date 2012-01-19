package bavaria.hightech.banking;

/**
 * @author mrochow
 */
public class GiroKonditionen extends Konditionen {
	
	private static int debitInterest;
	private static int creditInterest;
	private Money overdraft;
	private int creditRating;
	
	/**
	 * Default constructor
	 * 
	 * @param debitInterest
	 * @param creditInterest
	 * @param overdraft
	 * @param creditRating
	 */
	public GiroKonditionen(int debitInterest, int creditInterest, Money overdraft, int creditRating) {
		GiroKonditionen.debitInterest = debitInterest;
		GiroKonditionen.creditInterest = creditInterest;
		this.overdraft = overdraft;
		this.creditRating = creditRating;
	}
	
	/**
	 * Returns the debit interests
	 * 
	 * @return debitInterest
	 */
	public int getDebitInterest() {
		return debitInterest;
	}
	
	/**
	 * Return the credit interests
	 * 
	 * @return creditInterest
	 */
	public int getCreditInterest() {
		return creditInterest;
	}
	
	/**
	 * Returns the overdraft of accunt
	 * 
	 * @return overdraft
	 */
	public Money getOverdraft() {
		return overdraft;
	}
	
	/**
	 * Returns the credit rating
	 * 
	 * @return creditRating
	 */
	public int getCreditRating() {
		return creditRating;
	}
	
	/**
	 * Sets debit interests
	 * 
	 * @param debitInterest
	 */
	public static void setDebitInterest(int debitInterest) {
		GiroKonditionen.debitInterest = debitInterest;
	}
	
	/**
	 * Sets credit interests
	 * 
	 * @param creditInterest
	 */
	public static void setCreditInterest(int creditInterest) {
		GiroKonditionen.creditInterest = creditInterest;
	}
	
	/**
	 * Prints desired information
	 * 
	 * @return <code>String</code>
	 */
	public String toString() {
		return "Sollzins: " + debitInterest + ", Habenszins: " + creditInterest + ", Überziehungsrahmen: " + overdraft + ", Bonität: " + creditRating;
	}
	
}
