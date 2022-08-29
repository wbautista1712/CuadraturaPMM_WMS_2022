package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.oracle.dto.projection.ConsolidadoPmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.ResultadoPmmWmsDto;

public interface TblPmmWmsService {
	
	List<ConsolidadoPmmWmsDto> getAllConsolidadoPmmWms( Integer idCargaWms,	Integer idCargaPmm, String idCD);
	
	List<ResultadoPmmWmsDto> getAllResultadoPmmWms( Integer idCargaWms,	Integer idCargaPmm);
}
