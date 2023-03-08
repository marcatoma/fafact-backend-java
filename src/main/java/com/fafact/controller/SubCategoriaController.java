package com.fafact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fafact.models.SubCategoria;
import com.fafact.service.SubCategoriaService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/sub-categoria/")
public class SubCategoriaController {

	@Autowired
	SubCategoriaService subCategoriaService;

	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> RegistrarSubCategoria(@Valid @RequestBody SubCategoria subCategoria,
			BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			response.put("mensaje", "Existen uno o varios campos con errores.");
			response.put("error", obtenerErrores.Errores(result));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			subCategoriaService.RegistrarSubCategoria(subCategoria);
			response.put("mensaje", "Sub categoria registrada.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al registar.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar")
	public ResponseEntity<?> ObtenerTodasSubCategorias() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<SubCategoria> subCategorias = subCategoriaService.ObtenerListaSubCategorias();
			response.put("mensaje", "Lista de sub categorias obtenida.");
			response.put("content", subCategorias);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la lista de sub categorias.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/{id}")
	public ResponseEntity<?> ObtenerSubCategiriaByID(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			SubCategoria subCategoria = subCategoriaService.ObtenerSubCategoriaById(id);
			if (subCategoria == null) {
				response.put("mensaje", "la sub categoria solicitada no existe.");
				response.put("error", "No existe.");
				response.put("content", null);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("mensaje", "Sub categoria obtenida.");
			response.put("content", subCategoria);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la sub categoria solicitada.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
