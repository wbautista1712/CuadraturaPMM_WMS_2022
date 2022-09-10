package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.oracle.dto.projection.ConsolidadoPmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.ResultadoPmmWmsDto;

public interface TblPmmWmsService {
	
	List<ConsolidadoPmmWmsDto> getAllConsolidadoPmmWms( Integer idCargaWms,	Integer idCargaPmm, String idCD);
	
	List<ResultadoPmmWmsDto> getAllResultadoPmmWms( String idCD_org_name_short, Integer start, Integer end);
	
	void saveCrucePmmWms(int idCargaPMM, int idCargaWMS, String idCD, int idUsuario, int idCrucePmmWms) ;
	
	public Integer countResultadoPmmWms(String idCD_org_name_short) throws Exception;
}
