package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.Usuario;
import com.cuadratura.app.oracle.dto.projection.UsuarioDto;

public interface UsuarioRepositoryCustom {
	public Long saveUsuario(Usuario usuario);
	public void updateUsuario(Usuario usuario);
	
	public   List<UsuarioDto> getUsuarioRol();
}
