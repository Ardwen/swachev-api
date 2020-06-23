package com.evliion.ev.repository;

import com.evliion.ev.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
}