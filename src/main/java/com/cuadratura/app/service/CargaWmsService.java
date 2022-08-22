package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.oracle.dto.projection.FotoWmsDto;

public interface CargaWmsService extends GenericService<CargaWms, Integer> {
	public Long saveCargaWms(CargaWms cargaWms);
	
	public List<FotoWmsDto> getAllFindFotoWms(String idCentroDistribucion, String fechaDesde, String fechaHasta);
}
