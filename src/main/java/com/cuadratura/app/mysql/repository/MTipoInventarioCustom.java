package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.MTipoInventario;

public interface MTipoInventarioCustom {
	
	public   List<MTipoInventario> getTipoInventario();
	
	public   List<Object[]> getTipoInventarioLote(String idTipoInventario);
	
	public   List<MTipoInventario> getObtenerLote(String nombreLote);
}
