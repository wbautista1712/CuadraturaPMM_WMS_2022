package com.cuadratura.app.service;

import java.sql.SQLException;

public interface ProcesoService {
	public void cargarPmmTemp() throws SQLException;

	public void cargarPmmHistorial() throws SQLException;
}
