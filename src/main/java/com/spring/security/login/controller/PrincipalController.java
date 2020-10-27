package com.spring.security.login.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.spring.security.login.entity.Usuario;
import com.spring.security.login.message.request.PasswordResetRequest;
import com.spring.security.login.repository.IUsuarioRepository;
import com.spring.security.login.service.UserService;


@Controller
public class PrincipalController {
	
	Logger log = LoggerFactory.getLogger(PrincipalController.class);
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping({ "/", "/login" })
	public String index( Principal principal ) {
		
		if( principal != null ) {
			log.info("Ya ah iniciado sesión anteriormente");
			return "redirect:/menu-principal";
		}
		
		return "login";
	}
	
	@GetMapping("/menu-principal")
	public String menu() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    	        
	    Usuario usuario = usuarioRepository.findByUsername( userDetail.getUsername() );
	    
	    log.info("el usuario "+usuario.getUsername()+" ah solicitado iniciar sesion");
	    log.info("estado del usuario -> " + (usuario.getEnabled()? "Activo" :"Inactivo"));
	    
	    if( !usuario.getEnabled()) {
	    	log.info("el usuario no se encuentra activo, no pudo iniciar sesion");
	    	return "redirect:/login?error=true";
	    }
	    
	    log.info("usuario conectado con éxito");

		return "menu";
	}

	@RequestMapping( value = "/reset-password", method = RequestMethod.POST )
	public ResponseEntity<?> requestReset( @RequestBody PasswordResetRequest passwordResetRequest ){
		
		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();

		boolean isSuccess = userService.requestPasswordReset(passwordResetRequest.getEmail());
		
		if( isSuccess ) {
			
			responseBody.put("message", "The password change request was sent successfully");
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK );
			
		} else {
			
			responseBody.put("message", "There were problems sending the password change email, please try again later.");
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.BAD_REQUEST );
		}
		
		return response;
	}
	
	@RequestMapping( value = "/password-reset", method = RequestMethod.POST )
	public ResponseEntity<?> resetPassword( @RequestBody PasswordResetRequest passwordResetRequest ){
		
		ResponseEntity<String> response = null;
		
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		boolean isSuccess = userService.passwordReset(
				passwordResetRequest.getToken(), passwordResetRequest.getPassword());
		
		if( isSuccess ) {
			
			responseBody.put("message", "the password was changed successfully");
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.OK );
			
		} else {
			
			responseBody.put("message", "there were problems when changing the password");
			
			response = new ResponseEntity<String>( new Gson().toJson(responseBody), HttpStatus.BAD_REQUEST );
		}
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
