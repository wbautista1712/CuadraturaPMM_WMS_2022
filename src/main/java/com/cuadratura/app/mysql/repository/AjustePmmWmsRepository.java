package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.AjustePmmWms;

@Repository
public interface AjustePmmWmsRepository extends CrudRepository<AjustePmmWms, Integer>, AjustePmmWmsRepositoryCustom {

}
