package com.cuadratura.app.mysql.repository.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.repository.CargaWmsRepositoryCustom;

@Repository
@Transactional
public class CargaWmsRepositoryImpl implements CargaWmsRepositoryCustom {

	private static final Logger LOGGER = LogManager.getLogger(CargaWmsRepositoryImpl.class);

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Integer saveCargaWms(CargaWms cargaWms) {
		// TODO Auto-generated method stub
		Integer GENERATED_KEYS = null;
		try {
			String sql = "INSERT INTO db_cuadratura.carga_wms "
					+ "(fechaCarga,horaCarga,num_registros,usuario_carga,id_m_TipoImportacion,id_m_estadoCuadratura,estado) "
					+ "VALUES (?,?,?,?,?,?,?)";

			Query query = getEntityManager().createNativeQuery(sql);

			query.setParameter(1, cargaWms.getFechaCarga());
			query.setParameter(2, cargaWms.getHoraCarga());
			query.setParameter(3, cargaWms.getNumRegistros());
			query.setParameter(4, cargaWms.getUsuarioCarga());
			query.setParameter(5, cargaWms.getIdmTipoImportacion());
			query.setParameter(6, cargaWms.getIdmestadoCuadratura());
			query.setParameter(7, cargaWms.isEstado());


			BigInteger id = (BigInteger) query.getSingleResult();
			GENERATED_KEYS = id.intValue();
			LOGGER.info("GENERATED_KEYS " + GENERATED_KEYS);

		} catch (Exception e) {
			LOGGER.error(e);

		}
		return GENERATED_KEYS;
	}
}
