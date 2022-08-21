package com.cuadratura.app.service;

import java.util.List;

import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.oracle.entity.pk.FapinvbaleePK;

public interface FapinvbaleeService extends GenericService<Fapinvbalee, FapinvbaleePK> {
	List<Fapinvbalee> findAllPMMFapinvbalee();
}
