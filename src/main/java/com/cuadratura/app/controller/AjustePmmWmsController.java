package com.cuadratura.app.controller;

import java.math.BigInteger;
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

import com.cuadratura.app.oracle.dto.projection.SpBolsaSdiDto;
import com.cuadratura.app.oracle.entity.CuadraturaTransfer;
import com.cuadratura.app.service.AjustePmmWmsService;
import com.cuadratura.app.service.CuadraturaTransferService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class AjustePmmWmsController {

	private static final Logger LOGGER = LogManager.getLogger(AjustePmmWmsController.class);

	@Autowired
	private AjustePmmWmsService ajustePmmWmsService;

	@Autowired
	private CuadraturaTransferService cuadraturaTransferService;

	@GetMapping(value = "/procesarAjusteSDI")
	public ResponseEntity<String> procesarAjusteSDI() {
		try {
			CuadraturaTransfer cuadraturaTransfer = null;
			List<SpBolsaSdiDto> result = this.ajustePmmWmsService.getAllBolsaSdi();
			LOGGER.info("result " + result.size());
			for (SpBolsaSdiDto objeto : result) {
				cuadraturaTransfer = new CuadraturaTransfer();

				cuadraturaTransfer.setTransSession( new BigInteger("1344350") );//  SELECT INTEGRACION.WMS_SEQ_FAPINVTRHEE.nextval from dual;
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
				cuadraturaTransfer.setInnerPackId(new BigInteger("55519")  );//usar consulta oracle //
				/*
				 * 
				 Select i.inner_pack_id, i.* from pmm.prdpcdee i, pmm.prdmstee p
where i.prd_lvl_child = p.prd_lvl_child
and i.loose_pack_flag = 'F'
and i.sll_units_per_inner = 1
and i.inv_units_per_inner = 1
and p.prd_lvl_number = '135925'
and rownum = 1;

				 * 
				 * */
				cuadraturaTransfer.setTransInners(new BigInteger(objeto.getTransInners()));

				cuadraturaTransfer.setTransLote(objeto.getTransLote());
				cuadraturaTransfer.setTransVctoLote(new Date());//Select * from pmm.FAPPRDLOTEE x where x.prd_lvl_child =36278 and x.prd_nlote='10317639'; // corregir

				this.cuadraturaTransferService.saveCuadraturaTransferService(cuadraturaTransfer);
			}

			// return ResponseEntity.status(HttpStatus.OK).body(result);
			return ResponseEntity.status(HttpStatus.OK).body("Proceso Correcto");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}
