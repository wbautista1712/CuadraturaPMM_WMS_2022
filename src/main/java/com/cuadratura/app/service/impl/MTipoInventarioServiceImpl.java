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

	public List<MTipoInventario> getTipoInventario() {
		return mTipoInventarioRepository.getTipoInventario();
	}

	public List<MTipoInventario> getTipoInventarioLote(String idTipoInventario) {

		List<Object[]> listObject = mTipoInventarioRepository.getTipoInventarioLote(idTipoInventario);

		MTipoInventario comboDTO;
		List<MTipoInventario> listReturn = new ArrayList<MTipoInventario>();
		for (Object[] row : listObject) {
			LOGGER.info("oooooo");
			comboDTO = new MTipoInventario();
			comboDTO.setIdTipoInventario((Integer) row[0]);
			comboDTO.setNombre((String) row[1]);
			listReturn.add(comboDTO);
		}
		return listReturn;
	}
}
