package com.fafact.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Long> {

	@Query(value = "select * from producto p where ( LOWER(p.codigo) like %:q% or LOWER(p.codigo_aux) like %:q% or LOWER(p.descripcion) like %:q% or LOWER(p.nombre_producto) like %:q%)", nativeQuery = true)
	public Page<Producto> findByParams(Pageable pageable, String q);

	public Producto findByCodigoIgnoreCase(String codigo);
	
}
