package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.repository.CargaPmmRepository;
import com.cuadratura.app.service.CargaPmmService;


@Service
public class CargaPmmServiceImpl extends GenericServiceImpl<CargaPmm, Integer> implements CargaPmmService{
	private static final Logger LOGGER = LogManager.getLogger(CargaPmmServiceImpl.class);
	
	@Autowired
	private CargaPmmRepository cargaPmmRepository;

	@Override
	public CrudRepository<CargaPmm, Integer> getDao() {
		// TODO Auto-generated method stub
		return cargaPmmRepository;
	}
	
	
}
