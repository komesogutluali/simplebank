package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {
	
	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100.0);
		assertTrue(account.getBalance() == 100.0);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100.0);
		assertTrue(account.getBalance() == 100.0);
		account.withdraw(50.0);
		assertTrue(account.getBalance() == 50.0);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			account.deposit(100.0);
			account.withdraw(500.0);
		});

	}
	
	@Test
	public void testTransactions() throws InsufficientBalanceException {
		// Create account
		Account account = new Account("Canan Kaya", "1234");
		assertTrue(account.getTransactions().size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100.0);
		assertTrue(depositTrx.getCreateDate() != null);
		account.post(depositTrx,"depo");
		assertTrue(account.getBalance() == 100.0);
		assertTrue(account.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60.0);
		assertTrue(withdrawalTrx.getCreateDate() != null);
		account.post(withdrawalTrx,"with");
		assertTrue(account.getBalance() == 40.0);
		assertTrue(account.getTransactions().size() == 2);
	}
}
