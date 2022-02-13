package com.eteration.simplebanking.model;


import java.util.Date;

public  class Transaction {

    public Date createDate;
    public double amount;
    private String payee;
    public String phoneNumber;
    public Transaction(double amount)
    {
        this.amount=amount;
    }
    public Transaction(String payee,String phoneNumber,double amount)
    {
        this.payee=payee;
        this.phoneNumber=phoneNumber;
        this.amount=amount;
    }
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Date getCreateDate()
    {
        createDate=new Date();
        return createDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "createDate=" + createDate +
                ", amount=" + amount +
                '}';
    }
}
