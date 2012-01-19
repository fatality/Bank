package bavaria.hightech.banking;

import bavaria.hightech.exceptions.NotEnoughMoneyException;
import bavaria.hightech.exceptions.NullMoneyException;

/**
 * @author mrochow
 */
public interface BankKundenSicht {
	
	/**
     * Creates a new GiroKonto
     * 
     * @param accHolder: Name of customer
     */
	public void addGiroKonto(String accHolder);
	
	/**
     * Creates a new FestgeldKonto
     * 
     * @param accHolder: Name of customer
     * @param duration: duration of this account (1 month, 3 months, 12 months)
     */
	public void addFestgeldKonto(String accHolder, int duration);
	
	/**
     * Get account balance in cent (either <code>EURO</code> or <code>DOLLAR</code>
     * 
     * @param accNumber: Account number
     */
	public Money getAccountBalance(int AccNumber);
	
	/**
     * Credit entry to one account in cent
     * 
     * @param accNumber: Account number
     * @param amount: the amount to transact
     * @param reason: <code>String</code>
     * @throws NullMoneyException
     */
	public void creditEntry(int AccNumber, Money amount, String reason) throws NullMoneyException;
	
	/**
     * Debit entry to one account in cent
     * 
     * @param accNumber: Account number
     * @param amount: the amount to transact
     * @param reason: <code>String</code>
     * @throws NotEnoughMoneyException
     */
	public void debitEntry(int AccNumber, Money amount, String reason) throws NotEnoughMoneyException;
	
	/**
     * Transfers money from one account to another
     * 
     * @param fromAccNumber: from which account the money comes
     * @param toAccNumber: to this account the money goes
     * @param amount: the money
     * @param reason: <code>String</code>
     * @throws NotEnoughMoneyException
     */
	public void transferTo(int FromAccNumber, int ToAccNumber, Money amount, String reason) throws NotEnoughMoneyException;
	
	/**
	 * Gets all current ratings
	 */
	public void showBankConditions();
	
}
