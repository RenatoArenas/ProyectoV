package Proyecto.V.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Proyecto.V.model.usuario;
import Proyecto.V.repository.rolRepo;
import Proyecto.V.repository.usuarioRepo;
import Proyecto.V.service.usuarioService;

@Service
public class usuarioServiceImpl implements usuarioService{

	private usuarioRepo usuRepo;
	
	public usuarioServiceImpl(usuarioRepo usuRepo) {
		super();
		this.usuRepo = usuRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		usuario usuario = usuRepo.findByEmail(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos"); }
		
		List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		return new User(usuario.getEmail(), usuario.getPassword() , roles);
	}

}
