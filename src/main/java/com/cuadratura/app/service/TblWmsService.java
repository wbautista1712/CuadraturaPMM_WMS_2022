package com.cuadratura.app.service;

import java.sql.SQLException;
import java.util.List;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.WmsCinsDto;
import com.cuadratura.app.oracle.entity.Fapinvbalee;


public interface TblWmsService  extends GenericService<TblWms, Integer> {
	void saveTblWms(List<WmsCinsDto> listaTblPmmForm, int numeroLotes, int idCargaWMS) throws SQLException ;
}
