package com.cuadratura.app.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.oracle.dto.projection.FotoWmsDto;

@Repository
public interface CargaWmsRepository extends CrudRepository<CargaWms, Integer>, CargaWmsRepositoryCustom {
	@Query(value = "SELECT WMS.idCarga_WMS idCargaWMS, WMS.fechaCarga, WMS.horaCarga,  WMS.num_Registros numRegistros, WMS.usuario_Carga usuarioCarga, "
			+ "TI.nombreTI, EC.nombreEC AS estado  "
			+ "FROM db_cuadratura.carga_wms WMS "
			+ "INNER JOIN m_tipo_importacion TI ON WMS.id_m_TipoImportacion=TI.id_m_TipoImportacion "
			+ "INNER JOIN m_estado_cuadratura EC ON WMS.id_m_estadoCuadratura=EC.id_m_estadoCuadratura "
			+ "WHERE  "
			+ "WMS.fechaCarga BETWEEN '2022-08-21' AND '2022-08-21' ", nativeQuery = true)
	List<FotoWmsDto> getAllFindFotoWms(String idCentroDistribucion, String fechaDesde, String fechaHasta);
}
