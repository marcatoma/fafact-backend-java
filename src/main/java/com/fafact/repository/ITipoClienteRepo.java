package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.TipoCliente;

public interface ITipoClienteRepo extends JpaRepository<TipoCliente, Long> {

}
