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
import com.cibertec.proyectov.model.PurchaseModel;
import com.cibertec.proyectov.service.impl.PurchaseServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/purchase")
public class PurchaseController {
	
	 @Autowired
	 private PurchaseServiceImpl purchaseService;
	 
	 @GetMapping
	 public ResponseData<List<PurchaseModel>> getPurchases() {
		 List<PurchaseModel> purchaseres = purchaseService.findAll();
		 
		 
	 	 return new ResponseData<List<PurchaseModel>>("Ventas listadas con éxito", 1, purchaseres);
		 
	 }
	 
	 @PostMapping
	 public ResponseData<PurchaseModel> savePurchase(@Valid @RequestBody PurchaseModel purchasereq, HttpServletResponse response)  {
        
		 PurchaseModel purchaseres = purchaseService.save(purchasereq);
	 
	 	 return new ResponseData<PurchaseModel>("Venta creada con éxito", 1, purchaseres);
	 } 
	 
	 @DeleteMapping
	 public ResponseData<Object> deletePurchase(@RequestParam Long id){
		 purchaseService.delete(id);
		
		 return new ResponseData<Object>("Venta eliminada con éxito", 1);
	 }
}
