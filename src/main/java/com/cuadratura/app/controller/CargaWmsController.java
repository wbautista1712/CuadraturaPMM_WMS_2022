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

import com.cuadratura.app.oracle.dto.projection.FotoWmsDto;
import com.cuadratura.app.service.CargaWmsService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class CargaWmsController {
	private static final Logger LOGGER = LogManager.getLogger(CargaWmsController.class);

	@Autowired
	private CargaWmsService cargaWmsService;

	@GetMapping(value = "/getAllFotoWms")
	public ResponseEntity<List<FotoWmsDto>> getAllFotoWms(@RequestParam String idCD,
			@RequestParam String fechaDesde, @RequestParam String fechaHasta) {
		try {
			LOGGER.info("getAllFotoPmmm  fechaDesde "+fechaDesde);

			List<FotoWmsDto> result = cargaWmsService.getAllFindFotoWms(idCD, fechaDesde, fechaHasta);
			LOGGER.info("result getAllFotoWms "+result.size());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
}
