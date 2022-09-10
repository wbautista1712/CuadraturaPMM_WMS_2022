package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.oracle.dto.projection.ResultadoPmmWmsDto;

public interface TblPmmWmsRepositoryCustom {
	public void saveCrucePmmWms(int idCargaPMM, int idCargaWMS, String idCD, int idUsuario, int idCrucePmmWms);
	
	List<Object[]> getAllResultadoPmmWms( String idCD_org_name_short, Integer start, Integer end);
	
	public Integer countResultadoPmmWms(String idCD_org_name_short) throws Exception;
}
