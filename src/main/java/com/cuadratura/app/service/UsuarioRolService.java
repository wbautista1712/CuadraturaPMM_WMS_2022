package com.cuadratura.app.service;

import com.cuadratura.app.mysql.entity.UsuarioRol;

public interface UsuarioRolService {
	
	public UsuarioRol save(UsuarioRol usuarioRol);
	
	public UsuarioRol findById(int id);
	
	public UsuarioRol findUsuarioRol(UsuarioRol usuarioRol);
}
