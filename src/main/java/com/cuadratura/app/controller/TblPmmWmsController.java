package com.cuadratura.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.oracle.dto.projection.ConsolidadoPmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.ResultadoPmmWmsDto;
import com.cuadratura.app.service.TblPmmWmsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/wmsmysql")
public class TblPmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(TblPmmWmsController.class);

	@Autowired
	private TblPmmWmsService tblPmmWmsService;

	@GetMapping(value = "/getAllConsolidadoPmmWms")
	public ResponseEntity<List<ConsolidadoPmmWmsDto>> getAllConsolidadoPmmWms(@RequestParam Integer idCargaWms,
			@RequestParam Integer idCargaPmm, @RequestParam String idCD) {
		try {

			LOGGER.info("getAllConsolidadoPmmWms  idCargaWms " + idCargaWms);

			List<ConsolidadoPmmWmsDto> result = tblPmmWmsService.getAllConsolidadoPmmWms(idCargaWms, idCargaPmm, idCD);
			LOGGER.info("result getAllConsolidadoPmmWms " + result.size());
			return ResponseEntity.status(HttpStatus.OK).body(result);

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	@GetMapping(value = "/getAllResultadoPmmWms")
	public ResponseEntity<List<ResultadoPmmWmsDto>> getAllResultadoPmmWms(@RequestParam Integer idCargaWms,
			@RequestParam Integer idCargaPmm) {
		try {

			LOGGER.info("getAllResultadoPmmWms  idCargaWms " + idCargaWms);

			List<ResultadoPmmWmsDto> result = tblPmmWmsService.getAllResultadoPmmWms(idCargaWms, idCargaPmm);
			LOGGER.info("result getAllResultadoPmmWms " + result.size());
			return ResponseEntity.status(HttpStatus.OK).body(result);

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	@PostMapping(value = "/crearCrucePmmWms")
	public ResponseEntity<?> crearCrucePmmWms(@RequestParam Integer idCargaPMM, @RequestParam Integer idCargaWMS,
			@RequestParam String idCD, @RequestParam Integer idUsuario) throws Exception {
		if (idCargaPMM == null)
			return ResponseEntity.badRequest().body("Error Procesamiento");
		tblPmmWmsService.saveCrucePmmWms(idCargaPMM, idCargaWMS, idCD, idUsuario);
		return new ResponseEntity<>("Procesamiento Correcto. ", HttpStatus.OK);
	}
}
