package com.cuadratura.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.service.FapprdloteeService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")

public class FapprdloteeController {
private static final Logger LOGGER = LogManager.getLogger(FapprdloteeController.class);
	
@Autowired
private FapprdloteeService fapprdloteeService;
	
	@GetMapping(value = "/obtenerFapprdlotee")
	public ResponseEntity<String> obtenerFapprdlotee() throws Exception {
		LOGGER.info("obtenerFapprdlotee");
		return ResponseEntity.ok().body(fapprdloteeService.findFapprdlotee(50400, "SDF21"));
	}
	
}
