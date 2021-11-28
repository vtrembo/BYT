package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Someone");
		try {
			//check if account exists
			SweBank.openAccount("Ulrika");
		}catch (AccountExistsException ignored){
			return;
		}
		fail("Shouldn't allow to open accounts with same id");  // should fail
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(100000, SEK)); // should fail
		assertEquals(Integer.valueOf(100000), SweBank.getBalance("Ulrika"));
		try {
			SweBank.deposit("UnexistingGuy", new Money(100000, SEK));
		}catch (AccountDoesNotExistException ignored){
			return;
		}
		fail("Shouldn't allow to deposit on account that doesn't exist");
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(100000, SEK));
		SweBank.withdraw("Ulrika", new Money(10000, SEK));
		assertEquals(Integer.valueOf(90000), SweBank.getBalance("Ulrika")); // should fail
		try {
			SweBank.withdraw("UnexistingGuy", new Money(10000, SEK));
		}catch (AccountDoesNotExistException ignored){
			return;
		}
		fail("Shouldn't allow to withdraw from account that doesn't exist");
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika", new Money(100000, SEK));
		assertEquals(Integer.valueOf(100000), SweBank.getBalance("Ulrika"));
		try {
			SweBank.getBalance("UnexistingGuy");
		}catch (AccountDoesNotExistException ignored){
			return;
		}
		fail("Shouldn't allow to look at the balance of account that doesn't exist");
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(500000, SEK));
		Nordea.transfer("Bob", SweBank, "Bob", new Money(300000, SEK));
		assertEquals(Integer.valueOf(300000), SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(200000), Nordea.getBalance("Bob"));
		SweBank.transfer("Bob", "Ulrika", new Money(200000, SEK));
		assertEquals(Integer.valueOf(100000), SweBank.getBalance("Bob")); // failed
		assertEquals(Integer.valueOf(200000), SweBank.getBalance("Ulrika"));
		try{
			SweBank.transfer("UnexistingGuy", "Ulrika", new Money(100000, SEK));
		}catch (AccountDoesNotExistException exc){
			try{
				SweBank.transfer("Ulrika", "UnexistingGuy", new Money(100000, SEK));
			}catch (AccountDoesNotExistException ignored){
				return;
			}
		}
		fail("Shouldn't allow to send money from/to account that doesn't exist");
	}


	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(500000, SEK));
		SweBank.deposit("Ulrika", new Money(500000, SEK));
		SweBank.addTimedPayment("Bob", "first", 0, 0, new Money(10000, SEK), SweBank, "Ulrika");
		for(int timer = 0; timer < 15; ++timer){
			SweBank.tick();
			if(timer==3){
				assertEquals(Integer.valueOf(460000), SweBank.getBalance("Bob")); // failed
				assertEquals(Integer.valueOf(540000), SweBank.getBalance("Ulrika"));
				SweBank.removeTimedPayment("Bob", "first");
				SweBank.addTimedPayment("Ulrika", "second", 2, 3, new Money(30000, SEK), SweBank, "Bob");
			}
		}		
		assertEquals(Integer.valueOf(450000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(550000), SweBank.getBalance("Bob"));
	}
}