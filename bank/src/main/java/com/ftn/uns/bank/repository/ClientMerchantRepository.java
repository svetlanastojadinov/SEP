package com.ftn.uns.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.bank.model.ClientMerchant;

@Repository
public interface ClientMerchantRepository extends JpaRepository<ClientMerchant,String>{

}
