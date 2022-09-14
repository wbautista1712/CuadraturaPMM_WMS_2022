package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.MTipoInventario;

public interface MTipoInventarioRepositoryCustom {

	public List<MTipoInventario> getTipoInventario();

	public List<Object[]> getTipoInventarioLote(String idTipoInventario);

	public MTipoInventario getObtenerNombreInventario(String idInventario);
}
