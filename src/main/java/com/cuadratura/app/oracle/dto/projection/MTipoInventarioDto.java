package com.cuadratura.app.oracle.dto.projection;

public class MTipoInventarioDto {

	private Integer idCrucePmmWms;
	
	private Integer idTblPmmWms;
	private String fechaMatch;
	private String horaMatch;
	private Integer matPrdLvlChild;
	private String transLote;
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
	public String getFechaMatch() {
		return fechaMatch;
	}
	public void setFechaMatch(String fechaMatch) {
		this.fechaMatch = fechaMatch;
	}
	public String getHoraMatch() {
		return horaMatch;
	}
	public void setHoraMatch(String horaMatch) {
		this.horaMatch = horaMatch;
	}
	public Integer getMatPrdLvlChild() {
		return matPrdLvlChild;
	}
	public void setMatPrdLvlChild(Integer matPrdLvlChild) {
		this.matPrdLvlChild = matPrdLvlChild;
	}
	public String getTransLote() {
		return transLote;
	}
	public void setTransLote(String transLote) {
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
