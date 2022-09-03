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
import com.cuadratura.app.mysql.repository.CargaWmsRepository;
import com.cuadratura.app.mysql.repository.CrucePmmWmsRepository;
import com.cuadratura.app.oracle.dto.projection.AjustePmmWmsDto;
import com.cuadratura.app.oracle.dto.projection.CrucePmmWmsDto;

import com.cuadratura.app.service.CrucePmmWmsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CrucePmmWmsServiceImpl extends GenericServiceImpl<CrucePmmWms, Integer> implements CrucePmmWmsService {

	private static final Logger LOGGER = LogManager.getLogger(CrucePmmWmsServiceImpl.class);

	@Autowired
	private CrucePmmWmsRepository crucePmmWmsRepository;

	@Autowired
	private CargaWmsRepository cargaWmsRepository;

	@Override
	public CrudRepository<CrucePmmWms, Integer> getDao() {
		return crucePmmWmsRepository;
	}

	public Long saveCrucePmmWms(CrucePmmWms cargaPmm) {
		LOGGER.info("insert saveCrucePmmWms");
		return crucePmmWmsRepository.saveCrucePmmWms(cargaPmm);
	}

	@Override
	public List<CrucePmmWmsDto> listarAjusteBolsaDiscrepancia(Integer idCrucePmmWms) {
		// TODO Auto-generated method stub
		LOGGER.info("idCrucePmmWms: " + idCrucePmmWms);
	    String idCDOrgNameShort = cargaWmsRepository.getCDCrucePmmWms(idCrucePmmWms);
		LOGGER.info("...:::listarAjusteBolsaDiscrepancia:::..."+idCDOrgNameShort);
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
				LOGGER.info("id  de Dato Estado_solicitud==> " + jsonarr.getJSONObject(i).getInt("idCruce_pmm_wms"));
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
				
				if (jsonarr.getJSONObject(i).has("desc_Material")) {
					libro.setDescMaterial(jsonarr.getJSONObject(i).getString("desc_Material"));
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
					libro.setSctockBolsaDiscrepancia(jsonarr.getJSONObject(i).getInt("SCTOCK_BOLSA_DISCREPANCIA"));
				}
				LOGGER.info("---getCruceHomologado------- " + libro.getCruceHomologado());

				list.add(libro);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public List<AjustePmmWmsDto> listAnalisisAjustePmmWms(int idCrucePmmWms) {
		// TODO Auto-generated method stub
		AjustePmmWmsDto ajustePmmWmsDto = null;

		String cd = cargaWmsRepository.getCDCrucePmmWms(idCrucePmmWms);

		Map<String, Object> mapList = this.crucePmmWmsRepository.listAnalisisAjustePmmWms(idCrucePmmWms, cd);

		List<AjustePmmWmsDto> list = new ArrayList<AjustePmmWmsDto>();

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

				ajustePmmWmsDto = new AjustePmmWmsDto();

				if (jsonarr.getJSONObject(i).has("idcruce_pmm_wms")) {
					ajustePmmWmsDto.setIdcrucePmmWms(jsonarr.getJSONObject(i).getInt("idcruce_pmm_wms"));
				}

				if (jsonarr.getJSONObject(i).has("id_tbl_pmm_wms")) {
					ajustePmmWmsDto.setIdTblPmmWms(jsonarr.getJSONObject(i).getInt("id_tbl_pmm_wms"));
				}

				if (jsonarr.getJSONObject(i).has("cd")) {
					ajustePmmWmsDto.setCd(jsonarr.getJSONObject(i).getString("cd").toString());
				}

				if (jsonarr.getJSONObject(i).has("cod_mat")) {
					ajustePmmWmsDto.setCodMat(jsonarr.getJSONObject(i).getInt("cod_mat"));
				}

				if (jsonarr.getJSONObject(i).has("lote")) {
					ajustePmmWmsDto.setLote(jsonarr.getJSONObject(i).getString("lote").toString());
				}

				if (jsonarr.getJSONObject(i).has("pmm_disponible")) {
					ajustePmmWmsDto.setPmmDisponible(jsonarr.getJSONObject(i).getInt("pmm_disponible"));
				}

				if (jsonarr.getJSONObject(i).has("wms_disponible")) {
					ajustePmmWmsDto.setWmsDisponible(jsonarr.getJSONObject(i).getInt("wms_disponible"));
				}

				if (jsonarr.getJSONObject(i).has("dif_dispon")) {
					ajustePmmWmsDto.setDifDispon(jsonarr.getJSONObject(i).getInt("dif_dispon"));
				}

				if (jsonarr.getJSONObject(i).has("pmm_en_puerta")) {
					ajustePmmWmsDto.setPmmEnPuerta(jsonarr.getJSONObject(i).getInt("pmm_en_puerta"));
				}

				if (jsonarr.getJSONObject(i).has("wms_pu")) {
					ajustePmmWmsDto.setWmsPu(jsonarr.getJSONObject(i).getInt("wms_pu"));
				}

				if (jsonarr.getJSONObject(i).has("dif_enpuerta")) {
					ajustePmmWmsDto.setDifEnpuerta(jsonarr.getJSONObject(i).getInt("dif_enpuerta"));
				}

				if (jsonarr.getJSONObject(i).has("pmm_perdido_no_disponible")) {
					ajustePmmWmsDto.setPmmPerdidoNoDisponible(
							jsonarr.getJSONObject(i).getInt("pmm_perdido_no_disponible"));
				}

				if (jsonarr.getJSONObject(i).has("wms_pa_pd")) {
					ajustePmmWmsDto.setWmsPaPd(jsonarr.getJSONObject(i).getInt("wms_pa_pd"));
				}
				if (jsonarr.getJSONObject(i).has("dif_perdido")) {
					ajustePmmWmsDto.setDifPerdido(jsonarr.getJSONObject(i).getInt("dif_perdido"));
				}

				if (jsonarr.getJSONObject(i).has("pmm_acondiciona_cuarentena")) {
					ajustePmmWmsDto.setPmmAcondicionaCuarentena(
							jsonarr.getJSONObject(i).getInt("pmm_acondiciona_cuarentena"));
				}
				if (jsonarr.getJSONObject(i).has("wms_ac_qc")) {
					ajustePmmWmsDto.setWmsAcQc(jsonarr.getJSONObject(i).getInt("wms_ac_qc"));
				}

				if (jsonarr.getJSONObject(i).has("dif_acond_ac_qc")) {
					ajustePmmWmsDto.setDifAcondAcQc(jsonarr.getJSONObject(i).getInt("dif_acond_ac_qc"));
				}
				if (jsonarr.getJSONObject(i).has("pmm_canje_mercaderia")) {
					ajustePmmWmsDto.setPmmCanjeMercaderia(
							jsonarr.getJSONObject(i).getInt("pmm_canje_mercaderia"));
				}
				if (jsonarr.getJSONObject(i).has("wms_cj")) {
					ajustePmmWmsDto.setWmsCj(jsonarr.getJSONObject(i).getInt("wms_cj"));
				}

				if (jsonarr.getJSONObject(i).has("dif_mercaderia_cj")) {
					ajustePmmWmsDto
							.setDifMercaderiaCj(jsonarr.getJSONObject(i).getString("dif_mercaderia_cj").toString());
				}

				if (jsonarr.getJSONObject(i).has("pmm_mermas")) {
					ajustePmmWmsDto.setPmmMermas(jsonarr.getJSONObject(i).getInt("pmm_mermas"));
				}

				if (jsonarr.getJSONObject(i).has("wms_bj")) {
					ajustePmmWmsDto.setWmsBj(jsonarr.getJSONObject(i).getInt("wms_bj"));
				}
				if (jsonarr.getJSONObject(i).has("dif_mermas_bj")) {
					ajustePmmWmsDto.setDifMermasBj(jsonarr.getJSONObject(i).getInt("dif_mermas_bj"));
				}

				if (!cd.equalsIgnoreCase("CD12")) {
					if (jsonarr.getJSONObject(i).has("bolsa_discrepancia")) {

						ajustePmmWmsDto.setBolsaDiscrepancia(
								jsonarr.getJSONObject(i).getInt("bolsa_discrepancia"));
					}
				}

				if (cd.equalsIgnoreCase("CD12")) {

					if (jsonarr.getJSONObject(i).has("pmm_economato_disponible")) {
						ajustePmmWmsDto.setPmmEconomatoDisponible(
								jsonarr.getJSONObject(i).getString("pmm_economato_disponible").toString());
					}

					if (jsonarr.getJSONObject(i).has("wms_ec")) {
						ajustePmmWmsDto.setWmsEc(jsonarr.getJSONObject(i).getString("wms_ec").toString());
					}
					if (jsonarr.getJSONObject(i).has("dif_econo_disponible")) {
						ajustePmmWmsDto.setDifEconoDisponible(
								jsonarr.getJSONObject(i).getString("dif_econo_disponible").toString());
					}

					if (jsonarr.getJSONObject(i).has("pmm_economato_bloqueado")) {
						ajustePmmWmsDto.setPmmEconomatoBloqueado(
								jsonarr.getJSONObject(i).getString("pmm_economato_bloqueado").toString());
					}

					if (jsonarr.getJSONObject(i).has("wms_eb")) {
						ajustePmmWmsDto.setWmsEb(jsonarr.getJSONObject(i).getString("wms_eb").toString());
					}
					if (jsonarr.getJSONObject(i).has("dif_econo_bloqueado")) {
						ajustePmmWmsDto.setDifEconoBloqueado(
								jsonarr.getJSONObject(i).getString("dif_econo_bloqueado").toString());
					}
					if (jsonarr.getJSONObject(i).has("bolsa_discrepancia_econo")) {
						ajustePmmWmsDto.setBolsaDiscrepanciaEcono(
								jsonarr.getJSONObject(i).getString("bolsa_discrepancia_econo").toString());
					}
				}

				list.add(ajustePmmWmsDto);

			}

		}

		catch (JSONException e) {
			e.printStackTrace();
		}
		LOGGER.info(" fin listAnalisisAjustePmmWms " + list.size());
		return list;
	}

}
