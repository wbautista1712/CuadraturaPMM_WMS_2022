package com.cuadratura.app.mysql.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.FotoWmsByCargaDto;
import com.cuadratura.app.oracle.dto.WmsCinsDto;

public interface TblWmsRepositoryCustom {

	public void saveTblWms(WmsCinsDto listaTblPmmForm, int idCargaWMS) throws SQLException;
	
	public void saveTblWmsBatch(List<WmsCinsDto> listaTblPmmForm, int numeroLotes, int idCargaWMS) throws SQLException;
	
	public void uploadTblWms(TblWms tblWms) throws SQLException;
	
	public List<Map<String, Object>> obtenerFotoWMSCuadratura(int idCargaWMS) throws SQLException;
	public List<FotoWmsByCargaDto> getExportFotoWmsByIdCarga(Integer idCargaWMS) throws SQLException;

}
