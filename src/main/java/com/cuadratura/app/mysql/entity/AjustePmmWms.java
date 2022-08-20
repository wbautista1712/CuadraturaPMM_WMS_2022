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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ARANGO
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "ajuste_pmm_wms", schema = "db_cuadratura")

public class AjustePmmWms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAjuste_PMM_WMS")
    private Integer idAjustePMMWMS;
    @Basic(optional = false)
    @Column(name = "fechaAjuste")
    @Temporal(TemporalType.DATE)
    private Date fechaAjuste;
    @Basic(optional = false)
    @Column(name = "horaAjuste")
    private String horaAjuste;
    @Basic(optional = false)
    @Column(name = "loteInicial")
    private String loteInicial;
    @Basic(optional = false)
    @Column(name = "loteTraspaso")
    private String loteTraspaso;
    @Column(name = "cantTraspaso")
    private Integer cantTraspaso;
    @Basic(optional = false)
    @Column(name = "idEstado_cuadratura")
    private int idEstadocuadratura;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_m_TipoLogica", referencedColumnName = "id_m_TipoLogica")
    @ManyToOne(optional = false)
    private MTipoLogica idmTipoLogica;
    @JoinColumn(name = "id_tbl_pmm_wms", referencedColumnName = "id_tbl_pmm_wms")
    @ManyToOne(optional = false)
    private TblPmmWms idTblPmmWms;

   
    
}
