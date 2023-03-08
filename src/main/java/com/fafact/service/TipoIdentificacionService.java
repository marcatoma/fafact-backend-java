package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.TipoIdentificacion;
import com.fafact.repository.ITipoIdentificacionRepo;

@Service
public class TipoIdentificacionService {

	@Autowired
	ITipoIdentificacionRepo tipoIdentificacionRepo;

	@Transactional(readOnly = true)
	public TipoIdentificacion ObtenerTipoIdById(Long id) {
		return tipoIdentificacionRepo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<TipoIdentificacion> ObtenerTipoIdentificacionLista() {
		return tipoIdentificacionRepo.findAll();
	}

	@Transactional
	public Long RegistrarTipoIdentificacion(TipoIdentificacion tipoID) {
		return tipoIdentificacionRepo.save(tipoID).getId();
	}

}
