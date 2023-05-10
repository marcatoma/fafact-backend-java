package com.fafact.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Canton implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCanton;

	private String codigoCanton;

	private String nombreCanton;

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;

	public String getComposeCanton() {
		return nombreCanton + " - " + provincia.getNombreProvincia();
	}

	public Long getIdCanton() {
		return idCanton;
	}

	public void setIdCanton(Long idCanton) {
		this.idCanton = idCanton;
	}

	public String getCodigoCanton() {
		return codigoCanton;
	}

	public void setCodigoCanton(String codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public String getNombreCanton() {
		return nombreCanton;
	}

	public void setNombreCanton(String nombreCanton) {
		this.nombreCanton = nombreCanton;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	private static final long serialVersionUID = 1L;

}
