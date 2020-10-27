package com.spring.security.login.service;

public interface UserService {

	boolean requestPasswordReset( String email );
	
	boolean passwordReset( String token, String password );
	
}
