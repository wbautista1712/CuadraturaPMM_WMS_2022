package com.cuadratura.app.oracle.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.oracle.repository.PrdpcdeeRepositoryCustom;

@Repository
@Transactional
public class PrdpcdeeRepositoryImpl implements PrdpcdeeRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(PrdpcdeeRepositoryImpl.class);

	@Autowired
	@Qualifier("jdbctemplateOne")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long findPrdpcdee(String numeroProd) {
		LOGGER.info("findPrdpcdee numeroProd=== " + numeroProd);

		String sql = "SELECT i.Inner_Pack_Id   " + "  FROM pmm.prdpcdee i, pmm.prdmstee p "
				+ " WHERE     i.prd_lvl_child = p.prd_lvl_child " + "       AND i.loose_pack_flag = 'F'  "
				+ "       AND i.sll_units_per_inner = 1  " + "       AND i.inv_units_per_inner = 1 "
				+ "       AND p.prd_lvl_number =  " + numeroProd+ "       AND ROWNUM = 1 ";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Long>() {
			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getLong("INNER_PACK_ID") : null;
			}
		});

	}
}
