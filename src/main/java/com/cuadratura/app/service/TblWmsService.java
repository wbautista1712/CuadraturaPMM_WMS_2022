package com.cuadratura.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.FotoWmsByCargaDto;
import com.cuadratura.app.oracle.dto.WmsCinsDto;


public interface TblWmsService  extends GenericService<TblWms, Integer> {
	void saveTblWms(List<WmsCinsDto> listaTblPmmForm, int idCargaWMS) throws SQLException ;
	
	public void uploadTblWms(TblWms tblWms) throws SQLException;


	public List<Map<String, Object>> obtenerFotoWMSCuadratura(Integer idCargaWMS) throws SQLException;
	

	
	public List<FotoWmsByCargaDto> getExportFotoWmsByIdCarga(Integer idCargaWMS)throws SQLException ;

}
