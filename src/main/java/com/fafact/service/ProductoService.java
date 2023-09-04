package com.fafact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.Producto;
import com.fafact.repository.IProductoRepo;

@Service
public class ProductoService {

	@Autowired
	IProductoRepo productoRepo;

	@Transactional(readOnly = true)
	public Producto ObtenerProdcutoById(Long id) {
		return productoRepo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Producto ObtenerProdcutoByCodigoProducto(String query) {
		return productoRepo.findByCodigoIgnoreCase(query);
	}

	@Transactional
	public Producto RegistrarProducto(Producto prod) {
		return productoRepo.save(prod);
	}

	@Transactional(readOnly = true)
	public Page<Producto> ListarProductoParametro(Pageable pageable, String query) {
		return productoRepo.findByParams(pageable, query.toLowerCase());
	}

	@Transactional(readOnly = true)
	public boolean ValidarExistencia(Producto prod) {
		Producto producto = productoRepo.findByCodigoIgnoreCase(prod.getCodigo());
		if (producto == null)
			return false;

		return (producto.getIdProducto() == prod.getIdProducto()) ? false : true;
	}

}
