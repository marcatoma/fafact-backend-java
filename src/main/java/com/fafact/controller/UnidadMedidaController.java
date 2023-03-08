package com.fafact.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fafact.models.UnidadMedida;
import com.fafact.service.UnidadMedidaService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/unidad-medida/")
public class UnidadMedidaController {

	@Autowired
	UnidadMedidaService unidadMedidaService;
	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> RegistrarUnidadMedida(@Valid @RequestBody UnidadMedida unidadMedida,
			BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		// ObtenerErrores
		if (result.hasErrors()) {
			response.put("mensaje", "Uno o varios campos tienen errores.");
			Map<String, String> errors = new HashMap<>();
			// Service para obtener la lista de campos con errores
			errors = obtenerErrores.Errores(result);
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//validar si ya existe el registro
		try {
			boolean existe = unidadMedidaService.ValidarExistencia(unidadMedida);
			if (existe) {
				
				response.put("mensaje", "No registrado.");
				response.put("error", "Ya existe un registro con esos datos.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "No registrado.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		// guardar el registro
		try {
			unidadMedidaService.RegistrarUnidadMedida(unidadMedida);
			response.put("mensaje", "Creado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "No registrado.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/page/{page}/filas/{filas}")
	public ResponseEntity<?> ListarUnidadesMedida(@PathVariable int page, @PathVariable int filas,
			@RequestParam(required = false) String q) {
		Map<String, Object> response = new HashMap<>();
		try {
			q = (q == null) ? "" : q;
			Pageable pageable = PageRequest.of(page, filas);
			Page<UnidadMedida> unidMed = unidadMedidaService.ListarUnidadesMedidaParametros(pageable, q);
			response.put("content", unidMed);
			response.put("mensaje", "Lista de Unidades de Medida");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("content", null);
			response.put("mensaje", "Lista de unidades de medida no obtenida.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("listar/{id}")
	public ResponseEntity<?> ObtenerUnidadMedidaByID(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			UnidadMedida unidadMedida = unidadMedidaService.ObtenerUnidadMedidaByID(id);
			if (unidadMedida == null) {
				response.put("mensaje", "La unidad de medida solicitada no existe.");
				response.put("error", "No existe.");
				response.put("content", null);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("mensaje", "Unidad de medida obtenida.");
			response.put("content", unidadMedida);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la unidad de medida.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
