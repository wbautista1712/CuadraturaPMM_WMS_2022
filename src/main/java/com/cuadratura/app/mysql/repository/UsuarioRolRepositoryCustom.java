package com.cuadratura.app.mysql.repository;

import com.cuadratura.app.mysql.entity.UsuarioRol;

public interface UsuarioRolRepositoryCustom {
	public UsuarioRol findUsuarioRol(Integer idUsuario);

	public void updateUsuarioRol(Integer idUsuarioRol, Integer idRol);
}
