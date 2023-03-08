package com.fafact.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.Marca;

public interface IMarcaRepo extends JpaRepository<Marca, Long> {

	@Query(value = "select * from marca m where LOWER(m.descripcion) like %:q%", nativeQuery = true)
	public Page<Marca> findByParams(Pageable pageable, String q);

	public Marca findByDescripcionIgnoreCase(String descripcion);

}
