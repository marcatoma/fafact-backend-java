package com.fafact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fafact.models.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Long>{

}
