package com.cuadratura.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.UsuarioRol;
import com.cuadratura.app.mysql.repository.UsuarioRolRepository;
import com.cuadratura.app.service.UsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService{

	@Autowired
	private UsuarioRolRepository usuarioRolRepository;
	
	@Override
	public UsuarioRol save(UsuarioRol usuarioRol) {
		return usuarioRolRepository.save(usuarioRol);
	}

	@Override
	public UsuarioRol findById(int id) {
		return usuarioRolRepository.findById(id).orElse(null);
	}

}
