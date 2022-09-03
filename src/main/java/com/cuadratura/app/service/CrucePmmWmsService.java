package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.oracle.dto.projection.AjustePmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;


public interface CrucePmmWmsService {
	public Long saveCrucePmmWms(CrucePmmWms crucePmmWms);
	public List<CrucePmmWmsDto> listarAjusteBolsaDiscrepancia(Integer idCrucePmmWms );
	
	public List<AjustePmmWmsDto> listAnalisisAjustePmmWms(int idCrucePmmWms);
}
