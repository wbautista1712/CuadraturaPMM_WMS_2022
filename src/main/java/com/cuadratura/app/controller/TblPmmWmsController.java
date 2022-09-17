package com.cuadratura.app.controller;

import java.time.format.DateTimeFormatter;
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

import com.cuadratura.app.oracle.dto.projection.ConsolidadoPmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.ResultadoPmmWmsDto;
import com.cuadratura.app.response.ListResponse;
import com.cuadratura.app.service.CrucePmmWmsService;
import com.cuadratura.app.service.TblPmmWmsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/wmsmysql")
public class TblPmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(TblPmmWmsController.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private TblPmmWmsService tblPmmWmsService;

	@Autowired
	private CrucePmmWmsService crucePmmWmsService;

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
	public ResponseEntity<ListResponse> getAllResultadoPmmWms(@RequestParam String idCD_org_name_short, String fechaDesde, String fechaHasta,
			@RequestParam  Integer rows, @RequestParam Integer page) {
		 ListResponse listResponse = new ListResponse();
		 Integer records = 0;
	     Integer start = listResponse.getStart(page, rows);
		try {

			LOGGER.info("getAllResultadoPmmWms  idCD_org_name_short " + idCD_org_name_short);
			List<ResultadoPmmWmsDto> result = tblPmmWmsService.getAllResultadoPmmWms(idCD_org_name_short , fechaDesde,  fechaHasta, start,  rows);
			records =tblPmmWmsService.countResultadoPmmWms(idCD_org_name_short , fechaDesde,  fechaHasta);
			LOGGER.info("result getAllResultadoPmmWms " + result.size());
			//return ResponseEntity.status(HttpStatus.OK).body(result);
			return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}


}
