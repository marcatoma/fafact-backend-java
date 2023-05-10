package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.Marca;
import com.fafact.repository.IMarcaRepo;

@Service
public class MarcaService {
	@Autowired
	IMarcaRepo marcaRepo;

	@Transactional(readOnly = true)
	public Marca ObtenerMarcaById(Long id) {
		return marcaRepo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public List<Marca> ListarTodasMarcas() {
		return marcaRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Marca> ListarMarcasPageable(Pageable pageable, String query) {
		return marcaRepo.findByParams(pageable, query.toLowerCase());
	}

	@Transactional
	public Long RegistrarMarca(Marca marca) {
		return marcaRepo.save(marca).getIdMarca();
	}

	@Transactional
	public void EliminarMarcaLogico(Marca marca) {
		marcaRepo.save(marca);
	}

	@Transactional(readOnly = true)
	public boolean ValidarExistencia(Marca ma) {
		Marca marca = marcaRepo.findByDescripcionIgnoreCase(ma.getDescripcion());
		if (marca == null) return false;

		return (marca.getIdMarca() == ma.getIdMarca()) ? false : true;
	}
}
