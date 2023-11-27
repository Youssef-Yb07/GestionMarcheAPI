package com.example.gestionmarcheapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionMarcheApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionMarcheApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() { // added model mapper
		return new ModelMapper();
	}
}
