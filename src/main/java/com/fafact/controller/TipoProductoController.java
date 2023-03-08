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

import com.fafact.models.TipoProducto;
import com.fafact.service.TipoProductoService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/tipo-producto/")
public class TipoProductoController {

	@Autowired
	TipoProductoService tipoProductoService;

	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> RegistrarTipoProducto(@Valid @RequestBody TipoProducto tipoProducto,
			BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			response.put("mensaje", "Existen uno o varios campos con errores.");
			response.put("error", obtenerErrores.Errores(result));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			tipoProductoService.RegistrarTipoProducto(tipoProducto);
			response.put("mensaje", "Tipo de producto registrado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al registar.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar")
	public ResponseEntity<?> ObtenerTodosTiposProducto() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<TipoProducto> tipoProdList = tipoProductoService.listarTipoProductos();
			response.put("mensaje", "Lista de tipos de productos obtenida.");
			response.put("content", tipoProdList);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la lista de tipos de productos.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/{id}")
	public ResponseEntity<?> ObtenerTiposIdentificacionByID(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			TipoProducto tipoProd = tipoProductoService.ObtenerTipoProductoByID(id);
			if (tipoProd == null) {
				response.put("mensaje", "El tipo de producto solicitado no existe.");
				response.put("error", "No existe.");
				response.put("content", null);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("mensaje", "Tipo de producto obtenido.");
			response.put("content", tipoProd);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener el tipo de producto.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}
}
