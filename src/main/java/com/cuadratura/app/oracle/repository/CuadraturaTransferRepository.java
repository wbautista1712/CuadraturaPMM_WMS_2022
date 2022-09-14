package com.cuadratura.app.oracle.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.mysql.repository.TblWmsRepositoryCustom;

@Repository
public interface CuadraturaTransferRepository extends CrudRepository<TblWms, Integer>, CuadraturaTransferRepositoryCustom{

}
