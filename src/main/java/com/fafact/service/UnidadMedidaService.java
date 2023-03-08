package com.fafact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.UnidadMedida;
import com.fafact.repository.IUnidadMedidaRepo;

@Service
public class UnidadMedidaService {

	@Autowired
	IUnidadMedidaRepo unidadMedidaRepo;

	@Transactional(readOnly = true)
	public UnidadMedida ObtenerUnidadMedidaByID(Long id) {
		return unidadMedidaRepo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Page<UnidadMedida> ListarUnidadesMedidaParametros(Pageable pageable, String q) {
		return unidadMedidaRepo.findByParams(pageable, q.toLowerCase());
	}

	@Transactional
	public Long RegistrarUnidadMedida(UnidadMedida unidadMedida) {
		return unidadMedidaRepo.save(unidadMedida).getIdUnidadMedida();
	}

	@Transactional
	public Long EliminarUnidadMedidaLogico(UnidadMedida u) {
		return unidadMedidaRepo.save(u).getIdUnidadMedida();
	}

	@Transactional
	public void EliminarUnidadMedida(UnidadMedida u) {
		unidadMedidaRepo.deleteById(u.getIdUnidadMedida());
	}

	@Transactional(readOnly = true)
	public boolean ValidarExistencia(UnidadMedida u) {
		UnidadMedida rem = unidadMedidaRepo.findByDescripcionOrAbreviacionIgnoreCase(u.getDescripcion(),
				u.getAbreviacion());
		if (rem==null) return false;
		return (rem.getIdUnidadMedida() == u.getIdUnidadMedida()) ? false : true;
	}

}
