package com.cuadratura.app.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cuadratura.app.mysql.entity.CargaPmm;
import com.cuadratura.app.mysql.entity.CargaWms;
import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.FormatoExcelForm;
import com.cuadratura.app.oracle.dto.WmsCinsDto;
import com.cuadratura.app.service.CargaPmmService;
import com.cuadratura.app.service.CargaWmsService;
import com.cuadratura.app.service.TblPmmService;
import com.cuadratura.app.service.TblWmsService;
import com.cuadratura.app.util.Constantes;
import com.cuadratura.app.util.CuadraturaUtil;
import com.cuadratura.app.util.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class PruebaController {
	private static final Logger LOGGER = LogManager.getLogger(PruebaController.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private TblWmsService tblWmsService;
	
	@Autowired
	private CargaWmsService cargaWmsService;
	
	@Autowired
	private TblPmmService tblPmmService;
	

	@Autowired
	private CargaPmmService cargaPmmService;

	@PostMapping("/uploadTextoCuadratura")
	public ResponseEntity<String> uploadTextoCuadratura(@RequestBody String jsonData) throws Exception {
		// public ResponseEntity<String> uploadTextoCuadratura() throws Exception {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			TblWms ajustePmmWms = new TblWms();
			// File convertFile = new File(originalFilename);

			CargaWms cargaWms = new CargaWms();
			org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy"); // 21/08/2022

			cargaWms.setFechaCarga(new Date());
			cargaWms.setHoraCarga(dateTimeFormatter.format(LocalDateTime.now()));
			cargaWms.setEstado(true);
			// cargaWms.setNumRegistros(listaWmsCinsDto.size());
			cargaWms.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
			cargaWms.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);
			cargaWms.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);
			// cargaWms.setOrgNameShort(idCD);
			// cargaWms.setNombreArchivo(nombreArchivo);
			Integer id = cargaWmsService.saveCargaWms(cargaWms).intValue();
			LOGGER.info("id ==> " + id);

			List<WmsCinsDto> registroJsonList = om.readValue(jsonData, new TypeReference<List<WmsCinsDto>>() {
			});

			registroJsonList.stream().forEach(x -> {

				try {

					ajustePmmWms.setIdCargaWMS(id);// reecupera id
					ajustePmmWms.setNroCarga(id);
					ajustePmmWms.setCreateDate(x.getCreateDate());

					tblWmsService.uploadTblWms(ajustePmmWms);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});

			/*
			 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); for (String
			 * line = obj.readLine(); line != null; line = obj.readLine()) {
			 * LOGGER.info("xx " + line); TblWms objeto = null;
			 * 
			 * String separador = Pattern.quote("|"); String[] arrayColores =
			 * line.split(separador); LOGGER.info("arrayColores.length " +
			 * arrayColores.length);
			 * 
			 * int i = 0; if (arrayColores !=null) { objeto = new TblWms();
			 * 
			 * LOGGER.info("ooo " + arrayColores[i]);
			 */
			// objeto.setIdCargaWMS(1);// reecupera id
			// objeto.setNroCarga( new Long(arrayColores[i]));
			// objeto.setCreateDate(arrayColores[i]);

			/*
			 * objeto.setFacilityCode(arrayColores[i + 1]);
			 * objeto.setCompanyCode(arrayColores[i + 2]);
			 * objeto.setItemAlternate(arrayColores[i + 3]);
			 * objeto.setItemPartA(arrayColores[i + 4]); objeto.setItemPartB(arrayColores[i
			 * + 6]); objeto.setItemPartC(arrayColores[i + 7]);
			 * objeto.setItemPartD(arrayColores[i + 8]); objeto.setItemPartE(arrayColores[i
			 * + 9]); objeto.setItemPartF(arrayColores[i + 10]);
			 * objeto.setHierarchy1Code(arrayColores[i + 11]);
			 * objeto.setHierarchy2Code(arrayColores[i + 12]);
			 * objeto.setHierarchy3Code(arrayColores[i + 13]);
			 * objeto.setHierarchy4Code(arrayColores[i + 14]);
			 * objeto.setHierarchy5Code(arrayColores[i + 15]);
			 * objeto.setBatchNbr(arrayColores[i + 16]);
			 * objeto.setPrePackCode(arrayColores[i + 17]);
			 * objeto.setPrePackRatio(Integer.parseInt(arrayColores[i + 18]));
			 * objeto.setPrePackUnits(Integer.parseInt(arrayColores[i + 19]));
			 * objeto.setOblpnTotal(Integer.parseInt(arrayColores[i + 20]));
			 * objeto.setActiveTotal(Integer.parseInt(arrayColores[i + 21]));
			 * objeto.setActiveAllocated(Integer.parseInt(arrayColores[i + 22]));
			 * objeto.setActiveAllocatedLockcode(Integer.parseInt(arrayColores[i + 23]));
			 * objeto.setActiveAvailable(Integer.parseInt(arrayColores[i + 24]));
			 * objeto.setActiveLockcode(Integer.parseInt(arrayColores[i + 25]));
			 * objeto.setIblpnTotal(Integer.parseInt(arrayColores[i + 26]));
			 * objeto.setIblpnAllocated(Integer.parseInt(arrayColores[i + 27]));
			 * objeto.setIblpnAllocatedLockcode(Integer.parseInt(arrayColores[i + 28]));
			 * 
			 * objeto.setIblpnAvailable(Integer.parseInt(arrayColores[i + 29]));
			 * objeto.setIblpnNotverified(Integer.parseInt(arrayColores[i + 30]));
			 * objeto.setIblpnLockcode(Integer.parseInt(arrayColores[i + 31]));
			 * objeto.setIblpnLost(Integer.parseInt(arrayColores[i + 32]));
			 * objeto.setTotalAllocated(Integer.parseInt(arrayColores[i + 33]));
			 * objeto.setTotalAvailable(Integer.parseInt(arrayColores[i + 34]));
			 * objeto.setTotalInventory(Integer.parseInt(arrayColores[i + 35]));
			 * objeto.setFourWallInventory(Integer.parseInt(arrayColores[i + 36]));
			 * objeto.setOpenOrderQty(Integer.parseInt(arrayColores[i + 37]));
			 * objeto.setLockCode1(arrayColores[i + 38]);
			 * objeto.setLockCodeQty1(Integer.parseInt(arrayColores[i + 39]));
			 * objeto.setLockCode2(arrayColores[i + 40]);
			 * objeto.setLockCodeQty2(Integer.parseInt(arrayColores[i + 41]));
			 * objeto.setLockCode3(arrayColores[i + 42]);
			 * objeto.setLockCodeQty3(Integer.parseInt(arrayColores[i + 43]));
			 * 
			 * objeto.setLockCode4(arrayColores[i + 44]);
			 * objeto.setLockCodeQty4(Integer.parseInt(arrayColores[i + 45]));
			 * objeto.setLockCode5(arrayColores[i + 46]);
			 * objeto.setLockCodeQty5(Integer.parseInt(arrayColores[i + 47]));
			 * objeto.setLockCode6(arrayColores[i + 48]);
			 * objeto.setLockCodeQty6(Integer.parseInt(arrayColores[i + 49]));
			 * 
			 * objeto.setLockCode7(arrayColores[i + 50]);
			 * objeto.setLockCodeQty7(Integer.parseInt(arrayColores[i + 51]));
			 * objeto.setLockCode8(arrayColores[i + 52]);
			 * objeto.setLockCodeQty8(Integer.parseInt(arrayColores[i + 53]));
			 * objeto.setLockCode9(arrayColores[i + 54]);
			 * objeto.setLockCodeQty9(Integer.parseInt(arrayColores[i + 55]));
			 * objeto.setLockCode10(arrayColores[i + 56]);
			 * objeto.setLockCodeQty10(Integer.parseInt(arrayColores[i + 57]));
			 * 
			 * if (arrayColores[i + 58] != null) { Date date =
			 * formatter.parse(arrayColores[i + 58]); objeto.setDownloadDate1(date); } else
			 * { objeto.setDownloadDate1(null); }
			 * 
			 * objeto.setErrorCode(Integer.parseInt(arrayColores[i + 59]));
			 * objeto.setObservacionError(arrayColores[i + 60]);
			 * objeto.setFlgTipo(Integer.parseInt(arrayColores[i + 61]));
			 */

			/*
			 * tblWmsService.uploadTblWms(objeto); } }
			 * 
			 */

		} catch (Exception ex) {
			// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);

		}
		return new ResponseEntity<String>("Carga Exitoso", HttpStatus.OK);
	}

	// cargar excel
	@RequestMapping(value = "/procesarExcel", method = RequestMethod.POST)
	public ResponseEntity<Object> procesarExcel(@RequestPart(value = "file", required = false) MultipartFile excelfile,
			HttpServletRequest request, Model model) throws Exception {
		// GenericResponse response = new GenericResponse();
		Message msg = new Message();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {

			LOGGER.info(excelfile.getOriginalFilename());

			List<FormatoExcelForm> lstCabecera = new ArrayList<FormatoExcelForm>();

			// File archivoExcel = new File(System.getProperty("java.io.tmpdir") +
			// System.getProperty("file.separator") + excelfile.getOriginalFilename());
			// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelfile.getInputStream());
			String lowerCaseFileName = excelfile.getOriginalFilename().toLowerCase();

			Workbook hssfWorkbook;

			if (lowerCaseFileName.endsWith(".xlsx")) {
				hssfWorkbook = new XSSFWorkbook(excelfile.getInputStream());
			} else {
				hssfWorkbook = new HSSFWorkbook(excelfile.getInputStream());
			}

			FormatoExcelForm cabeceraExcelForm = null;
			
			// codigo de carga carga_pmm
			CargaPmm cargaPmm = new CargaPmm();

			cargaPmm.setFechaFoto(new Date());
			cargaPmm.setHoraFoto(dateTimeFormatter.format(LocalDateTime.now()));
			//cargaPmm.setNumRegistros(listaTblPmmForm.size());
			cargaPmm.setUsuarioCarga(Constantes.USUARIO_CARGA_AUTOMATICO);

			cargaPmm.setEstado(true);

			cargaPmm.setIdmTipoImportacion(Constantes.TIPO_IMPORTACION);
			cargaPmm.setIdmestadoCuadratura(Constantes.ESTADO_CUADRATURA);
			cargaPmm.setOrgLvlChild(5346); // revisar

			Integer id = cargaPmmService.saveCargaPmm(cargaPmm).intValue();
			LOGGER.info("id..."+id);
			String testDate  = "";
			// Read the Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// Read the Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					Row hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						cabeceraExcelForm = new FormatoExcelForm();
						LOGGER.info("idccc..."+ hssfRow.getCell(7));
						cabeceraExcelForm.setOrgLvlChild(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(0)));
						LOGGER.info("11...");
						cabeceraExcelForm.setPrdLvlChild(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(1)));
						cabeceraExcelForm.setInvTypeCode(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(2)));
						LOGGER.info("22...");
						cabeceraExcelForm.setTransLote(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(3)));
						cabeceraExcelForm.setOnHandQty(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(4)));
						LOGGER.info("33...");
						cabeceraExcelForm.setOnHandRetl(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(5)));
						cabeceraExcelForm.setOnHandCost(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(6)));
						LOGGER.info("aaaaa...");
						cabeceraExcelForm.setPoOrdQty( CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(7)));
						LOGGER.info("cccc..."+CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(7)) );
						cabeceraExcelForm.setPoOrdRetl(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(8)));
						LOGGER.info("bbbb...");
						cabeceraExcelForm.setPoOrdCost(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(9)));
						LOGGER.info("444...");
						cabeceraExcelForm.setPoIntrnQty(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(10)));
						cabeceraExcelForm.setPoIntrnRetl(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(11)));
						cabeceraExcelForm.setPoIntrnCost(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(12)));
						cabeceraExcelForm.setToOrdQty(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(13)));
						cabeceraExcelForm.setToOrdRetl(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(14)));
						cabeceraExcelForm.setToOrdCost(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(15)));
						cabeceraExcelForm.setToIntrnQty(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(16)));
						cabeceraExcelForm.setToIntrnRetl(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(17)));
						LOGGER.info("555...");
						cabeceraExcelForm.setToIntrnCost(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(18)));
						cabeceraExcelForm.setFirstPisDate(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(19)));
						cabeceraExcelForm.setLastPisDate(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(20)));
						cabeceraExcelForm.setLtdQty(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(21)));
						cabeceraExcelForm.setLtdRetl(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(22)));
						cabeceraExcelForm.setLtdCost(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(23)));
						cabeceraExcelForm.setLastChgDate(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(24)));
						cabeceraExcelForm.setOnHandWeight(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(25)));
						cabeceraExcelForm.setWeightUom(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(26)));
						cabeceraExcelForm.setPoOrdWeight(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(27)));
						cabeceraExcelForm.setPoIntrnWeight(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(28)));
						cabeceraExcelForm.setToOrdWeight(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(29)));
						cabeceraExcelForm.setToIntrnWeight(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(30)));
						cabeceraExcelForm.setLtdWeight(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(31)));
						cabeceraExcelForm.setPrdSllUom(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(32)));
						cabeceraExcelForm.setCurrCode(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(33)));
						cabeceraExcelForm.setOnHandEaches(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(34)));
						cabeceraExcelForm.setFirstShippedDate(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(35)));
						cabeceraExcelForm.setFirstSalesDate(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(36)));
						cabeceraExcelForm.setOnHandCostHm(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(37)));
						cabeceraExcelForm.setOnHandRetlHm(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(38)));
						cabeceraExcelForm.setToIntrnCostHm(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(39)));
						cabeceraExcelForm.setToIntrnRetlHm(CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(40)));
						LOGGER.info("valor capturado 41== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(41)));
						
						 testDate = CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(41));
					if (testDate!= null) {
						Date date = formatter.parse(testDate);
						cabeceraExcelForm.setTransVctoLote(date);
					}else {
						cabeceraExcelForm.setTransVctoLote(null);
					}
					
						
						cabeceraExcelForm.setIdCargaPMM(id);
						LOGGER.info("valor capturado 00== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(0)));
						LOGGER.info("valor capturado 01== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(1)));
						LOGGER.info("valor capturado 02== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(2)));
						LOGGER.info("valor capturado 03== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(3)));

						LOGGER.info("valor capturado 04== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(4)));
						LOGGER.info("valor capturado 05== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(5)));
						LOGGER.info("valor capturado 06== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(6)));
						LOGGER.info("valor capturado 07== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(7)));

						LOGGER.info("valor capturado 08== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(8)));
						LOGGER.info("valor capturado 09== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(9)));
						LOGGER.info("valor capturado 10== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(10)));
						LOGGER.info("valor capturado 11== " + CuadraturaUtil.evaluaCeldaExcel(hssfRow.getCell(11)));

						LOGGER.info("listo para addd");
						lstCabecera.add(cabeceraExcelForm);
					}

				}

			}

			LOGGER.info("===size===>>> " + lstCabecera.size());
			msg = this.tblPmmService.saveExcelTblPmm(cabeceraExcelForm);

			LOGGER.info("-- " + msg.getCodError());
			LOGGER.info("-- " + msg.getMsjError());

			if (msg.getCodError() == 1) {

				return new ResponseEntity<Object>(msg.getMsjError(), HttpStatus.OK);

			} else if (msg.getCodError() == -1) {

				return new ResponseEntity<Object>(msg.getMsjError(), HttpStatus.BAD_REQUEST);

			} else {
				return new ResponseEntity<Object>("Archivo inválido.", HttpStatus.BAD_REQUEST);
			}
			// response.setCodError(msg.getCodError());
			// response.setMensaje(msg.getMsjError());
			// hssfWorkbook.close();
			// ((OutputStream) hssfWorkbook).close();
		
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<Object>("Archivo inválido.", HttpStatus.BAD_REQUEST);
		} finally {
		
			  
			excelfile.getInputStream().close();
		}
		// return response;

	}

}
