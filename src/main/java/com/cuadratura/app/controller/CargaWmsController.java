package com.cuadratura.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.cuadratura.app.util.ExcelGeneratorFotoWms;

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
	
	@GetMapping("/exportFotoWmsExcel")
	public void exportFotoWmsExcelFile(@RequestParam String idCD,
			@RequestParam String fechaDesde, @RequestParam String fechaHasta, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=FotoWms" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<FotoWmsDto> listOfStudents = this.cargaWmsService.getAllFindFotoWms(idCD, fechaDesde, fechaHasta);
		ExcelGeneratorFotoWms generator = new ExcelGeneratorFotoWms(listOfStudents);

		generator.generateExcelFile(response);
	}
}
