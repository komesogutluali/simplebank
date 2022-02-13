package com.eteration.simplebanking.model;

public  class BillPaymentTransaction extends Transaction{

    public BillPaymentTransaction(String payee,String phoneNumber,double amount) {
        super(payee,phoneNumber,amount);
    }
}
