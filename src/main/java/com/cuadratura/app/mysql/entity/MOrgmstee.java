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
@Table(name = "m_orgmstee", schema = "cuadratura")

public class MOrgmstee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "org_lvl_child")
    private Integer orgLvlChild;
    
    @Basic(optional = false)
    @Column(name = "org_lvl_parent")
    private int orgLvlParent;
    
    @Column(name = "org_lvl_id")
    private Integer orgLvlId;
    
    @Basic(optional = false)
    @Column(name = "org_name_full")
    private String orgNameFull;
    
    @Column(name = "org_name_short")
    private String orgNameShort;
    
    @Column(name = "org_lvl_number")
    private Integer orgLvlNumber;
    
    @Column(name = "curr_code")
    private String currCode;
    
    @Column(name = "org_is_store")
    private String orgIsStore;
    

   
}
