package com.cuadratura.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.repository.TblPmmWmsRepository;
import com.cuadratura.app.oracle.dto.projection.ConsolidadoPmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.ResultadoPmmWmsDto;
import com.cuadratura.app.service.TblPmmWmsService;

@Service
public class TblPmmWmsServiceImpl implements TblPmmWmsService {

	private static final Logger LOGGER = LogManager.getLogger(TblPmmWmsServiceImpl.class);

	@Autowired
	private TblPmmWmsRepository tblPmmWmsRepository;

	@Override
	public List<ConsolidadoPmmWmsDto> getAllConsolidadoPmmWms(Integer idCargaWms, Integer idCargaPmm, String idCD) {

		LOGGER.info("paso");
		return tblPmmWmsRepository.getAllConsolidadoPmmWms(idCargaWms, idCargaPmm, idCD);
	}

	@Override
	public List<ResultadoPmmWmsDto> getAllResultadoPmmWms(String idCD_org_name_short) {
		return tblPmmWmsRepository.getAllResultadoPmmWms(idCD_org_name_short);
	}

	@Override
	public void saveCrucePmmWms(int idCargaPMM, int idCargaWMS, String idCD, int idUsuario, int idCrucePmmWms) {
		this.tblPmmWmsRepository.saveCrucePmmWms(idCargaPMM, idCargaWMS, idCD, idUsuario, idCrucePmmWms);
	}

}
