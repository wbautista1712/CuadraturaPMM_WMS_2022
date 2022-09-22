package com.cuadratura.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.service.FapinvbaleeService;

@RestController
@RequestMapping(path = "/api/wmsoracle")
@CrossOrigin(origins = "*")
public class FapinvbaleeController {

	private static final Logger LOGGER = LogManager.getLogger(FapinvbaleeController.class);
	
	@Autowired
	private FapinvbaleeService fapinvbaleeService;

	@GetMapping(value = "/obtenerListFapinvbalee")
	public ResponseEntity<List<Fapinvbalee>> obtenerTodos() {
		LOGGER.info("oracle Fapinvbalee  size "+fapinvbaleeService.findAllPMMFapinvbalee(5342).size());
		return null;
		//return ResponseEntity.ok().body(fapinvbaleeService.findAllFapinvbalee());
	}
}
