package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.NotEnoughMoneyException;
import bavaria.hightech.exceptions.NullMoneyException;

/**
 * @author mrochow
 */
public class FestgeldKonto extends Konto {
	
	/**
	 * Default constructor
	 * 
	 * @param accNumber
	 * @param accHolder
	 * @param conditions
	 */
	public FestgeldKonto(int accNumber, String accHolder, Konditionen conditions) {
		super(accNumber, accHolder, conditions);
	}
	
	/**
	 * Transact money to an account
	 * 
	 * @param amount
	 * @param reason
	 */
	@Override
	public void creditEntry(Money amount, String reason) throws NullMoneyException {
		if (amount.isGreater(new Money(0, Currency.EURO))) {
			super.creditEntry(amount, reason);
		} else {
			throw new NullMoneyException("You cannot transact money less or equal than 0!");
		}
	}
	
	/**
	 * Debits money from the accounts
	 * 
	 * @throws NotEnoughMoneyException
	 */
	@Override
	public void debitEntry(Money amount, String reason) throws NotEnoughMoneyException {
		if (amount.isGreater(new Money(0, Currency.EURO))) {
			super.debitEntry(amount, reason);
		} else {
			throw new NotEnoughMoneyException("To less amount on your account to withdraw money!");
		}
	}
	
	/**
	 * To pay interest of the desired account
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws NullMoneyException
	 */
	@Override
	public void verzinsen() throws NotEnoughMoneyException, NullMoneyException {
		double interest = (getAccountBalance().getAmount() * this.getFestgeldConditions().getInterestRate()) / 100f;
		long roundInterest = Math.round(interest);
		creditEntry(new Money(roundInterest, Currency.EURO), "Haben-Zinsen");
	}
	
	/**
	 * Returns the Conditions of the account
	 * 
	 * @return <code>Konditionen</code>
	 */
	public FestgeldKonditionen getFestgeldConditions() {
		return (FestgeldKonditionen) this.getConditions();
	}
	
}
