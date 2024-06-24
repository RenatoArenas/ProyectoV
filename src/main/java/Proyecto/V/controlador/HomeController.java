package Proyecto.V.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String verPaginaDeInicio(Authentication auth,  Model modelo) {
		
		
		return "index";
	}
	
}
