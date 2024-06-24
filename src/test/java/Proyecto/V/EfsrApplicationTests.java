package Proyecto.V;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import Proyecto.V.model.rol;
import Proyecto.V.model.ubigeo;
import Proyecto.V.model.usuario;
import Proyecto.V.repository.rolRepo;
import Proyecto.V.repository.usuarioRepo;
import Proyecto.V.security.SecurityConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class EfsrApplicationTests {

	BCryptPasswordEncoder secu = new BCryptPasswordEncoder();
	
	@Autowired
	private rolRepo rolRepo;
	
	@Autowired
	private usuarioRepo usuRepo;
	
	@Test
	void contextLoads() {
		
		rol rol1 = rolRepo.save(new rol("admin"));
		rol rol2 = rolRepo.save(new rol("usuario"));
		
		usuario usuario = new usuario();
		usuario.setNombre("Jhordan");
		usuario.setApellido("Via Pese");
		usuario.setEmail("admin@gmail.com");
		usuario.setPassword(secu.encode("admin"));
		usuario.setDni("13245645");
		usuario.setCelular("132456798");
		usuario.setRol(rol1);
		
		ubigeo ubi = new ubigeo();
		ubi.setDistrito("Lima");
		ubi.setUbicacion("En mi casa");
		usuario.setUbigeo(ubi);
		
		usuRepo.save(usuario);
		
	}

}
