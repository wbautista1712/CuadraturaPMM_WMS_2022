package com.cuadratura.app;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.entity.TblPmm;
import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.projection.WmsCinsDto;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.service.CargaPmmService;
import com.cuadratura.app.service.CargaWmsService;
import com.cuadratura.app.service.FapinvbaleeService;
import com.cuadratura.app.service.TblPmmService;
import com.cuadratura.app.service.TblWmsService;
import com.cuadratura.app.service.WmsCinsService;
import com.cuadratura.app.util.Constantes;

/**
 * Created by wilber bautista on 02/08/2022.
 */
@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	// Agregado por wilber
	private static final String TIME_ZONE = "America/Lima";

<<<<<<< HEAD
    @Scheduled(cron = "0 5 16 ? * 6 ", zone = TIME_ZONE) 
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
 
        List<Fapinvbalee>  listaTblPmmForm =  this.fapinvbaleeService.findAllPMMFapinvbalee();
        TblPmm tblPmm = null;
    	logger.info(".::: obj.listaTblPmmForm() :::. "+listaTblPmmForm.size());
 
    	//codigo de carga carga_pmm
    	CargaPmm cargaPmm = new CargaPmm();
=======
	@Autowired
	private FapinvbaleeService fapinvbaleeService;
>>>>>>> cff6d942a7e0f3ccc7c6329a0a93362bb1473ca3

	@Autowired
	private TblPmmService tblPmmService;

	@Autowired
	private CargaPmmService cargaPmmService;

	@Autowired
	private WmsCinsService wmsCinsService;

	@Autowired
	private TblWmsService tblWmsService;

	@Autowired
	private CargaWmsService cargaWmsService;

	@Scheduled(cron = "23 15 18 ? * 7 ", zone = TIME_ZONE)
	public void scheduleTaskWithFixedRate() throws SQLException {
		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		List<Fapinvbalee> listaTblPmmForm = this.fapinvbaleeService.findAllPMMFapinvbalee();
	//	TblPmm tblPmm = null;
		logger.info(".::: obj.listaTblPmmForm() :::. " + listaTblPmmForm.size());

		

		List<Object[]> lista = cargaPmmService.getLoteFotoPmm();
		
		for (int i = 0; i < lista.size(); i++) {
			
			// codigo de carga carga_pmm
			CargaPmm cargaPmm = new CargaPmm();

			cargaPmm.setFechaFoto(new Date());
			cargaPmm.setHoraFoto(dateTimeFormatter.format(LocalDateTime.now()));
			cargaPmm.setNumRegistros(listaTblPmmForm.size());
			cargaPmm.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);

			cargaPmm.setEstado(true);

			cargaPmm.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
			cargaPmm.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);
			
			logger.info(".::: insert sobj.getDisponiblesWms():::. " + lista.get(0).toString());
			cargaPmm.setOrgLvlChild(Integer.valueOf(lista.get(0).toString())); // distinct aqui automatico lots
			
			Integer id = cargaPmmService.saveCargaPmm(cargaPmm).intValue();
			
			tblPmmService.saveTblPmm(listaTblPmmForm,  i+1, id);
		}
			
/*
			for (Fapinvbalee obj : listaTblPmmForm) {
		
				tblPmm = new TblPmm();

				tblPmm.setCurrCode(obj.getCurrCode());
				tblPmm.setFirstPisDate(obj.getFirstPisDate());
				tblPmm.setFirstSalesDate(obj.getFirstSalesDate());
				tblPmm.setFirstShippedDate(obj.getFirstShippedDate());

<<<<<<< HEAD
    
    // A B C D E F
    /*
      	A: Segundos (0 - 59).
	   	B: Minutos (0 - 59).
		C: Horas (0 - 23).
		D: Día (1 - 31).
		E: Mes (1 - 12).
		F: Día de la semana (0 - 6).
    */
    
    
    @Scheduled(cron = "0 5 16 ? * 6 ", zone = TIME_ZONE) //a tarea anterior se ejecutará a las 23 horas con 9 minutos y 0 segundos, 
    //todos los meses, los días 5 (visernes).
    public void scheduleTaskWithCronExpression() throws Exception {
        
    	logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        
        List<WmsCinsDto> listaWmsCinsDto = wmsCinsService.findAllWMSWmsCins();
        
        CargaWms cargaWms = new CargaWms();
        cargaWms.setFechaCarga(new Date());
        cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
        cargaWms.setEstado(true);
        cargaWms.setNumRegistros(listaWmsCinsDto.size());
        cargaWms.setIdmTipoImportacion(1);
        cargaWms.setIdmestadoCuadratura(1);
        cargaWms.setUsuarioCarga("AUTOMÁTICA");
		cargaWms.setOrgNameShort("CD04");		// traer toda la datos de los CDS
        Integer id = cargaWmsService.saveCargaWms(cargaWms).intValue();
        logger.info("id ==> " +id);
        
        TblWms  tblWms = null;
        for (WmsCinsDto obj :listaWmsCinsDto) {
      
        	tblWms = new TblWms();
        	tblWms.setIdCargaWMS(id);// reecupera id
       	
        	//tblWms.setNroCarga(obj.getNroCarga().intValue());
        	
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
        	logger.info("fin insercion ok  ==> " );
        }
                   
    }
=======
				tblPmm.setIdCargaPMM(id);
				tblPmm.setInvTypeCode(obj.getFapinvbaleePK().getInvTypeCode());

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


				tblPmm.setOrgLvlChild((int) obj.getFapinvbaleePK().getOrgLvlChild());

				tblPmm.setPoIntrnCost(obj.getPoIntrnCost());
				tblPmm.setPoIntrnQty(obj.getPoIntrnQty());

				tblPmm.setPoIntrnRetl(obj.getPoIntrnRetl());
				tblPmm.setPoIntrnWeight(obj.getPoIntrnWeight());
				tblPmm.setPoOrdCost(obj.getPoOrdCost());
				tblPmm.setPoOrdQty(obj.getPoOrdQty());
				tblPmm.setPoOrdRetl(obj.getPoOrdRetl());
				tblPmm.setPoOrdWeight(obj.getPoOrdWeight());

		
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

				
				tblPmm.setTransLote(obj.getFapinvbaleePK().getTransLote());

				tblPmm.setTransVctoLote(obj.getTransVctoLote());
				tblPmm.setWeightUom(obj.getWeightUom());

				tblPmmService.save(tblPmm);
				
			}*/

		//}

	}

	/*
	 * @Scheduled(fixedDelay = 2000) public void scheduleTaskWithFixedDelay() {
	 * logger.info("Fixed Delay Task :: Execution Time - {}",
	 * dateTimeFormatter.format(LocalDateTime.now())); try {
	 * TimeUnit.SECONDS.sleep(5); logger.info(".:::maestro hever:::."); } catch
	 * (InterruptedException ex) { logger.error("Ran into an error {}", ex); throw
	 * new IllegalStateException(ex); } }
	 * 
	 */
	/*
	 * @Scheduled(fixedRate = 2000, initialDelay = 5000) public void
	 * scheduleTaskWithInitialDelay() {
	 * logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}",
	 * dateTimeFormatter.format(LocalDateTime.now())); }
	 * 
	 * @Scheduled(cron = "0 * * * * ?") public void scheduleTaskWithCronExpression()
	 * { logger.info("Cron Task :: Execution Time - {}",
	 * dateTimeFormatter.format(LocalDateTime.now())); }
	 */

	// A B C D E F
	/*
	 * A: Segundos (0 - 59). B: Minutos (0 - 59). C: Horas (0 - 23). D: Día (1 -
	 * 31). E: Mes (1 - 12). F: Día de la semana (0 - 6).
	 */

	@Scheduled(cron = "0 30 11 ? * 5 ", zone = TIME_ZONE) // a tarea anterior se ejecutará a las 23 horas con 9 minutos
															// y 0 segundos,
	// todos los meses, los días 5 (visernes).
	public void scheduleTaskWithCronExpression() throws Exception {

		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		List<WmsCinsDto> listaWmsCinsDto = wmsCinsService.findAllWMSWmsCins();

		CargaWms cargaWms = new CargaWms();
		cargaWms.setFechaCarga(new Date());
		cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
		cargaWms.setEstado(true);
		cargaWms.setNumRegistros(listaWmsCinsDto.size());
		cargaWms.setIdmTipoImportacion(1);
		cargaWms.setIdmestadoCuadratura(1);
		cargaWms.setUsuarioCarga("AUTOMÁTICA");
		cargaWms.setOrgNameShort("CD04"); // traer toda la datos de los CDS
		Integer id = cargaWmsService.saveCargaWms(cargaWms).intValue();
		logger.info("id ==> " + id);

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
			logger.info("fin insercion Wms  ==> ");
		}

	}
>>>>>>> cff6d942a7e0f3ccc7c6329a0a93362bb1473ca3
}