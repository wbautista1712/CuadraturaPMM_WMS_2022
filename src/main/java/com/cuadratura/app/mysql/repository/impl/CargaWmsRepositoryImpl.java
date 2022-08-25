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

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.repository.CargaWmsRepositoryCustom;

@Repository
@Transactional
public class CargaWmsRepositoryImpl implements CargaWmsRepositoryCustom {

	private static final Logger LOGGER = LogManager.getLogger(CargaWmsRepositoryImpl.class);

//	@PersistenceContext(unitName = "jpa_mysql")
//	private EntityManager entityManagerS;
	/*
	 * public EntityManager getEntityManagerS() { return entityManagerS; }
	 * 
	 * @PersistenceContext public void setEntityManagerS(EntityManager
	 * entityManagerS) { this.entityManagerS = entityManagerS; }
	 */

	@Autowired
	@Qualifier("jdbctemplateTwo")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long saveCargaWms(CargaWms cargaWms) {		

		String INSERT_MESSAGE_SQL = "INSERT INTO cuadratura.carga_wms "
				+ "(fechaCarga,horaCarga,num_registros,usuario_carga,id_m_TipoImportacion,id_m_estadoCuadratura,estado, org_name_short) "
				+ "VALUES (?,?,?,?,?,?,?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, new String[] { "idCarga_WMS" });

			java.util.Date utilDate = cargaWms.getFechaCarga();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			ps.setDate(1, sqlDate);
			ps.setString(2, cargaWms.getHoraCarga());
			ps.setInt(3, cargaWms.getNumRegistros());
			ps.setString(4, cargaWms.getUsuarioCarga());
			ps.setInt(5, cargaWms.getIdmTipoImportacion());
			ps.setInt(6, cargaWms.getIdmestadoCuadratura());
			ps.setBoolean(7, cargaWms.isEstado());
			ps.setString(8, cargaWms.getOrgNameShort());
			return ps;
		}, keyHolder);
		LOGGER.info("recupera");

		return keyHolder.getKey().longValue();

	}

}
