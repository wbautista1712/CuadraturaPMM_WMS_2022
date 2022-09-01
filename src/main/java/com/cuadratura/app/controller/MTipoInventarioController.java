package com.cuadratura.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.MTipoInventario;
import com.cuadratura.app.oracle.dto.projection.MTipoInventarioDto;
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
		LOGGER.info("getAll");
		return ResponseEntity.ok().body(mTipoInventarioService.getAll());
	}

	@GetMapping(value = "/getAnalisisAjustePmmWms")
	public ResponseEntity<List<MTipoInventarioDto>> getAnalisisAjustePmmWms(@RequestParam Integer idCrucePmmWms,
			@RequestParam Integer idTipoInventario) {
		try {
			LOGGER.info("listAnalisisAjustePmmWms  idCrucePmmWms "+idCrucePmmWms);
			List<MTipoInventarioDto> result = mTipoInventarioService.listAnalisisAjustePmmWms(idCrucePmmWms, idTipoInventario);
			LOGGER.info("result "+result.size());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
}
