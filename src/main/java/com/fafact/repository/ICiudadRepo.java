package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.Ciudad;

public interface ICiudadRepo extends JpaRepository<Ciudad, Long> {

}
