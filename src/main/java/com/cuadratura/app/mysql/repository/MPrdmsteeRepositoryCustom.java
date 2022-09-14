package com.cuadratura.app.mysql.repository;

import java.util.List;

import com.cuadratura.app.mysql.entity.MPrdmstee;

public interface MPrdmsteeRepositoryCustom {
	public   List<MPrdmstee> getMaterialLote(String nombreMaterial);
}
