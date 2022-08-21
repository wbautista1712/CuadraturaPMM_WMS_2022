package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.mysql.repository.TblWmsRepository;
import com.cuadratura.app.service.TblWmsService;

@Service
public class TblWmsServiceImpl extends GenericServiceImpl<TblWms, Integer> implements TblWmsService {
	private static final Logger LOGGER = LogManager.getLogger(TblWmsServiceImpl.class);
	
	@Autowired
	private TblWmsRepository usuarioRepository;

	@Override
	public CrudRepository<TblWms, Integer> getDao() {
		// TODO Auto-generated method stub
		return usuarioRepository;
	}
}
