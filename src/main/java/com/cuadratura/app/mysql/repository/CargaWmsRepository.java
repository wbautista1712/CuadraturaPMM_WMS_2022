package com.cuadratura.app.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.oracle.dto.projection.FotoWmsDto;

@Repository
public interface CargaWmsRepository extends CrudRepository<CargaWms, Integer>, CargaWmsRepositoryCustom {
			
	@Query(value = "SELECT DISTINCT WMS.org_name_short FROM cuadratura.cruce_pmm_wms C "
			+ "                INNER JOIN cuadratura.carga_wms WMS on C.idCarga_WMS=WMS.idCarga_WMS "
			+ "                WHERE C.idCruce_pmm_wms=:idCrucePmmWms", nativeQuery = true)
	String getCDCrucePmmWms( int  idCrucePmmWms);
	
	@Query(value = "SELECT CD.org_lvl_child,  C.idCarga_WMS,  "
			+ "date_format(CONCAT(SUBSTR(WMS.CREATE_DATE,1,4),'-',SUBSTR(WMS.CREATE_DATE,5,2),'-',SUBSTR(WMS.CREATE_DATE,7,2)),'%d/%m/%Y') AS FECHA_FOTO, "
			+ "CONCAT(SUBSTR(WMS.CREATE_DATE,9,2),':',SUBSTR(WMS.CREATE_DATE,11,2),':',SUBSTR(WMS.CREATE_DATE,13,2)) AS HORA_FOTO, "
			+ "date_format(now(), '%d/%m/%Y') AS FECHA_CARGA,  date_format(now(), '%H:%i:%s') AS HORA_CARGA, "
			+ "C.num_Registros AS REGISTROS, C.usuario_Carga as USUARIO, EC.nombreEC AS ESTADO FROM cuadratura.carga_wms C  "
			+ "INNER JOIN cuadratura.tbl_wms WMS ON C.idCarga_WMS=WMS.idCarga_WMS INNER JOIN cuadratura.m_estado_cuadratura EC ON  "
			+ "C.id_m_estadoCuadratura=EC.id_m_estadoCuadratura INNER JOIN cuadratura.m_orgmstee CD ON C.org_name_short=CD.org_name_short "
			+ "WHERE CD.org_lvl_child=:idCentroDistribucion  "		
			+ "AND date_format(CONCAT(SUBSTR(WMS.CREATE_DATE,1,4),'-',SUBSTR(WMS.CREATE_DATE,5,2),'-',SUBSTR(WMS.CREATE_DATE,7,2)),'%Y-%m-%d')  "		 
			+ "BETWEEN :fechaDesde AND :fechaHasta ", nativeQuery = true)
	List<FotoWmsDto> getAllFindFotoWmsExcel(String idCentroDistribucion, String fechaDesde, String fechaHasta);
}
