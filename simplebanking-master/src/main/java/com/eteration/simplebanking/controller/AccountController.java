package com.eteration.simplebanking.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eteration.simplebanking.config.AuthenticationConfigConstants;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Amount;
import com.eteration.simplebanking.model.ITransaction;
import com.eteration.simplebanking.repositorys.TransactionTypeRepository;
import com.eteration.simplebanking.services.AccountService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.mapping.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RequestMapping(value = "account/v1/")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionTypeRepository transactionTypeRepository;
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
    public  String restCredit(@PathVariable(value = "accountNumber") String accountNumber, @RequestBody Amount amount)
    {
        return accountService.Credit(accountNumber,amount);
    }
    @RequestMapping(method = RequestMethod.POST,value = "debit/{accountNumber}")
    public String restDebit(@PathVariable(value = "accountNumber")String accountNumber,  @RequestBody Amount amount)
    {
        return accountService.Debit(accountNumber,amount);

    }
    @RequestMapping(method = RequestMethod.GET,value = "{accountNumber}")
    public String restAccountCurrentData(@PathVariable(value = "accountNumber")String accountNumber)
    {
        return accountService.AccountCurrentData(accountNumber);
    }
    @RequestMapping(method = RequestMethod.GET,value = "insert")
    public String insert()
    {


        return "xzxz";

    }
}