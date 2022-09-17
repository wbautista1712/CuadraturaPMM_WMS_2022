package com.cuadratura.app.oracle.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.oracle.entity.Prdpcdee;
import com.cuadratura.app.oracle.repository.PrdpcdeeRepositoryCustom;


@Repository
@Transactional
public class PrdpcdeeRepositoryImpl implements PrdpcdeeRepositoryCustom{
	private static final Logger LOGGER = LogManager.getLogger(PrdpcdeeRepositoryImpl.class);



	@PersistenceContext(unitName = "jpa_oracle")
	private EntityManager em;
	
	@Override	
	public Prdpcdee findPrdpcdee(String numeroProd) {
		LOGGER.info("numeroProd=== " +numeroProd);
		String sql = "SELECT i.* "
				+ "  FROM pmm.prdpcdee i, pmm.prdmstee p "
				+ " WHERE     i.prd_lvl_child = p.prd_lvl_child "
				+ "       AND i.loose_pack_flag = 'F'  "
				+ "       AND i.sll_units_per_inner = 1  "
				+ "       AND i.inv_units_per_inner = 1 "
				+ "       AND p.prd_lvl_number = :numeroProd "
				+ "       AND ROWNUM = 1";		
	
		Query query = this.em.createNativeQuery(sql, Prdpcdee.class);
		query.setParameter("numeroProd", numeroProd);
	
	
	
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return (Prdpcdee) query.getSingleResult();
	}
}
