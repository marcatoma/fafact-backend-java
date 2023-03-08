package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fafact.models.Factura;

@Repository
public interface IFacturaRepo extends JpaRepository<Factura, Long> {

}
