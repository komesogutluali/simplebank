package com.eteration.simplebanking.repositorys;

import com.eteration.simplebanking.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
