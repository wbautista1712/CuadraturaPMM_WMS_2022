package com.cuadratura.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cuadratura.app.mysql.entity.Usuario;


public interface UsuarioService {

	public List<Usuario> findAll();
	public Page<Usuario> findAll(Pageable pageable);
	public Usuario findByUsername(String username);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);

	public Long saveUsuario(Usuario usuario);
	
	public void updateUsuario(Usuario usuario);
}
