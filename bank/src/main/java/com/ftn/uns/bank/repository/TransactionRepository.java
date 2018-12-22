package com.ftn.uns.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.bank.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
