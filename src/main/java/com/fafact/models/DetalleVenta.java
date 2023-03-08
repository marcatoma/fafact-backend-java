package com.fafact.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class DetalleVenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_detalle_venta;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto_item;

	private BigDecimal cantidad;

	private BigDecimal descuento;

	private BigDecimal importe;

	public Long getId_detalle_venta() {
		return id_detalle_venta;
	}

	public void setId_detalle_venta(Long id_detalle_venta) {
		this.id_detalle_venta = id_detalle_venta;
	}

	public Producto getProducto_item() {
		return producto_item;
	}

	public void setProducto_item(Producto producto_item) {
		this.producto_item = producto_item;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	private static final long serialVersionUID = 1L;
}
