package Proyecto.V.controlador;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import Proyecto.V.Utils.Jwtutil;
import Proyecto.V.dto.LoginRequest;
import Proyecto.V.service.implement.usuarioServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private final AuthenticationManager authenticationManager;

    private final usuarioServiceImpl usuServi;

    private final Jwtutil jwtUtil;
    
    @Autowired
    public LoginController(AuthenticationManager authenticationManager, usuarioServiceImpl usuarioServiceImpl, Jwtutil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.usuServi = usuarioServiceImpl;
        this.jwtUtil = jwtUtil;
    }

	@GetMapping
	public String iniciarSesion() {
		return "login";
		
	}
	
	@GetMapping("/admin")
	public String Jefe_Prestamista(Authentication auth, Model modelo) {
		
		modelo.addAttribute("rol", auth.getAuthorities().toString());
		
		return "index";
	}
	
	@PostMapping
	    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException("Incorrect email or password.");
	        } catch (DisabledException disabledException) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
	            return null;
	        }
	        final UserDetails userDetails = usuServi.loadUserByUsername(loginRequest.getEmail());
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

	        return jwt;
		}
	
}
