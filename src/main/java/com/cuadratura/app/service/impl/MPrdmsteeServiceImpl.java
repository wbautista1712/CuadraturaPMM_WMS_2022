package com.cuadratura.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.MPrdmstee;
import com.cuadratura.app.mysql.repository.MPrdmsteeRepository;
import com.cuadratura.app.service.MPrdmsteeService;

@Service
public class MPrdmsteeServiceImpl  extends GenericServiceImpl<MPrdmstee, Integer>  implements MPrdmsteeService{
	private static final Logger LOGGER = LogManager.getLogger(MPrdmsteeServiceImpl.class);

	@Autowired
	private MPrdmsteeRepository mPrdmsteeRepository;

	@Override
	public CrudRepository<MPrdmstee, Integer> getDao() {
		// TODO Auto-generated method stub
		LOGGER.info("mPrdmsteeRepository");
		return mPrdmsteeRepository;
	}
	
	public   List<MPrdmstee> getMaterialLote(String nombreMaterial){
		return this.mPrdmsteeRepository.getMaterialLote(nombreMaterial);
	}
}
