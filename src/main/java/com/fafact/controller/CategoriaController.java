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

import com.fafact.models.Categoria;
import com.fafact.service.CategoriaService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria/")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> RegistrarCategoria(@Valid @RequestBody Categoria categoria, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			errors = obtenerErrores.Errores(result);
			response.put("mensaje", "Existen uno o varios campos con errores.");
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			categoriaService.RegistrarCategoria(categoria);
			response.put("mensaje", "Categoria registrada.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "No registrado.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/page/{page}/filas/{filas}")
	public ResponseEntity<?> ObtenerTodasCategorias(@PathVariable int page, @PathVariable int filas,
			@RequestParam(required = false) String q) {
		Map<String, Object> response = new HashMap<>();
		try {
			q = (q == null) ? "" : q;
			Pageable pageable = PageRequest.of(page, filas);
			Page<Categoria> categorias = categoriaService.ListarCategoriasParametro(pageable, q);
			response.put("mensaje", "Lista de categorias obtenida.");
			response.put("content", categorias);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la lista de categorias.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/{id}")
	public ResponseEntity<?> ObtenerCategiriaByID(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Categoria categoria = categoriaService.ObtenerCategoriaById(id);
			if (categoria == null) {
				response.put("mensaje", "la categoria solicitada no existe.");
				response.put("error", "No existe.");
				response.put("content", null);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("mensaje", "Categoria obtenida.");
			response.put("content", categoria);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la categoria solicitada.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}
}
