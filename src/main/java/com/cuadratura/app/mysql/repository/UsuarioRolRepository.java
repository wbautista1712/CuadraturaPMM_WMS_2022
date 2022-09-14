package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.UsuarioRol;

@Repository
public interface UsuarioRolRepository extends CrudRepository<UsuarioRol, Integer>, UsuarioRolRepositoryCustom{

}
