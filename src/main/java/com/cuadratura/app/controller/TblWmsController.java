package com.cuadratura.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.service.TblWmsService;


@RestController
@RequestMapping(path = "/api/wmsmysql")
public class TblWmsController {
	
	private static final Logger LOGGER = LogManager.getLogger(TblWmsController.class);
	
	@Autowired
	private TblWmsService usuarioService;
	//2022.8.20
	@GetMapping(value = "/obtenerListTblWms")
	public ResponseEntity<List<TblWms>> obtenerTodos() {
		LOGGER.info("mysql");
		return null;
		//return ResponseEntity.ok().body(usuarioService.fetchTblWmsList());
	}
}
