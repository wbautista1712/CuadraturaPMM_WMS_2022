package com.cuadratura.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.oracle.entity.pk.FapinvbaleePK;
import com.cuadratura.app.oracle.repository.FapinvbaleeRepository;
import com.cuadratura.app.service.FapinvbaleeService;

@Service
public class FapinvbaleeServiceImpl extends GenericServiceImpl<Fapinvbalee, FapinvbaleePK> implements FapinvbaleeService {
	private static final Logger LOGGER = LogManager.getLogger(FapinvbaleeServiceImpl.class);
	
	@Autowired
	private FapinvbaleeRepository fapinvbaleeRepository;

	@Override
	public CrudRepository<Fapinvbalee, FapinvbaleePK> getDao() {
		// TODO Auto-generated method stub
		return fapinvbaleeRepository;
	}

	@Override
	public List<Fapinvbalee> findAllPMMFapinvbalee() {
		// TODO Auto-generated method stub
		LOGGER.info("findAllFapinvbalee");
		return (List<Fapinvbalee>) fapinvbaleeRepository.findAllPMMFapinvbalee();
	}
}
