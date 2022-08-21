package com.cuadratura.app.service;

import com.cuadratura.app.mysql.entity.CargaPmm;

public interface CargaPmmService  extends GenericService<CargaPmm, Integer> {
	public Long saveCargaPmm(CargaPmm cargaPmm);
}
