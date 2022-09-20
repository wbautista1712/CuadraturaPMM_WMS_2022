package com.cuadratura.app.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.AjusteComentario;
import com.cuadratura.app.mysql.repository.AjusteComentarioRepository;
import com.cuadratura.app.service.AjusteComentarioService;

@Service
public class AjusteComentarioServiceImpl extends GenericServiceImpl<AjusteComentario, Integer>
		implements AjusteComentarioService {
	private static final Logger LOGGER = LogManager.getLogger(AjusteComentarioServiceImpl.class);

	@Autowired
	private AjusteComentarioRepository ajusteComentarioRepository;

	@Override
	public CrudRepository<AjusteComentario, Integer> getDao() {
		return ajusteComentarioRepository;
	}

	@Override
	public void saveAjusteComentario(AjusteComentario ajusteComentario) {
		LOGGER.info(ajusteComentario.toString());
		this.ajusteComentarioRepository.save(ajusteComentario);
	}
	
	@Override
	 public Optional<AjusteComentario> findByIdAjusteComentario (Integer  id) {
		 return this.ajusteComentarioRepository.findById(id);
	 }

}
