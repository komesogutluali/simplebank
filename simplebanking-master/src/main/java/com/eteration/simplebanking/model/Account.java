package com.eteration.simplebanking.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public  String owner;
    public  String accountNumber;
    public  double balance;
    public List<ATransaction> listTransactions;
    public List<PhoneBillPaymentTransaction> listPhoneBillPaymentTransaction;
    public Account(String owner,String accountNumber)
    {
        this.owner=owner;
        this.accountNumber=accountNumber;
        this.balance=0.0;
        listTransactions=new ArrayList<ATransaction>();
        listPhoneBillPaymentTransaction=new ArrayList<PhoneBillPaymentTransaction>();
    }
    public List<ATransaction> getTransactions()
    {
        return listTransactions;
    }
    public double getBalance()
    {
        return balance;
    }
    public String getOwner()
    {
        return owner;
    }
    public String getAccountNumber()
    {
        return accountNumber;
    }


    public void deposit(double amount) throws InsufficientBalanceException {
        balance=balance+amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {

            double newbalance=balance-amount;
            if(newbalance>0||0==newbalance)
             balance=newbalance;
            else
                throw new InsufficientBalanceException();
    }
    public void phonebill(ATransaction transactions) throws InsufficientBalanceException {



    }
    public void post(ATransaction transactions,String TransactionKey) throws InsufficientBalanceException {

        switch (TransactionKey)
        {
            case  "depo":deposit(transactions.amount);listTransactions.add(transactions);break;
            case  "with":withdraw(transactions.amount);listTransactions.add(transactions);break;

        }
    }
}
