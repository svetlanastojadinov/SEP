package com.ftn.uns.payment_concentrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.uns.payment_concentrator.model.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
