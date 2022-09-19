package com.cuadratura.app.mysql.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.mysql.repository.AjustePmmWmsRepositoryCustom;
import com.cuadratura.app.util.Constantes;

@Repository
@Transactional
public class AjustePmmWmsRepositoryImpl implements AjustePmmWmsRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(AjustePmmWmsRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	@Override
	public void saveAjustePmmWms(AjustePmmWms ajustePmmWms) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO cuadratura.ajuste_pmm_wms "
				+ "(id_tbl_pmm_wms, id_tipo_inventario, fechaAjuste, horaAjuste, pmm, wms, sugerenciaAjuste, stockBolsaDiscrepancia, estado) "
				+ "VALUES(:idTblPmmWms, :idTipoInventario, :fechaAjuste, :horaAjuste, :pmm, :wms, :sugerenciaAjuste, :stockBolsaDiscrepancia, :estado)";
		LOGGER.info("xxxx " + ajustePmmWms.getIdTblPmmWms());
		this.em.createNativeQuery(query).setParameter("idTblPmmWms", ajustePmmWms.getIdTblPmmWms())
				.setParameter("idTipoInventario", ajustePmmWms.getIdTipoInventario())

				.setParameter("fechaAjuste", ajustePmmWms.getFechaAjuste())
				.setParameter("horaAjuste", ajustePmmWms.getHoraAjuste()).setParameter("pmm", ajustePmmWms.getPmm())
				.setParameter("wms", ajustePmmWms.getWms())
				.setParameter("sugerenciaAjuste", ajustePmmWms.getSugerenciaAjuste())
				.setParameter("stockBolsaDiscrepancia", ajustePmmWms.getStockBolsaDiscrepancia())
				.setParameter("estado", Constantes.ESTADO_ACTIVO).executeUpdate();
		LOGGER.info("xxxx getStockBolsaDiscrepancia " + ajustePmmWms.getStockBolsaDiscrepancia());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllBolsaSdi() {
		LOGGER.info("getAllBolsaSdi... " );
		String sql = "	SELECT ATE.TRANS_USER, ATE.TRANS_BATCH_DATE, ATE.TRANS_SOURCE, ATE.TRANS_AUDITED,  ATE.INV_MRPT_CODE,			"
				+ "	ABS(ATE.TRANS_QTY) TRANS_QTY, ATE.TRANS_TYPE_CODE, ATE.TRANS_TRN_CODE, ATE.INV_DRPT_CODE, ATE.TRANS_DATE, ATE.TRANS_CURR_CODE, 			"
				+ "	ATE.TRANS_ORG_LVL_NUMBER, ATE.TRANS_PRD_LVL_NUMBER, ATE.PROC_SOURCE, ATE.TRANS_INNERS, ATE.TRANS_LOTE, ATE.idAjuste_PMM_WMS	, ATE.mat_prd_lvl_child		"
				+ "	FROM			" + "	(			" + "	SELECT DISTINCT 			" +

				"		'Cuadratura' AS TRANS_USER,		" + "		 DATE(NOW()) AS TRANS_BATCH_DATE,         "
				+ "		'Ajuste Cuadratura' AS TRANS_SOURCE,    	" + "		'F' AS TRANS_AUDITED,      		" +

			//	"		AJ.idAjuste_PMM_WMS AS TRANS_SEQUENCE,    		" +

				"		'MV' AS INV_MRPT_CODE,    	" + "		AJ.sugerenciaAjuste AS TRANS_QTY,        	" +

				"		TI.tipoInventario AS TRANS_TYPE_CODE,       	" +

				"		(CASE WHEN AJ.sugerenciaAjuste>0 THEN 63 ELSE 64 END) AS TRANS_TRN_CODE,		" +

				"		(select cuadratura.f_obtenerCodigoSecuencial(TI.tipoInventario,(CASE WHEN AJ.sugerenciaAjuste>0 THEN 63 ELSE 64 END))) as INV_DRPT_CODE,  		"
				+

				"		AJ.fechaAjuste AS TRANS_DATE,            	" +

				"		CD.curr_code AS TRANS_CURR_CODE,  	" + "		CD.org_lvl_number AS TRANS_ORG_LVL_NUMBER,	"
				+ "		PROD.prd_lvl_number AS TRANS_PRD_LVL_NUMBER,  	"
				+ "		'Ajuste cuadratura' AS PROC_SOURCE,   	" +

				"		abs(AJ.sugerenciaAjuste) AS TRANS_INNERS,     		"
				+ "		TBL.trans_lote AS TRANS_LOTE,  		" + "		AJ.idAjuste_PMM_WMS	, TBL.mat_prd_lvl_child	" +

				"	FROM cuadratura.ajuste_pmm_wms AJ 		"
				+ "	INNER JOIN cuadratura.tbl_pmm_wms TBL ON AJ.id_tbl_pmm_wms=TBL.id_tbl_pmm_wms 			"
				+ "	INNER JOIN cuadratura.cruce_pmm_wms Cru ON TBL.idCruce_pmm_wms=Cru.idCruce_pmm_wms 		"
				+ "	INNER JOIN cuadratura.carga_wms WMS ON Cru.idCarga_WMS=WMS.idCarga_WMS 		"
				+ "	INNER JOIN cuadratura.m_orgmstee CD ON WMS.org_name_short=CD.org_name_short 		"
				+ "	INNER JOIN cuadratura.m_prdmstee PROD ON TBL.mat_prd_lvl_child=PROD.prd_lvl_child 			" +

				"	INNER JOIN cuadratura.m_tipo_inventario TI ON AJ.id_tipo_inventario=TI.id_tipo_inventario 			"
				+ "   where AJ.estado = true  " + "	UNION ALL			" + "	SELECT DISTINCT 			" +

				"		'Cuadratura' AS TRANS_USER,		" + "		 DATE(NOW()) AS TRANS_BATCH_DATE,         		"
				+ "		'Ajuste Cuadratura' AS TRANS_SOURCE,    	" + "		'F' AS TRANS_AUDITED,  		" +

			//	"		AJ.idAjuste_PMM_WMS AS TRANS_SEQUENCE,   		" +

				"		'MV' AS INV_MRPT_CODE,      	" + "		AJ.sugerenciaAjuste AS TRANS_QTY,     	" +

				"		(select cuadratura.f_obtener_cod_BolsaDiscrepancia(TI.tipoInventario,(CASE WHEN AJ.sugerenciaAjuste>0 THEN 64 ELSE 63 END)))  AS TRANS_TYPE_CODE,       	"
				+

				"		(CASE WHEN AJ.sugerenciaAjuste>0 THEN 64 ELSE 63 END) AS TRANS_TRN_CODE,		" +

				"		(select cuadratura.f_obtener_codIngreso_Salida(TI.tipoInventario,(CASE WHEN AJ.sugerenciaAjuste>0 THEN 64 ELSE 63 END))) as INV_DRPT_CODE,		"
				+

				"		AJ.fechaAjuste AS TRANS_DATE,            		" +

				"		CD.curr_code AS TRANS_CURR_CODE,  	"
				+ "		CD.org_lvl_number AS TRANS_ORG_LVL_NUMBER, 		"
				+ "		PROD.prd_lvl_number AS TRANS_PRD_LVL_NUMBER,   		"
				+ "		'Ajuste cuadratura' AS PROC_SOURCE,     	" +

				"		abs(AJ.sugerenciaAjuste) AS TRANS_INNERS,     		"
				+ "		TBL.trans_lote AS TRANS_LOTE,  		"
				+ "		AJ.idAjuste_PMM_WMS	,  TBL.mat_prd_lvl_child	" +

				"	FROM cuadratura.ajuste_pmm_wms AJ 		"
				+ "	INNER JOIN cuadratura.tbl_pmm_wms TBL ON AJ.id_tbl_pmm_wms=TBL.id_tbl_pmm_wms 		"
				+ "	INNER JOIN cuadratura.cruce_pmm_wms Cru ON TBL.idCruce_pmm_wms=Cru.idCruce_pmm_wms 	"
				+ "	INNER JOIN cuadratura.carga_wms WMS ON Cru.idCarga_WMS=WMS.idCarga_WMS 		"
				+ "	INNER JOIN cuadratura.m_orgmstee CD ON WMS.org_name_short=CD.org_name_short 		"
				+ "	INNER JOIN cuadratura.m_prdmstee PROD ON TBL.mat_prd_lvl_child=PROD.prd_lvl_child 		" +

				"	INNER JOIN cuadratura.m_tipo_inventario TI ON AJ.id_tipo_inventario=TI.id_tipo_inventario 			"
				+ " where AJ.estado = true  " + "	)ATE					"
				+ "	ORDER BY  ATE.TRANS_TYPE_CODE			";

		Query query = this.em.createNativeQuery(sql);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return query.getResultList();
	}

	@Override
	public void updateAjustePmmWms(Integer idAjustePMMWMS) {
		// TODO Auto-generated method stub
		LOGGER.info("idAjustePMMWMS " + idAjustePMMWMS);
		String query = "UPDATE cuadratura.ajuste_pmm_wms " + "SET estado=:estado "
				+ "WHERE idAjuste_PMM_WMS= :idAjuste_PMM_WMS";

		this.em.createNativeQuery(query).setParameter("idAjuste_PMM_WMS", idAjustePMMWMS)
				.setParameter("estado", Constantes.ESTADO_INACTIVO).executeUpdate();
		LOGGER.info("updateAjustePmmWms");
	}

}
