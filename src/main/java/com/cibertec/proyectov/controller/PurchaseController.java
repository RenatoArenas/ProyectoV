package com.cibertec.proyectov.controller;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyectov.dto.PurchaseResponse;
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
	 public ResponseEntity<Resource> savePurchase(@Valid @RequestBody PurchaseModel purchasereq, HttpServletResponse response) throws FileNotFoundException  {
        
		 PurchaseModel purchase = purchaseService.save(purchasereq);

		 purchaseService.report(purchase.getId());

		String filepathsave = "./resultrepventa.pdf";
		File file = new File(filepathsave);
		 
		 
		 InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		 
		 HttpHeaders headers = new HttpHeaders();
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=informe.pdf");
	        
		 return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(file.length())
		            .contentType(MediaType.APPLICATION_OCTET_STREAM)
		            .body(resource);
	 } 
	 
	 @DeleteMapping
	 public ResponseData<Object> deletePurchase(@RequestParam Long id){
		 purchaseService.delete(id);
		
		 return new ResponseData<Object>("Venta eliminada con éxito", 1);
	 }
}
