package com.cuadratura.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;
import com.cuadratura.app.response.ListResponse;
import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.oracle.dto.projection.AjustePmmWmsDto;
import com.cuadratura.app.service.CrucePmmWmsService;
import com.cuadratura.app.service.TblPmmWmsService;
import com.cuadratura.app.util.Constantes;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class CrucePmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsController.class);
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private CrucePmmWmsService crucePmmWmsService;
	
	@Autowired
	private TblPmmWmsService tblPmmWmsService;

	@GetMapping(value = "/getAjusteBolsaDiscrepancia")
	public ResponseEntity<ListResponse> getAjusteBolsaDiscrepancia(@RequestParam Integer idCrucePmmWms) {
		try {

			LOGGER.info("getAjusteBolsaDiscrepancia");
			/*
			ListResponse listResponse = new ListResponse();
			Integer records = 0;
		    Integer start = listResponse.getStart(page, rows);
		    */

			//List<CrucePmmWmsDto> result = this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms, start, rows);
			List<CrucePmmWmsDto> result = this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms);
			//records =result.size();
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
			//return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
			
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
			this.crucePmmWmsService.spActualizarEstadoWMSPMMTotal(idCrucePmmWms, Constantes.ESTADO_CUADRATURA_VALIDACION);
			
			return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
	
	@PostMapping(value = "/crearCrucePmmWms")
	public ResponseEntity<?> crearCrucePmmWms(@RequestParam Integer idCargaPMM, @RequestParam Integer idCargaWMS,
			@RequestParam String idCD, @RequestParam Integer idUsuario) throws Exception {
		if (idCargaPMM == null) {
			return ResponseEntity.badRequest().body("Error Procesamiento");
		} else {
			CrucePmmWms crucePmmWms = new CrucePmmWms();

			crucePmmWms.setFechaMatch(new Date());
			crucePmmWms.setHoraMatch(dateTimeFormatter.format(LocalDateTime.now()));
			crucePmmWms.setIdCargaPMM(idCargaPMM);
			crucePmmWms.setIdCargaWMS(idCargaWMS);

			crucePmmWms.setIdEstadoCuadratura(Constantes.ESTADO_CUADRATURA);

			Integer id = this.crucePmmWmsService.saveCrucePmmWms(crucePmmWms).intValue();
		
			this.tblPmmWmsService.saveCrucePmmWms(idCargaPMM, idCargaWMS, idCD, idUsuario, id);
			
			this.crucePmmWmsService.spActualizarEstadoWMSPMMTotal(id, Constantes.ESTADO_CUADRATURA_PROCESO);
			return new ResponseEntity<>("Procesamiento Correcto. ", HttpStatus.OK);
		}
	}

}
