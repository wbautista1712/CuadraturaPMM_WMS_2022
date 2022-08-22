package com.cuadratura.app.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.oracle.dto.projection.FotoPmmDto;

@Repository
public interface CargaPmmRepository extends CrudRepository<CargaPmm, Integer>, CargaPmmRepositoryCustom {
	@Query(value = "SELECT PMM.idcarga_PMM idCargaPMM, PMM.fechaCarga, PMM.horaCarga,  PMM.numRegistros, PMM.nombreArchivo, PMM.usuarioCarga, "
			+ "TI.nombreTI, EC.nombreEC AS estado "
			+ "FROM db_cuadratura.carga_pmm PMM "
			+ "INNER JOIN m_tipo_importacion TI ON PMM.id_m_TipoImportacion=TI.id_m_TipoImportacion "
			+ "INNER JOIN m_estado_cuadratura EC ON PMM.id_m_estadoCuadratura=EC.id_m_estadoCuadratura "
			+ "WHERE PMM.org_lvl_child=1 AND PMM.fechaCarga BETWEEN '2022-08-21' AND '2022-08-21' ", nativeQuery = true)
	List<FotoPmmDto> getAllFindFotoPmm(String idCentroDistribucion, String fechaDesde, String fechaHasta);
}
