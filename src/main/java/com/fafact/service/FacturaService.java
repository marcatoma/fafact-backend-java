package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.Factura;
import com.fafact.repository.IFacturaRepo;

@Service
public class FacturaService {

	@Autowired
	IFacturaRepo facturaRepo;

	@Transactional(readOnly = true)
	public Factura ObtenerFacturaById(Long id) {
		return facturaRepo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Factura> ListarTodasFacturas() {
		return facturaRepo.findAll();
	}

//	@Transactional(readOnly = true)
//	public Page<Factura> ListarFacturasPageable(Pageable pageable, String query) {
//		System.out.println("query " + query);
//		return clienteRepo.findByParams(pageable, query.toLowerCase());
//	}

	@Transactional
	public Long RegistrarFactura(Factura factura) {
		return facturaRepo.save(factura).getId_factura();
	}

}
