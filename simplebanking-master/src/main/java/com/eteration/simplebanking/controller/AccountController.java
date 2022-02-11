package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.ITransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "account/v1")
@RestController
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
	@RequestMapping(method = RequestMethod.POST,value = "credit/{accountNumber}")
    public TransactionStatus restCredit(@PathVariable(value = "accountNumber") String accountNumber, @RequestParam(value = "amount") Double amount)
    {
        return null;
    }
    @RequestMapping(method = RequestMethod.POST,value = "debit/{accountNumber}")
    public TransactionStatus restDebit(@PathVariable(value = "accountNumber") String accountNumber, @RequestParam(value = "amount") Double amount)
    {
        return null;
    }
}