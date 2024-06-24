package Proyecto.V.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
@Entity
@Table(name = "usuario")
public class usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;
    
    private String password;
    private String dni;
    private String celular;
    
    @ManyToOne
    @JoinColumn(name = "rol_id" , referencedColumnName = "id")
    private rol rol;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "ubigeo_id" , referencedColumnName = "id")
    private ubigeo ubigeo;
	
}
