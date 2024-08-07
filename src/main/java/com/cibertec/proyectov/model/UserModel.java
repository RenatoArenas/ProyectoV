package com.cibertec.proyectov.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "v_user")
public class UserModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El usuario es obligatorio")
    @Size(max = 30, message = "El tamaño máximo del usuario es 30 carácteres")
    private String username;
    
    @Column(unique = true)
    @NotBlank(message = "El correo es obligatorio")
	@Email(message = "No es una dirección de correo válida")
    private String email;
    
    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

	public UserModel() {
		
	}
	


	public UserModel(Long id,
			@NotBlank(message = "El usuario es obligatorio") @Size(max = 10, message = "El tamaño máximo del usuario es 10 carácteres") String username,
			@NotBlank(message = "El correo es obligatorio") @Email(message = "No es una dirección de correo válida") String email,
			@NotBlank(message = "La contraseña es obligatoria") String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
    
}
