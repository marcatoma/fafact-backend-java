package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.TipoProducto;

public interface ITipoProductoRepo extends JpaRepository<TipoProducto, Long> {

}
