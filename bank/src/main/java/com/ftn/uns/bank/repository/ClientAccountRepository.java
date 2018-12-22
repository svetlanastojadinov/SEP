package com.ftn.uns.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.bank.model.ClientAccount;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, String>{

}
