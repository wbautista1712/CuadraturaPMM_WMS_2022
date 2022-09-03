package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.MTipoInventario;

public interface MTipoInventarioService extends GenericService<MTipoInventario, Integer> {
	public List<MTipoInventario> getTipoInventario() ;
	
}
