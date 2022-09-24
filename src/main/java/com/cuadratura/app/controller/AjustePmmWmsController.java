package com.cuadratura.app.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		LOGGER.info("procesarAjusteSDI " );
		
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			CuadraturaTransfer cuadraturaTransfer = null;
			String fapprdlotee = null;
			long prdpcdee ;
			List<SpBolsaSdiDto> result = this.ajustePmmWmsService.getAllBolsaSdi();
			LOGGER.info("result " + result.size());
			Long idSesion = cuadraturaTransferService.getSequence();
			LOGGER.info("idSesion " + idSesion);
			int i =1;
			for (SpBolsaSdiDto objeto : result) {
				cuadraturaTransfer = new CuadraturaTransfer();

				cuadraturaTransfer.setTransSession(BigInteger.valueOf((idSesion))); //pk
				cuadraturaTransfer.setTransUser(objeto.getTransUser());// arreglar
				cuadraturaTransfer.setTransBatchDate(objeto.getTransBatchDate());
				cuadraturaTransfer.setTransSource(objeto.getTransSource());
				cuadraturaTransfer.setTransAudited(objeto.getTransAudited());
				cuadraturaTransfer.setTransSequence(new BigInteger( i +""));// pk //objeto.getTransSequence()
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
				Date date = formatter.parse(fapprdlotee);
				cuadraturaTransfer.setTransVctoLote(date);

				this.cuadraturaTransferService.saveCuadraturaTransferService(cuadraturaTransfer);
				this.ajustePmmWmsService.updateAjustePmmWms(objeto.getIdAjustePMMWMS());
				i=i+1;
			}

			this.cuadraturaTransferService.spCuadraturaTransfer(idSesion.intValue());
			LOGGER.info("Proceso Correcto ok ");
			return ResponseEntity.status(HttpStatus.OK).body("Proceso Correcto");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}
