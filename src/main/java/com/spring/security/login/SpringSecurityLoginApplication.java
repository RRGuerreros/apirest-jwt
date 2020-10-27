package com.spring.security.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.security.login.service.IClienteService;

@SpringBootApplication
public class SpringSecurityLoginApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLoginApplication.class, args);
	}
	
	@Autowired
	private IClienteService clienteServ;
	
	@Override
	public void run(String... args) throws Exception {		
		
				
	}
	
	
	
}
