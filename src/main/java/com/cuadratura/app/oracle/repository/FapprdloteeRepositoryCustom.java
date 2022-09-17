package com.cuadratura.app.oracle.repository;

import com.cuadratura.app.oracle.entity.Fapprdlotee;

public interface FapprdloteeRepositoryCustom {
	public Fapprdlotee findFapprdlotee(Integer cd, String numeroLote);
}
