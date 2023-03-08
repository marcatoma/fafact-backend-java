package com.fafact.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.UnidadMedida;

public interface IUnidadMedidaRepo extends JpaRepository<UnidadMedida, Long> {

	@Query(value = "select * from unidad_medida um where LOWER(um.descripcion) like %:q% or LOWER(um.abreviacion)  like %:q%", nativeQuery = true)
	public Page<UnidadMedida> findByParams(Pageable pageable, String q);

	public UnidadMedida findByDescripcionOrAbreviacionIgnoreCase(String descripcion, String abreviacion);
}
