package com.fafact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.fafact.models.DetalleVenta;
import com.fafact.models.Factura;
import com.fafact.service.FacturaService;
import com.fafact.service.ProductoService;
import com.fafact.validator.ObtenerErrores;

@RestController
@CrossOrigin("*")
@RequestMapping("/factura/")
public class FacturaController {

	@Autowired
	FacturaService facturaService;

	@Autowired
	ProductoService productoService;
	
	@Autowired
	ObtenerErrores obtenerErrores;

	@PostMapping("crear")
	public ResponseEntity<?> CrearFactura(@Valid @RequestBody Factura factura, BindingResult result) {
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
		// seguir el proceso
		
		try {
//			for (DetalleVenta item : factura.getDetalleVenta()) {
//				if (item.getProducto_item().getPrecio()!=null) break;
//				item.setProducto_item(productoService.ObtenerProdcutoById(item.getProducto_item().getIdProducto()));
//				System.out.println("precio: "+item.getProducto_item().getPrecio().doubleValue());
//				item.setImporte(item.getCantidad().multiply(item.getProducto_item().getPrecio()));
//				factura.setTotal(factura.getTotal().add(item.getImporte()));
//			}
			facturaService.RegistrarFactura(factura);
			response.put("mensaje", "Factura Creada.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "No registrado.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("listar/page/{page}/filas/{filas}")
	public ResponseEntity<?> ListarFacturas(@PathVariable int page, @PathVariable int filas,
			@RequestParam(required = false) String q) {
		Map<String, Object> response = new HashMap<>();
		try {
			q = (q == null) ? "" : q;
			Pageable pageable = PageRequest.of(page, filas);
//			Page<Factura> fact = facturaService.ListarFacturasPageable(pageable, q);
			List<Factura> fact = facturaService.ListarTodasFacturas();
			response.put("content", fact);
			response.put("mensaje", "Lista de facturas");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("content", null);
			response.put("mensaje", "Lista de facturas no obtenida.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
