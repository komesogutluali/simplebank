package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.entities.Transaction;
import com.eteration.simplebanking.repositorys.BankAccountRepository;
import com.eteration.simplebanking.repositorys.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    public void findAccount(String s) {
    }

    public TransactionStatus Credit(String accountNumber, Double amount)
    {
        Transaction transaction=new Transaction();
        transaction.setAmount(amount);

        return null;
    }
    public TransactionStatus Debit(String accountNumber,Double amount)
    {
        return null;
    }}
