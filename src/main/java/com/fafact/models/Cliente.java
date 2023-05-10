package com.fafact.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cliente extends Persona implements Serializable {

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
//	@NotBlank(message = "Seleccionar el tipo de cliente.")
	@JoinColumn(name = "tipo_cliente")
	private TipoCliente tipoCliente;


	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	private static final long serialVersionUID = 1L;

}
