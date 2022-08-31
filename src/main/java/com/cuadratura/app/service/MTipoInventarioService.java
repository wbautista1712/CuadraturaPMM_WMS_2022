package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.MTipoInventario;
import com.cuadratura.app.oracle.dto.projection.MTipoInventarioDto;

public interface MTipoInventarioService extends GenericService<MTipoInventario, Integer> {
	public List<MTipoInventarioDto> listAnalisisAjustePmmWms(int idCrucePmmWms, int idTipoInventario);
}
