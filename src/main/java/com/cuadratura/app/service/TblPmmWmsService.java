package com.cuadratura.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cuadratura.app.mysql.entity.TblPmm;
import com.cuadratura.app.mysql.entity.TblPmmWms;
import com.cuadratura.app.oracle.dto.ConsolidadoPmmWmsDto;
import com.cuadratura.app.oracle.dto.ResultadoPmmWmsDto;

public interface TblPmmWmsService extends GenericService<TblPmmWms, Integer>{
	
	List<ConsolidadoPmmWmsDto> getAllConsolidadoPmmWms( Integer idCargaWms,	Integer idCargaPmm, String idCD);
	
	List<ResultadoPmmWmsDto> getAllResultadoPmmWms( String idCD_org_name_short, String fechaDesde, String fechaHasta, Integer start, Integer end);
	
	void saveCrucePmmWms(int idCargaPMM, int idCargaWMS, String idCD, int idUsuario, int idCrucePmmWms) ;
	
	public Integer countResultadoPmmWms(String idCD_org_name_short, String fechaDesde, String fechaHasta) throws Exception;
	
}
