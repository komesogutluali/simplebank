package com.eteration.simplebanking.model;


import java.util.Date;

// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {
    public Date getCreateDate()
    {
        createDate=new Date();
        return createDate;
    }
    public DepositTransaction(Double amount) {
        this.amount=amount;
    }
}
