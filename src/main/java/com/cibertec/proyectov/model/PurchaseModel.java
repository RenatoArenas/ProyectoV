package com.cibertec.proyectov.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "v_purchase")
public class PurchaseModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotBlank(message = "El documento es obligatorio")
    @Size(min = 8, max = 11, message = "El tamaño del documento debe ser entre 8 y 11 carácteres")
	private String doc;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El tamaño máximo del nombre es 100 carácteres")
	private String name;
    

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El tamaño máximo del apellido es 100 carácteres")
	private String lname;
    
	@Email(message = "No es una dirección de correo válida")
    @NotBlank(message = "El email es obligatorio")
	private String email;
    

    @NotBlank(message = "El celular es obligatorio")
    @Size(min=9, max = 9, message = "El tamaño del celular debe ser de 9 caracteres")
	private String phone;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseDetailModel> purchasedetail; 

    
	public PurchaseModel() {
	}



	public PurchaseModel(Long id,
			@NotBlank(message = "El documento es obligatorio") @Size(min = 8, max = 11, message = "El tamaño del documento debe ser entre 8 y 11 carácteres") String doc,
			@NotBlank(message = "El nombre es obligatorio") @Size(max = 100, message = "El tamaño máximo del nombre es 100 carácteres") String name,
			@NotBlank(message = "El apellido es obligatorio") @Size(max = 100, message = "El tamaño máximo del apellido es 100 carácteres") String lname,
			@Email(message = "No es una dirección de correo válida") @NotBlank(message = "El email es obligatorio") String email,
			@NotBlank(message = "El celular es obligatorio") @Size(min = 9, max = 9, message = "El tamaño del celular debe ser de 9 caracteres") String phone,
			List<PurchaseDetailModel> purchasedetail) {
		this.id = id;
		this.doc = doc;
		this.name = name;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.purchasedetail = purchasedetail;
	}







	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDoc() {
		return doc;
	}



	public void setDoc(String doc) {
		this.doc = doc;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public List<PurchaseDetailModel> getPurchasedetail() {
		return purchasedetail;
	}



	public void setPurchasedetail(List<PurchaseDetailModel> purchasedetail) {
		this.purchasedetail = purchasedetail;
	}
	
    
    
    
    
}
