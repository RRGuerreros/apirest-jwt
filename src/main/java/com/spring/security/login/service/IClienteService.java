package com.spring.security.login.service;

import org.springframework.http.ResponseEntity;

import com.spring.security.login.entity.DataTablesParameters;
import com.spring.security.login.message.request.ClienteRequest;

public interface IClienteService {

	ResponseEntity<?> save( ClienteRequest clienteRequest );
	
	ResponseEntity<?> update( int id, ClienteRequest clienteRequest );
	
	ResponseEntity<?> delete( int id );
	
	ResponseEntity<?> findById( int id );
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findAllPageable( DataTablesParameters dataTablesParameters );
	
	
}
