package com.cuadratura.app.mysql.repository;

import java.sql.SQLException;
import java.util.Map;

import com.cuadratura.app.mysql.entity.CrucePmmWms;


public interface CrucePmmWmsCustom {
	public Long saveCrucePmmWms(CrucePmmWms crucePmmWms);
	
	public Map<String, Object> listarAjusteBolsaDiscrepancia(Integer idCrucePmmWms, String idCDOrgNameShort, Integer start, Integer end);
	
	public  Map<String, Object>  listAnalisisAjustePmmWms(int idCrucePmmWms, String idCDOrgNameShort, Integer start, Integer end);
	
	public void spActualizarEstadoWMSPMMTotal(int pidCrucePmmWms , int idEstado )  throws SQLException;

}
