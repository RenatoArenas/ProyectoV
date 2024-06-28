package com.cibertec.proyectov.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectov.model.PurchaseModel;
import com.cibertec.proyectov.repository.PurchaseRepository;
import com.cibertec.proyectov.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Override
	public List<PurchaseModel> findAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public PurchaseModel save(PurchaseModel purchase) {

		return purchaseRepository.save(purchase);
	}

	@Override
	public void delete(Long id) {
		
		Optional<PurchaseModel> purchaseex = purchaseRepository.findById(id);
		
		if (!purchaseex.isEmpty()) {
			PurchaseModel purchase = purchaseex.get();
			
			purchaseRepository.delete(purchase);
	    } else {
	    	throw new RuntimeException("No existe esta venta");
	    }
		
	}

}
