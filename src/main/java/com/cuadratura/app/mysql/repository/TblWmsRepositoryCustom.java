package com.cuadratura.app.mysql.repository;

import java.sql.SQLException;
import java.util.List;

import com.cuadratura.app.oracle.dto.projection.WmsCinsDto;

public interface TblWmsRepositoryCustom {

	public void saveTblWms(List<WmsCinsDto> listaTblPmmForm, int numeroLotes, int idCargaWMS) throws SQLException;
}
