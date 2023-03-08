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

import com.fafact.models.TipoIdentificacion;
import com.fafact.service.TipoIdentificacionService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/tipo-identificacion/")
public class TipoIdentificacionController {

	@Autowired
	TipoIdentificacionService tipoIdentificacionService;

	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> RegistrarTipoIdentificacion(@Valid @RequestBody TipoIdentificacion tipoIdentificacion,
			BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			response.put("mensaje", "Existen uno o varios campos con errores.");
			response.put("error", obtenerErrores.Errores(result));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			tipoIdentificacionService.RegistrarTipoIdentificacion(tipoIdentificacion);
			response.put("mensaje", "Tipo de identificacion registrado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al registar.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar")
	public ResponseEntity<?> ObtenerTodosTiposIdentificacion() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<TipoIdentificacion> tipoIdList = tipoIdentificacionService.ObtenerTipoIdentificacionLista();
			response.put("mensaje", "Lista de tipos de identificacion obtenida.");
			response.put("content", tipoIdList);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener la lista de tipos de identificacion.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("listar/{id}")
	public ResponseEntity<?> ObtenerTiposIdentificacionByID(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			TipoIdentificacion tipoID = tipoIdentificacionService.ObtenerTipoIdById(id);
			if (tipoID == null) {
				response.put("mensaje", "El tipo de identificacion solicitado no existe.");
				response.put("error", "No existe.");
				response.put("content", null);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("mensaje", "Tipo de identificacion obtenida.");
			response.put("content", tipoID);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al obtener el tipo de identificacion.");
			response.put("content", null);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
