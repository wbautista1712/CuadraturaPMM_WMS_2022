package com.cuadratura.app.mysql.repository.impl;

import java.sql.PreparedStatement;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.mysql.repository.CrucePmmWmsCustom;

@Repository
@Transactional
public class CrucePmmWmsRepositoryImpl  implements CrucePmmWmsCustom{

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsRepositoryImpl.class);
	
	@Autowired
	@Qualifier("jdbctemplateTwo")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Long saveCrucePmmWms(CrucePmmWms crucePmmWms) {
		// TODO Auto-generated method stub
		String INSERT_MESSAGE_SQL = "INSERT INTO cuadratura.cruce_pmm_wms "
				+ "(fechaMatch, horaMatch, idCarga_PMM, idCarga_WMS, idEstadoCuadratura) "
				+ "VALUES (?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, new String[] { "idCruce_pmm_wms" });

			java.util.Date utilDate = crucePmmWms.getFechaMatch();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			ps.setDate(1, sqlDate);
			ps.setString(2, crucePmmWms.getHoraMatch());
			ps.setInt(3, crucePmmWms.getIdCargaPMM());
			ps.setInt(4, crucePmmWms.getIdCargaWMS());
			ps.setInt(5, crucePmmWms.getIdEstadoCuadratura());
	
	
			return ps;
		}, keyHolder);
		LOGGER.info("id recupera");

		return keyHolder.getKey().longValue();
	}

}
