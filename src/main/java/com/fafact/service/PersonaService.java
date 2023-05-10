package com.fafact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fafact.repository.IPersonaRepo;

@Service
public class PersonaService {

	@Autowired
	IPersonaRepo personaRepo;
	
	

}
