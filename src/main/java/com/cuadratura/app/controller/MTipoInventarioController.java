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

import com.cuadratura.app.mysql.entity.MTipoInventario;
import com.cuadratura.app.service.MTipoInventarioService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class MTipoInventarioController {
	private static final Logger LOGGER = LogManager.getLogger(MTipoInventarioController.class);

	@Autowired
	private MTipoInventarioService mTipoInventarioService;

	@GetMapping(value = "/obtenerTipoInventario")
	public ResponseEntity<List<MTipoInventario>> obtenerTipoInventario() {
		LOGGER.info("obtenerTipoInventario");
		return ResponseEntity.ok().body(mTipoInventarioService.getTipoInventario());
	}

	@GetMapping(value = "/obtenerTipoInventarioLote")
	public ResponseEntity<List<MTipoInventario>> obtenerTipoInventarioLote(String codigoMaterial) {
		LOGGER.info("obtenerTipoInventario2");
		return ResponseEntity.ok().body(mTipoInventarioService.getTipoInventarioLote(codigoMaterial));
	}

}
