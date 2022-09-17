package com.cuadratura.app.oracle.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.oracle.entity.Fapprdlotee;
import com.cuadratura.app.oracle.repository.FapprdloteeRepositoryCustom;

@Repository
@Transactional
public class FapprdloteeRepositoryImpl implements FapprdloteeRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(FapprdloteeRepositoryImpl.class);



	@PersistenceContext(unitName = "jpa_oracle")
	private EntityManager em;

	@Override	
	public Fapprdlotee findFapprdlotee(Integer cd, String numeroLote) {
		LOGGER.info("cd " +cd);
		LOGGER.info("numeroLote " +numeroLote);
		// TODO Auto-generated method stub
		String sql = "SELECT * "
				+ "  FROM pmm.fapprdlotee x "
				+ " WHERE x.prd_lvl_child = :cd AND x.prd_nlote = :numeroLote ";		
	
		Query query = this.em.createNativeQuery(sql, Fapprdlotee.class);
	query.setParameter("cd", cd);
		query.setParameter("numeroLote", numeroLote);

	
	
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return (Fapprdlotee) query.getSingleResult();
	}

}
