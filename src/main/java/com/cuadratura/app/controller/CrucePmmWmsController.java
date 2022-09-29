package com.cuadratura.app.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.oracle.dto.AjustePmmWmsDto;
import com.cuadratura.app.oracle.dto.CrucePmmWmsDto;
import com.cuadratura.app.oracle.dto.SpBolsaSdiDto;
import com.cuadratura.app.oracle.entity.CuadraturaTransfer;
import com.cuadratura.app.service.AjustePmmWmsService;
import com.cuadratura.app.service.CrucePmmWmsService;
import com.cuadratura.app.service.CuadraturaTransferService;
import com.cuadratura.app.service.FapprdloteeService;
import com.cuadratura.app.service.PrdpcdeeService;
import com.cuadratura.app.service.TblPmmWmsService;
import com.cuadratura.app.util.Constantes;
import com.cuadratura.app.util.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.asm.Advice.Return;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "/api/wmsmysql")
public class CrucePmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsController.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private CrucePmmWmsService crucePmmWmsService;

	@Autowired
	private TblPmmWmsService tblPmmWmsService;

	@Autowired
	private AjustePmmWmsService ajustePmmWmsService;

	@Autowired
	private CuadraturaTransferService cuadraturaTransferService;

	@Autowired
	private FapprdloteeService fapprdloteeService;

	@Autowired
	private PrdpcdeeService prdpcdeeService;

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

			LOGGER.info("id==... " + id);

			this.tblPmmWmsService.saveCrucePmmWms(idCargaPMM, idCargaWMS, idCD, idUsuario, id);

			this.crucePmmWmsService.spActualizarEstadoWMSPMMTotal(id, Constantes.ESTADO_CUADRATURA_PROCESO);
			return new ResponseEntity<>("Procesamiento Correcto. ", HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getAnalisisAjustePmmWms")
	public ResponseEntity<List<AjustePmmWmsDto>> getAnalisisAjustePmmWms(@RequestParam Integer idCrucePmmWms) {

		try {
			LOGGER.info("listAnalisisAjustePmmWms  idCrucePmmWms " + idCrucePmmWms);

			List<AjustePmmWmsDto> result = crucePmmWmsService.listAnalisisAjustePmmWms(idCrucePmmWms);

			LOGGER.info("result " + result.size());

			this.crucePmmWmsService.spActualizarEstadoWMSPMMTotal(idCrucePmmWms,
					Constantes.ESTADO_CUADRATURA_VALIDACION);

			return ResponseEntity.status(HttpStatus.OK).body(result);
			// return
			// ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page,
			// rows, records, result));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}

	}
	
	@GetMapping(value = "/contador")
	public String contador(@RequestParam Integer contador) throws InterruptedException {
		
		LOGGER.info("Iniando Thread: ");
		
		Thread.sleep(contador*1000);

		return "Hola mundo - contador igual a =" + contador + " segundos";
	}

	@GetMapping(value = "/getAjusteBolsaDiscrepancia")
	public ResponseEntity<List<CrucePmmWmsDto>> getAjusteBolsaDiscrepancia(@RequestParam Integer idCrucePmmWms) {
		try {

			LOGGER.info("getAjusteBolsaDiscrepancia");

			// List<CrucePmmWmsDto> result =
			// this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms, start,
			// rows);
			List<CrucePmmWmsDto> result = this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms);

			return ResponseEntity.status(HttpStatus.OK).body(result);
			// return
			// ResponseEntity.status(HttpStatus.OK).body(listResponse.getPaginador(page,
			// rows, records, result));

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}

	}

	@PostMapping(value = "/nextAjusteBolsaDiscrepancia")
	public ResponseEntity<Message> nextAjusteBolsaDiscrepancia(@RequestBody String jsonData) {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Message msg = new Message();
		Integer codError = 0;// error
		String rpta = "";
		
		
		try {

			LOGGER.info("pruebita si cae 1 " + jsonData);

			LOGGER.info("nextAjusteBolsaDiscrepancia");
			AjustePmmWms ajustePmmWms = new AjustePmmWms();
			// List<CrucePmmWmsDto> result =
			// this.crucePmmWmsService.listarAjusteBolsaDiscrepancia(idCrucePmmWms, start,
			// rows);

			LOGGER.info("pruebita si cae 2" + ajustePmmWms);

			List<CrucePmmWmsDto> registroJsonList = om.readValue(jsonData, new TypeReference<List<CrucePmmWmsDto>>() {
			});

			LOGGER.info("nextAjusteBolsaDiscrepancia " + registroJsonList.size());

			registroJsonList.stream().forEach(x -> {

				if(x.getSugerenciaAjuste()!=0)
				{
					// LOGGER.info(x.getIdTipoInventario());
					// ajustePmmWms.setIdTipoInventario(x.getIdTipoInventario());
					LOGGER.info("dddddddddddd");
					ajustePmmWms.setFechaAjuste(new Date());
					// LOGGER.info(x.getIdTipoInventario());
					ajustePmmWms.setHoraAjuste(dateTimeFormatter.format(LocalDateTime.now()));
					ajustePmmWms.setPmm(x.getPmm());
					ajustePmmWms.setWms(x.getWms());
					LOGGER.info(x.getWms());
					ajustePmmWms.setSugerenciaAjuste(x.getSugerenciaAjuste());
					LOGGER.info(x.getSugerenciaAjuste());
					ajustePmmWms.setStockBolsaDiscrepancia(x.getSctockBolsaDiscrepancia());
					LOGGER.info(x.getSctockBolsaDiscrepancia());
					LOGGER.info(x.getSctockBolsaDiscrepancia());

					ajustePmmWms.setIdTblPmmWms(x.getIdTblPmmWms());
					ajustePmmWms.setIdTipoInventario(x.getIdTipoInventario());

					ajustePmmWmsService.saveAjustePmmWms(ajustePmmWms);
				}
			});
			
			
			CuadraturaTransfer cuadraturaTransfer = null;
			String fapprdlotee = null;
			long innerPackId;
			
			List<SpBolsaSdiDto> result = this.ajustePmmWmsService.getAllBolsaSdi();// mysql
			Long idSesion = cuadraturaTransferService.getSequence();
			
			int i = 1;
			for (SpBolsaSdiDto objeto : result) 
			{
				cuadraturaTransfer = new CuadraturaTransfer();
				cuadraturaTransfer.setTransSession(BigInteger.valueOf((idSesion))); // pk
				cuadraturaTransfer.setTransUser(objeto.getTransUser());// arreglar
				cuadraturaTransfer.setTransBatchDate(objeto.getTransBatchDate());
				cuadraturaTransfer.setTransSource(objeto.getTransSource());
				cuadraturaTransfer.setTransAudited(objeto.getTransAudited());
				cuadraturaTransfer.setTransSequence(new BigInteger(i + ""));// pk //objeto.getTransSequence()
				cuadraturaTransfer.setTransTrnCode(objeto.getTransTrnCode());
				cuadraturaTransfer.setTransTypeCode(objeto.getTransTypeCode());
				cuadraturaTransfer.setTransDate(objeto.getTransDate());
				cuadraturaTransfer.setInvMrptCode(objeto.getInvMrptCode());
				cuadraturaTransfer.setInvDrptCode(objeto.getInvDrptCode());
				cuadraturaTransfer.setTransCurrCode(objeto.getTransCurrCode());

				cuadraturaTransfer.setTransOrgLvlNumber(new BigInteger(objeto.getTransOrgLvlNumber()));
				
				cuadraturaTransfer.setTransPrdLvlNumber(objeto.getTransPrdLvlNumber());/// este debe ir a consulta

				cuadraturaTransfer.setProcSource(objeto.getProcSource());
				
				cuadraturaTransfer.setTransQty(new BigInteger(objeto.getTransQty() + ""));// valor absoluto
				
				innerPackId = prdpcdeeService.findPrdpcdee(objeto.getTransPrdLvlNumber());
				LOGGER.info(innerPackId);
				
				cuadraturaTransfer.setInnerPackId(BigInteger.valueOf(innerPackId));// usar consulta oracle
				
				
				cuadraturaTransfer.setTransInners(new BigInteger(objeto.getTransInners()));
				cuadraturaTransfer.setTransLote(objeto.getTransLote());

				
				fapprdlotee = fapprdloteeService.findFapprdlotee(objeto.getPrdLvlChild(), objeto.getTransLote());
				
				if (fapprdlotee != null) {
				
					Date date = formatter.parse(fapprdlotee);
					cuadraturaTransfer.setTransVctoLote(date);
				} else {
				
					cuadraturaTransfer.setTransVctoLote(null);
				} 

				 this.cuadraturaTransferService.saveCuadraturaTransferService(cuadraturaTransfer);
				
				 this.ajustePmmWmsService.updateAjustePmmWms(objeto.getIdAjustePMMWMS());
				
				i = i + 1;
			}
			
			this.cuadraturaTransferService.spCuadraturaTransfer(idSesion.intValue());
			
			
		} catch (Exception ex) {
			// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
			rpta=ex.getMessage();
		}
		
		msg.setMsjError(rpta);
		return new ResponseEntity<Message>(msg, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping(value = "/nextAjuste")
	public Map<String, Object> nextAjuste(@RequestBody String jsonData) {
		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Map<String, Object> rpta = new HashMap<String, Object>();
		
		try {

			AjustePmmWms ajustePmmWms = new AjustePmmWms();

			List<CrucePmmWmsDto> registroJsonList = om.readValue(jsonData, new TypeReference<List<CrucePmmWmsDto>>() {
			});

			registroJsonList.stream().forEach(x -> {

				if(x.getSugerenciaAjuste()!=0)
				{
					ajustePmmWms.setFechaAjuste(new Date());
					ajustePmmWms.setHoraAjuste(dateTimeFormatter.format(LocalDateTime.now()));
					ajustePmmWms.setPmm(x.getPmm());
					ajustePmmWms.setWms(x.getWms());
					ajustePmmWms.setSugerenciaAjuste(x.getSugerenciaAjuste());
					ajustePmmWms.setStockBolsaDiscrepancia(x.getSctockBolsaDiscrepancia());
					ajustePmmWms.setIdTblPmmWms(x.getIdTblPmmWms());
					ajustePmmWms.setIdTipoInventario(x.getIdTipoInventario());

					ajustePmmWmsService.saveAjustePmmWms(ajustePmmWms);
				}
			});

			CuadraturaTransfer cuadraturaTransfer = null;
			String fapprdlotee = null;
			long prdpcdee;
			
			List<SpBolsaSdiDto> result = this.ajustePmmWmsService.getAllBolsaSdi();
			Long idSesion = cuadraturaTransferService.getSequence();
			
			int i = 1;
			for (SpBolsaSdiDto objeto : result) 
			{
				cuadraturaTransfer = new CuadraturaTransfer();
				cuadraturaTransfer.setTransSession(BigInteger.valueOf((idSesion))); // pk
				cuadraturaTransfer.setTransUser(objeto.getTransUser());// arreglar
				cuadraturaTransfer.setTransBatchDate(objeto.getTransBatchDate());
				cuadraturaTransfer.setTransSource(objeto.getTransSource());
				cuadraturaTransfer.setTransAudited(objeto.getTransAudited());
				cuadraturaTransfer.setTransSequence(new BigInteger(i + ""));// pk //objeto.getTransSequence()
				cuadraturaTransfer.setTransTrnCode(objeto.getTransTrnCode());
				cuadraturaTransfer.setTransTypeCode(objeto.getTransTypeCode());
				cuadraturaTransfer.setTransDate(objeto.getTransDate());
				cuadraturaTransfer.setInvMrptCode(objeto.getInvMrptCode());
				cuadraturaTransfer.setInvDrptCode(objeto.getInvDrptCode());
				cuadraturaTransfer.setTransCurrCode(objeto.getTransCurrCode());

				cuadraturaTransfer.setTransOrgLvlNumber(new BigInteger(objeto.getTransOrgLvlNumber()));
				cuadraturaTransfer.setTransPrdLvlNumber(objeto.getTransPrdLvlNumber());/// este debe ir a consulta

				cuadraturaTransfer.setProcSource(objeto.getProcSource());
				cuadraturaTransfer.setTransQty(new BigInteger(objeto.getTransQty() + ""));// valor absoluto
				
				prdpcdee = prdpcdeeService.findPrdpcdee(objeto.getTransPrdLvlNumber());
			
				
				cuadraturaTransfer.setInnerPackId(BigInteger.valueOf(prdpcdee));// usar consulta oracle
				
				
				cuadraturaTransfer.setTransInners(new BigInteger(objeto.getTransInners()));
				cuadraturaTransfer.setTransLote(objeto.getTransLote());

				fapprdlotee = fapprdloteeService.findFapprdlotee(objeto.getPrdLvlChild(), objeto.getTransLote());
				
				if (fapprdlotee != null) {
				
					Date date = formatter.parse(fapprdlotee);
					cuadraturaTransfer.setTransVctoLote(date);
				} else {
				
					cuadraturaTransfer.setTransVctoLote(null);
				}

				this.cuadraturaTransferService.saveCuadraturaTransferService(cuadraturaTransfer);
				
				 this.ajustePmmWmsService.updateAjustePmmWms(objeto.getIdAjustePMMWMS());
				 
				i = i + 1;
			}
			
			LOGGER.info("Después de la operación ");
			
			this.cuadraturaTransferService.spCuadraturaTransfer(idSesion.intValue());
			
			LOGGER.info("Después de la operación ");
			
		} catch (Exception ex) {
			rpta.put("Respuesta", ex.getMessage());
		}
		
		LOGGER.info("Insertado correctamente ");
		return rpta;
	}

}