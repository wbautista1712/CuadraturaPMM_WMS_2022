package com.cuadratura.app.controller;

import java.math.BigInteger;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.oracle.dto.SpBolsaSdiDto;
import com.cuadratura.app.oracle.entity.CuadraturaTransfer;
import com.cuadratura.app.oracle.entity.Fapprdlotee;
import com.cuadratura.app.oracle.entity.Prdpcdee;
import com.cuadratura.app.service.AjustePmmWmsService;
import com.cuadratura.app.service.CuadraturaTransferService;
import com.cuadratura.app.service.FapprdloteeService;
import com.cuadratura.app.service.PrdpcdeeService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class AjustePmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(AjustePmmWmsController.class);

	@Autowired
	private AjustePmmWmsService ajustePmmWmsService;

	@Autowired
	private CuadraturaTransferService cuadraturaTransferService;
	
	@Autowired
	private FapprdloteeService fapprdloteeService;
	
	@Autowired
	private PrdpcdeeService prdpcdeeService;

	@GetMapping(value = "/procesarAjusteSDI")
	public ResponseEntity<String> procesarAjusteSDI() {
		try {
			CuadraturaTransfer cuadraturaTransfer = null;
			Fapprdlotee  fapprdlotee = null;
			Prdpcdee prdpcdee= null;
			List<SpBolsaSdiDto> result = this.ajustePmmWmsService.getAllBolsaSdi();
			LOGGER.info("result " + result.size());
			Long idSesion=  cuadraturaTransferService.getSequence();
			LOGGER.info("idSesion " + idSesion);
			for (SpBolsaSdiDto objeto : result) {
				cuadraturaTransfer = new CuadraturaTransfer();
			
				cuadraturaTransfer.setTransSession( BigInteger.valueOf((idSesion) ));//  SELECT INTEGRACION.WMS_SEQ_FAPINVTRHEE.nextval from dual;
				cuadraturaTransfer.setTransUser(objeto.getTransUser());//arreglar
				cuadraturaTransfer.setTransBatchDate(objeto.getTransBatchDate());
				cuadraturaTransfer.setTransSource(objeto.getTransSource());
				cuadraturaTransfer.setTransAudited(objeto.getTransAudited());
				cuadraturaTransfer.setTransSequence(new BigInteger(objeto.getTransSequence()) );
				cuadraturaTransfer.setTransTrnCode(objeto.getTransTrnCode());
				cuadraturaTransfer.setTransTypeCode(objeto.getTransTypeCode());

				cuadraturaTransfer.setTransDate(objeto.getTransDate());
				cuadraturaTransfer.setInvMrptCode(objeto.getInvMrptCode());
				cuadraturaTransfer.setInvDrptCode(objeto.getInvDrptCode());
				cuadraturaTransfer.setTransCurrCode(objeto.getTransCurrCode());

				cuadraturaTransfer.setTransOrgLvlNumber(new BigInteger(objeto.getTransOrgLvlNumber()));
				cuadraturaTransfer.setTransPrdLvlNumber(objeto.getTransPrdLvlNumber());/// este debe ir a consulta 

				cuadraturaTransfer.setProcSource(objeto.getProcSource());
				cuadraturaTransfer.setTransQty(new BigInteger(objeto.getTransQty()));
				
				prdpcdee= prdpcdeeService.findPrdpcdee(objeto.getTransPrdLvlNumber());
				cuadraturaTransfer.setInnerPackId(BigInteger.valueOf(prdpcdee.getInnerPackId())  );//usar consulta oracle //
		
				cuadraturaTransfer.setTransInners(new BigInteger(objeto.getTransInners()));

				cuadraturaTransfer.setTransLote(objeto.getTransLote());
				
				fapprdlotee =fapprdloteeService.findFapprdlotee(objeto.getPrdLvlChild(), objeto.getTransLote());//prd_lvl_child //prd_nlote
				cuadraturaTransfer.setTransVctoLote(fapprdlotee.getLotFechaVcto());//Select * from pmm.FAPPRDLOTEE x where x.prd_lvl_child =36278 and x.prd_nlote='10317639'; // corregir

				this.cuadraturaTransferService.saveCuadraturaTransferService(cuadraturaTransfer);
				
				this.ajustePmmWmsService.updateAjustePmmWms(objeto.getIdAjustePMMWMS());
			}
			
			this.cuadraturaTransferService.spCuadraturaTransfer(idSesion.intValue());
			LOGGER.info("Proceso Correcto ok " );
			return ResponseEntity.status(HttpStatus.OK).body("Proceso Correcto");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}
