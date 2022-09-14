package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.mysql.repository.AjustePmmWmsRepository;
import com.cuadratura.app.service.AjustePmmWmsService;

@Service
public class AjustePmmWmsServiceImpl  extends GenericServiceImpl<AjustePmmWms, Integer> implements AjustePmmWmsService{

	
	private static final Logger LOGGER = LogManager.getLogger(AjustePmmWmsServiceImpl.class);
	
	@Autowired
	private AjustePmmWmsRepository ajustePmmWmsRepository;

	@Override
	public CrudRepository<AjustePmmWms, Integer> getDao() {
		return ajustePmmWmsRepository;
	}
	
	public void saveAjustePmmWms(AjustePmmWms ajustePmmWms) {
		LOGGER.info(ajustePmmWms.getIdEstadocuadratura());
		ajustePmmWmsRepository.save(ajustePmmWms);
	}

}
