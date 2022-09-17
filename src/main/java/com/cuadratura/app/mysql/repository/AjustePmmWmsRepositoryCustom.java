package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.AjustePmmWms;

public interface AjustePmmWmsRepositoryCustom {

	public void saveAjustePmmWms (AjustePmmWms ajustePmmWms);
	
	public List<Object[]> getAllBolsaSdi();
	
	public void updateAjustePmmWms (Integer idAjustePMMWMS);
}
