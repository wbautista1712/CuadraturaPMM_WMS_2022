package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.Acceso;

@Repository
public interface AccesoRepository extends CrudRepository<Acceso, Integer>{

}
