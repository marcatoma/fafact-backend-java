package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.SubCategoria;
import com.fafact.repository.ISubCategoriaRepo;

@Service
public class SubCategoriaService {

	@Autowired
	ISubCategoriaRepo iSubCategoriaRepo;

	@Transactional(readOnly = true)
	public SubCategoria ObtenerSubCategoriaById(Long id) {
		return iSubCategoriaRepo.findById(id).orElse(null);
	}

	@Transactional
	public Long RegistrarSubCategoria(SubCategoria subCategoria) {
		return iSubCategoriaRepo.save(subCategoria).getId_sub_categoria();
	}

	@Transactional(readOnly = true)
	public List<SubCategoria> ObtenerListaSubCategorias() {
		return iSubCategoriaRepo.findAll();
	}

}
