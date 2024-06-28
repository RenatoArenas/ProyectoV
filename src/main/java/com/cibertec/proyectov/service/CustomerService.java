package com.cibertec.proyectov.service;

import java.util.List;

import com.cibertec.proyectov.model.CustomerModel;

public interface CustomerService {
	
	List<CustomerModel> findAll();
	
	CustomerModel save(CustomerModel customer);
	
	void delete(Long id);
}
