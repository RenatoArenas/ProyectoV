package com.cibertec.proyectov.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyectov.model.CustomerModel;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>{
	boolean existsByIdAndStateTrue(Long id);
	
	boolean existsByEmail(String email);

	boolean existsByDoc(String doc);

	boolean existsByEmailAndIdNot(String email, Long id);

	boolean existsByDocAndIdNot(String doc, Long id);
	
    Optional<CustomerModel> findByDocAndStateTrue(String doc);
	
    Optional<CustomerModel> findByIdAndStateTrue(Long id);
    
    List<CustomerModel> findByStateTrue();
    
}
