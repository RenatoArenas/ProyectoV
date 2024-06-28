package com.cibertec.proyectov.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyectov.model.PurchaseModel;
@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long>{
    
    
}
