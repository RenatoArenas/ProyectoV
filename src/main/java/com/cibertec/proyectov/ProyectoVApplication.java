package com.cibertec.proyectov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;


@OpenAPIDefinition(servers = {@Server(url = "https://{host}",
variables = @ServerVariable(name = "host",
defaultValue = "hostname_sit",
allowableValues = {"hostname_sit","hostname_uat"}),
description = "Host name by environment")})
@SpringBootApplication
public class ProyectoVApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoVApplication.class, args);
	}
	
	@Configuration
	public static class Myconfiguration{
		@Bean
		public WebMvcConfigurer corsConfigurer(){
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
				}
			};
		}
	}

}
