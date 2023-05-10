package com.fafact.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fafact.enums.TipoClienteEnum;

@Entity
public class TipoCliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoCliente;

	@NotNull
	@NotBlank(message = "El codigo es requerido.")
	@Column(unique = true)
	private String codigo;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoClienteEnum prefijo;

	private String descripcion;

	public Long getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Long idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoClienteEnum getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(TipoClienteEnum prefijo) {
		this.prefijo = prefijo;
	}

	private static final long serialVersionUID = 1L;

}
