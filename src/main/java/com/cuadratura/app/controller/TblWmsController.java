package com.cuadratura.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.projection.WmsCinsDto;
import com.cuadratura.app.service.CargaWmsService;
import com.cuadratura.app.service.TblWmsService;
import com.cuadratura.app.service.WmsCinsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/wmsmysql")
public class TblWmsController {

	private static final Logger LOGGER = LogManager.getLogger(TblWmsController.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private TblWmsService tblWmsService;

	@Autowired
	private WmsCinsService wmsCinsService;

	@Autowired
	private CargaWmsService cargaWmsService;

	@PostMapping(value = "/crearCuadraturaWMS")
	public String crearCuadraturaWMS(@RequestBody String  fechaProceso) throws Exception {
		LOGGER.info("inicio");
		List<WmsCinsDto> listaWmsCinsDto = wmsCinsService.findAllWMSWmsCins();
		CargaWms cargaWms = new CargaWms();
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"); //21/08/2022 14:06:33
		DateTime dateTimeProceso = DateTime.parse(fechaProceso, formatter);
		
		cargaWms.setFechaCarga(dateTimeProceso.toDate());
		cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
		cargaWms.setEstado(true);
		cargaWms.setNumRegistros(listaWmsCinsDto.size());
		cargaWms.setIdmTipoImportacion(1);
		cargaWms.setIdmestadoCuadratura(1);
		cargaWms.setUsuarioCarga("WBAUTISTA");
		cargaWms.setOrgNameShort("CD04");
	//	cargaWms.setNombreArchivo(nombreArchivo);
		Integer id = cargaWmsService.saveCargaWms(cargaWms).intValue();
		LOGGER.info("id ==> " + id);

		TblWms tblWms = null;
		for (WmsCinsDto obj : listaWmsCinsDto) {

			tblWms = new TblWms();
			tblWms.setIdCargaWMS(id);// reecupera id

			// tblWms.setNroCarga(obj.getNroCarga().intValue());

			tblWms.setNroCarga(obj.getNroCarga().intValue());
			tblWms.setCreateDate(obj.getCreateDate());
			tblWms.setFacilityCode(obj.getFacilityCode());
			tblWms.setCompanyCode(obj.getCompanyCode());

			tblWms.setItemPartA(obj.getItemPartA());
			tblWms.setItemPartB(obj.getItemPartB());
			tblWms.setItemPartC(obj.getItemPartC());
			tblWms.setItemPartD(obj.getItemPartD());
			tblWms.setItemPartE(obj.getItemPartE());
			tblWms.setItemPartF(obj.getItemPartF());

			tblWms.setHierarchy1Code(obj.getHierarchy1Code());
			tblWms.setHierarchy2Code(obj.getHierarchy2Code());
			tblWms.setHierarchy3Code(obj.getHierarchy3Code());
			tblWms.setHierarchy4Code(obj.getHierarchy4Code());
			tblWms.setHierarchy5Code(obj.getHierarchy5Code());

			tblWms.setBatchNbr(obj.getBatchNbr());
			tblWms.setPrePackCode(obj.getPrePackCode());
			tblWms.setPrePackRatio(obj.getPrePackRatio());
			tblWms.setPrePackUnits(obj.getPrePackUnits());

			tblWms.setOblpnTotal(obj.getOblpnTotal());
			tblWms.setActiveTotal(obj.getActiveTotal());
			tblWms.setActiveAllocated(obj.getActiveAllocated());
			tblWms.setActiveAllocatedLockcode(obj.getActiveAllocatedLockcode());
			tblWms.setActiveAvailable(obj.getActiveAvailable());
			tblWms.setActiveLockcode(obj.getActiveLockcode());

			tblWms.setIblpnTotal(obj.getIblpnTotal());
			tblWms.setIblpnAllocated(obj.getIblpnAllocated());
			tblWms.setIblpnAllocatedLockcode(obj.getIblpnAllocatedLockcode());
			tblWms.setIblpnAvailable(obj.getIblpnAvailable());
			tblWms.setIblpnNotverified(obj.getIblpnNotverified());
			tblWms.setIblpnLockcode(obj.getIblpnLockcode());
			tblWms.setIblpnLost(obj.getIblpnLost());

			tblWms.setTotalAllocated(obj.getTotalAllocated());

			tblWms.setTotalAvailable(obj.getTotalAvailable());
			tblWms.setTotalInventory(obj.getTotalInventory());

			tblWms.setFourWallInventory(obj.getFourWallInventory());
			tblWms.setOpenOrderQty(obj.getOpenOrderQty());

			tblWms.setLockCode1(obj.getLockCode1());
			tblWms.setLockCodeQty1(obj.getLockCodeQty1());

			tblWms.setLockCode2(obj.getLockCode2());
			tblWms.setLockCodeQty2(obj.getLockCodeQty2());

			tblWms.setLockCode3(obj.getLockCode3());
			tblWms.setLockCodeQty3(obj.getLockCodeQty3());

			tblWms.setLockCode4(obj.getLockCode4());
			tblWms.setLockCodeQty4(obj.getLockCodeQty4());

			tblWms.setLockCode5(obj.getLockCode5());
			tblWms.setLockCodeQty5(obj.getLockCodeQty5());

			tblWms.setLockCode6(obj.getLockCode6());
			tblWms.setLockCodeQty6(obj.getLockCodeQty6());

			tblWms.setLockCode7(obj.getLockCode7());
			tblWms.setLockCodeQty7(obj.getLockCodeQty7());

			tblWms.setLockCode8(obj.getLockCode8());
			tblWms.setLockCodeQty8(obj.getLockCodeQty8());

			tblWms.setLockCode9(obj.getLockCode9());
			tblWms.setLockCodeQty9(obj.getLockCodeQty9());

			tblWms.setLockCode10(obj.getLockCode10());
			tblWms.setLockCodeQty10(obj.getLockCodeQty10());

			tblWms.setDownloadDate1(obj.getDownloadDate1());
			tblWms.setErrorCode(obj.getErrorCode());
			tblWms.setObservacionError(obj.getObservacionError());
			tblWms.setFlgTipo(obj.getFlgTipo().intValue());

			tblWmsService.save(tblWms);
			LOGGER.info("fin insercion postman  ==> ");
		}
return "ok";
		//return ResponseEntity.status(HttpStatus.CREATED).body("Proceso Completo WMS");
	}
}
