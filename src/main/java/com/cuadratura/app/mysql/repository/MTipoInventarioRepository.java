package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.MTipoInventario;

@Repository
public interface MTipoInventarioRepository  extends CrudRepository<MTipoInventario, Integer>, MTipoInventarioRepositoryCustom {

}
