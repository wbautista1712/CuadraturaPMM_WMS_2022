package com.cuadratura.app.service.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.oracle.entity.CuadraturaTransfer;
import com.cuadratura.app.oracle.repository.CuadraturaTransferRepository;
import com.cuadratura.app.service.CuadraturaTransferService;

@Service
public class CuadraturaTransferServiceImpl extends GenericServiceImpl<CuadraturaTransfer, BigDecimal>
		implements CuadraturaTransferService {
	
	private static final Logger LOGGER = LogManager.getLogger(CuadraturaTransferServiceImpl.class);
	
	@Autowired
	private CuadraturaTransferRepository cuadraturaTransferRepository;

	@Override
	public CrudRepository<CuadraturaTransfer, BigDecimal> getDao() {
		// TODO Auto-generated method stub
		return cuadraturaTransferRepository;
	}
}
