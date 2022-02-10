package com.eteration.simplebanking.model;

public class Account {
    public  String owner;
    public  String accountNumber;
    public  Double balance;
    public ITransaction transactions;
    public Account(String owner,String accountNumber)
    {
        this.owner=owner;
        this.accountNumber=accountNumber;
        this.balance=0.0;
    }
    public ITransaction getTransactions()
    {
        return transactions;
    }
    public Double getBalance()
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


    public void deposit(Double amount) {
        balance+=amount;
    }

    public void withdraw(Double amount) {
        balance-=amount;
    }

    public void post(ITransaction transactions) {
        this.transactions=transactions;
    }
}
