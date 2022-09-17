package com.cuadratura.app.oracle.repository;

import com.cuadratura.app.oracle.entity.CuadraturaTransfer;

public interface CuadraturaTransferRepositoryCustom {
	
	public Long getSequence() ;
	public void saveCuadraturaTransfer(CuadraturaTransfer cuadraturaTransfer) ;
}
