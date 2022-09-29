package com.cuadratura.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.WmsCinsCDDto;
import com.cuadratura.app.oracle.dto.WmsCinsDto;
import com.cuadratura.app.service.CargaWmsService;
import com.cuadratura.app.service.TblWmsService;
import com.cuadratura.app.service.WmsCinsService;
import com.cuadratura.app.util.Constantes;

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
	public ResponseEntity<String> crearCuadraturaWMS(@RequestParam String fechaProceso, @RequestParam String idCD) throws Exception {
		LOGGER.info("inicio");
		//List<WmsCinsCDDto> listaFH = wmsCinsService.getFechaHoraFotoWms(idCD);
		List<WmsCinsCDDto> listaFH = wmsCinsService.getNroCargaFotoWms(idCD);
		for (int j = 0; j < listaFH.size(); j++) {
			//List<WmsCinsDto> listaWmsCinsDto = wmsCinsService.findAllWMSWmsCins( listaFH.get(j).getFechaHora());
			List<WmsCinsDto> listaWmsCinsDto = wmsCinsService.findAllxNroCargaWMSWmsCins( listaFH.get(j).getNroCarga());
			CargaWms cargaWms = new CargaWms();
			org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy"); // 21/08/2022
																										// 14:06:33
			DateTime dateTimeProceso = DateTime.parse(fechaProceso, formatter);

			cargaWms.setFechaCarga(dateTimeProceso.toDate());
			cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
			cargaWms.setEstado(true);
			cargaWms.setNumRegistros(listaWmsCinsDto.size());
			cargaWms.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
			cargaWms.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);
			cargaWms.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);
			cargaWms.setOrgNameShort(idCD);
			// cargaWms.setNombreArchivo(nombreArchivo);
			Integer id = cargaWmsService.saveCargaWms(cargaWms).intValue();
			LOGGER.info("id ==> " + id);

			TblWms tblWms = null;
			for (WmsCinsDto obj : listaWmsCinsDto) {

				tblWms = new TblWms();
				tblWms.setIdCargaWMS(id);// reecupera id


				tblWms.setNroCarga(obj.getNroCarga().intValue());
				tblWms.setCreateDate(obj.getCreateDate().trim());
				tblWms.setFacilityCode(obj.getFacilityCode().trim());
				tblWms.setCompanyCode(obj.getCompanyCode().trim());

				tblWms.setItemPartA(obj.getItemPartA().trim());
				tblWms.setItemPartB(obj.getItemPartB().trim());
				tblWms.setItemPartC(obj.getItemPartC().trim());
				tblWms.setItemPartD(obj.getItemPartD().trim());
				tblWms.setItemPartE(obj.getItemPartE().trim());
				tblWms.setItemPartF(obj.getItemPartF().trim());

				tblWms.setHierarchy1Code(obj.getHierarchy1Code().trim());
				tblWms.setHierarchy2Code(obj.getHierarchy2Code().trim());
				tblWms.setHierarchy3Code(obj.getHierarchy3Code().trim());
				tblWms.setHierarchy4Code(obj.getHierarchy4Code().trim());
				tblWms.setHierarchy5Code(obj.getHierarchy5Code().trim());

				tblWms.setBatchNbr(obj.getBatchNbr().trim());
				tblWms.setPrePackCode(obj.getPrePackCode().trim());
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

				tblWms.setLockCode1(obj.getLockCode1().trim());
				tblWms.setLockCodeQty1(obj.getLockCodeQty1());

				tblWms.setLockCode2(obj.getLockCode2().trim());
				tblWms.setLockCodeQty2(obj.getLockCodeQty2());

				tblWms.setLockCode3(obj.getLockCode3().trim());
				tblWms.setLockCodeQty3(obj.getLockCodeQty3());

				tblWms.setLockCode4(obj.getLockCode4().trim());
				tblWms.setLockCodeQty4(obj.getLockCodeQty4());

				tblWms.setLockCode5(obj.getLockCode5().trim());
				tblWms.setLockCodeQty5(obj.getLockCodeQty5());

				tblWms.setLockCode6(obj.getLockCode6().trim());
				tblWms.setLockCodeQty6(obj.getLockCodeQty6());

				tblWms.setLockCode7(obj.getLockCode7().trim());
				tblWms.setLockCodeQty7(obj.getLockCodeQty7());

				tblWms.setLockCode8(obj.getLockCode8().trim());
				tblWms.setLockCodeQty8(obj.getLockCodeQty8());

				tblWms.setLockCode9(obj.getLockCode9().trim());
				tblWms.setLockCodeQty9(obj.getLockCodeQty9());

				tblWms.setLockCode10(obj.getLockCode10().trim());
				tblWms.setLockCodeQty10(obj.getLockCodeQty10());

				tblWms.setDownloadDate1(obj.getDownloadDate1());
				tblWms.setErrorCode(obj.getErrorCode());
				tblWms.setObservacionError(obj.getObservacionError().trim());
				tblWms.setFlgTipo(obj.getFlgTipo().intValue());

				tblWmsService.save(tblWms);
				LOGGER.info("fin insercion postman  ==> ");
			}
		}	
		return ResponseEntity.status(HttpStatus.OK).body( "Proceso Correcto");
	}
}
