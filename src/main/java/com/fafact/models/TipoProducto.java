package com.fafact.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fafact.enums.TipoProductoEnum;

@Entity
public class TipoProducto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoProductoEnum tipoProductoPrefijo;

	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoProductoEnum getTipoProductoPrefijo() {
		return tipoProductoPrefijo;
	}

	public void setTipoProductoPrefijo(TipoProductoEnum tipoProductoPrefijo) {
		this.tipoProductoPrefijo = tipoProductoPrefijo;
	}

	private static final long serialVersionUID = 1L;

}
