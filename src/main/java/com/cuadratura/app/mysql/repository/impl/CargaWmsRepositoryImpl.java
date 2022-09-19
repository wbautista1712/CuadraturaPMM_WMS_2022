package com.cuadratura.app.mysql.repository.impl;

import java.sql.PreparedStatement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
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


	@Autowired
	@Qualifier("jdbctemplateTwo")
	private JdbcTemplate jdbcTemplate;
	
	

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	@Override
	public Long saveCargaWms(CargaWms cargaWms) {		

		String INSERT_MESSAGE_SQL = "INSERT INTO pmm.carga_wms "
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
		
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllFindFotoWms(String idCentroDistribucion, String fechaDesde, String fechaHasta, Integer start, Integer end){
		LOGGER.info("::: getAllFindFotoWms:::start "+start);
		LOGGER.info("::: getAllFindFotoWms:::end "+end);
	String sql = "SELECT distinct CD.org_lvl_child,  C.idCarga_WMS,  "
				+ "date_format(CONCAT(SUBSTR(WMS.CREATE_DATE,1,4),'-',SUBSTR(WMS.CREATE_DATE,5,2),'-',SUBSTR(WMS.CREATE_DATE,7,2)),'%d/%m/%Y') AS FECHA_FOTO, "
				+ "CONCAT(SUBSTR(WMS.CREATE_DATE,9,2),':',SUBSTR(WMS.CREATE_DATE,11,2),':',SUBSTR(WMS.CREATE_DATE,13,2)) AS HORA_FOTO, "
				+ "date_format(now(), '%d/%m/%Y') AS FECHA_CARGA,  date_format(now(), '%H:%i:%s') AS HORA_CARGA, "
				+ "C.num_Registros AS REGISTROS, C.usuario_Carga as USUARIO, EC.nombreEC AS ESTADO FROM pmm.carga_wms C  "
				+ "INNER JOIN pmm.tbl_wms WMS ON C.idCarga_WMS=WMS.idCarga_WMS INNER JOIN pmm.m_estado_cuadratura EC ON  "
				+ "C.id_m_estadoCuadratura=EC.id_m_estadoCuadratura INNER JOIN pmm.m_orgmstee CD ON C.org_name_short=CD.org_name_short "
				+ "WHERE CD.org_lvl_child=:idCentroDistribucion  "		
				+ "AND date_format(CONCAT(SUBSTR(WMS.CREATE_DATE,1,4),'-',SUBSTR(WMS.CREATE_DATE,5,2),'-',SUBSTR(WMS.CREATE_DATE,7,2)),'%Y-%m-%d')  "		 
				+ "BETWEEN :fechaDesde AND :fechaHasta ";		
	
		Query query = this.em.createNativeQuery(sql);
		query.setParameter("idCentroDistribucion", idCentroDistribucion);
		query.setParameter("fechaDesde", fechaDesde);
		query.setParameter("fechaHasta",fechaHasta);
	
		query.setFirstResult(start);
		query.setMaxResults(end);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return query.getResultList();
	    
	}	
	
	public Integer countFotoWms(String idCentroDistribucion, String fechaDesde, String fechaHasta) throws Exception{
		String sql = "SELECT COUNT(*) FROM (SELECT distinct CD.org_lvl_child,  C.idCarga_WMS,  "
				+ "date_format(CONCAT(SUBSTR(WMS.CREATE_DATE,1,4),'-',SUBSTR(WMS.CREATE_DATE,5,2),'-',SUBSTR(WMS.CREATE_DATE,7,2)),'%d/%m/%Y') AS FECHA_FOTO, "
				+ "CONCAT(SUBSTR(WMS.CREATE_DATE,9,2),':',SUBSTR(WMS.CREATE_DATE,11,2),':',SUBSTR(WMS.CREATE_DATE,13,2)) AS HORA_FOTO, "
				+ "date_format(now(), '%d/%m/%Y') AS FECHA_CARGA,  date_format(now(), '%H:%i:%s') AS HORA_CARGA, "
				+ "C.num_Registros AS REGISTROS, C.usuario_Carga as USUARIO, EC.nombreEC AS ESTADO FROM pmm.carga_wms C  "
				+ "INNER JOIN pmm.tbl_wms WMS ON C.idCarga_WMS=WMS.idCarga_WMS INNER JOIN pmm.m_estado_cuadratura EC ON  "
				+ "C.id_m_estadoCuadratura=EC.id_m_estadoCuadratura INNER JOIN pmm.m_orgmstee CD ON C.org_name_short=CD.org_name_short "
				+ "WHERE CD.org_lvl_child=:idCentroDistribucion  "		
				+ "AND date_format(CONCAT(SUBSTR(WMS.CREATE_DATE,1,4),'-',SUBSTR(WMS.CREATE_DATE,5,2),'-',SUBSTR(WMS.CREATE_DATE,7,2)),'%Y-%m-%d')  "		 
				+ "BETWEEN :fechaDesde AND :fechaHasta )   tt ";		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idCentroDistribucion", idCentroDistribucion);
		query.setParameter("fechaDesde", fechaDesde);
		query.setParameter("fechaHasta",fechaHasta);	
		
		return Integer.valueOf(query.getSingleResult().toString());
	}

}
