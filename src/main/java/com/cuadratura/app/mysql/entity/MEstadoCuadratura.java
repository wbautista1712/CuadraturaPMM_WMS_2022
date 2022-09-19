/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuadratura.app.mysql.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "m_estado_cuadratura", schema = "pmm")

public class MEstadoCuadratura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_m_estadoCuadratura")
    private Integer idmestadoCuadratura;
    @Basic(optional = false)
    @Column(name = "nombreEC")
    private String nombreEC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmestadoCuadratura")
    private Collection<CargaPmm> cargaPmmCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmestadoCuadratura")
    private Collection<CargaWms> cargaWmsCollection;

   
}
