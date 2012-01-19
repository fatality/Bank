package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.NotEnoughMoneyException;
import bavaria.hightech.exceptions.NullMoneyException;

/**
 * @author mrochow
 */
public class GiroKonto extends Konto {
	
	/**
	 * Default constructor
	 * 
	 * @param accNumber
	 * @param accHolder
	 * @param conditions
	 */
	public GiroKonto(int accNumber, String accHolder, Konditionen conditions) {
		super(accNumber, accHolder, conditions);
	}
	
	/**
	 * Transact money to an account
	 * 
	 * @param amount
	 * @param reason
	 * @throws NullMoneyException
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
	 * Transact money from an account
	 * 
	 * @param amount
	 * @param reason
	 * @throws NotEnoughMoneyException
	 */
	@Override
	public void debitEntry(Money amount, String reason) throws NotEnoughMoneyException {
		Money overdraft = ((GiroKonditionen) getConditions()).getOverdraft();
		if (amount.isGreater(new Money(0, Currency.EURO))) { 
			if (getAccountBalance().getAmount() < 0 && (getAccountBalance().getAmount() - amount.getAmount()) * -1 > overdraft.getAmount())
				throw new NotEnoughMoneyException("Überziehungsrahmen überschritten");
			super.debitEntry(amount, reason);
		} else {
			throw new NotEnoughMoneyException("You cannot transact less or equal than 0!");
		}
	}
	
	/**
	 * To pay interest on an account
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws NullMoneyException
	 */
	@Override
	public void verzinsen() throws NotEnoughMoneyException, NullMoneyException {
		if (getAccountBalance().getAmount() >= 0) {
			double interest = (getAccountBalance().getAmount() * this.getGiroConditions().getCreditInterest()) / 100f;
			long roundInterest = Math.round(interest);
			creditEntry(new Money(roundInterest, Currency.EURO), "Haben-Zinsen");
		} else {
			long absAccountBalance = getAccountBalance().getAmount() * -1;
			double interest = (absAccountBalance * this.getGiroConditions().getDebitInterest()) / 100f;
			long roundInterest = Math.round(interest);
			debitEntry(new Money(roundInterest, Currency.EURO), "Soll-Zinsen");
		}
	}
	
	/**
	 * Get interest conditions of an account
	 * 
	 * @return <code>GiroKonditionen</code>
	 */
	public GiroKonditionen getGiroConditions() {
		return (GiroKonditionen) getConditions();
	}
	
}
