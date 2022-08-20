package com.cuadratura.app.oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.oracle.dto.projection.WmsCinsDto;


@Repository
public interface WmsCinsRepository {

	 @Query(value="SELECT NRO_CARGA,CREATE_DATE,FACILITY_CODE,COMPANY_CODE,ITEM_ALTERNATE,ITEM_PART_A,ITEM_PART_B,ITEM_PART_C,ITEM_PART_D,ITEM_PART_E, "
	 		+ "ITEM_PART_F,HIERARCHY1_CODE,HIERARCHY2_CODE,HIERARCHY3_CODE,HIERARCHY4_CODE,HIERARCHY5_CODE,BATCH_NBR,PRE_PACK_CODE, "
	 		+ "PRE_PACK_RATIO,PRE_PACK_UNITS,OBLPN_TOTAL,ACTIVE_TOTAL,ACTIVE_ALLOCATED,ACTIVE_ALLOCATED_LOCKCODE,ACTIVE_AVAILABLE,ACTIVE_LOCKCODE, "
	 		+ "IBLPN_TOTAL,IBLPN_ALLOCATED,IBLPN_ALLOCATED_LOCKCODE,IBLPN_AVAILABLE,IBLPN_NOTVERIFIED,IBLPN_LOCKCODE,IBLPN_LOST,TOTAL_ALLOCATED, "
	 		+ "TOTAL_AVAILABLE,TOTAL_INVENTORY,FOUR_WALL_INVENTORY,OPEN_ORDER_QTY,LOCK_CODE_1,LOCK_CODE_QTY_1,LOCK_CODE_2,LOCK_CODE_QTY_2,LOCK_CODE_3, "
	 		+ "LOCK_CODE_QTY_3,LOCK_CODE_4,LOCK_CODE_QTY_4,LOCK_CODE_5,LOCK_CODE_QTY_5,LOCK_CODE_6,LOCK_CODE_QTY_6,LOCK_CODE_7,LOCK_CODE_QTY_7,LOCK_CODE_8, "
	 		+ "LOCK_CODE_QTY_8,LOCK_CODE_9,LOCK_CODE_QTY_9,LOCK_CODE_10,LOCK_CODE_QTY_10,DOWNLOAD_DATE1,ERROR_CODE,OBSERVACION_ERROR,FLG_TIPO "
	 		+ "FROM INTEGRACION.WMS_CINS WHERE FACILITY_CODE IN ('CD06','CD11','CD12','CD ECO')  AND hierarchy2_code ='F01' AND item_alternate= '141211'", nativeQuery=true)
		 List<WmsCinsDto> findAllWMSWmsCins2();
}
