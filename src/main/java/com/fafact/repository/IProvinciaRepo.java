package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.Provincia;

public interface IProvinciaRepo extends JpaRepository<Provincia, Long> {

}
