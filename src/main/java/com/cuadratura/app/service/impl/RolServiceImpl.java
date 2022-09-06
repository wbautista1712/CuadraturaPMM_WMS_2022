package com.cuadratura.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuadratura.app.mysql.entity.Rol;
import com.cuadratura.app.mysql.repository.RolRepository;
import com.cuadratura.app.service.RolService;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Rol> findAll() {
		
		return (List<Rol>) rolRepository.findAll();
	}

	@Override
	public Rol save(Rol rol) { 
		return rolRepository.save(rol);
	}


	@Override
	public Rol findById(int id) {
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		rolRepository.deleteById(id);
	}
	
}
