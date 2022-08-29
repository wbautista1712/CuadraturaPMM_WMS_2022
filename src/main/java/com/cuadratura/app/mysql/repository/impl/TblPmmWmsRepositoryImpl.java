package com.cuadratura.app.mysql.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.repository.TblPmmWmsRepositoryCustom;

@Repository
public class TblPmmWmsRepositoryImpl implements TblPmmWmsRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(TblPmmWmsRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	
	public void saveCrucePmmWms(int idCargaPMM, int idCargaWMS, String idCD, int idUsuario) {
		LOGGER.info("idCargaPMM " + idCargaPMM);	

		StoredProcedureQuery query = em.createStoredProcedureQuery("cuadratura.sp_carga_pmm_wms")
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN).setParameter(1, idCargaPMM)
				.setParameter(2, idCargaWMS).setParameter(3, idCD).setParameter(4, idUsuario);
		query.execute();
	}
}
