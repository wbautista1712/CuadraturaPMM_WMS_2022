package com.cuadratura.app.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.MOrgmstee;

@Repository
public interface MOrgmsteeRepository extends CrudRepository<MOrgmstee, Integer> {

}
