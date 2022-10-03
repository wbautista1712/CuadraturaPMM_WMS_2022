package com.cuadratura.app.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuadratura.app.oracle.repository.ProcesoRepository;
import com.cuadratura.app.service.ProcesoService;

@Service
public class ProcesoServiceImpl implements ProcesoService{

	@Autowired
	private ProcesoRepository procesoRepository;
	
	@Override
	public void cargarPmmTemp() throws SQLException {
		// TODO Auto-generated method stub
		this.procesoRepository.cargarPmmTemp();
	}

	@Override
	public void cargarPmmHistorial() throws SQLException {
		// TODO Auto-generated method stub
		this.procesoRepository.cargarPmmHistorial();
	}

}
