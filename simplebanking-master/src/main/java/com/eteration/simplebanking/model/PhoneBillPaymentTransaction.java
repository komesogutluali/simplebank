package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends BillPaymentTransaction{

    private String phoneNumber;
    public PhoneBillPaymentTransaction(String payee,String phoneNumber,Double amount) {
        super(payee);
        this.phoneNumber=phoneNumber;
        this.amount=amount;

    }
}
