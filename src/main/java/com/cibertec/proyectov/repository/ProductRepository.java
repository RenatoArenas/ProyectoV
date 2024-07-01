package com.cibertec.proyectov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyectov.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>{

	boolean existsBySku(String sku);
	
	boolean existsBySkuAndIdNot(String sku, Long id);
    
}
