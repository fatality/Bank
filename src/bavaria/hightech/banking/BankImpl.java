package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.NotEnoughMoneyException;
import bavaria.hightech.exceptions.NullMoneyException;

/**
 * @author mrochow
 */
public class BankImpl implements BankKundenSicht, BankAdministration {
	
	private Konto[] account = new Konto[1000];
	private int counter = 0;
	private FestgeldKonditionen[] festgeldConditionList;
	private GiroKonditionen[] giroConditionList;
    
	/**
	 * Default constructor
	 * 
	 * @param debitInterest
	 * @param creditInterest
	 */
    public BankImpl(int debitInterest, int creditInterest) {
		initConditionList(debitInterest, creditInterest);
	}
	
    /**
     * Creates a new GiroKonto
     * 
     * @param accHolder: Name of customer
     */
    @Override
    public void addGiroKonto(String accHolder) {
		account[counter] = new GiroKonto(1000+counter, accHolder, parseGiroConditions());
		counter++;
	}
	
    /**
     * Creates a new FestgeldKonto
     * 
     * @param accHolder: Name of customer
     * @param duration: duration of this account (1 month, 3 months, 12 months)
     */
    @Override
	public void addFestgeldKonto(String accHolder, int duration) {
		account[counter] = new FestgeldKonto(1000+counter, accHolder, parseFestgeldConditions(duration));
		counter++;
	}
	
    /**
     * Get account balance in cent (either <code>EURO</code> or <code>DOLLAR</code>
     * 
     * @param accNumber: Account number
     */
    @Override
	public Money getAccountBalance(int accNumber) {
		return getAccount(accNumber).getAccountBalance();
	}
	
    /**
     * Credit entry to one account in cent
     * 
     * @param accNumber: Account number
     * @param amount: the amount to transact
     * @param reason: <code>String</code>
     */
    @Override
	public void creditEntry(int accNumber, Money amount, String reason) {
		Konto acc = getAccount(accNumber);
		try {
			acc.creditEntry(amount, reason);
		} catch(NullMoneyException e) {
			System.out.println(e.getMessage());
		}
	}
	
    /**
     * Debit entry to one account in cent
     * 
     * @param accNumber: Account number
     * @param amount: the amount to transact
     * @param reason: <code>String</code>
     * @throws NotEnoughMoneyException
     */
    @Override
	public void debitEntry(int accNumber, Money amount, String reason) {
		Konto acc = getAccount(accNumber);
		try {
			acc.debitEntry(amount, reason);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		}
	}
    
    /**
     * Transfers money from one account to another
     * 
     * @param fromAccNumber: from which account the money comes
     * @param toAccNumber: to this account the money goes
     * @param amount: the money
     * @param reason: <code>String</code>
     * @throws NotEnoughMoneyException
     * @throws NullMoneyException
     */
	@Override
	public void transferTo(int fromAccNumber, int toAccNumber, Money amount, String reason) {
		Konto accTo = getAccount(toAccNumber);
		Konto accFrom = getAccount(fromAccNumber);
		try {
			accFrom.debitEntry(amount, reason);
			accTo.creditEntry(amount, reason);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} catch (NullMoneyException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	/**
	 * To pay interest on every account
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws NullMoneyException
	 */
	public void verzinsen() {
		for (int i = 0; i < counter; i++) {
			try {
				account[i].verzinsen();
			} catch (NotEnoughMoneyException e) {
				System.out.println(e.getMessage());
			} catch (NullMoneyException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
	
	/**
	 * Get account
	 * 
	 * @param accountNumber: Account number
	 * @return the customer's account
	 * @throws NullPointerException
	 */
	private Konto getAccount(int accountNumber) {
		try {
			return account[accountNumber-1000];
		} catch (NullPointerException e) {
			e.getStackTrace();
		}
		return null;
	}
	
	/**
	 * List all Accounts
	 */
	public void list() {
		System.out.println("All Accounts:");
		System.out.println("-----------------------------------");
		for (int i = 0; i < counter; i++) {
			if (account[i] != null) {
				System.out.println(account[i].toString());
				System.out.println("-----------------------------------");
			}
		}
	}
	
	/**
	 * Print all postings by an account
	 * 
	 * @param accNumber: account Number
	 * @return all postings by this account
	 */
	public String getKontoauszug(int accNumber) {
		Konto acc = getAccount(accNumber);
		return acc.getPostingList();
	}
	
	/**
	 * Ratings for specified accounts
	 * 
	 * @param debitInterest: Sollzinsen
	 * @param creditInterest: Habenszinsen
	 */
	@Override
	public void initConditionList(int debitInterest, int creditInterest) {
    	// Festgeld Konditionen
    	festgeldConditionList = new FestgeldKonditionen[3];
    	festgeldConditionList[0] = new FestgeldKonditionen(1, 1); // 1% Zinsen, 1 Monat Laufzeit
    	festgeldConditionList[1] = new FestgeldKonditionen(3, 2); // 2% Zinsen, 3 Monate Laufzeit
    	festgeldConditionList[2] = new FestgeldKonditionen(12, 3); // 3% Zinsen, 12 Monate Laufzeit
    	
    	// Girokonto
    	giroConditionList = new GiroKonditionen[3];
    	giroConditionList[0] = new GiroKonditionen(debitInterest, creditInterest, new Money(1000, Currency.EURO), 1);
    	giroConditionList[1] = new GiroKonditionen(debitInterest, creditInterest, new Money(5000, Currency.EURO), 2);
    	giroConditionList[2] = new GiroKonditionen(debitInterest, creditInterest, new Money(2000, Currency.EURO), 3);
    }
	
	/**
	 * Sets ratings for <code>GiroKonto</code>
	 * 
	 * @param debitInterest: Sollzinsen
	 * @param creditInterest: Habenszinsen
	 */
	@Override
	public void editGiroInterests(int debitInterest, int creditInterest) {
		GiroKonditionen.setCreditInterest(creditInterest);
		GiroKonditionen.setDebitInterest(debitInterest);
	}
	
	/**
	 * Gets all ratings from every account
	 */
	@Override
	public void showBankConditions() {
		System.out.println("Festgeldkonditionen:");
		for (int i = 0; i < festgeldConditionList.length; i++) {
			if (festgeldConditionList[i] != null) System.out.println(festgeldConditionList[i].toString());
		}
		System.out.println("\nGirokonditionen:");
		for (int i = 0; i < giroConditionList.length; i++) {
			if (giroConditionList[i] != null) System.out.println(giroConditionList[i].toString());
		}
		System.out.println("\n");
	}
	
	/**
	 * Chooses the desired Conditions
	 * 
	 * @param duration
	 * @return conditions from <code>FestgeldKonto</code>
	 */
	private FestgeldKonditionen parseFestgeldConditions(int duration) {
    	for (FestgeldKonditionen element : festgeldConditionList) {
    		if (element.getDuration() == duration) return element;
    	}
    	return null;
    }
    
	/**
	 * Chooses a randomly generated credit rating
	 * 
	 * @return credit rating
	 */
    private GiroKonditionen parseGiroConditions() {
    	int creditRating = (int) Math.round(Math.random() * 2 + 1); // zufällige Bonität auswählen
    	for (GiroKonditionen element : giroConditionList) {
    		if (element.getCreditRating() == creditRating) return element;
    	}
    	return null;
    }
	
}
