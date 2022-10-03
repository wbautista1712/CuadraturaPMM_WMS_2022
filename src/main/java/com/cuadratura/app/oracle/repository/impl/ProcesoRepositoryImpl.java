package com.cuadratura.app.oracle.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.oracle.repository.ProcesoRepository;

@Repository
public class ProcesoRepositoryImpl implements ProcesoRepository {

	private static final Logger LOGGER = LogManager.getLogger(ProcesoRepositoryImpl.class);
	

	@Autowired
	@Qualifier("jdbctemplateOne")
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public void cargarPmmTemp() throws SQLException {
		// TODO Auto-generated method stub
		DataSource ds = jdbcTemplate.getDataSource();
		Connection conn = null;
		CallableStatement csmt = null;

	
		try {
			conn = ds.getConnection();
			csmt = conn.prepareCall("{call cuadraturawyp.PR_TEMP_FT_FAPINVBALEE()}");

			
			csmt.execute();

		} catch (Exception ex) {
			LOGGER.error("Error en cargarPmmTemp()", ex);
		} finally {
			if (csmt != null) {
				csmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public void cargarPmmHistorial()throws SQLException  {
		// TODO Auto-generated method stub
		DataSource ds = jdbcTemplate.getDataSource();
		Connection conn = null;
		CallableStatement csmt = null;

	
		try {
			conn = ds.getConnection();
			csmt = conn.prepareCall("{call cuadraturawyp.PR_HST_FT_FAPINVBALEE()}");


			csmt.execute();

		} catch (Exception ex) {
			LOGGER.error("Error en cargarPmmHistorial()", ex);
		} finally {
			if (csmt != null) {
				csmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

}
