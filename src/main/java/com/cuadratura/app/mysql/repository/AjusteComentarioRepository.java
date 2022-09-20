package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.Acceso;
import com.cuadratura.app.mysql.entity.AjusteComentario;
@Repository
public interface AjusteComentarioRepository  extends CrudRepository< AjusteComentario, Integer> , AjusteComentarioRepositoryCustom {

}
