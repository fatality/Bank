package bavaria.hightech.testit;

import bavaria.hightech.banking.BankImpl;
import bavaria.hightech.banking.Money;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.time.Zeitgeber;

/**
 * @author mrochow
 */
public class Bank {
	
	public static BankImpl bank = new BankImpl(2, 5);
	
	/**
	 * Starts the test runner
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Anlegen
		bank.addFestgeldKonto("Anja", 1);
		bank.addFestgeldKonto("Marc", 3);
		bank.addFestgeldKonto("Stefan", 12);
		
		bank.addGiroKonto("Max");
		bank.addGiroKonto("Mustermann");
		bank.addGiroKonto("Meixner");
		
		// Ausgeben
		System.out.println("\n");
		bank.list();
		System.out.println("\n");
		
		// Einzahlen
		bank.creditEntry(1000, new Money(1000, Currency.EURO), "Einzahlen");
		bank.creditEntry(1001, new Money(1000, Currency.EURO), "Einzahlen");
		bank.creditEntry(1002, new Money(1000, Currency.EURO), "Einzahlen");
		bank.creditEntry(1003, new Money(1000, Currency.EURO), "Einzahlen");
		bank.creditEntry(1004, new Money(1000, Currency.EURO), "Einzahlen");
		bank.creditEntry(1005, new Money(1000, Currency.EURO), "Einzahlen");
		
		// Ausgeben
		System.out.println("\n");
		bank.list();
		System.out.println("\n");
		
		// Abheben
		bank.debitEntry(1000, new Money(200, Currency.EURO), "Auszahlen");
		bank.debitEntry(1001, new Money(200, Currency.EURO), "Auszahlen");
		bank.debitEntry(1002, new Money(200, Currency.EURO), "Auszahlen");
		bank.debitEntry(1003, new Money(200, Currency.EURO), "Auszahlen");
		bank.debitEntry(1004, new Money(200, Currency.EURO), "Auszahlen");
		bank.debitEntry(1005, new Money(200, Currency.EURO), "Auszahlen");
		
		// Ausgeben
		System.out.println("\n");
		bank.list();
		System.out.println("\n");
		
		// Überweisen
		bank.transferTo(1003, 1000, new Money(400, Currency.EURO), "Sparen");
		bank.transferTo(1004, 1001, new Money(400, Currency.EURO), "Sparen");
		bank.transferTo(1005, 1002, new Money(400, Currency.EURO), "Sparen");
		
		// Ausgeben
		System.out.println("\n");
		bank.list();
		System.out.println("\n");
		
		bank.showBankConditions();
		
		// Verzinsen
		bank.verzinsen();
		
		// Kontauszüge drucken
		System.out.println(bank.getKontoauszug(1000));
		System.out.println(bank.getKontoauszug(1001));
		System.out.println(bank.getKontoauszug(1002));
		System.out.println(bank.getKontoauszug(1003));
		System.out.println(bank.getKontoauszug(1004));
		System.out.println(bank.getKontoauszug(1005));
		
		Zeitgeber.getZeitgeber().elapsTime(5);
		System.out.println(bank.getKontoauszug(1004));
	}
	
}
