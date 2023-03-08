package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.DetalleVenta;

public interface IDetalleVentaRepo extends JpaRepository<DetalleVenta, Long> {

}
