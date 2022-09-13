package com.cuadratura.app.oracle.repository;

import java.util.List;

public interface WmsCinsRepository {
	List<Object[]> findAllWMSWmsCins(String idCD, String fechaHora) throws Exception;
	
	public List<Object[]> getCDFotoWms();
	
	public List<Object[]> getFechaHoraFotoWms(String idCD);
}
