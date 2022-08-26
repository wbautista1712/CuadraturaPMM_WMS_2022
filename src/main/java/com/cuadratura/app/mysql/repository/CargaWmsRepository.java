package com.cuadratura.app.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.oracle.dto.projection.FotoWmsDto;

@Repository
public interface CargaWmsRepository extends CrudRepository<CargaWms, Integer>, CargaWmsRepositoryCustom {
	@Query(value = "SELECT C.idCarga_WMS, "
			+ "SUBSTR(WMS.CREATE_DATE,1,8) AS FECHA_FOTO, "
			+ "SUBSTR(WMS.CREATE_DATE,9,6) AS HORA_FOTO, "
			+ "date_format(now(), '%d-%m-%Y') AS FECHA_CARGA,  date_format(now(), '%H:%i:%s') AS HORA_CARGA, "
			+ "C.num_Registros AS REGISTROS, C.usuario_Carga as USUARIO, EC.nombreEC AS ESTADO "
			+ "FROM cuadratura.carga_wms C "
			+ "INNER JOIN cuadratura.tbl_wms WMS ON C.idCarga_WMS=WMS.idCarga_WMS "
			+ "INNER JOIN cuadratura.m_estado_cuadratura EC ON C.id_m_estadoCuadratura=EC.id_m_estadoCuadratura "
			+ "INNER JOIN cuadratura.m_orgmstee CD ON C.org_name_short=CD.org_name_short "
			+ "WHERE CD.org_lvl_child=:idCentroDistribucion AND SUBSTR(WMS.CREATE_DATE,1,8) BETWEEN :fechaDesde AND :fechaHasta LIMIT 1 ", nativeQuery = true)
	List<FotoWmsDto> getAllFindFotoWms(String idCentroDistribucion, String fechaDesde, String fechaHasta);
}
