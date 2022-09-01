package com.cuadratura.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cuadratura.app.mysql.entity.CrucePmmWms;
import com.cuadratura.app.mysql.repository.CrucePmmWmsRepository;
import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;
import com.cuadratura.app.service.CrucePmmWmsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CrucePmmWmsServiceImpl extends GenericServiceImpl<CrucePmmWms, Integer> implements CrucePmmWmsService {

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsServiceImpl.class);

	@Autowired
	private CrucePmmWmsRepository crucePmmWmsRepository;

	@Override
	public CrudRepository<CrucePmmWms, Integer> getDao() {
		return crucePmmWmsRepository;
	}

	public Long saveCrucePmmWms(CrucePmmWms cargaPmm) {
		LOGGER.info("insert saveCrucePmmWms");
		return crucePmmWmsRepository.saveCrucePmmWms(cargaPmm);
	}

	@Override
	public List<CrucePmmWmsDto> listarAjusteBolsaDiscrepancia(Integer idCrucePmmWms, String idCDOrgNameShort) {
		// TODO Auto-generated method stub
		LOGGER.info("idCrucePmmWms: " + idCrucePmmWms);

		LOGGER.info("...:::listarAjusteBolsaDiscrepancia:::...");
		Map<String, Object> mapList = crucePmmWmsRepository.listarAjusteBolsaDiscrepancia(idCrucePmmWms,
				idCDOrgNameShort);

		List<CrucePmmWmsDto> list = new ArrayList<CrucePmmWmsDto>();
		CrucePmmWmsDto libro = null;

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		LOGGER.info("\nPretty toString ==> " + mapList.toString());
		LOGGER.info("\nPretty toString ==> " + mapList.get("#result-set-1"));
		String prettyJson = prettyGson.toJson(mapList.get("#result-set-1"));

		LOGGER.info("\nPretty JSONObject ==> " + prettyJson);
		LOGGER.info("mapList mapList ==> " + mapList.size());

		try {
			JSONArray jsonarr = new JSONArray(prettyJson);// prettyJson.getJSONArray("#result-set-1");
			LOGGER.info("tamaño ==> " + jsonarr.length());
			for (int i = 0; i < jsonarr.length(); i++) {
				libro = new CrucePmmWmsDto();

				LOGGER.info("id  de Dato dd==> " + jsonarr.getJSONObject(i));
				LOGGER.info("id  de Dato Canal_solicitud==> " + jsonarr.getJSONObject(i).getInt("idCruce_pmm_wms"));
				LOGGER.info(
						"id  de Dato Estado_solicitud==> " + jsonarr.getJSONObject(i).getInt("idCruce_pmm_wms"));
				if (jsonarr.getJSONObject(i).has("idCruce_pmm_wms")) {
					LOGGER.info("\nTipo de Dato ==> " + jsonarr.getJSONObject(i).getInt("idCruce_pmm_wms"));
					libro.setIdcrucePmmWms(jsonarr.getJSONObject(i).getInt("idCruce_pmm_wms"));
				}

				if (jsonarr.getJSONObject(i).has("id_tbl_pmm_wms")) {
					libro.setIdTblPmmWms(jsonarr.getJSONObject(i).getInt("id_tbl_pmm_wms"));

				}

				if (jsonarr.getJSONObject(i).has("CD")) {
					libro.setCd(jsonarr.getJSONObject(i).getString("CD").toString());
				}

				if (jsonarr.getJSONObject(i).has("COD_MAT")) {
					libro.setCodMat(jsonarr.getJSONObject(i).getInt("COD_MAT"));
				}

				if (jsonarr.getJSONObject(i).has("LOTE")) {
					libro.setLote(jsonarr.getJSONObject(i).getString("LOTE").toString());
				}

				if (jsonarr.getJSONObject(i).has("CRUCE_HOMOLOGADO")) {
					libro.setCruceHomologado(jsonarr.getJSONObject(i).getString("CRUCE_HOMOLOGADO").toString());
				}

				if (jsonarr.getJSONObject(i).has("PMM")) {
					libro.setPmm(jsonarr.getJSONObject(i).getInt("PMM"));
				}
				if (jsonarr.getJSONObject(i).has("WMS")) {
					libro.setWms(jsonarr.getJSONObject(i).getInt("WMS"));
				}
				if (jsonarr.getJSONObject(i).has("SUGERENCIA_AJUSTE")) {
					libro.setSugerenciaAjuste(jsonarr.getJSONObject(i).getInt("SUGERENCIA_AJUSTE"));
				}
				if (jsonarr.getJSONObject(i).has("SCTOCK_BOLSA_DISCREPANCIA")) {
					libro.setSctockBolsaDiscrepancia(
							jsonarr.getJSONObject(i).getInt("SCTOCK_BOLSA_DISCREPANCIA"));
				}
				LOGGER.info("---getCruceHomologado------- " + libro.getCruceHomologado());

				list.add(libro);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;

	}
}
