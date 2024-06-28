package com.cibertec.proyectov.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "v_customer")
public class CustomerModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(unique = true)
    @NotBlank(message = "El documento es obligatorio")
	private String doc;
    
    @NotBlank(message = "El nombre es obligatorio")
	private String name;
    

    @NotBlank(message = "El apellido es obligatorio")
	private String lname;
    
	@Email(message = "No es una dirección de correo válida")
    @Column(unique = true)
    @NotBlank(message = "El email es obligatorio")
	private String email;
    

    @NotBlank(message = "El celular es obligatorio")
	private String phone;
    
	private boolean state = true;

	public CustomerModel() {
	}

	

	public CustomerModel(Long id, @NotBlank(message = "El documento es obligatorio") String doc,
			@NotBlank(message = "El nombre es obligatorio") String name,
			@NotBlank(message = "El apellido es obligatorio") String lname,
			@Email(message = "No es una dirección de correo válida") @NotBlank(message = "El email es obligatorio") String email,
			@NotBlank(message = "El celular es obligatorio") String phone, boolean state) {
		this.id = id;
		this.doc = doc;
		this.name = name;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.state = state;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	
    
    
    
    
}
