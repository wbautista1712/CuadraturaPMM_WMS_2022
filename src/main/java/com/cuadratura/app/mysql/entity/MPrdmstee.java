/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuadratura.app.mysql.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "m_prdmstee", schema = "pmm")

public class MPrdmstee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "prd_lvl_child")
    private Integer prdLvlChild;
    @Basic(optional = false)
    @Column(name = "prd_lvl_parent")
    private int prdLvlParent;
    @Basic(optional = false)
    @Column(name = "prd_lvl_id")
    private int prdLvlId;
    @Basic(optional = false)
    @Column(name = "prd_name_full")
    private String prdNameFull;
}
