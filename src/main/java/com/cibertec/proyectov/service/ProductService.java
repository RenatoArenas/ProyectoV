package com.cibertec.proyectov.service;

import java.util.List;

import com.cibertec.proyectov.model.ProductModel;

public interface ProductService {


	public List<ProductModel> getAll();

	public ProductModel saveOrUpdate(ProductModel product);
	
	public void delete(Long id);

	public ProductModel getById(Long id);

}
