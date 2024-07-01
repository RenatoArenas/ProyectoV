package com.cibertec.proyectov.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyectov.dto.ResponseData;
import com.cibertec.proyectov.model.ProductModel;
import com.cibertec.proyectov.service.impl.ProductServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/product")
public class ProductController {
	
	 @Autowired
	 private ProductServiceImpl productService;
	 
	 @GetMapping
	 public ResponseData<List<ProductModel>> getProducts() {
		 List<ProductModel> productres = productService.getAll();
		 
		 
	 	 return new ResponseData<List<ProductModel>>("Productos listados con éxito", 1, productres);
		 
	 }
	 
	 @GetMapping("/{productid}")
	 public ResponseData<ProductModel> getProductsById(@PathVariable("productid") Long productid) {
		 ProductModel productres = productService.getById(productid);
		 
		 
	 	 return new ResponseData<ProductModel>("Producto listado con éxito", 1, productres);
		 
	 }
	 
	 
	 @PostMapping
	 public ResponseData<ProductModel> saveProduct(@Valid @RequestBody ProductModel productreq, HttpServletResponse response)  {
        
		 ProductModel productres = productService.saveOrUpdate(productreq);
	 
	 	 return new ResponseData<ProductModel>("Producto creado con éxito", 1, productres);
	 } 
	 
	 @PutMapping
	 public ResponseData<ProductModel> updateProduct(@Valid @RequestBody ProductModel productreq, HttpServletResponse response)  {
        
		 ProductModel productres = productService.saveOrUpdate(productreq);
	 
	 	 return new ResponseData<ProductModel>("Producto actualizado con éxito", 1, productres);
	 } 
	 
	 
	 @DeleteMapping
	 public ResponseData<Object> deleteCustomer(@RequestParam Long id){
		 productService.delete(id);
		
		 return new ResponseData<Object>("Producto eliminado con éxito", 1);
	 }
}
