package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.Cliente;
import com.fafact.repository.IClienteRepo;

@Service
public class ClienteService {

	@Autowired
	IClienteRepo clienteRepo;

	@Transactional(readOnly = true)
	public Cliente ObtenerClienteById(Long id) {
		return clienteRepo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Cliente> ListarTodosClientes() {
		return clienteRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Cliente> ListarClientesPageable(Pageable pageable, String query) {
		System.out.println("query " + query);
		return clienteRepo.findByParams(pageable, query.toLowerCase());
	}

	@Transactional
	public Long RegistrarCliente(Cliente cliente) {
		return clienteRepo.save(cliente).getIdPersona();
	}

	@Transactional
	public void EliminarClienteLogico(Cliente cliente) {
		// clienteRepo.save(marca);
	}

	@Transactional(readOnly = true)
	public boolean ValidarExistencia(Cliente cli) {
		Cliente cliente = clienteRepo.findByIdentificacion(cli.getIdentificacion());
		// retorna falso si no existe un cliente con esa identificacion y procede para
		// guardar
		if (cliente == null)
			return false;
		// OPCION SOLO PARA LA ACTUALIZACION DE DATOS, PORQUE CASO CONTRARIO NO EXITE Y
		// SE CREA UN NUEVO REGISTRO
		// comparamos dos personas si se cumple la condicion es xq ya existe una persona
		// con el
		// id e identificacion buscada
		return (cliente.getIdPersona() == cli.getIdPersona()) ? false : true;
	}

}
