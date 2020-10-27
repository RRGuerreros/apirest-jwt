package com.spring.security.login.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
@RequestMapping("/api")
public class TestJwtApiRest {

	@GetMapping("/test/user")
	@Secured({"ROLE_USER"})
	public String testUser() {
		return "Bienvenido Usuario";
	}
	
	@GetMapping("/test/admin")
	@Secured({"ROLE_ADMIN"})
	public String testAdmin() {
		return "Bienvenido Admin";
	}
	
	@GetMapping("/test/global")
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public String testGlobal() {
		return "Bienvenido Admin con rol de usuario adicional";
	}
	
	
}
