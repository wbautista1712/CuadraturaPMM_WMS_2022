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
	public UsuarioRol findUsuarioRol(UsuarioRol usuarioRol) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM cuadratura.usuario_rol x WHERE  x.idRol = :idRol and x.idUsuario = :idUsuario ");

		LOGGER.info(sb.toString());
		Query q = em.createNativeQuery(sb.toString(), UsuarioRol.class);
		q.setParameter("idRol", usuarioRol.getIdRol());
		q.setParameter("idUsuario",usuarioRol.getIdUsuario());
		return (UsuarioRol) q.getSingleResult();
	}
	
	public void updateUsuarioRol(Integer idUsuarioRol,Integer idRol) {
		
	}

}
