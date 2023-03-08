package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.TipoProducto;
import com.fafact.repository.ITipoProductoRepo;

@Service
public class TipoProductoService {

	@Autowired
	ITipoProductoRepo tipoProductoRepo;

	@Transactional(readOnly = true)
	public List<TipoProducto> listarTipoProductos() {
		return tipoProductoRepo.findAll();
	}

	@Transactional(readOnly = true)
	public TipoProducto ObtenerTipoProductoByID(Long id) {
		return tipoProductoRepo.findById(id).orElse(null);
	}

	@Transactional
	public Long RegistrarTipoProducto(TipoProducto tipoProducto) {
		return tipoProductoRepo.save(tipoProducto).getId();
	}
}
