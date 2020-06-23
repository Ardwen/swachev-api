package com.evliion.ev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evliion.ev.model.Transaction;



@Repository
public interface TransRepository extends  JpaRepository<Transaction, Long>{
   
}
