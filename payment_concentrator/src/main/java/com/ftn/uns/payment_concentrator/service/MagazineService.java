package com.ftn.uns.payment_concentrator.service;

import java.util.List;

import com.ftn.uns.payment_concentrator.model.*;

public interface MagazineService {
	
	public Magazine findOne(String issn);

	public List<Magazine> findAll();

	public Magazine save(Magazine magazine);

	public void delete(Magazine magazine);

	public Magazine update(Magazine magazine, String issn);

}
