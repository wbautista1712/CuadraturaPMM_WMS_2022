package com.cuadratura.app.mysql.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.repository.TblPmmWmsRepositoryCustom;
import com.cuadratura.app.oracle.repository.impl.WmsCinsRepositoryImpl;

@Repository
public class TblPmmWmsRepositoryImpl implements TblPmmWmsRepositoryCustom{
	private static final Logger LOGGER = LogManager.getLogger(WmsCinsRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void saveCrucePmmWms(int idCargaPMM, int idCargaWMS, String idCD, int idUsuario) {
		LOGGER.info("idCargaPMM "+idCargaPMM);
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("cuadratura.sp_carga_pmm_wms");
		query.setParameter("p_idCarga_PMM", idCargaPMM);
		query.setParameter("p_idCarga_WMS", idCargaWMS);
		query.setParameter("p_idCD", idCD);
		query.setParameter("p_idUsuario", idUsuario);
		query.execute();
	}
}
