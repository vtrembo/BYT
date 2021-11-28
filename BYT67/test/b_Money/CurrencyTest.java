package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}
    
	@Test
	public void testGetName() {
		//Check if the method getName returns correct currency name
	    assertEquals("SEK", SEK.getName());
	    assertEquals("DKK", DKK.getName());
	    assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		 //Check if the method getRate returns correct currency rate
        assertEquals(Double.valueOf(0.15), SEK.getRate());
        assertEquals(Double.valueOf(0.3), DKK.getRate());
        assertEquals(Double.valueOf(1.5), EUR.getRate());
	}

	@Test
	public void testSetRate() {
		//Check setRate method
		SEK.setRate(2.0);
		assertEquals(Double.valueOf(2.0), SEK.getRate());
	}

	@Test
	public void testGlobalValue() {
		//Check conversion tp universal value
		assertEquals(Integer.valueOf(20), SEK.universalValue(100), 0.00001);
	}

	@Test
	//check conversion to different currency
	public void testValueInThisCurrency() {
       assertEquals(Integer.valueOf(10), SEK.valueInThisCurrency(100, EUR));
       assertEquals(Integer.valueOf(100), EUR.valueInThisCurrency(10, SEK));	
       }
	}