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
		LOGGER.info( "xxxx " +ajustePmmWms.getIdTblPmmWms());
		this.em.createNativeQuery(query).setParameter("idTblPmmWms", ajustePmmWms.getIdTblPmmWms())
				.setParameter("idTipoInventario", ajustePmmWms.getIdTipoInventario())

				.setParameter("fechaAjuste", ajustePmmWms.getFechaAjuste())
				.setParameter("horaAjuste", ajustePmmWms.getHoraAjuste()).setParameter("pmm", ajustePmmWms.getPmm())
				.setParameter("wms", ajustePmmWms.getWms())
				.setParameter("sugerenciaAjuste", ajustePmmWms.getSugerenciaAjuste())
				.setParameter("stockBolsaDiscrepancia", ajustePmmWms.getStockBolsaDiscrepancia())	
				.setParameter("estado", true).executeUpdate();
		LOGGER.info( "xxxx getStockBolsaDiscrepancia " +ajustePmmWms.getStockBolsaDiscrepancia());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllBolsaSdi(){
		String sql =  "	SELECT ATE.TRANS_USER, ATE.TRANS_BATCH_DATE, ATE.TRANS_SOURCE, ATE.TRANS_AUDITED, ATE.TRANS_SEQUENCE, ATE.INV_MRPT_CODE,			"+
				"	ATE.TRANS_QTY, ATE.TRANS_TYPE_CODE, ATE.TRANS_TRN_CODE, ATE.INV_DRPT_CODE, ATE.TRANS_DATE, ATE.TRANS_CURR_CODE, 			"+
				"	ATE.TRANS_ORG_LVL_NUMBER, ATE.TRANS_PRD_LVL_NUMBER, ATE.PROC_SOURCE, ATE.TRANS_INNERS, ATE.TRANS_LOTE, ATE.ESTADO			"+
				"	FROM			"+
				"	(			"+
				"	SELECT DISTINCT 			"+
			
				"		'Cuadratura' AS TRANS_USER,		"+
				"		 DATE(NOW()) AS TRANS_BATCH_DATE,          -- Fecha de creación del batch		"+
				"		'Ajuste Cuadratura' AS TRANS_SOURCE,     -- Campo Libre de Texto: Ajuste cuadratura		"+
				"		'F' AS TRANS_AUDITED,     -- Trans_audited. = ' F ' No es necesario llenar los costos, el sistema ejecuta la lookup. 		"+
			
				"		AJ.idAjuste_PMM_WMS AS TRANS_SEQUENCE,    -- (id_tbl_pmm_wms ID DEL AJUSTE TABLA MYSQL)		"+
			
				"		'MV' AS INV_MRPT_CODE, -- m_orgmstee,        -- Siempre será 'MV' de movimiento		"+
				"		AJ.sugerenciaAjuste AS TRANS_QTY,         -- Cantidad de ajuste (Sin considerar signo)		"+
			
				"		TI.tipoInventario AS TRANS_TYPE_CODE,       -- Código de tipo de inventario         - Validar en INVTYPEE	'01'  (CÓDIGOS DEL MAESTRO DE TIPO DE INVENTARIO)	"+
			
				"		(CASE WHEN AJ.sugerenciaAjuste>0 THEN 63 ELSE 64 END) AS TRANS_TRN_CODE,		"+
		
				"		(select cuadratura.f_obtenerCodigoSecuencial(TI.tipoInventario,(CASE WHEN AJ.sugerenciaAjuste>0 THEN 63 ELSE 64 END))) as INV_DRPT_CODE,  		"+
			
				"		AJ.fechaAjuste AS TRANS_DATE,                -- Fecha ajuste - es la fecha actual		"+
		
				"		CD.curr_code AS TRANS_CURR_CODE,   -- Opcional sobre tipo de moneda | opcional (default soles)		"+
				"		CD.org_lvl_number AS TRANS_ORG_LVL_NUMBER, -- Código del CD (9004, 9006, 9011, 9012, 9015) MAESTRO: ORGMSTEE.ORG_LVL_NUMBER		"+
				"		PROD.prd_lvl_number AS TRANS_PRD_LVL_NUMBER,    -- CODIGO SAP (PRODUCTO) | MAESTRO PRODUCTO m_prdmstee		"+
				"		'Ajuste cuadratura' AS PROC_SOURCE,      --  Campo Libre de Texto: Ajuste cuadratura o ingresar CD		"+
				
				"		abs(AJ.sugerenciaAjuste) AS TRANS_INNERS,      -- Cantidad de ajuste (Sin considerar signo)		"+
				"		TBL.trans_lote AS TRANS_LOTE,   -- LOTE PRODUCTO Trans_Lote (tbl_pmm_wms)		"+
				"		AJ.estado		"+

				"	FROM cuadratura.ajuste_pmm_wms AJ -- TABLA AJUSTE			"+
				"	INNER JOIN cuadratura.tbl_pmm_wms TBL ON AJ.id_tbl_pmm_wms=TBL.id_tbl_pmm_wms -- TABLA PMM - WMS			"+
				"	INNER JOIN cuadratura.cruce_pmm_wms Cru ON TBL.idCruce_pmm_wms=Cru.idCruce_pmm_wms -- CRUCE PMM WMS			"+
				"	INNER JOIN cuadratura.carga_wms WMS ON Cru.idCarga_WMS=WMS.idCarga_WMS -- FOTO WMS			"+
				"	INNER JOIN cuadratura.m_orgmstee CD ON WMS.org_name_short=CD.org_name_short -- CD			"+
				"	INNER JOIN cuadratura.m_prdmstee PROD ON TBL.mat_prd_lvl_child=PROD.prd_lvl_child -- MAESTRO PRODUCTO			"+
		
				"	INNER JOIN cuadratura.m_tipo_inventario TI ON AJ.id_tipo_inventario=TI.id_tipo_inventario 			"+
		
				"	UNION ALL			"+
				"	SELECT DISTINCT 			"+
		
				"		'Cuadratura' AS TRANS_USER,		"+
				"		 DATE(NOW()) AS TRANS_BATCH_DATE,          -- Fecha de creación del batch		"+
				"		'Ajuste Cuadratura' AS TRANS_SOURCE,     -- Campo Libre de Texto: Ajuste cuadratura		"+
				"		'F' AS TRANS_AUDITED,     -- Trans_audited. = ' F ' No es necesario llenar los costos, el sistema ejecuta la lookup. 		"+
		
				"		AJ.idAjuste_PMM_WMS AS TRANS_SEQUENCE,    -- (id_tbl_pmm_wms ID DEL AJUSTE TABLA MYSQL)		"+
			
				"		'MV' AS INV_MRPT_CODE, -- m_orgmstee,        -- Siempre será 'MV' de movimiento		"+
				"		AJ.sugerenciaAjuste AS TRANS_QTY,         -- Cantidad de ajuste (Sin considerar signo)		"+
		
				"		(select cuadratura.f_obtener_cod_BolsaDiscrepancia(TI.tipoInventario,(CASE WHEN AJ.sugerenciaAjuste>0 THEN 64 ELSE 63 END)))  AS TRANS_TYPE_CODE,       -- Código de tipo de inventario         - Validar en INVTYPEE	'01'  (CÓDIGOS DEL MAESTRO DE TIPO DE INVENTARIO)	"+
		
				"		(CASE WHEN AJ.sugerenciaAjuste>0 THEN 64 ELSE 63 END) AS TRANS_TRN_CODE,		"+
			
				"		(select cuadratura.f_obtener_codIngreso_Salida(TI.tipoInventario,(CASE WHEN AJ.sugerenciaAjuste>0 THEN 64 ELSE 63 END))) as INV_DRPT_CODE,		"+
			
				"		AJ.fechaAjuste AS TRANS_DATE,                -- Fecha ajuste - es la fecha actual		"+
		
				"		CD.curr_code AS TRANS_CURR_CODE,   -- Opcional sobre tipo de moneda | opcional (default soles)		"+
				"		CD.org_lvl_number AS TRANS_ORG_LVL_NUMBER, -- Código del CD (9004, 9006, 9011, 9012, 9015) MAESTRO: ORGMSTEE.ORG_LVL_NUMBER		"+
				"		PROD.prd_lvl_number AS TRANS_PRD_LVL_NUMBER,    -- CODIGO SAP (PRODUCTO) | MAESTRO PRODUCTO m_prdmstee		"+
				"		'Ajuste cuadratura' AS PROC_SOURCE,      --  Campo Libre de Texto: Ajuste cuadratura o ingresar CD		"+
	
				"		abs(AJ.sugerenciaAjuste) AS TRANS_INNERS,      -- Cantidad de ajuste (Sin considerar signo)		"+
				"		TBL.trans_lote AS TRANS_LOTE,   -- LOTE PRODUCTO Trans_Lote (tbl_pmm_wms)		"+
				"		AJ.estado		"+

				"	FROM cuadratura.ajuste_pmm_wms AJ -- TABLA AJUSTE			"+
				"	INNER JOIN cuadratura.tbl_pmm_wms TBL ON AJ.id_tbl_pmm_wms=TBL.id_tbl_pmm_wms -- TABLA PMM - WMS			"+
				"	INNER JOIN cuadratura.cruce_pmm_wms Cru ON TBL.idCruce_pmm_wms=Cru.idCruce_pmm_wms -- CRUCE PMM WMS			"+
				"	INNER JOIN cuadratura.carga_wms WMS ON Cru.idCarga_WMS=WMS.idCarga_WMS -- FOTO WMS			"+
				"	INNER JOIN cuadratura.m_orgmstee CD ON WMS.org_name_short=CD.org_name_short -- CD			"+
				"	INNER JOIN cuadratura.m_prdmstee PROD ON TBL.mat_prd_lvl_child=PROD.prd_lvl_child -- MAESTRO PRODUCTO			"+
			
				"	INNER JOIN cuadratura.m_tipo_inventario TI ON AJ.id_tipo_inventario=TI.id_tipo_inventario 			"+
				"	)ATE			"+
				"	ORDER BY ATE.TRANS_SEQUENCE, ATE.TRANS_TYPE_CODE			";


		
		Query query = this.em.createNativeQuery(sql);		
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return query.getResultList();
	}
	
}
