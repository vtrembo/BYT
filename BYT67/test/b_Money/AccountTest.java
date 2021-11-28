package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		String tmsg = "Should exist"; // True message
		String fmsg = "Should NOT exist"; // False message
		testAccount.addTimedPayment("1", 1, 1, new Money(10000, SEK), SweBank, "Alice" );
		assertTrue(tmsg, testAccount.timedPaymentExists("1"));
		assertFalse(fmsg, testAccount.timedPaymentExists("2"));

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 2, 0, new Money(10000, SEK), SweBank, "Alice");
        for (int i = 0; i < 10; i++) {
            testAccount.tick();
        }
        assertEquals(Integer.valueOf(975000), testAccount.getBalance());
    }


	@Test
	public void testAddWithdraw() {
		//Withdrawing more than available
		String tmsg = "The same";
		testAccount.withdraw(new Money(1000000, SEK));
		assertTrue(tmsg, testAccount.getBalance().equals(new Money(9000000, SEK)));
		testAccount.withdraw(new Money(2000000, SEK));
		assertTrue(tmsg, testAccount.getBalance().equals(new Money(7000000, SEK)));
	}
	@Test
	public void testGetBalance() {
		String tmsg = "The same";
		assertTrue(tmsg, testAccount.getBalance().equals(new Money(10000000, SEK)));

	}
}
