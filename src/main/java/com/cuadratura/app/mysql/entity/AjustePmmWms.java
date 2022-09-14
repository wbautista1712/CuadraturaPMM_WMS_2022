/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuadratura.app.mysql.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "ajuste_pmm_wms", schema = "cuadratura")
public class AjustePmmWms implements Serializable {

	private static final long serialVersionUID = 1L;
   
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "idAjuste_PMM_WMS")
	    private Integer idAjustePMMWMS;
	 
	@Basic(optional = false)
    @NotNull
    @Column(name = "fechaAjuste")
    @Temporal(TemporalType.DATE)
    private Date fechaAjuste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "horaAjuste")
    private String horaAjuste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pmm")
    private int pmm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wms")
    private int wms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sugerenciaAjuste")
    private int sugerenciaAjuste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stockBolsaDiscrepancia")
    private int stockBolsaDiscrepancia;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "loteInicial")
    private String loteInicial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "loteTraspaso")
    private String loteTraspaso;
    @Column(name = "cantTraspaso")
    private Integer cantTraspaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstado_cuadratura")
    private int idEstadocuadratura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;

   
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_inventario")
    private int idTipoInventario;
}
