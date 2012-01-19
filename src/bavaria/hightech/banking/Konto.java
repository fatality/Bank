package bavaria.hightech.banking;

import java.text.DateFormat;
import java.util.Calendar;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.NotEnoughMoneyException;
import bavaria.hightech.exceptions.NullMoneyException;
import bavaria.hightech.time.Zeitgeber;

/**
 * @author mrochow
 */
public abstract class Konto {
	
	private int accountNumber;
	private Money accountBalance;
	private String accountHolder;
	private Buchungsliste postingList = new Buchungsliste();
	private Konditionen conditions;
	public Calendar createDate;
	
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat timeFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	
	/**
	 * Default constructor
	 * 
	 * @param accNumber
	 * @param accHolder
	 * @param conditions
	 */
	public Konto(int accNumber, String accHolder, Konditionen conditions) {
		this.accountNumber = accNumber;
		this.accountBalance = new Money(0, Currency.EURO);
		this.accountHolder = accHolder;
		this.conditions = conditions;
		createDate = Zeitgeber.getZeitgeber().getCalendar();
	}
	
	/**
	 * Transact money an account
	 * 
	 * @param amount
	 * @param reason
	 */
	public void creditEntry(Money amount, String reason) throws NullMoneyException {
		verbuchen(amount, reason, "+");
	}
	
	/**
	 * Transact money from an account
	 * 
	 * @param amount
	 * @param reason
	 * @throws NotEnoughMoneyException
	 */
	public void debitEntry(Money amount, String reason) throws NotEnoughMoneyException {
		verbuchen(amount, reason, "-");
	}
	
	/**
	 * Add or subtract money from an account and transaction will be added to an posting list
	 * 
	 * @param amount
	 * @param reason
	 * @param transaction
	 */
	private void verbuchen(Money amount, String reason, String transaction) {
		if (transaction.equals("-")) {
			this.accountBalance.sub(amount);
		} else {
			this.accountBalance.add(amount);
		}
		postingList.add(new Buchung(reason, amount, transaction));
	}
	
	/**
	 * Gets account number
	 * 
	 * @return accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * Gets account Balance
	 * 
	 * @return accountBalance
	 */
	public Money getAccountBalance() {
		return accountBalance;
	}
	
	/**
	 * Gets account holder
	 * 
	 * @return accountHolder
	 */
	public String getAccountHolder() {
		return accountHolder;
	}
	
	/**
	 * Gets interest conditions
	 * 
	 * @return conditions
	 */
	public Konditionen getConditions() {
		return conditions;
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
	 * Prints desired information
	 * 
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Creation date: " + dateFormat.format(getCreationDate().getTime()) + " " + timeFormat.format(getCreationDate().getTime()));
		sb.append("\n");
		sb.append("Name: " + getAccountHolder());
		sb.append("\n");
		sb.append("Account number: " + getAccountNumber());
		sb.append("\n");
		sb.append("Account Balance: " + accountBalance.getAmount()/100F + getAccountBalance().getCurrency().getCurrencySign());
		return sb.toString();
	}
	
	/**
	 * Prints all postings
	 * 
	 * @return <code>String</code>
	 */
	public String getPostingList() {
		StringBuilder sb = new StringBuilder();
		sb.append("Kontoauszug für");
		sb.append("\n");
		sb.append("Kontonummer: " + getAccountNumber() + " Kontoinhaber: " + getAccountHolder());
		sb.append(postingList.toString());
		sb.append("Kontostand neu: " + accountBalance.getAmount()/100F + getAccountBalance().getCurrency().getCurrencySign());
		sb.append("\n");
		sb.append("Konditionen: " + getConditions());
		sb.append("\n");
		return sb.toString();
	}
	
	/**
	 * To pay interest on an account
	 * 
	 * @throws NotEnoughMoneyException
	 */
	protected abstract void verzinsen() throws NotEnoughMoneyException, NullMoneyException;
	
}
