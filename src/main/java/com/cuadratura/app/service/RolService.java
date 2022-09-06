package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.Rol;

public interface RolService  {
	
	public List<Rol> findAll();
	public Rol findById(int id);
	public Rol save(Rol rol);	
	public void delete(int id);
}
