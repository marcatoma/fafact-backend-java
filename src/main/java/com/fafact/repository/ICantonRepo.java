package com.fafact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.Canton;

public interface ICantonRepo extends JpaRepository<Canton, Long> {

	@Query(value = "select * from canton c where lower(c.nombre_canton) like %:q%", nativeQuery = true)
	List<Canton> findByNombreCantonQuery(String q);

}
