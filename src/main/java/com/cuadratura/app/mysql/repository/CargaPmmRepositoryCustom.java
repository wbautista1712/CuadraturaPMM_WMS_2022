package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.oracle.dto.projection.FotoPmmDto;

public interface CargaPmmRepositoryCustom {
	public Long saveCargaPmm(CargaPmm cargaPmm);

	public List<FotoPmmDto> getAllFindFotoPmm(String idCentroDistribucion, String fechaDesde, String fechaHasta, Integer start, Integer end);
	
	public Integer countFotoPmm(String idCentroDistribucion, String fechaDesde, String fechaHasta) throws Exception;
}
