package com.cibertec.proyectov.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.proyectov.exceptions.UniqueException;
import com.cibertec.proyectov.model.UserModel;
import com.cibertec.proyectov.repository.UserRepository;
import com.cibertec.proyectov.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserModel createUser(UserModel user) {
		
		String hashPassword = passwordEncoder.encode(user.getPassword());
		
		if(user.getId() != null) {
			if(!userRepository.existsById(user.getId())) {
				throw new RuntimeException("No existe este cliente");
			}
			
			
			if(userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
				throw new UniqueException("email", "Ya existe un usuario con este email");
			}
			
		} else {
			
			if(userRepository.existsByEmail(user.getEmail())) {
				throw new UniqueException("email", "Ya existe un usuario con este email");
			}
		}
		
		user.setPassword(hashPassword);
		
		return userRepository.save(user);
	}
	
	
	


}
