package com.cuadratura.app.mysql.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.MPrdmstee;
import com.cuadratura.app.mysql.repository.MPrdmsteeCustom;

@Repository
public class MPrdmsteeRepositoryImpl implements MPrdmsteeCustom {
	private static final Logger LOGGER = LogManager.getLogger(MPrdmsteeRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<MPrdmstee> getMaterialLote(String nombreMaterial) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * from cuadratura.m_prdmstee x WHERE x.prd_name_full LIKE :nombreMaterial ");

		LOGGER.info(sb.toString());
		Query q = em.createNativeQuery(sb.toString(), MPrdmstee.class);
		q.setParameter("nombreMaterial", "%" + nombreMaterial + "%");
		return q.getResultList();
	}

}
