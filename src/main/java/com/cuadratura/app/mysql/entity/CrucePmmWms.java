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
@Table(name = "cruce_pmm_wms", schema = "cuadratura")
public class CrucePmmWms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCruce_pmm_wms")
    private Integer idCrucepmmwms;
    @Basic(optional = false)
    @Column(name = "fechaMatch")
    @Temporal(TemporalType.DATE)
    private Date fechaMatch;
    @Basic(optional = false)
    @Column(name = "horaMatch")
    private String horaMatch;
    @Basic(optional = false)
    @Column(name = "idEstadoCuadratura")
    private int idEstadoCuadratura;
    
    @Column(name = "idCarga_WMS")
    private Integer idCargaWMS;
    
    @Column(name = "idCarga_PMM")
    private Integer idCargaPMM;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCrucepmmwms")
    private Collection<TblPmmWms> tblPmmWmsCollection;

   
    
}
