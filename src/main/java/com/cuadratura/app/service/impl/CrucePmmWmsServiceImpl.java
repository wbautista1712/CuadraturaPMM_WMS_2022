package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.mysql.repository.CrucePmmWmsRepository;
import com.cuadratura.app.service.CrucePmmWmsService;

@Service
public class CrucePmmWmsServiceImpl  extends GenericServiceImpl<CrucePmmWms, Integer> implements CrucePmmWmsService{
	
	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsServiceImpl.class);
	
	@Autowired
	private CrucePmmWmsRepository crucePmmWmsRepository;

	@Override
	public CrudRepository<CrucePmmWms, Integer> getDao() {	
		return crucePmmWmsRepository;
	}
	
	public Long saveCrucePmmWms(CrucePmmWms cargaPmm) {
		LOGGER.info("insert saveCrucePmmWms");
		return crucePmmWmsRepository.saveCrucePmmWms(cargaPmm);
	}
}
