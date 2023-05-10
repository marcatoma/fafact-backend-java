package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.TipoCliente;
import com.fafact.repository.ITipoClienteRepo;

@Service
public class TipoClienteService {

	@Autowired
	ITipoClienteRepo tipoClienteRepo;

	@Transactional(readOnly = true)
	public TipoCliente ObtenerTipoClienteById(Long id) {
		return tipoClienteRepo.findById(id).orElse(null);
	}

	@Transactional
	public Long RegistrarTipoCliente(TipoCliente tipoCliente) {
		return tipoClienteRepo.save(tipoCliente).getIdTipoCliente();
	}

	@Transactional(readOnly = true)
	public List<TipoCliente> ObtenerListaTipoCliente() {
		return tipoClienteRepo.findAll();
	}
}
