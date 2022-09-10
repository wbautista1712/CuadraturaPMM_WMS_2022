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
import com.cuadratura.app.response.ListResponse;
import com.cuadratura.app.oracle.dto.projection.AjustePmmWmsDto;
import com.cuadratura.app.service.CrucePmmWmsService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class CrucePmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsController.class);
	@Autowired
	private CrucePmmWmsService crucePmmWmsService;

	@GetMapping(value = "/getAjusteBolsaDiscrepancia")
	public ResponseEntity<ListResponse> getAjusteBolsaDiscrepancia(@RequestParam Integer idCrucePmmWms,  @RequestParam  Integer rows, @RequestParam Integer page) {
		try {

			LOGGER.info("getAjusteBolsaDiscrepancia");
			ListResponse listResponse = new ListResponse();
			Integer records = 0;
		    Integer start = listResponse.getStart(page, rows);

			List<CrucePmmWmsDto> result = crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms, start, rows);
			records =result.size();
			
			//return ResponseEntity.status(HttpStatus.OK).body(result);
			return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
	
	@GetMapping(value = "/getAnalisisAjustePmmWms")
	public ResponseEntity<ListResponse> getAnalisisAjustePmmWms(@RequestParam Integer idCrucePmmWms,  @RequestParam  Integer rows, @RequestParam Integer page) {
		ListResponse listResponse = new ListResponse();
		Integer records = 0;
	    Integer start = listResponse.getStart(page, rows);
		try 
		{
			LOGGER.info("listAnalisisAjustePmmWms  idCrucePmmWms "+idCrucePmmWms);
			
			List<AjustePmmWmsDto> result = crucePmmWmsService.listAnalisisAjustePmmWms(idCrucePmmWms, start, rows);
			records =result.size();
			
			LOGGER.info("result "+result.size());
		//	return ResponseEntity.status(HttpStatus.OK).body(result);
			return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}
