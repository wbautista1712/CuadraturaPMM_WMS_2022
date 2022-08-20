package com.cuadratura.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuadratura.app.oracle.dto.projection.WmsCinsDto;
import com.cuadratura.app.oracle.repository.FapinvbaleeRepository;
import com.cuadratura.app.oracle.repository.WmsCinsRepository;
import com.cuadratura.app.service.WmsCinsService;

@Service
public class WmsCinsServiceImpl implements WmsCinsService{
	private static final Logger LOGGER = LogManager.getLogger(WmsCinsServiceImpl.class);

	@Autowired
	private FapinvbaleeRepository fapinvbaleeRepository;
	
	@Override
	public List<WmsCinsDto> findAllWMSWmsCins() {
		// TODO Auto-generated method stub
		//return null;
		return fapinvbaleeRepository.findAllWMSWmsCins();
	}

}
