package com.ftn.uns.payment_concentrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.uns.payment_concentrator.model.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long>{

}
