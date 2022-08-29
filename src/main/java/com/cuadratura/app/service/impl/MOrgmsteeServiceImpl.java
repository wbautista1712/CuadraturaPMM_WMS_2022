package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.MOrgmstee;
import com.cuadratura.app.mysql.repository.MOrgmsteeRepository;
import com.cuadratura.app.service.MOrgmsteeService;

@Service
public class MOrgmsteeServiceImpl extends GenericServiceImpl<MOrgmstee, Integer> implements MOrgmsteeService {
	private static final Logger LOGGER = LogManager.getLogger(MOrgmsteeServiceImpl.class);

	@Autowired
	private MOrgmsteeRepository mOrgmsteeRepository;

	@Override
	public CrudRepository<MOrgmstee, Integer> getDao() {
		// TODO Auto-generated method stub
		LOGGER.info("mOrgmsteeRepository");
		return mOrgmsteeRepository;
	}
}
