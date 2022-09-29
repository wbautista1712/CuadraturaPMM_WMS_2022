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
import com.cuadratura.app.mysql.entity.MCronJob;
import com.cuadratura.app.oracle.dto.FapinvbaleeDto;
import com.cuadratura.app.oracle.dto.WmsCinsCDDto;
import com.cuadratura.app.oracle.dto.WmsCinsDto;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.service.CargaPmmService;
import com.cuadratura.app.service.CargaWmsService;
import com.cuadratura.app.service.FapinvbaleeService;
import com.cuadratura.app.service.MCronJobService;
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

	private static final String cronExpressionWms = "0 16 09 ? * 1 ";
	private static final String cronExpressionPmm = "0 12 10 ? * 1 ";
	
//	private static final String cronExpressionWms = "0 02 10 ? * 2 ";
//	private static final String cronExpressionPmm = "0 12 10 ? * 2 ";


	private static final String TIME_ZONE = "America/Lima";

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

	@Autowired
	private MCronJobService mCronJobService;

	//@Bean
	public String getCronValue() {
		String job = "";

		MCronJob mCronJob = mCronJobService.getCronJob();

		job = mCronJob.getSegundo() + " " + mCronJob.getMinuto() + " " + mCronJob.getHora() + " " + mCronJob.getDiaMes()
				+ " " + mCronJob.getMes() + " " + mCronJob.getDiaSemana();
		logger.info("Cron Expression:: "+job);

		return job;
	}

	@Scheduled(cron = cronExpressionPmm, zone = TIME_ZONE)
	//@Scheduled(cron = "#{@getCronValue}", zone = TIME_ZONE)
	public void scheduleTaskWithFixedRate() throws SQLException {
		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		List<FapinvbaleeDto> lista = fapinvbaleeService.getLoteFotoPmm();
		logger.info(".:::oracle  tamaño lote :::. " + lista.size());

		for (int i = 0; i < lista.size(); i++) {
			logger.info(".::: obj.getLoteFotoPmm() :::. " + lista.size());

			logger.info(".::: insert idCD:::. " + lista.get(i).getIdCD());

			List<Fapinvbalee> listaTblPmmForm = this.fapinvbaleeService.findAllPMMFapinvbalee(lista.get(i).getIdCD());
			// TblPmm tblPmm = null;
			logger.info(".::: oracle tamaño de importacion :::. " + listaTblPmmForm.size());

			// codigo de carga carga_pmm
			CargaPmm cargaPmm = new CargaPmm();

			cargaPmm.setFechaFoto(new Date());
			cargaPmm.setHoraFoto(dateTimeFormatter.format(LocalDateTime.now()));
			cargaPmm.setNumRegistros(listaTblPmmForm.size());
			cargaPmm.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);

			cargaPmm.setEstado(true);

			cargaPmm.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
			cargaPmm.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);

			cargaPmm.setOrgLvlChild(lista.get(i).getIdCD()); // distinct aqui automatico lots

			Integer id = this.cargaPmmService.saveCargaPmm(cargaPmm).intValue();

			this.tblPmmService.saveTblPmm(listaTblPmmForm,  id);
		}
	}

	/*
	 * for (Fapinvbalee obj : listaTblPmmForm) {
	 * 
	 * tblPmm = new TblPmm();
	 * 
	 * tblPmm.setCurrCode(obj.getCurrCode());
	 * tblPmm.setFirstPisDate(obj.getFirstPisDate());
	 * tblPmm.setFirstSalesDate(obj.getFirstSalesDate());
	 * tblPmm.setFirstShippedDate(obj.getFirstShippedDate());
	 * 
	 * <<<<<<< HEAD
	 * 
	 * // A B C D E F /* A: Segundos (0 - 59). B: Minutos (0 - 59). C: Horas (0 -
	 * 23). D: Día (1 - 31). E: Mes (1 - 12). F: Día de la semana (0 - 6).
	 */

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

	// @Scheduled(cron = "0 30 11 ? * 5 ", zone = TIME_ZONE) // a tarea anterior se
	// ejecutará a las 23 horas con 9 minutos
	@Scheduled(cron = cronExpressionWms, zone = TIME_ZONE) 
	//@Scheduled(fixedDelay = 2000) 
	public void scheduleTaskWithCronExpression() throws Exception {

		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		List<WmsCinsCDDto> listaCDxFH = wmsCinsService.getCDXFechaHoraFotoWms();
		logger.info(".:::oracle  tamaño lote listaCDxFH :::. " + listaCDxFH.size());

		for (int i = 0; i < listaCDxFH.size(); i++) {
			logger.info(".::: obj.getLoteFotoPmm() :::. " + listaCDxFH.size());

			logger.info(".::: insert idCD:::. " + listaCDxFH.get(i).getIdCD());

			logger.info(".:::oracle  listaFH.get(j).getFechaHora() :::. " + listaCDxFH.get(i).getFechaHora());

			List<WmsCinsDto> listaTblWmsForm = this.wmsCinsService.findAllWMSWmsCins(listaCDxFH.get(i).getFechaHora());
			// TblPmm tblPmm = null;
			logger.info(".::: oracle tamaño WMS de importacion :::. " + listaTblWmsForm.size());

			// codigo de carga carga_pmm

			CargaWms cargaWms = new CargaWms();
			cargaWms.setFechaCarga(new Date());
			cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
			cargaWms.setEstado(true);
			cargaWms.setNumRegistros(listaTblWmsForm.size());
			cargaWms.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
			cargaWms.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);
			cargaWms.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);
			cargaWms.setOrgNameShort(listaCDxFH.get(i).getIdCD()); // traer toda la datos de los CDS
			Integer id = this.cargaWmsService.saveCargaWms(cargaWms).intValue();
			logger.info("id ==> " + id);
			this.tblWmsService.saveTblWms(listaTblWmsForm,  id);
			logger.info("i ==> " + i);
		}

	}

}