package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.MPrdmstee;

public interface MPrdmsteeService extends GenericService<MPrdmstee, Integer> {
	
	public List<MPrdmstee> getMaterialLote(String nombreMaterial);
}
