/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuadratura.app.mysql.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_pmm_wms", schema = "db_cuadratura")

public class TblPmmWms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tbl_pmm_wms")
    private Integer idTblPmmWms;
    @Basic(optional = false)
    @Column(name = "fecha_match")
    @Temporal(TemporalType.DATE)
    private Date fechaMatch;
    @Basic(optional = false)
    @Column(name = "hora_match")
    private String horaMatch;
    @Basic(optional = false)
    @Column(name = "mat_prd_lvl_child")
    private int matPrdLvlChild;
    @Basic(optional = false)
    @Column(name = "cd_org_lvl_child")
    private int cdOrgLvlChild;
    @Basic(optional = false)
    @Column(name = "trans_lote")
    private String transLote;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_1_2")
    private int pmmTipoInv12;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_4")
    private int pmmTipoInv4;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_5")
    private int pmmTipoInv5;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_6")
    private int pmmTipoInv6;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_7")
    private int pmmTipoInv7;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_8")
    private int pmmTipoInv8;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_12")
    private int pmmTipoInv121;
    @Basic(optional = false)
    @Column(name = "pmm_tipo_inv_13")
    private int pmmTipoInv13;
    @Basic(optional = false)
    @Column(name = "pmm_cant_registros")
    private int pmmCantRegistros;
    @Basic(optional = false)
    @Column(name = "wms_tipo_1_2")
    private int wmsTipo12;
    @Basic(optional = false)
    @Column(name = "wms_tipo_4_PU")
    private int wmstipo4PU;
    @Basic(optional = false)
    @Column(name = "wms_tipo_5_PA_PD")
    private int wmstipo5PAPD;
    @Basic(optional = false)
    @Column(name = "wms_tipo_6_AC_QC")
    private int wmstipo6ACQC;
    @Basic(optional = false)
    @Column(name = "wms_tipo_7_CJ")
    private int wmstipo7CJ;
    @Basic(optional = false)
    @Column(name = "wms_tipo_8_BJ")
    private int wmstipo8BJ;
    @Basic(optional = false)
    @Column(name = "wms_tipo_12_EC")
    private int wmstipo12EC;
    @Basic(optional = false)
    @Column(name = "wms_tipo_13_EB")
    private int wmstipo13EB;
    @Basic(optional = false)
    @Column(name = "wms_cant_registros")
    private int wmsCantRegistros;
    @Basic(optional = false)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTblPmmWms")
    private Collection<AjustePmmWms> ajustePmmWmsCollection;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "idCarga_WMS", referencedColumnName = "idCarga_WMS")
    @ManyToOne(optional = false)
    private CargaWms idCargaWMS;
    @JoinColumn(name = "idCarga_PMM", referencedColumnName = "idCarga_PMM")
    @ManyToOne(optional = false)
    private CargaPmm idCargaPMM;

   
    
}
