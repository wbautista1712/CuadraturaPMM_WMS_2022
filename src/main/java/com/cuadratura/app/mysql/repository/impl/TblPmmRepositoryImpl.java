package com.cuadratura.app.mysql.repository.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.repository.TblPmmRepositoryCustom;
import com.cuadratura.app.oracle.dto.FormatoExcelForm;
import com.cuadratura.app.oracle.entity.Fapinvbalee;
import com.cuadratura.app.util.Message;

@Repository
public class TblPmmRepositoryImpl implements TblPmmRepositoryCustom {

	private static final Logger LOGGER = LogManager.getLogger(TblPmmRepositoryImpl.class);

	@Autowired
	@Qualifier("jdbctemplateTwo")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveTblPmm(Fapinvbalee obj, int idCargaPMM) throws SQLException {

		String sql = "INSERT INTO cuadratura.tbl_pmm "
				+ " ( org_lvl_child, prd_lvl_child, inv_type_code, trans_lote, on_hand_qty, on_hand_retl, on_hand_cost, "
				+ " po_ord_qty, po_ord_retl, po_ord_cost, po_intrn_qty, po_intrn_retl, po_intrn_cost, to_ord_qty, to_ord_retl, "
				+ " to_ord_cost, to_intrn_qty, to_intrn_retl, to_intrn_cost, first_pis_date, last_pis_date, ltd_qty, ltd_retl, "
				+ " ltd_cost, last_chg_date, on_hand_weight, weight_uom, po_ord_weight, po_intrn_weight, to_ord_weight, to_intrn_weight, "
				+ " ltd_weight, prd_sll_uom, curr_code, on_hand_eaches, first_shipped_date, first_sales_date, on_hand_cost_hm, on_hand_retl_hm, "
				+ " to_intrn_cost_hm, to_intrn_retl_hm, trans_vcto_lote, idCarga_PMM ) "

				+ " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?) ";

		int insertCount = jdbcTemplate.update(sql,

				obj.getFapinvbaleePK().getOrgLvlChild(), obj.getFapinvbaleePK().getPrdLvlChild(),
				obj.getFapinvbaleePK().getInvTypeCode(), obj.getFapinvbaleePK().getTransLote(),

				obj.getOnHandQty(), obj.getOnHandRetl(), obj.getOnHandCost(), obj.getPoOrdQty(), obj.getPoOrdRetl(),
				obj.getPoOrdCost(),

				obj.getPoIntrnQty(), obj.getPoIntrnRetl(), obj.getPoIntrnCost(), obj.getToOrdQty(), obj.getToOrdRetl(),
				obj.getToOrdCost(), obj.getToIntrnQty(), obj.getToIntrnRetl(), obj.getToIntrnCost(),

				obj.getFirstPisDate(), obj.getLastPisDate(),

				obj.getLtdQty(), obj.getLtdRetl(), obj.getLtdCost(), obj.getLastChgDate(),

				obj.getOnHandWeight(),

				obj.getWeightUom(), obj.getPoOrdWeight(), obj.getPoIntrnWeight(), obj.getToOrdWeight(),
				obj.getToIntrnWeight(),

				obj.getLtdWeight(), obj.getPrdSllUom(), obj.getCurrCode(), obj.getOnHandEaches(),
				obj.getFirstShippedDate(),

				obj.getFirstSalesDate(), obj.getOnHandCostHm(), obj.getOnHandRetlHm(), obj.getToIntrnCostHm(),
				obj.getToIntrnRetlHm(), obj.getTransVctoLote(),

				idCargaPMM);

		LOGGER.info("> insercion correcta " + insertCount);

	}

	@Override
	public Message saveExcelTblPmm(FormatoExcelForm obj) throws SQLException {
		Message msg = new Message();
		 java.util.Date utilDate =  obj.getTransVctoLote();
		 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		 LOGGER.info("> saveExcelTblPmm sqlDate " + sqlDate);
		String sql = "insert into cuadratura.tbl_pmm ( "
				+ "ORG_LVL_CHILD,	PRD_LVL_CHILD,	INV_TYPE_CODE,	TRANS_LOTE,	ON_HAND_QTY,	ON_HAND_RETL, "
				+ "ON_HAND_COST,	PO_ORD_QTY	,PO_ORD_RETL,	PO_ORD_COST,	PO_INTRN_QTY,	PO_INTRN_RETL	, "
				+ "PO_INTRN_COST	,TO_ORD_QTY,	TO_ORD_RETL	,TO_ORD_COST,	TO_INTRN_QTY,	TO_INTRN_RETL	, "
				+ "TO_INTRN_COST,	FIRST_PIS_DATE	,LAST_PIS_DATE	,LTD_QTY,	LTD_RETL	,LTD_COST	,LAST_CHG_DATE	, "
				+ "ON_HAND_WEIGHT,	WEIGHT_UOM	,PO_ORD_WEIGHT,	PO_INTRN_WEIGHT	,TO_ORD_WEIGHT	,TO_INTRN_WEIGHT	, "
				+ "LTD_WEIGHT,	PRD_SLL_UOM	,CURR_CODE	,ON_HAND_EACHES,	FIRST_SHIPPED_DATE,	FIRST_SALES_DATE, "
				+ "ON_HAND_COST_HM	,ON_HAND_RETL_HM,	TO_INTRN_COST_HM	,TO_INTRN_RETL_HM	,TRANS_VCTO_LOTE,  "
				+ "idCarga_PMM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?) ";

		int insertCount = jdbcTemplate.update(sql,

				obj.getOrgLvlChild(), obj.getPrdLvlChild(), obj.getInvTypeCode(), obj.getTransLote(),
				obj.getOnHandQty(), obj.getOnHandRetl(), obj.getOnHandCost(), obj.getPoOrdQty(), obj.getPoOrdRetl(),
				obj.getPoOrdCost(), obj.getPoIntrnQty(), obj.getPoIntrnRetl(), obj.getPoIntrnCost(), obj.getToOrdQty(),
				obj.getToOrdRetl(), obj.getToOrdCost(), obj.getToIntrnQty(), obj.getToIntrnRetl(), obj.getToIntrnCost(),
				obj.getFirstPisDate(), obj.getLastPisDate(), obj.getLtdQty(), obj.getLtdRetl(), obj.getLtdCost(),
				obj.getLastChgDate(), obj.getOnHandWeight(), obj.getWeightUom(), obj.getPoOrdWeight(),
				obj.getPoIntrnWeight(), obj.getToOrdWeight(), obj.getToIntrnWeight(), obj.getLtdWeight(),
				obj.getPrdSllUom(), obj.getCurrCode(), obj.getOnHandEaches(), obj.getFirstShippedDate(),
				obj.getFirstSalesDate(), obj.getOnHandCostHm(), obj.getOnHandRetlHm(), obj.getToIntrnCostHm(),
				obj.getToIntrnRetlHm(),sqlDate, obj.getIdCargaPMM());

		LOGGER.info("> insercion correcta saveExcelTblPmm " + insertCount);
		if (insertCount== 1) {
			msg.setCodError(insertCount);
			msg.setMsjError("Proceso Correcto");
		}else  {
			msg.setCodError(-1);
			msg.setMsjError("Proceso inv??lido.");
		}
	
		
return msg;

	}

	@Override
	public void saveTblPmmBatch(List<Fapinvbalee> listaTblPmmForm, int numeroLotes, int idCargaPMM)
			throws SQLException {
		// TODO Auto-generated method stub
		DataSource ds = jdbcTemplate.getDataSource();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		final int batchSize = listaTblPmmForm.size();
		int count = 0;// contador de lotes
		int batch = numeroLotes;// numero de lotes
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				// El Prepared Statement para los insert

				String sql = "INSERT INTO cuadratura.tbl_pmm "
						+ " ( org_lvl_child, prd_lvl_child, inv_type_code, trans_lote, on_hand_qty, on_hand_retl, on_hand_cost, "
						+ " po_ord_qty, po_ord_retl, po_ord_cost, po_intrn_qty, po_intrn_retl, po_intrn_cost, to_ord_qty, to_ord_retl, "
						+ " to_ord_cost, to_intrn_qty, to_intrn_retl, to_intrn_cost, first_pis_date, last_pis_date, ltd_qty, ltd_retl, "
						+ " ltd_cost, last_chg_date, on_hand_weight, weight_uom, po_ord_weight, po_intrn_weight, to_ord_weight, to_intrn_weight, "
						+ " ltd_weight, prd_sll_uom, curr_code, on_hand_eaches, first_shipped_date, first_sales_date, on_hand_cost_hm, on_hand_retl_hm, "
						+ " to_intrn_cost_hm, to_intrn_retl_hm, trans_vcto_lote, idCarga_PMM ) "

						+ " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
						+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?) ";

				statement = connection.prepareStatement(sql);

				// Se a??aden los datos al batch
				for (Fapinvbalee obj : listaTblPmmForm) {

					// org_lvl_child, prd_lvl_child, inv_type_code, trans_lote, on_hand_qty,
					statement.setInt(1, (int) obj.getFapinvbaleePK().getOrgLvlChild());
					statement.setInt(2, (int) (long) (obj.getFapinvbaleePK().getPrdLvlChild()));
					statement.setString(3, obj.getFapinvbaleePK().getInvTypeCode());
					statement.setString(4, obj.getFapinvbaleePK().getTransLote());

					statement.setBigDecimal(5, obj.getOnHandQty() == null ? BigDecimal.ZERO : obj.getOnHandQty());

					// on_hand_retl, on_hand_cost, po_ord_qty, po_ord_retl, po_ord_cost,
					statement.setBigDecimal(6, obj.getOnHandRetl());
					statement.setBigDecimal(7, obj.getOnHandCost());
					statement.setBigDecimal(8, obj.getPoOrdQty());
					statement.setBigDecimal(9, obj.getPoOrdRetl());
					statement.setBigDecimal(10, obj.getPoOrdCost());

					// po_intrn_qty, po_intrn_retl, po_intrn_cost, to_ord_qty, to_ord_retl,
					statement.setBigDecimal(11, obj.getPoIntrnQty());
					statement.setBigDecimal(12, obj.getPoIntrnRetl());
					statement.setBigDecimal(13, obj.getPoIntrnCost());
					statement.setBigDecimal(14, obj.getToOrdQty());
					statement.setBigDecimal(15, obj.getToOrdRetl());

					// to_ord_cost, to_intrn_qty, to_intrn_retl, to_intrn_cost, first_pis_date,
					statement.setBigDecimal(16, obj.getToOrdCost());
					statement.setBigDecimal(17, obj.getToIntrnQty());
					statement.setBigDecimal(18, obj.getToIntrnRetl());
					statement.setBigDecimal(19, obj.getToIntrnCost());

					if (obj.getFirstPisDate() != null) {
						Date firstPisDate = new java.sql.Date(obj.getFirstPisDate().getTime());
						statement.setDate(20, (java.sql.Date) firstPisDate);
					} else {
						statement.setDate(20, null);
					}

					// last_pis_date, ltd_qty, ltd_retl, ltd_cost, last_chg_date, on_hand_weight,

					if (obj.getLastPisDate() != null) {
						Date lastPisDate = new java.sql.Date(obj.getLastPisDate().getTime());
						statement.setDate(21, (java.sql.Date) lastPisDate);
					} else {
						statement.setDate(21, null);
					}

					statement.setBigDecimal(22, obj.getLtdQty());
					statement.setBigDecimal(23, obj.getLtdRetl());
					statement.setBigDecimal(24, obj.getLtdCost());

					if (obj.getLastChgDate() != null) {
						Date lastChgDate = new java.sql.Date(obj.getLastChgDate().getTime());
						statement.setDate(25, (java.sql.Date) lastChgDate);
					} else {
						statement.setDate(25, null);
					}

					statement.setBigDecimal(26, obj.getOnHandWeight());

					// weight_uom, po_ord_weight, po_intrn_weight, to_ord_weight, to_intrn_weight,
					statement.setString(27, obj.getWeightUom());
					statement.setBigDecimal(28, obj.getPoOrdWeight());
					statement.setBigDecimal(29, obj.getPoIntrnWeight());
					statement.setBigDecimal(30, obj.getToOrdWeight());
					statement.setBigDecimal(31, obj.getToIntrnWeight());

					// ltd_weight, prd_sll_uom, curr_code, on_hand_eaches, first_shipped_date,
					// first_sales_date,
					statement.setBigDecimal(32, obj.getLtdWeight());
					statement.setString(33, obj.getPrdSllUom());
					statement.setString(34, obj.getCurrCode());
					statement.setBigDecimal(35, obj.getOnHandEaches());

					if (obj.getFirstShippedDate() != null) {
						Date firstShippedDate = new java.sql.Date(obj.getFirstShippedDate().getTime());
						statement.setDate(36, (java.sql.Date) firstShippedDate);
					} else {
						statement.setDate(36, null);
					}

					if (obj.getFirstSalesDate() != null) {
						Date firstSalesDate = new java.sql.Date(obj.getFirstSalesDate().getTime());
						statement.setDate(37, (java.sql.Date) firstSalesDate);
					} else {
						statement.setDate(37, null);
					}
					// on_hand_cost_hm, on_hand_retl_hm, to_intrn_cost_hm, to_intrn_retl_hm,
					// trans_vcto_lote, idCarga_PMM
					statement.setBigDecimal(38, obj.getOnHandCostHm());
					statement.setBigDecimal(39, obj.getOnHandRetlHm());
					statement.setBigDecimal(40, obj.getToIntrnCostHm());
					statement.setBigDecimal(41, obj.getToIntrnRetlHm());

					if (obj.getTransVctoLote() != null) {
						Date transVctoLote = new java.sql.Date(obj.getTransVctoLote().getTime());
						statement.setDate(42, (java.sql.Date) transVctoLote);
					} else {
						statement.setDate(42, null);
					}

					statement.setInt(43, idCargaPMM);

					LOGGER.info("> Registro ( %s | %s ) agregado al lote #%s\n", obj.getCurrCode(), obj.getWeightUom(),
							batch);
					statement.addBatch();

					// Se van diviendo los lotes segun un limite establecido
					if (++count % batchSize == 0) {
						// se ejecuta lote #N
						statement.executeBatch();
						LOGGER.info("> Ejecutando lote #%s\n", batch);
						batch++;
					}
				}

				// Se ejecuta el lote restante
				if (listaTblPmmForm.size() % batchSize != 0) {
					LOGGER.info("> Ejecutando lote #%s\n", batch);
					statement.executeBatch();
				}

				// confirma transacci??n
				connection.commit();
				LOGGER.info("> TOTAL: [ %s ] foto registrados\n", listaTblPmmForm.size());
				LOGGER.info("> Programa terminado");
			} catch (SQLException ex) {// si se produce algun error
				LOGGER.error(ex.getMessage());
				try {
					LOGGER.error("> Transacci??n abortada");
					LOGGER.error("> Valores restaurados");
					LOGGER.error("> Programa terminado");
					connection.rollback();
				} catch (SQLException ex1) {
					LOGGER.error(ex1.getMessage());
				}
			} finally {
				if (statement != null) {
					statement.close();
				}
			}
		}
	}

	public List<Map<String, Object>> obtenerFotoPMMCuadratura(int idCargaPMM) throws SQLException {
		
		List<Map<String, Object>> ms_result = new ArrayList<Map<String,Object>>();
		
		DataSource ds = jdbcTemplate.getDataSource();
		Connection conn = null;
		CallableStatement csmt = null;
	
		try {
			conn = ds.getConnection();
			csmt = conn.prepareCall("{call cuadratura.sp_obtener_foto_pmm_cuadratura(?)}");
			csmt.setInt(1, idCargaPMM);
			csmt.execute();
			ResultSet rs = csmt.getResultSet();
			
			ResultSetMetaData md = rs.getMetaData();
		    int columns = md.getColumnCount();
			
			while (rs.next()) {
		        Map<String,Object> row = new HashMap<String, Object>(columns);
		        for(int i=1; i<=columns; ++i) {
		            row.put(md.getColumnName(i),rs.getObject(i));
		        }
		        ms_result.add(row);
		    }

		} catch (Exception ex) {
			LOGGER.error("Error en sp_obtener_foto_pmm_cuadratura", ex);
		} finally {
			if (csmt != null) {
				csmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return ms_result;
	}
	
}
