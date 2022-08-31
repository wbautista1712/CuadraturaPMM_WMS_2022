package com.cuadratura.app.oracle.dto.projection;

public class MTipoInventarioDto {

	private Integer idCrucePmmWms;
	private Integer idTblPmmWms;
	private Integer fechaMatch;
	private Integer horaMatch;
	private Integer matPrdLvlChild;
	private Integer transLote;
	private Integer pmmDisponible;
	private Integer wmsDisponible;
	private Integer diferencia;

	public Integer getIdCrucePmmWms() {
		return idCrucePmmWms;
	}

	public void setIdCrucePmmWms(Integer idCrucePmmWms) {
		this.idCrucePmmWms = idCrucePmmWms;
	}

	public Integer getIdTblPmmWms() {
		return idTblPmmWms;
	}

	public void setIdTblPmmWms(Integer idTblPmmWms) {
		this.idTblPmmWms = idTblPmmWms;
	}

	public Integer getFechaMatch() {
		return fechaMatch;
	}

	public void setFechaMatch(Integer fechaMatch) {
		this.fechaMatch = fechaMatch;
	}

	public Integer getHoraMatch() {
		return horaMatch;
	}

	public void setHoraMatch(Integer horaMatch) {
		this.horaMatch = horaMatch;
	}

	public Integer getMatPrdLvlChild() {
		return matPrdLvlChild;
	}

	public void setMatPrdLvlChild(Integer matPrdLvlChild) {
		this.matPrdLvlChild = matPrdLvlChild;
	}

	public Integer getTransLote() {
		return transLote;
	}

	public void setTransLote(Integer transLote) {
		this.transLote = transLote;
	}

	public Integer getPmmDisponible() {
		return pmmDisponible;
	}

	public void setPmmDisponible(Integer pmmDisponible) {
		this.pmmDisponible = pmmDisponible;
	}

	public Integer getWmsDisponible() {
		return wmsDisponible;
	}

	public void setWmsDisponible(Integer wmsDisponible) {
		this.wmsDisponible = wmsDisponible;
	}

	public Integer getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(Integer diferencia) {
		this.diferencia = diferencia;
	}

}
