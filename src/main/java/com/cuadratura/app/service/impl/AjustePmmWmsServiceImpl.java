package com.cuadratura.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.AjustePmmWms;
import com.cuadratura.app.mysql.repository.AjustePmmWmsRepository;
import com.cuadratura.app.oracle.dto.SpBolsaSdiDto;
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
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

		List<Object[]> lista = ajustePmmWmsRepository.getAllBolsaSdi();
		LOGGER.info("get lista getAllBolsaSdi " + lista.size());
		SpBolsaSdiDto fotoWms;
		for (Object[] filaObj : lista) {
			fotoWms = new SpBolsaSdiDto();

			fotoWms.setTransUser(filaObj[0] == null ? "" : filaObj[0].toString());

			if (filaObj[1] != null) {
				DateTime jodaDateTransBatch = DateTime.parse(filaObj[1].toString(), formatter);
				java.util.Date dateTransBatchDate = jodaDateTransBatch.toDate();
				fotoWms.setTransBatchDate(dateTransBatchDate);
			} else {
				fotoWms.setTransBatchDate(null);
			}

			fotoWms.setTransSource(filaObj[2] == null ? "" : filaObj[2].toString());
			fotoWms.setTransAudited(filaObj[3] == null ? "" : filaObj[3].toString());
			//fotoWms.setTransSequence(filaObj[4] == null ? "" : filaObj[4].toString());
			fotoWms.setInvMrptCode(filaObj[4] == null ? "" : filaObj[4].toString());
			fotoWms.setTransQty(filaObj[5] == null ? 0 : Integer.parseInt(filaObj[5].toString()));
			fotoWms.setTransTypeCode(filaObj[6] == null ? "" : filaObj[6].toString());
			fotoWms.setTransTrnCode(filaObj[7] == null ? "" : filaObj[7].toString());
			fotoWms.setInvDrptCode(filaObj[8] == null ? "" : filaObj[8].toString());
			if (filaObj[9] != null) {
				DateTime jodaDateTrans = DateTime.parse(filaObj[9].toString(), formatter);
				java.util.Date dateTransDate = jodaDateTrans.toDate();

				fotoWms.setTransDate(dateTransDate);
			} else {
				fotoWms.setTransDate(null);
			}

			fotoWms.setTransCurrCode(filaObj[10] == null ? "" : filaObj[10].toString());
			fotoWms.setTransOrgLvlNumber(filaObj[11] == null ? "" : filaObj[11].toString());
			fotoWms.setTransPrdLvlNumber(filaObj[12] == null ? "" : filaObj[12].toString());
			fotoWms.setProcSource(filaObj[13] == null ? "" : filaObj[13].toString());
			fotoWms.setTransInners(filaObj[14] == null ? "" : filaObj[14].toString());
			fotoWms.setTransLote(filaObj[15] == null ? "" : filaObj[15].toString());
			LOGGER.info(filaObj[16]);
			fotoWms.setIdAjustePMMWMS(filaObj[16] == null ? 0 : Integer.valueOf(filaObj[16].toString()));
			fotoWms.setPrdLvlChild(filaObj[17] == null ? 0 : Integer.valueOf(filaObj[17].toString()));
			listaClasificadores.add(fotoWms);
		}
		return listaClasificadores;
	}

	public void updateAjustePmmWms(Integer idAjustePMMWMS) {
		ajustePmmWmsRepository.updateAjustePmmWms(idAjustePMMWMS);
	}

}
