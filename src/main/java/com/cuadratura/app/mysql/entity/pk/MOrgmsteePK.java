package com.cuadratura.app.mysql.entity.pk;



import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ARANGO
 */
@Embeddable
public class MOrgmsteePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "org_lvl_child")
    private int orgLvlChild;
    @Basic(optional = false)
    @Column(name = "org_name_short")
    private String orgNameShort;
}
