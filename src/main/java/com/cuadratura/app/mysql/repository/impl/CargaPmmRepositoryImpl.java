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

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.repository.CargaPmmRepositoryCustom;

@Repository
@Transactional
public class CargaPmmRepositoryImpl implements CargaPmmRepositoryCustom{
	
	private static final Logger LOGGER = LogManager.getLogger(CargaPmmRepositoryImpl.class);
	
	@Autowired
	@Qualifier("jdbctemplateTwo")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Long saveCargaPmm(CargaPmm cargaPmm) {
	
		String INSERT_MESSAGE_SQL = "INSERT INTO cuadratura.carga_pmm "
				+ "(estado, fechaCarga, horaCarga, id_m_TipoImportacion, id_m_estadoCuadratura, nombreArchivo, numRegistros, org_lvl_child, usuarioCarga) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, new String[] { "idCarga_PMM" });

			java.util.Date utilDate = cargaPmm.getFechaFoto();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			ps.setBoolean(1, cargaPmm.isEstado());
			ps.setDate(2, sqlDate);
			ps.setString(3, cargaPmm.getHoraFoto());
			ps.setInt(4, cargaPmm.getIdmTipoImportacion());
			ps.setInt(5, cargaPmm.getIdmestadoCuadratura());
			ps.setString(6,  cargaPmm.getNombreArchivo());
			ps.setInt(7, cargaPmm.getNumRegistros());
			ps.setInt(8, cargaPmm.getOrgLvlChild());
			ps.setString(9, cargaPmm.getUsuarioCarga());
	
			return ps;
		}, keyHolder);
		LOGGER.info("id recupera");

		return keyHolder.getKey().longValue();
	}

}
