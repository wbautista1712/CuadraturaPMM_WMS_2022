package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.MPrdmstee;

@Repository
public interface MPrdmsteeRepository  extends CrudRepository<MPrdmstee, Integer>, MPrdmsteeCustom {

}
