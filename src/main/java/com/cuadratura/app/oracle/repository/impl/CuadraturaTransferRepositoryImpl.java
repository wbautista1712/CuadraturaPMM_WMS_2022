package com.cuadratura.app.oracle.repository.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.oracle.entity.CuadraturaTransfer;
import com.cuadratura.app.oracle.repository.CuadraturaTransferRepository;
import com.cuadratura.app.oracle.repository.CuadraturaTransferRepositoryCustom;

@Repository
@Transactional
public class CuadraturaTransferRepositoryImpl implements CuadraturaTransferRepositoryCustom {
	private static final Logger LOGGER = LogManager.getLogger(CuadraturaTransferRepository.class);

	@Autowired
	@Qualifier("jdbctemplateOne")
	private JdbcTemplate jdbcTemplate;
	
	public Long getSequence() {
		  Long seq;
		  String sql = "SELECT integracion.wms_seq_fapinvtrhee.NEXTVAL FROM DUAL";
		  seq = jdbcTemplate.queryForObject(sql, new Object[] {}, Long.class);
		  LOGGER.info("seq " +seq);
		  return seq;
	}
	public void saveCuadraturaTransfer(CuadraturaTransfer cuadraturaTransfer) {
		LOGGER.info("getIdCuadraturaTransfer " + cuadraturaTransfer.getIdCuadraturaTransfer());
		LOGGER.info("getTransSession " + cuadraturaTransfer.getTransSession());
		LOGGER.info("getTransUser " + cuadraturaTransfer.getTransUser());
		
		  String INSERT_QUERY = " insert into CUADRATURAWYP.CUADRATURA_TRANSFER " + "    ( " + "      id_cuadratura_transfer        , "
					+ "      trans_session               , " + "     trans_user                 , "
					+ "    trans_batch_date             , " + "     trans_source                , "
					+ "     trans_audited                , " + "     trans_sequence             , "
					+ "    trans_trn_code            , " + "    trans_type_code             , "
					+ "    trans_date                 , " + "    inv_mrpt_code               , "
					+ "     inv_drpt_code               , " + "    trans_curr_code              , "
					+ "     trans_org_lvl_number        , " + "     trans_prd_lvl_number        , "
					+ "      proc_source                   , " + "     trans_qty                  , "
					+ "    inner_pack_id             , " + "     trans_inners                 , "
					+ "    trans_lote                  , " + "     trans_vcto_lote             "
					+ "       ) values ( CUADRATURAWYP.CUADRATURA_TRANSFER_seq.nextval,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";
		  

   jdbcTemplate.update(INSERT_QUERY, 
		 
	   cuadraturaTransfer.getTransSession(),
					cuadraturaTransfer.getTransUser(), cuadraturaTransfer.getTransBatchDate(),
					cuadraturaTransfer.getTransSource(), cuadraturaTransfer.getTransAudited(),
					cuadraturaTransfer.getTransSequence(), cuadraturaTransfer.getTransTrnCode(),
					cuadraturaTransfer.getTransTypeCode(), cuadraturaTransfer.getTransDate(),
					cuadraturaTransfer.getInvMrptCode(), cuadraturaTransfer.getInvDrptCode(),
					cuadraturaTransfer.getTransCurrCode(), cuadraturaTransfer.getTransOrgLvlNumber(),
					cuadraturaTransfer.getTransPrdLvlNumber(), cuadraturaTransfer.getProcSource(),
					cuadraturaTransfer.getTransQty(), cuadraturaTransfer.getInnerPackId(),
					cuadraturaTransfer.getTransInners(), cuadraturaTransfer.getTransLote(),

					cuadraturaTransfer.getTransVctoLote());
		     
		     

		LOGGER.info("fin " );
	}
}
