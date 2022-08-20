package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.TblPmm;
import com.cuadratura.app.mysql.repository.TblPmmRepository;
import com.cuadratura.app.service.TblPmmService;

@Service
public class TblPmmServiceImpl extends GenericServiceImpl<TblPmm, Integer> implements TblPmmService {
	private static final Logger LOGGER = LogManager.getLogger(TblPmmServiceImpl.class);
	
	@Autowired
	private TblPmmRepository tblPmmRepository;

	@Override
	public CrudRepository<TblPmm, Integer> getDao() {
		// TODO Auto-generated method stub
		return tblPmmRepository;
	}
	
}
