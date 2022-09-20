package com.cuadratura.app.service;

import java.util.Optional;

import com.cuadratura.app.mysql.entity.AjusteComentario;

public interface AjusteComentarioService {
	public void saveAjusteComentario(AjusteComentario ajusteComentario);

	public Optional<AjusteComentario> findByIdAjusteComentario(Integer id);
}
