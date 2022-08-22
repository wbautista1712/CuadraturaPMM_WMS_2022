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

import com.cuadratura.app.mysql.entity.MOrgmstee;
import com.cuadratura.app.service.MOrgmsteeService;


@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class MOrgmsteeController {
	private static final Logger LOGGER = LogManager.getLogger(MOrgmsteeController.class);
	
	@Autowired
	private MOrgmsteeService mOrgmsteeService;
	
	@GetMapping(value = "/obtenerListCD")
	public ResponseEntity<List<MOrgmstee>> obtenerListCD() {
		LOGGER.info("getAll");
		return ResponseEntity.ok().body(mOrgmsteeService.getAll());
	}
	
}