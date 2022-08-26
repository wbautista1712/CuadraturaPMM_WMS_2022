package com.cuadratura.app.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.oracle.dto.projection.FotoPmmDto;

@Repository
public interface CargaPmmRepository extends CrudRepository<CargaPmm, Integer>, CargaPmmRepositoryCustom {
	@Query(value = "SELECT C.idCarga_PMM, date_format(C.fechaFoto, '%d/%m/%Y') AS FECHA_FOTO, C.horaFoto AS HORA_FOTO, date_format(now(), '%d/%m/%Y') AS FECHA_CARGA,  date_format(now(), '%H:%i:%s') AS HORA_CARGA, "
			+ "C.numRegistros AS REGISTROS, C.usuarioCarga as USUARIO, C.nombreArchivo AS NOMBRE_ARCHIVO, EC.nombreEC AS ESTADO "
			+ "FROM cuadratura.carga_pmm C "
			+ "INNER JOIN cuadratura.m_estado_cuadratura EC ON C.id_m_estadoCuadratura=EC.id_m_estadoCuadratura "		
			+ "WHERE C.org_lvl_child=:idCentroDistribucion AND date_format(C.fechaFoto, '%d/%m/%Y') BETWEEN :fechaDesde AND :fechaHasta ", nativeQuery = true)
	List<FotoPmmDto> getAllFindFotoPmm(String idCentroDistribucion, String fechaDesde, String fechaHasta);
}
