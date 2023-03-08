package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.TipoIdentificacion;

public interface ITipoIdentificacionRepo extends JpaRepository<TipoIdentificacion, Long> {

}
