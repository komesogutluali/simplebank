package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.ITransaction;
import org.springframework.http.ResponseEntity;

// This class is a place holder you can change the complete implementation
public class AccountController {


    public ResponseEntity<Account> getAccount(String accountNumber) {
        return null;
    }

    public ResponseEntity<TransactionStatus> credit(String accountNumber, ITransaction transaction) {
        return null;
    }
    public ResponseEntity<TransactionStatus> debit(String accountNumber, ITransaction transaction) {
        return null;
	}
}