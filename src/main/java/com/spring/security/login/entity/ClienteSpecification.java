package com.spring.security.login.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification implements Specification<Cliente> {

	private static final long serialVersionUID = 1L;
	
	private final Cliente cliente;
	
	public ClienteSpecification( DataTablesParameters dtParameters ) {
		this.cliente = new Cliente();	
		
		if( dtParameters.getColumns().size() == 7 ) {
		
			this.cliente.setRazonSocial( dtParameters.getColumns().get(1).getSearch().getValue() );
			this.cliente.setRuc(dtParameters.getColumns().get(2).getSearch().getValue());
			this.cliente.setTelefono(dtParameters.getColumns().get(3).getSearch().getValue());
			this.cliente.setTipoTelefono(dtParameters.getColumns().get(4).getSearch().getValue());
			this.cliente.setPaginaWeb(dtParameters.getColumns().get(5).getSearch().getValue());
			this.cliente.setDireccion(dtParameters.getColumns().get(6).getSearch().getValue());
			
		} else {
			
			this.cliente.setRazonSocial( dtParameters.getColumns().get(0).getSearch().getValue() );
			this.cliente.setRuc(dtParameters.getColumns().get(1).getSearch().getValue());
			this.cliente.setTelefono(dtParameters.getColumns().get(2).getSearch().getValue());
			this.cliente.setTipoTelefono(dtParameters.getColumns().get(3).getSearch().getValue());
			this.cliente.setPaginaWeb(dtParameters.getColumns().get(4).getSearch().getValue());
			this.cliente.setDireccion(dtParameters.getColumns().get(5).getSearch().getValue());
		}
		
	}
	
	
	@Override
	public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if( !this.cliente.getRazonSocial().equals("") ) 
			predicates.add(criteriaBuilder.like(root.get("razonSocial"), addPrefixCoincidenciaAmbosLados(cliente.getRazonSocial()) ));		
		
		if( !this.cliente.getRuc().equals("") ) 
			predicates.add(criteriaBuilder.like(root.get("ruc"), addPrefixCoincidenciaAmbosLados(cliente.getRuc()) ));
				
		if( !this.cliente.getTelefono().equals(""))
			predicates.add(criteriaBuilder.like(root.get("telefono"), addPrefixCoincidenciaAmbosLados(cliente.getTelefono())));
		
		if( !this.cliente.getTipoTelefono().equals(""))
			predicates.add(criteriaBuilder.equal(root.get("tipoTelefono"), cliente.getTipoTelefono()));
		
		if( !this.cliente.getPaginaWeb().equals("") ) 
			predicates.add(criteriaBuilder.like(root.get("paginaWeb"), addPrefixCoincidenciaAmbosLados(cliente.getPaginaWeb())));
		
		if( !this.cliente.getDireccion().equals(""))
			predicates.add(criteriaBuilder.like(root.get("direccion"), addPrefixCoincidenciaAmbosLados(cliente.getDireccion()) ));
		
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return query.getGroupRestriction();
	}
	
	private String addPrefixCoincidenciaAmbosLados( String texto ){
		return "%".concat(texto).concat("%");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
