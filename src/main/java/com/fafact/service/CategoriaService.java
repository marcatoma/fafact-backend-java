package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.Categoria;
import com.fafact.repository.ICategoriaRepo;

@Service
public class CategoriaService {

	@Autowired
	ICategoriaRepo categoriaRepo;

	@Transactional(readOnly = true)
	public Categoria ObtenerCategoriaById(Long id) {
		return categoriaRepo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Categoria> ListarCategorias() {
		return categoriaRepo.findAll();
	}

	@Transactional
	public Long RegistrarCategoria(Categoria categoria) {
		return categoriaRepo.save(categoria).getIdCategoria();
	}

	@Transactional(readOnly = true)
	public Page<Categoria> ListarCategoriasParametro(Pageable pageable, String query) {
		return categoriaRepo.findByParams(pageable, query.toLowerCase());
	}

	@Transactional(readOnly = true)
	public boolean ValidarExistencia(Categoria u) {
		Categoria cat = categoriaRepo.findByDescripcionIgnoreCase(u.getDescripcion());
		if (cat == null)
			return false;
		return (cat.getIdCategoria() == u.getIdCategoria()) ? false : true;
	}

}
