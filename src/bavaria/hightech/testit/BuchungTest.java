package bavaria.hightech.testit;

import bavaria.hightech.banking.Buchungsliste;
import bavaria.hightech.banking.Buchung;
import bavaria.hightech.banking.Money;
import bavaria.hightech.banking.Money.Currency;

/**
 * @author mrochow
 */
public class BuchungTest {
	
	/**
	 * Starts the test environment
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Buchungsliste b1 = new Buchungsliste();
		
		// Liste befüllen
		b1.add(new Buchung("Test", new Money(200, Currency.EURO), "+"));
		b1.add(new Buchung("Foobar", new Money(800, Currency.EURO), "+"));
		b1.add(new Buchung("Einzahlung", new Money(100, Currency.EURO), "+"));
		b1.add(new Buchung("Auszahlung", new Money(500, Currency.EURO), "-"));
		b1.add(new Buchung("Überweisung", new Money(200, Currency.EURO), "-"));
		
		// Test von next()
		System.out.println("Test von next()");
		System.out.println("-----------------------");
		System.out.println("<<Grund: Betrag>>");
		
		for (int i = 0; i < 10; i++) {
			Buchung b = b1.next();
			if (b != null) System.out.println(b.toString());
		}
		
		// Test von resetIteration()
		System.out.println("\n\nTest von resetIteration()");
		System.out.println("-----------------------");
		b1.resetIteration();
		System.out.println("<<Grund: Betrag>>");
		
		for (int i = 0; i < 10; i++) {
			Buchung b = b1.next();
			if (b != null) System.out.println(b.toString());
		}
		
		// Test toString()
		System.out.println("\n\nTest von toString() - dämlich I know...");
		System.out.println("-----------------------");
		System.out.println("<<Grund: Betrag>>");
        System.out.println(b1.toString());
        
        // Test von clear()
        b1.clear();
        System.out.println("\n\nTest von clear()");
		System.out.println("-----------------------");
		System.out.println("<<Grund: Betrag>>");
		
		for (int i = 0; i < 10; i++) {
			Buchung b = b1.next();
			if (b != null) System.out.println(b.toString());
		}
		
	}

}
