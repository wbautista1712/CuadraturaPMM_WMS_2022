package com.cuadratura.app.oracle.dto.projection;

import java.math.BigDecimal;

public class MTipoInventarioDto {

	private Integer idCrucePmmWms;
	
	private Integer idTblPmmWms;
	private String fechaMatch;
	private String horaMatch;
	private Integer matPrdLvlChild;
	private String transLote;
	private BigDecimal pmmDisponible;
	private BigDecimal wmsDisponible;
	private BigDecimal diferencia;
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
	public BigDecimal getPmmDisponible() {
		return pmmDisponible;
	}
	public void setPmmDisponible(BigDecimal pmmDisponible) {
		this.pmmDisponible = pmmDisponible;
	}
	public BigDecimal getWmsDisponible() {
		return wmsDisponible;
	}
	public void setWmsDisponible(BigDecimal wmsDisponible) {
		this.wmsDisponible = wmsDisponible;
	}
	public BigDecimal getDiferencia() {
		return diferencia;
	}
	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}
	
	
	
	
}
