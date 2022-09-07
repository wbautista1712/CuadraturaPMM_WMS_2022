package com.cuadratura.app.mysql.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.UsuarioRol;
import com.cuadratura.app.mysql.repository.UsuarioRolCustom;

@Repository
@Transactional
public class UsuarioRolRepositoryImpl implements   UsuarioRolCustom{
	
	private static final Logger LOGGER = LogManager.getLogger(UsuarioRolRepositoryImpl.class);
	

	@PersistenceContext(unitName = "jpa_mysql")
	private EntityManager em;
	
	
	@Override
	public UsuarioRol findUsuarioRol(Integer idUsuario) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM cuadratura.usuario_rol x WHERE x.idUsuario = :idUsuario ");

		LOGGER.info(sb.toString());
		Query q = this.em.createNativeQuery(sb.toString(), UsuarioRol.class);		
		q.setParameter("idUsuario",idUsuario);
		return (UsuarioRol) q.getSingleResult();
	}
	
	public void updateUsuarioRol(Integer idUsuarioRol, Integer idRol) {
		this.em.createNativeQuery("UPDATE  cuadratura.usuario_rol x  set x.idRol = :idRol WHERE  x.idUsuario_rol  = :idUsuarioRol ").setParameter(1, idRol)
	      .setParameter(2, idUsuarioRol).executeUpdate();
	}

}
