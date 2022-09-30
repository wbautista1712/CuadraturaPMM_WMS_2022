package com.cuadratura.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cuadratura.app.mysql.entity.TblPmm;
import com.cuadratura.app.oracle.dto.FormatoExcelForm;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.util.Message;

public interface TblPmmService extends GenericService<TblPmm, Integer> {
	void saveTblPmm(List<Fapinvbalee> listaTblPmmForm, int idCargaPMM) throws SQLException;

	public Message saveExcelTblPmm(FormatoExcelForm obj) throws SQLException;



	public List<Map<String, Object>> obtenerFotoPMMCuadratura(int idCargaPMM) throws SQLException;


}
