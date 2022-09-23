package com.cuadratura.app.oracle.dto;

public class AjustePmmWmsDto {

	private Integer idcrucePmmWms;
	private Integer idTblPmmWms;
	private String fechaMatch;
	private String horaMatch;
	private String cd;
	private String descCD;
	private Integer codMat;
	private String lote;
	private Integer pmmDisponible;
	private Integer wmsDisponible;
	private Integer difDispon;
	private Integer pmmEnPuerta;
	private Integer wmsPu;
	private Integer difEnpuerta;
	private Integer pmmPerdidoNoDisponible;
	private Integer wmsPaPd;
	private Integer difPerdido;
	private Integer pmmAcondicionaCuarentena;
	private Integer wmsAcQc;
	private Integer difAcondAcQc;
	private Integer pmmCanjeMercaderia;
	private Integer wmsCj;
	private Integer difMercaderiaCj;
	private Integer pmmMermas;
	private Integer wmsBj;
	private Integer difMermasBj;
	private Integer bolsaDiscrepancia;
	private Integer pmmEconomatoDisponible;
	private Integer wmsEc;
	private Integer difEconoDisponible;
	private Integer pmmEconomatoBloqueado;
	private Integer wmsEb;
	private Integer difEconoBloqueado;
	private Integer bolsaDiscrepanciaEcono;
	private String descMaterial;

	public String getDescCD() {
		return descCD;
	}

	public void setDescCD(String descCD) {
		this.descCD = descCD;
	}

	public String getDescMaterial() {
		return descMaterial;
	}

	public void setDescMaterial(String descMaterial) {
		this.descMaterial = descMaterial;
	}

	public Integer getIdcrucePmmWms() {
		return idcrucePmmWms;
	}

	public void setIdcrucePmmWms(Integer idcrucePmmWms) {
		this.idcrucePmmWms = idcrucePmmWms;
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

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public Integer getCodMat() {
		return codMat;
	}

	public void setCodMat(Integer codMat) {
		this.codMat = codMat;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
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

	public Integer getDifDispon() {
		return difDispon;
	}

	public void setDifDispon(Integer difDispon) {
		this.difDispon = difDispon;
	}

	public Integer getPmmEnPuerta() {
		return pmmEnPuerta;
	}

	public void setPmmEnPuerta(Integer pmmEnPuerta) {
		this.pmmEnPuerta = pmmEnPuerta;
	}

	public Integer getWmsPu() {
		return wmsPu;
	}

	public void setWmsPu(Integer wmsPu) {
		this.wmsPu = wmsPu;
	}

	public Integer getDifEnpuerta() {
		return difEnpuerta;
	}

	public void setDifEnpuerta(Integer difEnpuerta) {
		this.difEnpuerta = difEnpuerta;
	}

	public Integer getPmmPerdidoNoDisponible() {
		return pmmPerdidoNoDisponible;
	}

	public void setPmmPerdidoNoDisponible(Integer pmmPerdidoNoDisponible) {
		this.pmmPerdidoNoDisponible = pmmPerdidoNoDisponible;
	}

	public Integer getWmsPaPd() {
		return wmsPaPd;
	}

	public void setWmsPaPd(Integer wmsPaPd) {
		this.wmsPaPd = wmsPaPd;
	}

	public Integer getDifPerdido() {
		return difPerdido;
	}

	public void setDifPerdido(Integer difPerdido) {
		this.difPerdido = difPerdido;
	}

	public Integer getPmmAcondicionaCuarentena() {
		return pmmAcondicionaCuarentena;
	}

	public void setPmmAcondicionaCuarentena(Integer pmmAcondicionaCuarentena) {
		this.pmmAcondicionaCuarentena = pmmAcondicionaCuarentena;
	}

	public Integer getWmsAcQc() {
		return wmsAcQc;
	}

	public void setWmsAcQc(Integer wmsAcQc) {
		this.wmsAcQc = wmsAcQc;
	}

	public Integer getDifAcondAcQc() {
		return difAcondAcQc;
	}

	public void setDifAcondAcQc(Integer difAcondAcQc) {
		this.difAcondAcQc = difAcondAcQc;
	}

	public Integer getPmmCanjeMercaderia() {
		return pmmCanjeMercaderia;
	}

	public void setPmmCanjeMercaderia(Integer pmmCanjeMercaderia) {
		this.pmmCanjeMercaderia = pmmCanjeMercaderia;
	}

	public Integer getWmsCj() {
		return wmsCj;
	}

	public void setWmsCj(Integer wmsCj) {
		this.wmsCj = wmsCj;
	}

	public Integer getDifMercaderiaCj() {
		return difMercaderiaCj;
	}

	public void setDifMercaderiaCj(Integer difMercaderiaCj) {
		this.difMercaderiaCj = difMercaderiaCj;
	}

	public Integer getPmmMermas() {
		return pmmMermas;
	}

	public void setPmmMermas(Integer pmmMermas) {
		this.pmmMermas = pmmMermas;
	}

	public Integer getWmsBj() {
		return wmsBj;
	}

	public void setWmsBj(Integer wmsBj) {
		this.wmsBj = wmsBj;
	}

	public Integer getDifMermasBj() {
		return difMermasBj;
	}

	public void setDifMermasBj(Integer difMermasBj) {
		this.difMermasBj = difMermasBj;
	}

	public Integer getBolsaDiscrepancia() {
		return bolsaDiscrepancia;
	}

	public void setBolsaDiscrepancia(Integer bolsaDiscrepancia) {
		this.bolsaDiscrepancia = bolsaDiscrepancia;
	}

	public Integer getPmmEconomatoDisponible() {
		return pmmEconomatoDisponible;
	}

	public void setPmmEconomatoDisponible(Integer pmmEconomatoDisponible) {
		this.pmmEconomatoDisponible = pmmEconomatoDisponible;
	}

	public Integer getWmsEc() {
		return wmsEc;
	}

	public void setWmsEc(Integer wmsEc) {
		this.wmsEc = wmsEc;
	}

	public Integer getDifEconoDisponible() {
		return difEconoDisponible;
	}

	public void setDifEconoDisponible(Integer difEconoDisponible) {
		this.difEconoDisponible = difEconoDisponible;
	}

	public Integer getPmmEconomatoBloqueado() {
		return pmmEconomatoBloqueado;
	}

	public void setPmmEconomatoBloqueado(Integer pmmEconomatoBloqueado) {
		this.pmmEconomatoBloqueado = pmmEconomatoBloqueado;
	}

	public Integer getWmsEb() {
		return wmsEb;
	}

	public void setWmsEb(Integer wmsEb) {
		this.wmsEb = wmsEb;
	}

	public Integer getDifEconoBloqueado() {
		return difEconoBloqueado;
	}

	public void setDifEconoBloqueado(Integer difEconoBloqueado) {
		this.difEconoBloqueado = difEconoBloqueado;
	}

	public Integer getBolsaDiscrepanciaEcono() {
		return bolsaDiscrepanciaEcono;
	}

	public void setBolsaDiscrepanciaEcono(Integer bolsaDiscrepanciaEcono) {
		this.bolsaDiscrepanciaEcono = bolsaDiscrepanciaEcono;
	}
}
