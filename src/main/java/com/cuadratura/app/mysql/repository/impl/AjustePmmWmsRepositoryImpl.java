package com.cuadratura.app.mysql.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
				+ "(id_tbl_pmm_wms, id_tipo_inventario, fechaAjuste, horaAjuste, pmm, wms, sugerenciaAjuste, stockBolsaDiscrepancia) "
				+ "VALUES(:idTblPmmWms, :idTipoInventario, :fechaAjuste, :horaAjuste, :pmm, :wms, :sugerenciaAjuste, :stockBolsaDiscrepancia)";
		LOGGER.info( "xxxx " +ajustePmmWms.getIdTblPmmWms());
		this.em.createNativeQuery(query).setParameter("idTblPmmWms", ajustePmmWms.getIdTblPmmWms())
				.setParameter("idTipoInventario", ajustePmmWms.getIdTipoInventario())

				.setParameter("fechaAjuste", ajustePmmWms.getFechaAjuste())
				.setParameter("horaAjuste", ajustePmmWms.getHoraAjuste()).setParameter("pmm", ajustePmmWms.getPmm())
				.setParameter("wms", ajustePmmWms.getWms())
				.setParameter("sugerenciaAjuste", ajustePmmWms.getSugerenciaAjuste())
				.setParameter("stockBolsaDiscrepancia", ajustePmmWms.getStockBolsaDiscrepancia()).executeUpdate();
		LOGGER.info( "xxxx getStockBolsaDiscrepancia " +ajustePmmWms.getStockBolsaDiscrepancia());
	}
}
