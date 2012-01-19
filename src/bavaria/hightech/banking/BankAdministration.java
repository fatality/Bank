package bavaria.hightech.banking;

/**
 * @author mrochow
 */
public interface BankAdministration {
	
	/**
	 * Sets ratings for <code>GiroKonto</code>
	 * 
	 * @param debitInterest
	 * @param creditInterest
	 */
	public void editGiroInterests(int debitInterest, int creditInterest);
	
	/**
	 * Ratings for specified accounts
	 * 
	 * @param debitInterest
	 * @param creditInterest
	 */
	public void initConditionList(int debitInterest, int creditInterest);
	
}
