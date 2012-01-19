package bavaria.hightech.testit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bavaria.hightech.banking.Money;
import bavaria.hightech.banking.Money.Currency;

/**
 * @author mrochow
 */
public class MoneyTest {
	
	private Money mEuro1;
	private Money mEuro2;
	private Money mEuro3;
	private Money mDollar1;
	private static final long amount1 = 236;
	private static final long amount2 = 589;
	private static final long amount3 = 632;
	private static final long DOLLARKURS = 69171;
	private static final long EUROKURS = 14458;
	
	/**
	 * Set up the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mEuro1 = new Money(amount1, Currency.EURO);
		mEuro2 = new Money(amount2, Currency.EURO);
		mEuro3 = new Money(amount1, Currency.EURO);
		mDollar1 = new Money(amount3, Currency.DOLLAR);
	}
	
	/**
	 * Test add method
	 */
	@Test
	public void testAdd() {
		mEuro1.add(mEuro2);
		long expectedResult = amount1 + amount2;
		assertEquals(expectedResult, mEuro1.getAmount());
	}
	
	/**
	 * Test subtraktion method
	 */
	@Test
	public void testSub() {
		mEuro2.sub(mEuro1);
		long expectedResult = amount2 - amount1;
		assertEquals(expectedResult, mEuro2.getAmount());
	}
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals() {
		assertTrue(mEuro1.equals(mEuro3));
		assertFalse(mEuro1.equals(mEuro2));
	}
	
	/**
	 * Test is greater method
	 */
	@Test
	public void testIsGreater() {
		assertTrue(mEuro2.isGreater(mDollar1));
		assertTrue(mEuro2.isGreater(mEuro1));
	}
	
	/**
	 * Test get amount method
	 */
	@Test
	public void testGetAmount() {
		assertEquals(amount1, mEuro1.getAmount());
	}
	
	/**
	 * Test get currency method
	 */
	@Test
	public void testGetCurrency() {
		assertEquals(mDollar1.getCurrency(), Currency.DOLLAR);
	}
	
	/**
	 * Test convert method
	 */
	@Test
	public void testConvert() {
		long expectedResult = (amount3 * DOLLARKURS) / 100000;
		long actualResult = mDollar1.convert(Currency.EURO);
		assertEquals(expectedResult, actualResult);
		System.out.println(amount3 + "$C == " + actualResult + "C");
		
		long expectedResult2 = (amount1 * EUROKURS) / 10000;
		long actualResult2 = mEuro1.convert(Currency.DOLLAR);
		assertEquals(expectedResult2, actualResult2);
		System.out.println(amount1 + "C == " + actualResult2 + "$C");
	}

}
