package com.cuadratura.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.MPrdmstee;
import com.cuadratura.app.service.MPrdmsteeService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class MPrdmsteeController {
	
	private static final Logger LOGGER = LogManager.getLogger(MPrdmsteeController.class);

	@Autowired
	private MPrdmsteeService mPrdmsteeService;

	@GetMapping(value = "/obtenerMaterial")
	public ResponseEntity<List<MPrdmstee>> obtenerMaterial(@RequestParam String nombreMaterial) {
		LOGGER.info("nombreMaterial");
		return ResponseEntity.ok().body(mPrdmsteeService.getMaterialLote(nombreMaterial.toUpperCase()));
	}
}
