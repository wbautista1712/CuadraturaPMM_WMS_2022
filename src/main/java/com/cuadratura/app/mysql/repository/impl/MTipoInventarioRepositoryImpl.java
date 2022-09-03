package com.cuadratura.app.mysql.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.repository.MTipoInventarioCustom;

@Repository
public class MTipoInventarioRepositoryImpl implements MTipoInventarioCustom {
	private static final Logger LOGGER = LogManager.getLogger(MTipoInventarioRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;
	
	
	/*
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> listAnalisisAjustePmmWmsNCD12(int idCrucePmmWms, int idTipoInventario) {
		LOGGER.info("idCrucePmmWms " + idCrucePmmWms);	

		StoredProcedureQuery query = em.createStoredProcedureQuery("cuadratura.sp_listar_ajuste_param")
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
				.setParameter(1, idCrucePmmWms)
				.setParameter(2, idTipoInventario);
		  List<Object[]> personComments = query.getResultList();
		  return personComments;
	}*/

}
