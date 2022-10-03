package com.cuadratura.app.oracle.repository;

import java.sql.SQLException;

public interface ProcesoRepository {

	public void cargarPmmTemp()throws SQLException ;
	
	public void cargarPmmHistorial()throws SQLException ;
}
