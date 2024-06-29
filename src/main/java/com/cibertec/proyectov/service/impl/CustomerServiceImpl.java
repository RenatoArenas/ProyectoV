package com.cibertec.proyectov.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectov.exceptions.UniqueException;
import com.cibertec.proyectov.model.CustomerModel;
import com.cibertec.proyectov.repository.CustomerRepository;
import com.cibertec.proyectov.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<CustomerModel> findAll() {
		return customerRepository.findByStateTrue();
	}

	@Override
	public CustomerModel save(CustomerModel customer) {
		
		if(customer.getId() != null) {
			if(!customerRepository.existsByIdAndStateTrue(customer.getId())) {
				throw new RuntimeException("No existe este cliente");
			}
			
			if(customerRepository.existsByDocAndIdNot(customer.getDoc(), customer.getId())) {
				throw new UniqueException("doc", "Ya existe un cliente con este documento");
			}
			
			if(customerRepository.existsByEmailAndIdNot(customer.getEmail(), customer.getId())) {
				throw new UniqueException("email", "Ya existe un cliente con este email");
			}
			
		} else {
			if(customerRepository.existsByDoc(customer.getDoc())) {
				throw new UniqueException("doc", "Ya existe un cliente con este documento");
			}
			
			if(customerRepository.existsByEmail(customer.getEmail())) {
				throw new UniqueException("email", "Ya existe un cliente con este email");
			}
		}
		
		
		return customerRepository.save(customer);
	}

	
	
	@Override
	public void delete(Long id) {
		
		
		Optional<CustomerModel> customerex = customerRepository.findByIdAndStateTrue(id);
		
		if (!customerex.isEmpty()) {
			CustomerModel customer = customerex.get();
			customer.setState(false);
			
			customerRepository.save(customer);
	    } else {
	    	throw new RuntimeException("No existe este cliente");
	    }

	}



	

}
