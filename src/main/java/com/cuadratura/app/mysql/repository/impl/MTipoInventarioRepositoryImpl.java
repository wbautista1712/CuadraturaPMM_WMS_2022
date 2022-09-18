package com.cuadratura.app.mysql.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.MTipoInventario;
import com.cuadratura.app.mysql.repository.MTipoInventarioRepositoryCustom;
import com.cuadratura.app.util.Constantes;

@Repository
public class MTipoInventarioRepositoryImpl implements MTipoInventarioRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(MTipoInventarioRepositoryImpl.class);

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<MTipoInventario> getTipoInventario() {
		LOGGER.info("getTipoInventario ");
		List<MTipoInventario> listOfEmailDomains = em.createNativeQuery(
				" SELECT I.* FROM cuadratura.m_tipo_inventario I WHERE I.estado= true ",
				MTipoInventario.class).getResultList();
		return listOfEmailDomains;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getTipoInventarioLote(String idTipoInventario) {
		LOGGER.info("getTipoInventario ");
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT I.id_tipo_inventario, I.nombre FROM cuadratura.m_tipo_inventario I ");
		sb.append("WHERE I.estado = :estado ");
		if (!idTipoInventario.equals(Constantes.VACIO)) {
			sb.append("AND I.id_tipo_inventario IN (");
			String cad[] = idTipoInventario.split(",");
			for (int i = 0; i < cad.length; i++) {
				if (i == 0)
					sb.append("'" + cad[i] + "'");
				else
					sb.append(",'" + cad[i] + "'");
			}
			sb.append(") ");
		}
		Query q = em.createNativeQuery(sb.toString());
		q.setParameter("estado", Constantes.ESTADO_ACTIVO);
		LOGGER.info(sb.toString());
		return q.getResultList();

	}

	@Override
	public String getObtenerNombreInventario(Integer idInventario){
		// TODO Auto-generated method stub
		// LOGGER.info("getTipoInventario ");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT I.nombre FROM cuadratura.m_tipo_inventario I where I.id_tipo_inventario=:idInventario ");
	
		Query q = em.createNativeQuery(sb.toString());
		q.setParameter("idInventario", idInventario);
		// LOGGER.info(sb.toString());
		return (String) q.getSingleResult();
	}
}
