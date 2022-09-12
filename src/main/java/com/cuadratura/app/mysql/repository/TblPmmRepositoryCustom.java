package com.cuadratura.app.mysql.repository;

import java.sql.SQLException;
import java.util.List;

import com.cuadratura.app.oracle.entity.Fapinvbalee;

public interface TblPmmRepositoryCustom {

	public void saveTblPmm(List<Fapinvbalee> listaTblPmmForm, int numeroLotes, int idCargaPMM) throws SQLException;
}
