package com.eteration.simplebanking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eteration.simplebanking.config.AuthenticationConfigConstants;
import com.eteration.simplebanking.config.PersistenceConfig;
import com.eteration.simplebanking.entities.BankAccount;
import com.eteration.simplebanking.entities.Transaction;

import com.eteration.simplebanking.model.Amount;
import com.eteration.simplebanking.repositorys.BankAccountRepository;
import com.eteration.simplebanking.repositorys.TransactionRepository;
import com.eteration.simplebanking.repositorys.TransactionTypeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityTransaction;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;


@Service
public class AccountService {
    EntityTransaction et;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionTypeRepository transactionTypeRepository;
    @Autowired
    PersistenceConfig persistenceConfig;

    public BankAccount findAccount(String accountNumber) {
        BankAccount bankAccount=bankAccountRepository.findAll().stream().filter(b->b.getAccountnumber().equals(accountNumber)).findFirst().get();
    return bankAccount;
    }
    public String Credit(String accountNumber, Amount amount)
    {
        JSONObject response=new JSONObject();
        et = persistenceConfig.entityManagerFactory().getNativeEntityManagerFactory().createEntityManager().getTransaction();
        try
        {
            et.begin();
            String localDateTime=LocalDateTime.now().toString();
            BankAccount bankAccount=bankAccountRepository.findAll().stream().filter(b->b.getAccountnumber().equals(accountNumber)).findFirst().get();
            Transaction transaction=new Transaction();
            transaction.setAmount(amount.getAmount().doubleValue());
            transaction.setTransactionType(transactionTypeRepository.findAll().stream().filter(t->t.getId().equals(1)).findFirst().get());
            transaction.setBankAccount(bankAccount);
            transaction.setCreateDate(LocalDateTime.now().toString());
            bankAccount.setBalance(bankAccount.getBalance()+amount.getAmount().doubleValue());
            String token = JWT.create()
                    .withSubject(Base64.getEncoder().encodeToString((localDateTime+"."+amount+"."+transaction.getTransactionType().getType()+"."+bankAccount.getId()).getBytes(StandardCharsets.UTF_8)))
                    .withExpiresAt(new Date(System.currentTimeMillis()))
                    .sign(Algorithm.HMAC256(AuthenticationConfigConstants.SECRET.getBytes()));
            transaction.setApprovalcode(token);
            bankAccountRepository.save(bankAccount);
            transactionRepository.save(transaction);
            et.commit();
            response.put("status","OK");
            response.put("approvalCode",token);
        }catch (Exception exception)
        {
            et.rollback();
            response.put("status","Error");
        }

        return response.toString();
    }
    public String Debit(String accountNumber,Amount amount)
    {
        JSONObject response=new JSONObject();
        et = persistenceConfig.entityManagerFactory().getNativeEntityManagerFactory().createEntityManager().getTransaction();
        try {
            et.begin();
            BankAccount bankAccount = bankAccountRepository.findAll().stream().filter(b -> b.getAccountnumber().equals(accountNumber)).findFirst().get();
            if (bankAccount.getBalance().doubleValue() != 0) {
                String localDateTime = LocalDateTime.now().toString();
                Transaction transaction = new Transaction();
                transaction.setAmount(amount.getAmount().doubleValue());
                transaction.setTransactionType(transactionTypeRepository.findAll().stream().filter(t -> t.getId().equals(2)).findFirst().get());
                transaction.setBankAccount(bankAccount);
                transaction.setCreateDate(localDateTime);
                bankAccount.setBalance(bankAccount.getBalance() - amount.getAmount().doubleValue());
                String token = JWT.create()
                        .withSubject(Base64.getEncoder().encodeToString((localDateTime + "." + amount + "." + transaction.getTransactionType().getType() + "." + bankAccount.getId()).getBytes(StandardCharsets.UTF_8)))
                        .withExpiresAt(new Date(System.currentTimeMillis()))
                        .sign(Algorithm.HMAC256(AuthenticationConfigConstants.SECRET.getBytes()));
                transaction.setApprovalcode(token);
                bankAccountRepository.save(bankAccount);
                transactionRepository.save(transaction);
                response.put("status", "OK");
                response.put("approvalCode", token);
                et.commit();
            }
        }
        catch (Exception ex)
        {
            et.rollback();
            response.put("status","Error");
        }
        return response.toString();
        }


    public String AccountCurrentData(String accountNumber)
    {
        BankAccount bankAccount=bankAccountRepository.findAll().stream().filter(b->b.getAccountnumber().equals(accountNumber)).findFirst().get();
        JSONObject jsonAccountCurrentData=new JSONObject();
        jsonAccountCurrentData.put("accountNumber",bankAccount.getAccountnumber());
        jsonAccountCurrentData.put("owner",bankAccount.getOwner());
        jsonAccountCurrentData.put("balance",bankAccount.getBalance().doubleValue()+"");
        jsonAccountCurrentData.put("createDate",bankAccount.getCreateDate());
        JSONArray jsonArrayTransactionData=new JSONArray();
         transactionRepository.findAll().stream().filter(tr->tr.getBankAccount().getId().equals(bankAccount.getId())).
                 collect(Collectors.toList()).forEach(tran-> {
             JSONObject jsonTransactionData=new JSONObject();

             jsonTransactionData.put("date", tran.getCreateDate());
             jsonTransactionData.put("amount",tran.getAmount());
             jsonTransactionData.put("type",tran.getTransactionType().getType());
             jsonTransactionData.put("approvalCode",tran.getApprovalcode());
             jsonArrayTransactionData.put(jsonTransactionData);
                 }
         );
        jsonAccountCurrentData.put("transactions",jsonArrayTransactionData);

        return jsonAccountCurrentData.toString();
    }
}
