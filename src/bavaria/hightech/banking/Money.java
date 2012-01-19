package bavaria.hightech.banking;

/**
 * @author mrochow
 */
public class Money {
	
	/**
	 * @author mrochow
	 */
	public enum Currency {
		
		EURO(14458L, "€"), DOLLAR(69171L, "$");
		
		public long marketValue;
		public String currencySign;
		
		/**
		 * Default constructor
		 * 
		 * @param marketValue
		 * @param currencySign
		 */
		private Currency(long marketValue, String currencySign) {
			this.marketValue = marketValue;
			this.currencySign = currencySign;
		}
		
		/**
		 * Returns the desired market value
		 * 
		 * @return marketValue
		 */
		public long getMarketValue() {
			return this.marketValue;
		}
		
		/**
		 * Return the desired currency sign
		 * 
		 * @return currencySign
		 */
		public String getCurrencySign() {
			return this.currencySign;
		}
		
	}
	
	private long amount;
	private Currency currency;
	
	/**
	 * Default constructor
	 * 
	 * @param amount
	 * @param currency
	 */
	public Money(long amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	/**
	 * Returns the currency
	 * 
	 * @return currency
	 */
	public Currency getCurrency() {
		return this.currency;
	}
	
	/**
	 * Returns the amount of an account
	 * 
	 * @return amount
	 */
	public long getAmount() {
		return this.amount;
	}
	
	/**
	 * Converts from one currency to another
	 * 
	 * @param c
	 * @return new currency
	 */
	public long convert(Currency c) {
		switch (c) {
			case EURO:
				return (this.amount * this.getCurrency().getMarketValue()) / 100000;
			case DOLLAR:
				return (this.amount * this.getCurrency().getMarketValue() / 10000);
			default:
				return 0;
		}
	}
	
	/**
	 * Adds money
	 * 
	 * @param m
	 */
	public void add(Money m) {
		if (this.currency == m.getCurrency()) {
			this.amount += m.getAmount();
		} else {
			this.amount += m.convert(this.getCurrency());
		}
	}
	
	/**
	 * Subtract money
	 * 
	 * @param m
	 */
	public void sub(Money m) {
		if (this.currency == m.getCurrency()) {
			this.amount -= m.getAmount();
		} else {
			this.amount -= m.convert(this.getCurrency());
		}
	}
	
	/**
	 * Checks if money == money
	 */
	public boolean equals(Object o) {
		if (o instanceof Money) {
			Money tmp = (Money)o;
			if (tmp.getAmount() == this.getAmount() && tmp.getCurrency() == this.getCurrency()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if money is greater than another
	 * 
	 * @param m
	 * @return boolean
	 */
	public boolean isGreater(Money m) {
		if (this.equals(m) && this.amount > m.getAmount()) {
			return true;
		} else {
			long tmpAmount = m.convert(this.getCurrency());
			if (this.amount > tmpAmount) return true;
		}
		return false;
	}
	
	public String toString() {
		return this.amount/100F + getCurrency().getCurrencySign();
	}
	
}
