package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends BillPaymentTransaction{

    public PhoneBillPaymentTransaction(String payee,String phoneNumber,double amount) {
        super(payee,phoneNumber,amount);
    }
}
