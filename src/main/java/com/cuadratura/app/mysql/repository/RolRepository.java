package com.cuadratura.app.mysql.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer>
{
	public List<Rol> findAll();
}

