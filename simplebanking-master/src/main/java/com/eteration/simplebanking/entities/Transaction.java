package com.eteration.simplebanking.entities;

import javax.persistence.*;

@Entity
@Table (name = "Transaction")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;
    @Column(name = "amount")
    private Double amount;
    @OneToOne
    @JoinColumn(name = "TypeId",referencedColumnName = "id")
    TransactionType transactionType;
    @OneToOne
    @JoinColumn(name = "accountId",referencedColumnName = "id")
    BankAccount bankAccount;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "approvalcode",length =350)
    String  approvalcode;
    public String getApprovalcode() {
        return approvalcode;
    }

    public void setApprovalcode(String approvalcode) {
        this.approvalcode = approvalcode;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }


}
