package com.cuadratura.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.entity.TblPmm;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.service.CargaPmmService;
import com.cuadratura.app.service.FapinvbaleeService;
import com.cuadratura.app.service.TblPmmService;
import com.cuadratura.app.util.Constantes;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/wmsmysql")
public class TblPmmController {
	private static final Logger LOGGER = LogManager.getLogger(TblPmmController.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private FapinvbaleeService fapinvbaleeService;

	@Autowired
	private CargaPmmService cargaPmmService;
	@Autowired
	private TblPmmService tblPmmService;

	@PostMapping(value = "/crearCuadraturaPMM")
	public String crearCuadraturaPMM(@RequestParam @Valid String fechaProceso, @RequestParam @Valid Integer idCD)
			throws Exception {
		List<Fapinvbalee> listaTblPmmForm = this.fapinvbaleeService.findAllPMMFapinvbalee();
		TblPmm tblPmm = null;
		LOGGER.info(".::: obj.listaTblPmmForm() :::. " + listaTblPmmForm.size());
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dateTimeProceso = DateTime.parse(fechaProceso, formatter);
		// codigo de carga carga_pmm
		CargaPmm cargaPmm = new CargaPmm();

		cargaPmm.setFechaFoto(dateTimeProceso.toDate());
		cargaPmm.setHoraFoto(dateTimeFormatter.format(LocalDateTime.now()));
		cargaPmm.setNumRegistros(listaTblPmmForm.size());
		cargaPmm.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);

		cargaPmm.setEstado(true);

		cargaPmm.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
		cargaPmm.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);
		cargaPmm.setOrgLvlChild(idCD); // distinct

		Integer id = cargaPmmService.saveCargaPmm(cargaPmm).intValue();

		for (Fapinvbalee obj : listaTblPmmForm) {
			LOGGER.info(".::: insert sobj.getDisponiblesWms():::. " + obj.getCurrCode());
			tblPmm = new TblPmm();
			tblPmm.setCurrCode(obj.getCurrCode());
			tblPmm.setFirstPisDate(obj.getFirstPisDate());
			tblPmm.setFirstSalesDate(obj.getFirstSalesDate());
			tblPmm.setFirstShippedDate(obj.getFirstShippedDate());

			tblPmm.setIdCargaPMM(id);
			tblPmm.setInvTypeCode("01");

			tblPmm.setLastChgDate(obj.getLastChgDate());
			tblPmm.setLastPisDate(obj.getLastPisDate());
			tblPmm.setLtdCost(obj.getLtdCost());

			tblPmm.setLtdQty(obj.getLtdQty());
			tblPmm.setLtdRetl(obj.getLtdRetl());
			tblPmm.setLtdWeight(obj.getLtdWeight());

			tblPmm.setOnHandCost(obj.getOnHandCost());
			tblPmm.setOnHandCostHm(obj.getOnHandCostHm());
			tblPmm.setOnHandEaches(obj.getOnHandEaches());
			tblPmm.setOnHandQty(obj.getOnHandQty());

			tblPmm.setOnHandRetl(obj.getOnHandRetl());
			tblPmm.setOnHandRetlHm(obj.getOnHandRetlHm());
			tblPmm.setOnHandWeight(obj.getOnHandWeight());

			// tblPmm.setOrgLvlChild(obj.getOrgLvlChild());
			tblPmm.setOrgLvlChild((int) obj.getFapinvbaleePK().getOrgLvlChild());

			tblPmm.setPoIntrnCost(obj.getPoIntrnCost());
			tblPmm.setPoIntrnQty(obj.getPoIntrnQty());

			tblPmm.setPoIntrnRetl(obj.getPoIntrnRetl());
			tblPmm.setPoIntrnWeight(obj.getPoIntrnWeight());
			tblPmm.setPoOrdCost(obj.getPoOrdCost());
			tblPmm.setPoOrdQty(obj.getPoOrdQty());
			tblPmm.setPoOrdRetl(obj.getPoOrdRetl());
			tblPmm.setPoOrdWeight(obj.getPoOrdWeight());

			// tblPmm.setPrdLvlChild(obj.getPrdLvlChild());
			tblPmm.setPrdLvlChild((int) (long) (obj.getFapinvbaleePK().getPrdLvlChild()));

			tblPmm.setPrdSllUom(obj.getPrdSllUom());
			tblPmm.setToIntrnCost(obj.getToIntrnCost());
			tblPmm.setToIntrnCostHm(obj.getToIntrnCostHm());
			tblPmm.setToIntrnQty(obj.getToIntrnQty());
			tblPmm.setToIntrnRetl(obj.getToIntrnRetl());
			tblPmm.setToIntrnRetlHm(obj.getToIntrnRetlHm());
			tblPmm.setToIntrnWeight(obj.getToIntrnWeight());
			tblPmm.setToOrdCost(obj.getToOrdCost());
			tblPmm.setToOrdQty(obj.getToOrdQty());

			tblPmm.setToOrdRetl(obj.getToOrdRetl());
			tblPmm.setToOrdWeight(obj.getToOrdWeight());

			// tblPmm.setTransLote(obj.getTransLote());
			tblPmm.setTransLote(obj.getFapinvbaleePK().getTransLote());

			tblPmm.setTransVctoLote(obj.getTransVctoLote());
			tblPmm.setWeightUom(obj.getWeightUom());

			tblPmmService.save(tblPmm);

		}
		return "ok";
		// return ResponseEntity.status(HttpStatus.CREATED).body("Proceso Completo
		// PMM");
	}

}