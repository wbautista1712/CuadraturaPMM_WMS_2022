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

import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;
import com.cuadratura.app.service.CrucePmmWmsService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class CrucePmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsController.class);
	@Autowired
	private CrucePmmWmsService crucePmmWmsService;

	@GetMapping(value = "/getAjusteBolsaDiscrepancia")
	public ResponseEntity<List<CrucePmmWmsDto>> getAjusteBolsaDiscrepancia(@RequestParam int idCrucePmmWms,
			@RequestParam String idCDOrgNameShort) {
		try {

			LOGGER.info("getAjusteBolsaDiscrepancia");

			List<CrucePmmWmsDto> result = crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms,
					idCDOrgNameShort);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}