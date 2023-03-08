package com.fafact.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Transportador extends Persona implements Serializable {

	private Long idTransportador;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "transportador_vehiculo", joinColumns = @JoinColumn(name = "transportador_id"), inverseJoinColumns = @JoinColumn(name = "vehiculo_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "transportador_id", "vehiculo_id" }) })
	private List<Vehiculo> vehiculos;

	public Long getIdTransportador() {
		return idTransportador;
	}

	public void setIdTransportador(Long idTransportador) {
		this.idTransportador = idTransportador;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	private static final long serialVersionUID = 1L;

}
