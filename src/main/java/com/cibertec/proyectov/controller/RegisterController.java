package com.cibertec.proyectov.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyectov.dto.RegisterResponse;
import com.cibertec.proyectov.dto.ResponseData;
import com.cibertec.proyectov.model.UserModel;
import com.cibertec.proyectov.service.impl.UserRegisterServiceImpl;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("/register")
public class RegisterController {


    @Autowired
    private UserRegisterServiceImpl userService;


    @PostMapping
    public ResponseData<RegisterResponse> register(@Valid @RequestBody UserModel userreq, HttpServletResponse response) throws IOException, BadCredentialsException {
        
    	UserModel user = userService.createUser(userreq);
        
    	RegisterResponse res = new RegisterResponse(user.getId(), user.getEmail(), user.getUsername());
    	
        return new ResponseData<RegisterResponse>("Usuario registrado con éxito", 1, res);
    }

}