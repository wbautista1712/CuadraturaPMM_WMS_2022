package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.oracle.entity.pk.FapinvbaleePK;
import com.cuadratura.app.service.CuadraturaTransferService;

@Service
public class CuadraturaTransferServiceImpl  extends GenericServiceImpl<CrucePmmWms, Integer>  implements CuadraturaTransferService {
	private static final Logger LOGGER = LogManager.getLogger(CuadraturaTransferServiceImpl.class);
	@Override
	public CrudRepository<Fapinvbalee, FapinvbaleePK> getDao() {
		// TODO Auto-generated method stub
		return fapinvbaleeRepository;
	}
}
