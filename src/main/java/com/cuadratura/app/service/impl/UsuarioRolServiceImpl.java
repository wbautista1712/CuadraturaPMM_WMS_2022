package com.cuadratura.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.UsuarioRol;
import com.cuadratura.app.mysql.repository.UsuarioRolRepository;
import com.cuadratura.app.service.UsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService{
	
	private static final Logger LOGGER = LogManager.getLogger(UsuarioRolServiceImpl.class);
	
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
	
	@Override
	public UsuarioRol findUsuarioRol(Integer idUsuario) {
		return usuarioRolRepository.findUsuarioRol(idUsuario);
	}
	
	public void updateUsuarioRol(Integer idUsuarioRol, Integer idRol) {
		LOGGER.info("idUsuarioRol==>> "+idUsuarioRol);
		LOGGER.info("idRol==>> "+idRol);
		this.usuarioRolRepository.updateUsuarioRol(idUsuarioRol, idRol);
	}

}
