package com.cuadratura.app.mysql.repository;

import java.util.List;

public interface MTipoInventarioCustom {
	public   List<Object[]> listAnalisisAjustePmmWms(int idCrucePmmWms, int idTipoInventario);
}
