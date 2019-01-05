package com.ftn.uns.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.pcc.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
