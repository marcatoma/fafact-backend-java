package com.fafact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fafact.models.SubCategoria;

public interface ISubCategoriaRepo extends JpaRepository<SubCategoria, Long> {

	@Query(value = "select * from sub_categoria sc where categoria_id_categoria =:q", nativeQuery = true)
	public List<SubCategoria> findByParams(Long q);
	
}
