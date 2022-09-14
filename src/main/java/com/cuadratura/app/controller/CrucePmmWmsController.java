package com.cuadratura.app.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.oracle.dto.projection.AjustePmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;
import com.cuadratura.app.service.AjustePmmWmsService;
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
	
	@Autowired
	private AjustePmmWmsService ajustePmmWmsService;

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
	
	@GetMapping(value = "/getAnalisisAjustePmmWms")
	public ResponseEntity<List<AjustePmmWmsDto>> getAnalisisAjustePmmWms(@RequestParam Integer idCrucePmmWms) {
	
		try 
		{
			LOGGER.info("listAnalisisAjustePmmWms  idCrucePmmWms "+idCrucePmmWms);
			
			List<AjustePmmWmsDto> result = crucePmmWmsService.listAnalisisAjustePmmWms(idCrucePmmWms);			
			
			LOGGER.info("result "+result.size());			
			this.crucePmmWmsService.spActualizarEstadoWMSPMMTotal(idCrucePmmWms, Constantes.ESTADO_CUADRATURA_VALIDACION);
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
		//	return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
	
	@GetMapping(value = "/getAjusteBolsaDiscrepancia")
	public ResponseEntity<List<CrucePmmWmsDto>> getAjusteBolsaDiscrepancia(@RequestParam Integer idCrucePmmWms) {
		try {

			LOGGER.info("getAjusteBolsaDiscrepancia");

			//List<CrucePmmWmsDto> result = this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms, start, rows);
			List<CrucePmmWmsDto> result = this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms);
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
			//return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
	
	@PostMapping(value = "/nextAjusteBolsaDiscrepancia")
	public ResponseEntity<String> nextAjusteBolsaDiscrepancia(@RequestBody String jsonData) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {

			LOGGER.info("nextAjusteBolsaDiscrepancia");
			LOGGER.info(jsonData);
			AjustePmmWms ajustePmmWms =null;
			//List<CrucePmmWmsDto> result = this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms, start, rows);
		     List<CrucePmmWmsDto> registroJsonList  = om.readValue(jsonData, new TypeReference<List<CrucePmmWmsDto>>(){});
		     LOGGER.info("nextAjusteBolsaDiscrepancia "+registroJsonList.size());
			  for(int i = 0; i < registroJsonList.size(); i++) {
				  ajustePmmWms = new  AjustePmmWms();
				  
				  LOGGER.info("nextAjusteBolsaDiscrepancia "+registroJsonList.get(i).getIdTipoInventario());
				  ajustePmmWms.setIdTipoInventario(registroJsonList.get(i).getIdTipoInventario());
				  
				  ajustePmmWms.setFechaAjuste(new Date());
				  ajustePmmWms.setHoraAjuste(dateTimeFormatter.format(LocalDateTime.now()));
				  ajustePmmWms.setPmm(registroJsonList.get(i).getPmm());
				  ajustePmmWms.setWms(registroJsonList.get(i).getWms()); 
				  ajustePmmWms.setSugerenciaAjuste(registroJsonList.get(i).getSugerenciaAjuste());
				  ajustePmmWms.setStockBolsaDiscrepancia(registroJsonList.get(i).getSctockBolsaDiscrepancia());
				  
				ajustePmmWmsService.saveAjustePmmWms(ajustePmmWms);
			}
		
			return new ResponseEntity<String>("Procesamiento Correcto.", HttpStatus.OK);
			//return ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page, rows, records, result));
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}
