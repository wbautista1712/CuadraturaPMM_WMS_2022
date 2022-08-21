package com.cuadratura.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.entity.TblPmm;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.service.CargaPmmService;
import com.cuadratura.app.service.FapinvbaleeService;
import com.cuadratura.app.service.TblPmmService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/wmsmysql")
public class TblPmmController {
	private static final Logger LOGGER = LogManager.getLogger(TblWmsController.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private FapinvbaleeService fapinvbaleeService;

	@Autowired
	private CargaPmmService cargaPmmService;

	@Autowired
	private TblPmmService tblPmmService;

	@PostMapping(value = "/crearCuadraturaPMM")
	public ResponseEntity<?> crearCuadraturaPMM(@RequestBody @Valid String fechaProceso) throws Exception {
		List<Fapinvbalee> listaTblPmmForm = this.fapinvbaleeService.findAllPMMFapinvbalee();
		TblPmm tblPmm = null;
		LOGGER.info(".::: obj.listaTblPmmForm() :::. " + listaTblPmmForm.size());

		// codigo de carga carga_pmm
		CargaPmm cargaPmm = new CargaPmm();

		cargaPmm.setFechaCarga(new Date());
		cargaPmm.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
		cargaPmm.setNumRegistros(listaTblPmmForm.size());
		cargaPmm.setUsuarioCarga("Wilber");

		cargaPmm.setEstado(true);

		cargaPmm.setIdmTipoImportacion(1);
		cargaPmm.setIdmestadoCuadratura(1);
		cargaPmm.setOrgLvlChild(1); // distinct

		Integer id = cargaPmmService.saveCargaPmm(cargaPmm).intValue();

		for (Fapinvbalee obj : listaTblPmmForm) {
			LOGGER.info(".::: insert sobj.getDisponiblesWms():::. " + obj.getCurrCode());
			tblPmm = new TblPmm();
			tblPmm.setCurrCode(obj.getCurrCode());

			tblPmm.setToOrdCost(obj.getToOrdCost());
			tblPmm.setIdCargaPMM(id);

			tblPmm.setOnHandQty(obj.getOnHandQty());
			tblPmm.setOrgLvlChild((int) obj.getFapinvbaleePK().getOrgLvlChild());

			tblPmm.setPrdLvlChild(1);

			tblPmm.setInvTypeCode("01");
			tblPmm.setTransLote(obj.getFapinvbaleePK().getTransLote());

			tblPmmService.save(tblPmm);

		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Proceso Completo PMM");
	}
}
