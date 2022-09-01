package com.cuadratura.app.mysql.repository;

import java.util.Map;

import com.cuadratura.app.mysql.entity.CrucePmmWms;


public interface CrucePmmWmsCustom {
	public Long saveCrucePmmWms(CrucePmmWms crucePmmWms);
	
	public Map<String, Object> listarAjusteBolsaDiscrepancia(Integer idCrucePmmWms , String idCDOrgNameShort);
}
