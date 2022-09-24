package com.cuadratura.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.service.PrdpcdeeService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")

public class PrdpcdeeController {
	private static final Logger LOGGER = LogManager.getLogger(PrdpcdeeController.class);
	
	@Autowired
	private PrdpcdeeService prdpcdeeService;
	
	@GetMapping(value = "/obtenerPrdpcdee")
	public ResponseEntity<Long> obtenerPrdpcdee(String codigoMaterial) {
		LOGGER.info("obtenerPrdpcdee");
		return ResponseEntity.ok().body(prdpcdeeService.findPrdpcdee("143540"));
	}
	
}
