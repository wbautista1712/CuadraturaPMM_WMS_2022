package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.oracle.dto.SpBolsaSdiDto;

public interface AjustePmmWmsService {
	public void saveAjustePmmWms(AjustePmmWms ajustePmmWms);
	public List<SpBolsaSdiDto> getAllBolsaSdi();
	
	public void updateAjustePmmWms(Integer idAjustePMMWMS);
}
