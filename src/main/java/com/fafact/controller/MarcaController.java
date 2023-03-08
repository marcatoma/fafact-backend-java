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

import com.fafact.models.Marca;
import com.fafact.service.MarcaService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/marca/")
public class MarcaController {

	@Autowired
	MarcaService marcaService;
	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> RegistrarMarca(@Valid @RequestBody Marca marca, BindingResult result) {
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

		// validar si ya existe el registro
		try {
			boolean existe = marcaService.ValidarExistencia(marca);
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
			marcaService.RegistrarMarca(marca);
			response.put("mensaje", "Creado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "No registrado.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/page/{page}/filas/{filas}")
	public ResponseEntity<?> ListarMarca(@PathVariable int page, @PathVariable int filas,
			@RequestParam(required = false) String q) {
		Map<String, Object> response = new HashMap<>();
		try {
			q = (q == null) ? "" : q;
			Pageable pageable = PageRequest.of(page, filas);
			Page<Marca> marca = marcaService.ListarMarcasPageable(pageable, q);
			response.put("content", marca);
			response.put("mensaje", "Lista de Marcas");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("content", null);
			response.put("mensaje", "Lista de marcas no obtenida.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("listar/{id}")
	public ResponseEntity<?> ObtenerMarcaByID(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Marca marca = marcaService.ObtenerMarcaById(id);
			if (marca == null) {
				response.put("mensaje", "La marca solicitada no existe.");
				response.put("error", "No existe.");
				response.put("content", null);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("mensaje", "Marca obtenida.");
			response.put("content", marca);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la marca.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
