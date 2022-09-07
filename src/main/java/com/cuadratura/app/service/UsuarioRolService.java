package com.cuadratura.app.service;

import com.cuadratura.app.mysql.entity.UsuarioRol;

public interface UsuarioRolService {
	
	public UsuarioRol save(UsuarioRol usuarioRol);
	
	public UsuarioRol findById(int id);
	
	public UsuarioRol findUsuarioRol(Integer idUsuario);
	
	public void updateUsuarioRol(Integer idUsuarioRol, Integer idRol);
}
