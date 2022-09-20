package com.cuadratura.app.mysql.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.AjusteComentario;
import com.cuadratura.app.mysql.repository.AjusteComentarioRepositoryCustom;

@Repository
@Transactional
public class AjusteComentarioRepositoryImpl implements AjusteComentarioRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(AjusteComentarioRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	public AjusteComentario findByIdAjusteComentario(Integer id) {
		LOGGER.info(id);
		Query query = em.createNativeQuery("select * from cuadratura.ajuste_Comentario x where x.id_tbl_pmm_wms = ? ",
				AjusteComentario.class);
		query.setParameter(1, id);
		return (AjusteComentario) query.getSingleResult();
	}
}
