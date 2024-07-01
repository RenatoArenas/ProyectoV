package com.cibertec.proyectov.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "v_product")
public class ProductModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El tamaño máximo del nombre es 100 carácteres")
	private String name;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 200, message = "El tamaño máximo de la descripción es 200 carácteres")
	private String description;

	 @NotNull(message = "El precio es obligatorio")
	 private double price;
	 
	 @NotNull(message = "El stock es obligatorio")
	 private double stock;

	@NotBlank(message = "El sku es obligatorio")
    @Column(unique = true)
    @Size(max = 5, message = "El tamaño máximo del sku es 5 carácteres")
	private String sku;
    
	private boolean state = true;

	
	
	public ProductModel() {
	}



	public ProductModel(Long id,
			@NotBlank(message = "El nombre es obligatorio") @Size(max = 100, message = "El tamaño máximo del nombre es 100 carácteres") String name,
			@NotBlank(message = "El nombre es obligatorio") @Size(max = 200, message = "El tamaño máximo de la descripción es 200 carácteres") String description,
			@NotNull(message = "El precio es obligatorio") double price,
			@NotNull(message = "El stock es obligatorio") double stock,
			@NotBlank(message = "El sku es obligatorio") @Size(max = 5, message = "El tamaño máximo del sku es 5 carácteres") String sku,
			boolean state) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.sku = sku;
		this.state = state;
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getStock() {
		return stock;
	}



	public void setStock(double stock) {
		this.stock = stock;
	}



	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}



	public boolean isState() {
		return state;
	}



	public void setState(boolean state) {
		this.state = state;
	}

	
	
    
    
    
    
}
