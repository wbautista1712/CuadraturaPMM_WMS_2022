package com.cuadratura.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.MTipoInventario;
import com.cuadratura.app.mysql.repository.MTipoInventarioRepository;
import com.cuadratura.app.oracle.dto.projection.MTipoInventarioDto;
import com.cuadratura.app.service.MTipoInventarioService;

@Service
public class MTipoInventarioServiceImpl  extends GenericServiceImpl<MTipoInventario, Integer> implements MTipoInventarioService{
	private static final Logger LOGGER = LogManager.getLogger(MTipoInventarioServiceImpl.class);

	@Autowired
	private MTipoInventarioRepository mTipoInventarioRepository;

	@Override
	public CrudRepository<MTipoInventario, Integer> getDao() {
		// TODO Auto-generated method stub
		LOGGER.info("mTipoInventarioRepository");
		return mTipoInventarioRepository;
	}

	@Override
	public List<MTipoInventarioDto> listAnalisisAjustePmmWms(int idCrucePmmWms, int idTipoInventario) {
		// TODO Auto-generated method stub
		MTipoInventarioDto wmsCinsDto = null;
		List<Object[]> list =this.mTipoInventarioRepository.listAnalisisAjustePmmWms(idCrucePmmWms, idTipoInventario);
		List<MTipoInventarioDto> listConciliaPf = new ArrayList<MTipoInventarioDto>();
		LOGGER.info(" fin fffff list "+list.size());
		for (Object[] fila : list) {
			wmsCinsDto = new MTipoInventarioDto();
			
			/*
			SELECT C.idCruce_pmm_wms, TBL.id_tbl_pmm_wms,date_format(C.fechaMatch, '%d/%m/%Y') AS fechaMatch, C.horaMatch,
			TBL.mat_prd_lvl_child, TBL.trans_lote, ifnull(TBL.pmm_disponible,0) as pmm_disponible, ifnull(TBL.wms_disponible,0) as wms_disponible, (ifnull(TBL.pmm_disponible,0)-ifnull(TBL.wms_disponible,0)) AS Diferencia
			FROM cuadratura.cruce_pmm_wms C
			INNER JOIN cuadratura.tbl_pmm_wms TBL ON C.idCruce_pmm_wms=TBL.idCruce_pmm_wms
			WHERE C.idCruce_pmm_wms=idCruce_pmm_wms;
			*/
			

			LOGGER.info(" fin fffff 1 "+fila[0]);
			
			wmsCinsDto.setIdCrucePmmWms((fila[0] == null ? 0 : ((Integer) fila[0])));

			LOGGER.info(" fin fffff 2 "+fila[1]);
			
			wmsCinsDto.setIdTblPmmWms(((BigDecimal) fila[1] == null ? 0 : ((BigDecimal) fila[1])).intValue());

			LOGGER.info(" fin fffff 3");
			
			wmsCinsDto.setFechaMatch((fila[2] == null ?"" : ((String) fila[2])));

			LOGGER.info(" fin fffff4 ");
			
			wmsCinsDto.setHoraMatch((fila[3] == null ?"" : ((String) fila[3])));
			wmsCinsDto.setMatPrdLvlChild(((BigDecimal) fila[4] == null ? 0 : ((BigDecimal) fila[4])).intValue());
			wmsCinsDto.setTransLote((fila[5] == null ?"" : ((String) fila[5])));
			wmsCinsDto.setPmmDisponible(((BigDecimal) fila[6] == null ?  BigDecimal.ZERO  : ((BigDecimal) fila[6])));
			wmsCinsDto.setWmsDisponible(((BigDecimal) fila[7] == null ?  BigDecimal.ZERO  : ((BigDecimal) fila[7])));
			wmsCinsDto.setDiferencia(((BigDecimal) fila[8] == null ?  BigDecimal.ZERO  : ((BigDecimal) fila[8])));
			listConciliaPf.add(wmsCinsDto);
			
		}
		LOGGER.info(" fin listAnalisisAjustePmmWms "+listConciliaPf.size());
		return listConciliaPf;
	}
	
	
}
