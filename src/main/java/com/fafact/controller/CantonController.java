package com.fafact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fafact.models.Canton;
import com.fafact.service.CantonService;

@RestController
@CrossOrigin("*")
@RequestMapping("/canton/")
public class CantonController {

	@Autowired
	CantonService cantonService;

	@GetMapping("listar")
	public ResponseEntity<?> ObtenerTodosCantones(@RequestParam(required = false) String q) {
		Map<String, Object> response = new HashMap<>();
		try {
			q = (q == null) ? "" : q;
			List<Canton> cantones = cantonService.listarCantones(q);
			response.put("mensaje", "lista de cantones obtenida.");
			response.put("content", cantones);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Se a producido un error al obtener los cantones.");
			response.put("content", null);
			response.put("errror", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

	}

}
