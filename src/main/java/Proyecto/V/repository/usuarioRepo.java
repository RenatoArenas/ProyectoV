package Proyecto.V.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.V.model.usuario;

public interface usuarioRepo extends JpaRepository<usuario, Long>{

	public usuario findByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
