package com.cuadratura.app.oracle.repository;

import java.sql.SQLException;

import com.cuadratura.app.oracle.entity.CuadraturaTransfer;

public interface CuadraturaTransferRepositoryCustom {
	
	public Long getSequence() ;
	public void saveCuadraturaTransfer(CuadraturaTransfer cuadraturaTransfer) ;
	
	public void spCuadraturaTransfer(int idSesion) throws SQLException ;
}
