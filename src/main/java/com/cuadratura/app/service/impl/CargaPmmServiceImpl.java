package com.cuadratura.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.repository.CargaPmmRepository;
import com.cuadratura.app.oracle.dto.projection.FotoPmmDto;
import com.cuadratura.app.service.CargaPmmService;


@Service
public class CargaPmmServiceImpl extends GenericServiceImpl<CargaPmm, Integer> implements CargaPmmService{
	
	private static final Logger LOGGER = LogManager.getLogger(CargaPmmServiceImpl.class);
	
	@Autowired
	private CargaPmmRepository cargaPmmRepository;

	@Override
	public CrudRepository<CargaPmm, Integer> getDao() {	
		return cargaPmmRepository;
	}
	
	public Long saveCargaPmm(CargaPmm cargaPmm) {
		LOGGER.info("insert saveCargaPmm");
		return cargaPmmRepository.saveCargaPmm(cargaPmm);
	}
	
	public List<FotoPmmDto> getAllFindFotoPmmExcel(String idCentroDistribucion, String fechaDesde, String fechaHasta){
		LOGGER.info("get getAllFindFotoPmm");
		return cargaPmmRepository.getAllFindFotoPmmExcel(idCentroDistribucion, fechaDesde, fechaHasta);
	}
	
	public List<FotoPmmDto> getAllFindFotoPmm(String idCentroDistribucion, String fechaDesde, String fechaHasta, Integer start, Integer end){
		return cargaPmmRepository.getAllFindFotoPmm(idCentroDistribucion, fechaDesde, fechaHasta, start, end);
	}
	
	public Integer countFotoPmm(String idCentroDistribucion, String fechaDesde, String fechaHasta) throws Exception{
		return cargaPmmRepository.countFotoPmm(idCentroDistribucion, fechaDesde, fechaHasta);
	}
}
