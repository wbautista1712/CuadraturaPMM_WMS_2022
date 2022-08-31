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
		for (Object[] fila : list) {
			wmsCinsDto = new MTipoInventarioDto();
			

			LOGGER.info(" fin fffff ");
			
			wmsCinsDto.setIdCrucePmmWms(((BigDecimal) fila[0] == null ? 0 : ((BigDecimal) fila[0])).intValue());
			wmsCinsDto.setIdTblPmmWms(((BigDecimal) fila[1] == null ? 0 : ((BigDecimal) fila[1])).intValue());
			wmsCinsDto.setFechaMatch(((BigDecimal) fila[2] == null ? 0 : ((BigDecimal) fila[2])).intValue());
			wmsCinsDto.setHoraMatch(((BigDecimal) fila[3] == null ? 0 : ((BigDecimal) fila[3])).intValue());
			wmsCinsDto.setMatPrdLvlChild(((BigDecimal) fila[4] == null ? 0 : ((BigDecimal) fila[4])).intValue());
			wmsCinsDto.setTransLote(((BigDecimal) fila[5] == null ? 0 : ((BigDecimal) fila[5])).intValue());
			wmsCinsDto.setPmmDisponible(((BigDecimal) fila[6] == null ? 0 : ((BigDecimal) fila[6])).intValue());
			wmsCinsDto.setWmsDisponible(((BigDecimal) fila[7] == null ? 0 : ((BigDecimal) fila[7])).intValue());
			wmsCinsDto.setDiferencia(((BigDecimal) fila[8] == null ? 0 : ((BigDecimal) fila[8])).intValue());
			listConciliaPf.add(wmsCinsDto);
			
		}
		LOGGER.info(" fin listAnalisisAjustePmmWms "+listConciliaPf.size());
		return listConciliaPf;
	}
	
	
}
