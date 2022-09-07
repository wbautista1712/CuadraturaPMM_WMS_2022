package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.UsuarioRol;

public interface UsuarioRolCustom {
	public UsuarioRol findUsuarioRol(UsuarioRol usuarioRol) ;
	
	public void updateUsuarioRol(Integer idUsuarioRol,Integer idRol);
}
