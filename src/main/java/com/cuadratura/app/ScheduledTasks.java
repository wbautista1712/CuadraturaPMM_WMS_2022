package com.cuadratura.app;

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


/**
 * Created by wilber bautista on 02/08/2022.
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Autowired
    private FapinvbaleeService fapinvbaleeService;

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
    
    /*
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
 
        List<Fapinvbalee>  listaTblPmmForm =  this.fapinvbaleeService.findAllPMMFapinvbalee();
        TblPmm tblPmm = null;
    	logger.info(".::: obj.listaTblPmmForm() :::. "+listaTblPmmForm.size());
 
    	//codigo de carga carga_pmm
    	CargaPmm cargaPmm = new CargaPmm();

    	cargaPmm.setFechaCarga(new Date());
    	cargaPmm.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));    
    	cargaPmm.setNumRegistros(listaTblPmmForm.size());    
    	cargaPmm.setUsuarioCarga("Wilber"); 
    	
    	cargaPmm.setEstado(true);     
    	
    	cargaPmm.setIdmTipoImportacion(1);       
    	cargaPmm.setIdmestadoCuadratura(1);      
    	cargaPmm.setOrgLvlChild(1);     // distinct 
    	
    	cargaPmmService.save(cargaPmm);
    	
        for (Fapinvbalee obj :listaTblPmmForm) {
        	logger.info(".::: insert sobj.getDisponiblesWms():::. "+obj.getCurrCode());
        	tblPmm = new TblPmm();
        	tblPmm.setCurrCode(obj.getCurrCode());
        
        	tblPmm.setToOrdCost(obj.getToOrdCost());
        	tblPmm.setIdCargaPMM(1);
        	
        	tblPmm.setOnHandQty(obj.getOnHandQty()); 
        	
        	
        	
        	tblPmm.setOrgLvlChild( (int)obj.getFapinvbaleePK().getOrgLvlChild());
        	
        	
        	tblPmm.setPrdLvlChild(1);
        	
        	tblPmm.setInvTypeCode("01");
        	tblPmm.setTransLote(obj.getFapinvbaleePK().getTransLote());
        
        	tblPmmService.save(tblPmm);
        	
		}
       
    }

    */
    
    /*
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
            logger.info(".:::maestro hever:::.");
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }
    
    */
/*
    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }*/

    
    
    @Scheduled(cron = "0 23 18 ? * 5 ") //a tarea anterior se ejecutará a las 23 horas con 9 minutos y 0 segundos, 
    //todos los meses, los días 5 (viernes).
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        List<WmsCinsDto>  listaWmsCinsDto =       wmsCinsService.findAllWMSWmsCins();
        CargaWms cargaWms = new CargaWms();
        
        cargaWms.setFechaCarga(new Date());
        cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
        cargaWms.setEstado(true);
        cargaWms.setNumRegistros(listaWmsCinsDto.size());
        cargaWms.setIdmTipoImportacion(1);
        cargaWms.setIdmestadoCuadratura(1);
        cargaWms.setUsuarioCarga("wilber");
       Integer id = cargaWmsService.saveCargaWms(cargaWms);
        
        TblWms  tblWms = null;
        for (WmsCinsDto obj :listaWmsCinsDto) {
        	tblWms = new TblWms();
        	tblWms.setIdCargaWMS(id);// reecupera id
        	//tblWms.setNroCarga(obj.getNroCarga().intValue());
        	tblWms.setNroCarga(55555);
        	tblWms.setCreateDate(obj.getCreateDate());
        	tblWms.setFacilityCode(obj.getFacilityCode());
        	tblWms.setCompanyCode(obj.getCompanyCode());
        	
        	tblWms.setItemPartA(obj.getItemPartA());
        	tblWms.setDownloadDate1(obj.getDownloadDate1());
        	tblWms.setTotalInventory(obj.getTotalInventory());
        	tblWms.setActiveLockcode(obj.getActiveLockcode());
        	
        	tblWmsService.save(tblWms);
        }
    }
}
