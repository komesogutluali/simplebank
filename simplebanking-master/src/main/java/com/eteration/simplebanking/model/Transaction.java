package com.eteration.simplebanking.model;



public  class Transaction extends ATransaction {

    @Override
    public String toString() {
        return "Transaction{" +
                "createDate=" + createDate +
                ", amount=" + amount +
                '}';
    }
}
