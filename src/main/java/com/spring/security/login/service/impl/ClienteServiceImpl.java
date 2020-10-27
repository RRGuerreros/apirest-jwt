package com.spring.security.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.security.login.entity.Cliente;
import com.spring.security.login.entity.ClienteSpecification;
import com.spring.security.login.entity.DataTablesParameters;
import com.spring.security.login.message.request.ClienteRequest;
import com.spring.security.login.repository.IClienteRepository;
import com.spring.security.login.service.IClienteService;

import com.google.gson.Gson;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	private static final String MESSAGE_ERROR_RESPONSE = "Hubo un problema, porfavor consulte con soporte técnico este problema -> ";

	@Override
	public ResponseEntity<?> save(ClienteRequest clienteRequest) {

		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		try {

			Cliente cliente = new Cliente();

			cliente.setRazonSocial(clienteRequest.getRazonSocial());
			cliente.setDireccion(clienteRequest.getDireccion());
			cliente.setRuc(clienteRequest.getRuc());
			cliente.setTelefono(clienteRequest.getTelefono());
			cliente.setPaginaWeb(clienteRequest.getPaginaWeb());
			cliente.setTipoTelefono(clienteRequest.getTipoTelefono());

			clienteRepository.save(cliente);

			responseBody.put("message", "Se registró el cliente correctamente");
			responseBody.put("cliente",  cliente);
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.CREATED);

		} catch (Exception e) {

			responseBody.put("message", MESSAGE_ERROR_RESPONSE + e.getMessage());
			responseBody.put("cliente", null);

			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> update(int id, ClienteRequest clienteRequest) {

		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		try {

			Cliente cliente = clienteRepository.findById(id).orElse(null);

			if (cliente == null) {
				
				responseBody.put("message", "El cliente con el id: ".concat(String.valueOf(id)).concat(" no existe"));
				
				return response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.NOT_FOUND);
			}

			cliente.setRazonSocial(clienteRequest.getRazonSocial());
			cliente.setDireccion(clienteRequest.getDireccion());
			cliente.setRuc(clienteRequest.getRuc());
			cliente.setTelefono(clienteRequest.getTelefono());
			cliente.setPaginaWeb(clienteRequest.getPaginaWeb());
			cliente.setTipoTelefono(clienteRequest.getTipoTelefono());

			clienteRepository.save(cliente);

			responseBody.put("message", "Se actualizó el cliente correctamente");
			responseBody.put("cliente",  cliente);
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK);

		} catch (Exception e) {

			responseBody.put("message", MESSAGE_ERROR_RESPONSE + e.getMessage());
			responseBody.put("cliente", null);

			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> delete(int id) {

		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		try {

			clienteRepository.deleteById(id);

			responseBody.put("message", "Se eliminó el cliente correctamente");
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK);

		} catch (Exception e) {

			responseBody.put("message", MESSAGE_ERROR_RESPONSE + e.getMessage());
			responseBody.put("cliente", null);

			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> findById(int id) {
		
		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		try {

			Cliente cliente = clienteRepository.findById(id).orElse(null);
			
			if( cliente == null ) {
				
				responseBody.put("message", "El cliente no existe");
				responseBody.put("cliente", null);
				
				return response = new ResponseEntity<>( new Gson().toJson(responseBody) , HttpStatus.NOT_FOUND );
			}
			
			responseBody.put("message", "Cliente encontrado con éxito");
			responseBody.put("cliente", cliente);
			
			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.OK);

		} catch (Exception e) {
			
			responseBody.put("message", MESSAGE_ERROR_RESPONSE + e.getMessage());
			responseBody.put("cliente", null);

			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> findAll() {
		
		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		try {

			List<Cliente> clientes = clienteRepository.findAll();
			
			if( clientes.isEmpty() ) {
				
				responseBody.put("message", "No hay clientes registrados");
				responseBody.put("clientes", null);
				
				return response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK );
			}
			
			responseBody.put("message", "Lista de clientes cargados correctamente");
			responseBody.put("clientes", clientes);
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK);

		} catch (Exception e) {

			responseBody.put("message", MESSAGE_ERROR_RESPONSE + e.getMessage());
			responseBody.put("clientes", null);

			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> findAllPageable( DataTablesParameters dtParameters ) {
		
		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		try {
			
			int page = dtParameters.getStart() / dtParameters.getLength();
			
			Pageable pageable = PageRequest.of( page, dtParameters.getLength());
			
			ClienteSpecification clienteSpecificacion = new ClienteSpecification(dtParameters);
			
			Page<Cliente> pageClientes = clienteRepository.findAll( clienteSpecificacion, pageable);
			
			List<Cliente> clientes = pageClientes.getContent();
			
			if( clientes.isEmpty() ) {
				
				responseBody.put("message", "No hay clientes registrados");
				responseBody.put("clientes", null);
				responseBody.put("recordsTotal", 0 );
				responseBody.put("recordsFiltered", 0 );
				responseBody.put("totalPages", 0 );
				responseBody.put("draw", 0 );
				
				return response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK );
			}
			
			responseBody.put("message", "Lista de clientes cargados correctamente");
			responseBody.put("clientes", clientes);
			responseBody.put("recordsTotal", pageClientes.getTotalElements() );
			responseBody.put("recordsFiltered", pageClientes.getTotalElements() );
			responseBody.put("totalPages", pageClientes.getTotalPages() );
			responseBody.put("draw", dtParameters.getDraw() );
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			
			responseBody.put("message", MESSAGE_ERROR_RESPONSE + e.getMessage());
			responseBody.put("clientes", null);

			response = new ResponseEntity<>( new Gson().toJson(responseBody), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
