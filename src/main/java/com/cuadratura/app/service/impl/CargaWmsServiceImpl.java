package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.repository.CargaWmsRepository;
import com.cuadratura.app.service.CargaWmsService;

@Service
public class CargaWmsServiceImpl extends GenericServiceImpl<CargaWms, Integer>  implements CargaWmsService{
private static final Logger LOGGER = LogManager.getLogger(CargaWmsServiceImpl.class);
	
	@Autowired
	private CargaWmsRepository cargaWmsRepository;

	@Override
	public CrudRepository<CargaWms, Integer> getDao() {
		// TODO Auto-generated method stub
		return cargaWmsRepository;
	}
	
	public Integer saveCargaWms(CargaWms cargaWms) {
		LOGGER.info("insert");
		return cargaWmsRepository.saveCargaWms(cargaWms);
	}
	
}
