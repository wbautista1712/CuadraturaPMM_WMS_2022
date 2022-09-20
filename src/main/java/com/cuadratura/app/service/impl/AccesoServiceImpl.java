package com.cuadratura.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cuadratura.app.mysql.entity.Acceso;
import com.cuadratura.app.mysql.repository.AccesoRepository;
import com.cuadratura.app.service.AccesoService;

public class AccesoServiceImpl implements AccesoService {

	@Autowired
	private AccesoRepository accesoRepository;
	
	@Override
	public void saveAcceso(Acceso acceso) 
	{
		accesoRepository.save(acceso);
	}

}
