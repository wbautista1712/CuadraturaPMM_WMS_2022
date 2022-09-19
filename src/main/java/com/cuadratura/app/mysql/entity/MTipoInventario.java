package com.cuadratura.app.mysql.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "m_tipo_inventario", schema = "pmm")
public class MTipoInventario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_tipo_inventario")
	private Integer idTipoInventario;

	@Basic(optional = false)
	@Column(name = "tipoInventario")
	private String tipoInventario;

	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;

	@Basic(optional = false)
	@Column(name = "observaciones")
	private String observaciones;

	@Basic(optional = false)
	@Column(name = "estado")
	private boolean estado;

}
