package com.eteration.simplebanking.controller;


import com.eteration.simplebanking.model.ATransaction;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Amount;
import com.eteration.simplebanking.repositorys.TransactionTypeRepository;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




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
    public ResponseEntity<TransactionStatus> credit(String accountNumber, ATransaction transaction) {
        return null;
    }
    public ResponseEntity<TransactionStatus> debit(String accountNumber, ATransaction transaction) {
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