package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Long> {

}
