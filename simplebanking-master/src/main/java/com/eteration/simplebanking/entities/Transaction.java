package com.eteration.simplebanking.entities;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table (name = "Transaction")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Integer id;
    @Column(name = "amount")
    private Double amount;
    @OneToOne
    @JoinColumn(name = "TypeId",referencedColumnName = "id")
    TransactionType transactionType;

    @Column(name = "createDate")
    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }


}
