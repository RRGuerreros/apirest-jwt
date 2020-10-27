package com.spring.security.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.login.entity.DataTablesParameters;
import com.spring.security.login.message.request.ClienteRequest;
import com.spring.security.login.service.IClienteService;

@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600 )
@RestController
@RequestMapping("/api/rest/v1")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping( method = RequestMethod.POST, value = "/clientes")
	public ResponseEntity<?> save( @RequestBody ClienteRequest clienteRequest ){
		return clienteService.save(clienteRequest);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping( method = RequestMethod.GET, value = "/clientes")
	public ResponseEntity<?> findAll(){
		return clienteService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping( method = RequestMethod.POST, value = "/clientes/server-side")
	public ResponseEntity<?> findAllServerSide( 	@RequestBody DataTablesParameters dataTablesParameters ){
		return clienteService.findAllPageable(dataTablesParameters);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping( method = RequestMethod.PUT, value  = "/clientes/{id}")
	public ResponseEntity<?> update( @PathVariable("id") int id, @RequestBody ClienteRequest clienteRequest ){
		return clienteService.update( id, clienteRequest);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping( method = RequestMethod.DELETE, value = "/clientes/{id}")
	public ResponseEntity<?> delete( @PathVariable int id ){
		return clienteService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping( method = RequestMethod.GET, value = "/clientes/{id}")
	public ResponseEntity<?> findById( @PathVariable int id ){
		return clienteService.findById(id);
	}
	
	
	
	
	
	
	
	
	
}
