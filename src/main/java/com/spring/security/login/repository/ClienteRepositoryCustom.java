package com.spring.security.login.repository;

import java.util.List;

import com.spring.security.login.entity.Cliente;

public interface ClienteRepositoryCustom {

	List<Cliente> findByRazonSocialOrRuc( String razon, String ruc );
	
	List<Cliente> findByRazonSocialOrRucV2( String filtro, String dato );
	
}
