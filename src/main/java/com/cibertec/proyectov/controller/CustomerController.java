package com.cibertec.proyectov.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyectov.dto.ResponseData;
import com.cibertec.proyectov.model.CustomerModel;
import com.cibertec.proyectov.service.impl.CustomerServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/customer")
public class CustomerController {
	
	 @Autowired
	 private CustomerServiceImpl customerService;
	 
	 @GetMapping
	 public ResponseData<List<CustomerModel>> getCustomers() {
		 List<CustomerModel> customerres = customerService.findAll();
		 
		 
	 	 return new ResponseData<List<CustomerModel>>("Clientes listados con éxito", 1, customerres);
		 
	 }
	 
	 
	 @PostMapping
	 public ResponseData<CustomerModel> saveCustomer(@Valid @RequestBody CustomerModel customerreq, HttpServletResponse response)  {
        
	 	 CustomerModel customerres = customerService.save(customerreq);
	 
	 	 return new ResponseData<CustomerModel>("Cliente creado con éxito", 1, customerres);
	 } 
	 
	 @DeleteMapping
	 public ResponseData<Object> deleteCustomer(@RequestParam Long id){
		 customerService.delete(id);
		
		 return new ResponseData<Object>("Cliente eliminado con éxito", 1);
	 }
}
