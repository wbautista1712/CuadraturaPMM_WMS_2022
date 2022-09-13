package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.oracle.dto.projection.WmsCinsCDDto;
import com.cuadratura.app.oracle.dto.projection.WmsCinsDto;

public interface WmsCinsService {

	List<WmsCinsDto> findAllWMSWmsCins(String idCD, String fechaHora) throws Exception;
	
	public List<WmsCinsCDDto> getCDFotoWms();
	
	public List<WmsCinsCDDto> getFechaHoraFotoWms(String idCD);
}
