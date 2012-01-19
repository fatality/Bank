package bavaria.hightech.testit;

import bavaria.hightech.banking.FestgeldKonditionen;
import bavaria.hightech.banking.FestgeldKonto;
import bavaria.hightech.banking.GiroKonditionen;
import bavaria.hightech.banking.GiroKonto;
import bavaria.hightech.banking.Money;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.NotEnoughMoneyException;
import bavaria.hightech.exceptions.NullMoneyException;

/**
 * @author mrochow
 */
public class Kunde {
	
	/**
	 * Starts test program
	 * 
	 * @param args
	 * @throws NotEnoughMoneyException
	 * @throws NullMoneyException 
	 */
	public static void main(String[] args) throws NotEnoughMoneyException, NullMoneyException {
		FestgeldKonto test1 = new FestgeldKonto(12345, "Max Mustermann", new FestgeldKonditionen(3, 5));
		test1.creditEntry(new Money(5000, Currency.EURO), "Einzahlung");
		test1.debitEntry(new Money(1000, Currency.EURO), "iPod");
		test1.verzinsen();
		System.out.println(test1);
		
		GiroKonto test2 = new GiroKonto(10001, "Lady Gaga", new GiroKonditionen(2, 3, new Money(10000, Currency.EURO), 1));
		test2.creditEntry(new Money(500, Currency.EURO), "Lohn");
		test2.debitEntry(new Money(200, Currency.EURO), "Neue Kleidung");
		test2.verzinsen();
		System.out.println(test2);
	}

}
