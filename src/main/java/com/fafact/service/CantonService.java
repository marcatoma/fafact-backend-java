package com.fafact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fafact.models.Canton;
import com.fafact.repository.ICantonRepo;

@Service
public class CantonService {

	@Autowired
	ICantonRepo cantonRepo;

	@Transactional(readOnly = true)
	public List<Canton> listarCantones(String query) {
		return cantonRepo.findByNombreCantonQuery(query.toLowerCase());
	}

}
