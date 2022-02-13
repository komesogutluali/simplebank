package com.eteration.simplebanking.model;

public  class BillPaymentTransaction extends ATransaction{


    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    private String payee;
    public BillPaymentTransaction(String payee)
    {
        this.payee=payee;
    }
}
