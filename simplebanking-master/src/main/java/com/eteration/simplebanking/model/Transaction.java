package com.eteration.simplebanking.model;


import java.util.Date;

// This class is a place holder you can change the complete implementation
public  class Transaction implements ITransaction {
	public Date createDate;
	public Double amount;

    public Date getCreateDate()
    {
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
