package com.cuadratura.app.oracle.repository;

import java.util.List;

public interface WmsCinsRepository {
	List<Object[]> findAllWMSWmsCins() throws Exception;
}
