package com.cuadratura.app.mysql.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.MTipoInventario;
import com.cuadratura.app.mysql.repository.MTipoInventarioCustom;

@Repository
public class MTipoInventarioRepositoryImpl implements MTipoInventarioCustom {
	private static final Logger LOGGER = LogManager.getLogger(MTipoInventarioRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<MTipoInventario> getTipoInventario() {
		LOGGER.info("getTipoInventario ");
		List<MTipoInventario> listOfEmailDomains = em.createNativeQuery(
				"SELECT I.id_tipo_inventario, I.nombre FROM cuadratura.m_tipo_inventario I WHERE estado=1",
				MTipoInventario.class).getResultList();
		return listOfEmailDomains;
	}

}
