package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.oracle.dto.projection.ConsolidadoPmmWmsDto;

public interface TblPmmWmsService {
	
	List<ConsolidadoPmmWmsDto> getAllConsolidadoPmmWms( Integer idCargaWms,	Integer idCargaPmm, String idCD);
}
