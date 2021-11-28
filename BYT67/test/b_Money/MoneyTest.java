package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
        //Check if the method getAmount returns correct amount
	    assertEquals(Integer.valueOf(10000), SEK100.getAmount());
	    assertEquals(Integer.valueOf(1000), EUR10.getAmount());
	    assertEquals(Integer.valueOf(0), EUR0.getAmount());
	    assertEquals(Integer.valueOf(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
        //Check if the method getCurrency returns correct currency
        assertEquals(SEK, SEK100.getCurrency());
        assertEquals(EUR, EUR10.getCurrency());
        assertEquals(EUR, EUR0.getCurrency());
        assertEquals(SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() throws NumberFormatException {
		//Check if it makes correctly String representation of money
        assertEquals("100.00 SEK", SEK100.toString());
        assertEquals("10.00 EUR", EUR10.toString());
        assertEquals("0.0 EUR", EUR0.toString());
        assertEquals("-100.00 SEK", SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		// Checking universalValue method
		assertEquals(Integer.valueOf(0), EUR0.universalValue());
	}

	@Test
			(expected = NullPointerException.class)
	public void testEqualsMoney() {
		//Check if different money have the same value
		String tmsg = "Those money are equal";
		String fmsg = "Those money are NOT equal";
		assertTrue(tmsg, SEK100.equals(EUR10));
		assertFalse(fmsg, EUR10.equals(SEK200));;
		assertFalse(fmsg, SEKn100.equals(null));
	}

	@Test
			(expected = NullPointerException.class)
	public void testAdd() {
		// Testing addition of other currencies to different this currencies
		String tmsg = "Should be the same";
		assertTrue(tmsg, SEK100.add(EUR10).equals(new Money(20000, SEK)));
		assertTrue(tmsg, SEKn100.add(SEK100).equals(new Money(0, SEK)));
		assertTrue(tmsg, SEKn100.add(null).equals(SEKn100));
	}

	@Test
			(expected = NullPointerException.class)
	public void testSub() {
		// Testing subtraction of other currencies to different this currencies
		String tmsg = "Should be the same";
		assertTrue(tmsg, SEK100.sub(EUR10).equals(new Money(0, SEK)));
		assertTrue(tmsg, EUR10.sub(SEK200).equals(new Money(-1000, EUR)));
		assertTrue(tmsg, SEK0.sub(EUR0).equals(new Money(0, SEK)));
		assertTrue(tmsg, EUR0.sub(SEKn100).equals(new Money(1000, EUR)));
		assertTrue(tmsg, SEKn100.sub(null).equals(new Money(-20000, SEK)));
	}

	@Test
	public void testIsZero() {
		// Testing if the amount is 0.0
		String tmsg = "Those money are equal";
		String fmsg = "Those money are NOT equal";
		assertFalse(fmsg, SEK100.isZero());
		assertFalse(fmsg, EUR10.isZero());
		assertTrue(tmsg, SEK0.isZero());
		assertTrue(tmsg, EUR0.isZero());
		assertFalse(fmsg, SEKn100.isZero());
	}

	@Test
	public void testNegate() {
		// Testing negation of money amount
		String tmsg = "Should be the same";
		assertTrue(tmsg, SEK100.negate().equals(new Money(-10000, SEK)));
		assertTrue(tmsg, EUR10.negate().equals(new Money(-1000, EUR)));
		assertTrue(tmsg, SEK200.negate().equals(new Money(-20000, SEK)));
		assertTrue(tmsg, EUR0.negate().equals(new Money(0, EUR)));
		assertTrue(tmsg, SEKn100.negate().equals(new Money(10000, SEK)));
	}

	@Test
	public void testCompareTo() {
		// Comparing amounts of money
		assertEquals(0, SEK100.compareTo(EUR10));
		assertEquals(1, EUR20.compareTo(SEK0));
		assertEquals(0, SEK0.compareTo(EUR0));
		assertEquals(-1, SEKn100.compareTo(null));
	}
}
