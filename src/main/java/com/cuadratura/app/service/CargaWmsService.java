package com.cuadratura.app.service;

import com.cuadratura.app.mysql.entity.CargaWms;

public interface CargaWmsService  extends GenericService<CargaWms, Integer> {
	public Long saveCargaWms(CargaWms cargaWms);
}
