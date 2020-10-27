package com.spring.security.login.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.spring.security.login.entity.Cliente;

@Repository
public class ClienteRepositoryImpl implements ClienteRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	public List<Cliente> findByRazonSocialOrRuc(String razon, String ruc) {
		
		CriteriaBuilder construirCriterio = entityManager.getCriteriaBuilder(); 
		
		CriteriaQuery<Cliente> consulta = construirCriterio.createQuery(Cliente.class);
		
		// esto es para traer las variables que hay en la clase que se especifica
		Root<Cliente> clienteRoot = consulta.from(Cliente.class);
		
		Predicate condicionRazonSocial = construirCriterio.equal(clienteRoot.get("razonSocial"), razon );
		
		consulta.select(clienteRoot).where(condicionRazonSocial);
		
				
		return entityManager.createQuery(consulta).getResultList();
		
	}

	@Override
	public List<Cliente> findByRazonSocialOrRucV2( String filtro, String dato ) {
		
		String queryString = "SELECT * FROM tb_cliente";
		
		if( filtro.equals("razonSocial") ) {
			
			queryString += " where razonSocial = ?";
			
		} else if( filtro.equals("dato") ) {
			
			queryString += " where ruc = ?";
		}
		
		Query query = entityManager.createNativeQuery(queryString, "clienteResultV1" );
		
		query.setParameter(1, dato);				
		
		return query.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
