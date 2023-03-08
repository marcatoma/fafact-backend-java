package com.fafact.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.Categoria;

public interface ICategoriaRepo extends JpaRepository<Categoria, Long> {

	@Query(value = "select * from categoria c where LOWER(c.descripcion) like %:q%", nativeQuery = true)
	public Page<Categoria> findByParams(Pageable pageable, String q);

	public Categoria findByDescripcionIgnoreCase(String descripcion);
	
}
