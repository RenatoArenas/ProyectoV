package com.cibertec.proyectov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "v_purchasedetail")
public class PurchaseDetailModel {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @NotNull(message = "El nombre es obligatorio")
	 @Size(max = 100, message = "El tamaño máximo del nombre es 100 carácteres")
	 private String name;
	 
	 @NotNull(message = "El precio es obligatorio")
	 private double price;
	 
	 @NotNull(message = "La cantidad es obligatoria")
	 private int quantity;
	 
	 @NotNull(message = "El total es obligatorio")
	 private double total;

	public PurchaseDetailModel() {
	}


	public PurchaseDetailModel(Long id,
			@NotNull(message = "El nombre es obligatorio") @Size(max = 100, message = "El tamaño máximo del nombre es 100 carácteres") String name,
			@NotNull(message = "El precio es obligatorio") double price,
			@NotNull(message = "La cantidad es obligatoria") int quantity,
			@NotNull(message = "El total es obligatorio") double total) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	 
	 
}
