package com.spring.security.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.login.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

	PasswordResetToken findByToken( String token );
	
}
