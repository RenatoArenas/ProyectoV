package com.cibertec.proyectov.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyectov.dto.LoginRequest;
import com.cibertec.proyectov.dto.LoginResponse;
import com.cibertec.proyectov.dto.ResponseData;
import com.cibertec.proyectov.service.impl.UserServiceImpl;
import com.cibertec.proyectov.utils.JwtUtil;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseData<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException, BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales Incorrectas.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no activado");
            return null;
        }
        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        
        LoginResponse res = new LoginResponse(userDetails.getUsername(), jwt);
        
        return new ResponseData<LoginResponse>("Usuario logueado con éxito", 1, res);
    }

}