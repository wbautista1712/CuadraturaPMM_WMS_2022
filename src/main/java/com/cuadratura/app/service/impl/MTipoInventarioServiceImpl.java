package com.cuadratura.app.service.impl;

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
public class MTipoInventarioServiceImpl extends GenericServiceImpl<MTipoInventario, Integer>
		implements MTipoInventarioService {
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
		MTipoInventarioDto mTipoInventarioDto = null;
		List<Object[]> list = this.mTipoInventarioRepository.listAnalisisAjustePmmWms(idCrucePmmWms, idTipoInventario);
		List<MTipoInventarioDto> listConciliaPf = new ArrayList<MTipoInventarioDto>();
		LOGGER.info("  list " + list.size());

		for (Object[] fila : list) {
			mTipoInventarioDto = new MTipoInventarioDto();

			mTipoInventarioDto.setIdCrucePmmWms(fila[0] == null ? 0 : ((Integer) fila[0]));
			mTipoInventarioDto.setIdTblPmmWms(fila[1] == null ? 0 : ((Integer) fila[1]));
			mTipoInventarioDto.setFechaMatch(fila[2] == null ? "" : ((String) fila[2]));
			mTipoInventarioDto.setHoraMatch(fila[3] == null ? "" : ((String) fila[3]));
			mTipoInventarioDto.setMatPrdLvlChild(fila[4] == null ? 0 : ((Integer) fila[4]));
			mTipoInventarioDto.setTransLote(fila[5] == null ? "" : ((String) fila[5]));
			mTipoInventarioDto.setPmmDisponible(fila[6] == null ? 0 : Integer.valueOf(fila[6].toString()));
			mTipoInventarioDto.setWmsDisponible(fila[7] == null ? 0 : Integer.valueOf(fila[7].toString()));
			mTipoInventarioDto.setDiferencia(fila[8] == null ? 0 : Integer.valueOf(fila[8].toString()));

			listConciliaPf.add(mTipoInventarioDto);

		}
		LOGGER.info(" fin listAnalisisAjustePmmWms " + listConciliaPf.size());
		return listConciliaPf;
	}

}
