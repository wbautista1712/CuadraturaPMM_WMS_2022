package com.cuadratura.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.mysql.repository.AjustePmmWmsRepository;
import com.cuadratura.app.oracle.dto.projection.SpBolsaSdiDto;
import com.cuadratura.app.service.AjustePmmWmsService;

@Service
public class AjustePmmWmsServiceImpl extends GenericServiceImpl<AjustePmmWms, Integer> implements AjustePmmWmsService {

	private static final Logger LOGGER = LogManager.getLogger(AjustePmmWmsServiceImpl.class);

	@Autowired
	private AjustePmmWmsRepository ajustePmmWmsRepository;

	@Override
	public CrudRepository<AjustePmmWms, Integer> getDao() {
		return ajustePmmWmsRepository;
	}

	public void saveAjustePmmWms(AjustePmmWms ajustePmmWms) {
		LOGGER.info(ajustePmmWms.getHoraAjuste());
		LOGGER.info(ajustePmmWms.getIdTipoInventario());
		LOGGER.info(ajustePmmWms.getIdTblPmmWms());
		ajustePmmWmsRepository.saveAjustePmmWms(ajustePmmWms);
	}

	public List<SpBolsaSdiDto> getAllBolsaSdi() {
		List<SpBolsaSdiDto> listaClasificadores = new ArrayList<SpBolsaSdiDto>();

		List<Object[]> lista = ajustePmmWmsRepository.getAllBolsaSdi();
		LOGGER.info("get lista " + lista.size());
		SpBolsaSdiDto fotoWms;
		for (Object[] filaObj : lista) {
			fotoWms = new SpBolsaSdiDto();

			fotoWms.setTransUser(filaObj[0] == null ? "" : filaObj[0].toString());
			fotoWms.setTransBatchDate(filaObj[1] == null ? "" : filaObj[1].toString());
			fotoWms.setTransSource(filaObj[2] == null ? "" : filaObj[2].toString());
			fotoWms.setTransAudited(filaObj[3] == null ? "" : filaObj[3].toString());
			fotoWms.setTransSequence(filaObj[4] == null ? "" : filaObj[4].toString());
			fotoWms.setInvMrptCode(filaObj[5] == null ? "" : filaObj[5].toString());
			fotoWms.setTransQty(filaObj[6] == null ? "" : filaObj[6].toString());
			fotoWms.setTransTypeCode(filaObj[7] == null ? "" : filaObj[7].toString());
			fotoWms.setTransTrnCode(filaObj[8] == null ? "" : filaObj[8].toString());
			fotoWms.setInvDrptCode(filaObj[9] == null ? "" : filaObj[9].toString());
			fotoWms.setTransDate(filaObj[10] == null ? "" : filaObj[10].toString());
			fotoWms.setTransCurrCode(filaObj[11] == null ? "" : filaObj[11].toString());
			fotoWms.setTransOrgLvlNumber(filaObj[12] == null ? "" : filaObj[12].toString());
			fotoWms.setTransPrdLvlNumber(filaObj[13] == null ? "" : filaObj[13].toString());
			fotoWms.setProcSource(filaObj[14] == null ? "" : filaObj[14].toString());
			fotoWms.setTransInners(filaObj[15] == null ? "" : filaObj[15].toString());
			fotoWms.setTransLote(filaObj[16] == null ? "" : filaObj[16].toString());

			listaClasificadores.add(fotoWms);
		}
		return listaClasificadores;
	}

}
