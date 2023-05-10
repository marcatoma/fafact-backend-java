package com.fafact.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Long> {

	@Query(value = "select * from cliente c inner join persona p on LOWER(p.nombre_razon_social) like %:q% or lower(p.email) like %:q% or p.celular like %:q% or p.identificacion like %:q% where p.id_persona = c.id_persona", nativeQuery = true)
	public Page<Cliente> findByParams(Pageable pageable, String q);

	public Cliente findByIdentificacion(String identificacion);
}
