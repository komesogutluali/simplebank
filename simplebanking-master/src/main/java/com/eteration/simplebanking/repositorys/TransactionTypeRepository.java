package com.eteration.simplebanking.repositorys;

import com.eteration.simplebanking.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType,Long> {
}
