package com.ftn.uns.payment_concentrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ftn.uns.payment_concentrator.model.*;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, String> {

}