package com.cibertec.proyectov.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectov.exceptions.UniqueException;
import com.cibertec.proyectov.model.ProductModel;
import com.cibertec.proyectov.repository.ProductRepository;
import com.cibertec.proyectov.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<ProductModel> getAll() {
		return productRepository.findAll();
	}

	@Override
	public ProductModel saveOrUpdate(ProductModel product) {
		
		if(product.getId() != null) {
			if(!productRepository.existsById(product.getId())) {
				throw new RuntimeException("No existe este producto");
			}
			
			if(productRepository.existsBySkuAndIdNot(product.getSku(), product.getId())) {
				throw new UniqueException("doc", "Ya existe un producto con este sku");
			}
			
		} else {
			if(productRepository.existsBySku(product.getSku())) {
				throw new UniqueException("doc", "Ya existe un producto con este sku");
			}
			
		}
		
		return productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		Optional<ProductModel> customerex = productRepository.findById(id);
		
		if (!customerex.isEmpty()) {
			ProductModel customer = customerex.get();
			
			productRepository.delete(customer);
	    } else {
	    	throw new RuntimeException("No existe este producto");
	    }
		
	}

	@Override
	public ProductModel getById(Long id) {
		Optional<ProductModel> customerex = productRepository.findById(id);
		ProductModel product = new ProductModel();
		if (!customerex.isEmpty()) {
			product  = customerex.get();
			
	    } else {
	    	throw new RuntimeException("No existe este producto");
	    }
		
		return product;
	}

}
