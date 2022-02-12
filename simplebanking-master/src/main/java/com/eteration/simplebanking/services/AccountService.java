package com.eteration.simplebanking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eteration.simplebanking.config.AuthenticationConfigConstants;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.entities.BankAccount;
import com.eteration.simplebanking.entities.Transaction;
import com.eteration.simplebanking.entities.TransactionType;
import com.eteration.simplebanking.model.Amount;
import com.eteration.simplebanking.repositorys.BankAccountRepository;
import com.eteration.simplebanking.repositorys.TransactionRepository;
import com.eteration.simplebanking.repositorys.TransactionTypeRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionTypeRepository transactionTypeRepository;
    public void findAccount(String s) {
    }
    public void insert()
    {
/*
        BankAccount bankAccount=new BankAccount();
        bankAccount.setOwner("Ali");
        bankAccount.setBalance(0.0);
        bankAccount.setAccountnumber("7454");
        bankAccount.setCreateDate(LocalDateTime.now().toString());
        bankAccountRepository.save(bankAccount);

        TransactionType transactionType=new TransactionType();
        transactionType.setType("DepositTransaction");
        TransactionType transactionType1=new TransactionType();
        transactionType1.setType("WithdrawalTransaction");
        transactionTypeRepository.save(transactionType);
        transactionTypeRepository.save(transactionType1);
*/



    }
    public JSONObject Credit(String accountNumber, Amount amount)
    {
        String localDateTime=LocalDateTime.now().toString();
        BankAccount bankAccount=bankAccountRepository.findAll().stream().filter(b->b.getAccountnumber().equals(accountNumber)).findFirst().get();
        Transaction transaction=new Transaction();
        transaction.setAmount(amount.getAmount().doubleValue());
        transaction.setTransactionType(transactionTypeRepository.findAll().stream().filter(t->t.getId().equals(1)).findFirst().get());
        transaction.setBankAccount(bankAccount);
        transaction.setCreateDate(LocalDateTime.now().toString());
        bankAccount.setBalance(bankAccount.getBalance()+amount.getAmount().doubleValue());
        bankAccountRepository.save(bankAccount);
        transactionRepository.save(transaction);
        String token = JWT.create()
                .withSubject(localDateTime+amount+transaction.getTransactionType().getType()+bankAccount.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
        JSONObject response=new JSONObject();
        response.put("status","OK");
        response.put("approvalCode",token);
        return response;
    }
    public String Debit(String accountNumber,Amount amount)
    {
        BankAccount bankAccount=bankAccountRepository.findAll().stream().filter(b->b.getAccountnumber().equals(accountNumber)).findFirst().get();
        if(bankAccount.getBalance().doubleValue()!=0)
        {
           String localDateTime=LocalDateTime.now().toString();
            Transaction transaction=new Transaction();
            transaction.setAmount(amount.getAmount().doubleValue());
            transaction.setTransactionType(transactionTypeRepository.findAll().stream().filter(t->t.getId().equals(1)).findFirst().get());
            transaction.setBankAccount(bankAccount);
            transaction.setCreateDate(localDateTime);
            bankAccount.setBalance(bankAccount.getBalance()+amount.getAmount().doubleValue());
            bankAccountRepository.save(bankAccount);
            transactionRepository.save(transaction);
            String token = JWT.create()
                    .withSubject(localDateTime+amount+transaction.getTransactionType().getType()+bankAccount.getId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
            return token;
        }
        return "0";
    }}
