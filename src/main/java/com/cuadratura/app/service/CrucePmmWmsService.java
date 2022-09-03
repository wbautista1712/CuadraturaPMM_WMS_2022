package com.cuadratura.app.service;

import java.util.Date;
import java.util.List;

import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.AjustePmmWmsDto;


public interface CrucePmmWmsService {
	public Long saveCrucePmmWms(CrucePmmWms crucePmmWms);
	public List<CrucePmmWmsDto> listarAjusteBolsaDiscrepancia(Integer idCrucePmmWms , String idCDOrgNameShort);
	
	public List<AjustePmmWmsDto> listAnalisisAjustePmmWms(int idCrucePmmWms);
}
