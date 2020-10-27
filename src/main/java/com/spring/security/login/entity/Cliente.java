package com.spring.security.login.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@SqlResultSetMapping(
	name = "clienteResultV1", classes = {
		@ConstructorResult( targetClass = Cliente.class,
			columns = {
				@ColumnResult( name = "razon_social", type = String.class )
			})
	} 
)


@Entity
@Table( name = "tb_cliente" )
public class Cliente {
	
	private int id;
	private String razonSocial;
	private String direccion;
	private String ruc;
	private String telefono;
	private String tipoTelefono;
	private String paginaWeb;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public Cliente( String razonSocial ) {
		this.razonSocial = razonSocial;		
	}
	
	public Cliente(int id, String razonSocial, String direccion, String ruc, String telefono, String tipoTelefono,
			String paginaWeb) {
		this.id = id;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.ruc = ruc;
		this.telefono = telefono;
		this.tipoTelefono = tipoTelefono;
		this.paginaWeb = paginaWeb;
	}
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Basic
	@Column(name = "razon_social")
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	@Basic
	@Column(name = "direccion")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Basic
	@Column(name = "ruc")
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	@Basic
	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Basic
	@Column(name = "tipo_telefono")
	public String getTipoTelefono() {
		return tipoTelefono;
	}
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	@Basic
	@Column(name = "pagina_web")
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

}
