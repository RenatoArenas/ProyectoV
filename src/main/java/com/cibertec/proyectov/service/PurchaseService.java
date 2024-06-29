package com.cibertec.proyectov.service;

import java.util.List;

import com.cibertec.proyectov.model.PurchaseModel;

public interface PurchaseService {
	
	List<PurchaseModel> findAll();
	
	PurchaseModel save(PurchaseModel purchase);
	
	void delete(Long id);
	
	String report(Long id);
	
}
