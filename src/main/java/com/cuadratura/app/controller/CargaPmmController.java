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

import com.cuadratura.app.oracle.dto.projection.FotoPmmDto;
import com.cuadratura.app.service.CargaPmmService;
import com.cuadratura.app.util.ExcelGeneratorFotoPmm;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class CargaPmmController {
	private static final Logger LOGGER = LogManager.getLogger(CargaPmmController.class);

	@Autowired
	private CargaPmmService cargaPmmService;

	@GetMapping(value = "/getAllFotoPmm")
	public ResponseEntity<List<FotoPmmDto>> getAllFotoPmm(@RequestParam String idCD, @RequestParam String fechaDesde,
			@RequestParam String fechaHasta) {
		try {
			LOGGER.info("getAllFotoPmmm  fechaDesde " + fechaDesde);

			List<FotoPmmDto> result = cargaPmmService.getAllFindFotoPmm(idCD, fechaDesde, fechaHasta);
			LOGGER.info("result " + result.size());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	@GetMapping("/exportFotoPmmExcel")
	public void exportFotoPmmExcelFile(@RequestParam String idCD, @RequestParam String fechaDesde,
			@RequestParam String fechaHasta, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=FotoPmm" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<FotoPmmDto> listOfStudents = this.cargaPmmService.getAllFindFotoPmm(idCD, fechaDesde, fechaHasta);
		ExcelGeneratorFotoPmm generator = new ExcelGeneratorFotoPmm(listOfStudents);

		generator.generateExcelFile(response);
	}
}
