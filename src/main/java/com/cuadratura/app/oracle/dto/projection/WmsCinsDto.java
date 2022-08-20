package com.cuadratura.app.oracle.dto.projection;

import java.math.BigInteger;
import java.util.Date;

public interface WmsCinsDto {

	public Long getNroCarga();

	public String getCreateDate();

	public String getFacilityCode();

	public String getCompanyCode();

	public String getItemAlternate();

	public String getItemPartA();

	public String getItemPartB();

	public String getItemPartC();

	public String getItemPartD();

	public String getItemPartE();

	public String getItemPartF();

	public String getHierarchy1Code();

	public String getHierarchy2Code();

	public String getHierarchy3Code();

	public String getHierarchy4Code();

	public String getHierarchy5Code();

	public String getBatchNbr();

	public String getPrePackCode();

	public Integer getPrePackRatio();

	public Integer getPrePackUnits();

	public Integer getOblpnTotal();

	public Integer getActiveTotal();

	public Integer getActiveAllocated();

	public Integer getActiveAllocatedLockcode();

	public Integer getActiveAvailable();

	public Integer getActiveLockcode();

	public Integer getIblpnTotal();

	public Integer getIblpnAllocated();

	public Integer getIblpnAllocatedLockcode();

	public Integer getIblpnAvailable();

	public Integer getIblpnNotverified();

	public Integer getIblpnLockcode();

	public Integer getIblpnLost();

	public Integer getTotalAllocated();

	public Integer getTotalAvailable();

	public Integer getTotalInventory();

	public Integer getFourWallInventory();

	public Integer getOpenOrderQty();

	public String getLockCode1();

	public Integer getLockCodeQty1();

	public String getLockCode2();

	public Integer getLockCodeQty2();

	public String getLockCode3();

	public Integer getLockCodeQty3();

	public String getLockCode4();

	public Integer getLockCodeQty4();

	public String getLockCode5();

	public Integer getLockCodeQty5();

	public String getLockCode6();

	public Integer getLockCodeQty6();

	public String getLockCode7();

	public Integer getLockCodeQty7();

	public String getLockCode8();

	public Integer getLockCodeQty8();

	public String getLockCode9();

	public Integer getLockCodeQty9();

	public String getLockCode10();

	public Integer getLockCodeQty10();

	public Date getDownloadDate1();

	public Integer getErrorCode();

	public String getObservacionError();

	public BigInteger getFlgTipo();

}
