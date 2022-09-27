package com.cuadratura.app.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cuadratura.app.mysql.entity.TblWms;
import com.cuadratura.app.oracle.dto.AjustePmmWmsDto;
import com.cuadratura.app.util.Message;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class PruebaController {
	private static final Logger LOGGER = LogManager.getLogger(PruebaController.class);

	@PostMapping("/uploadTextoCuadratura")
	// public ResponseEntity<String> uploadTextoCuadratura(@RequestParam("file")
	// MultipartFile file) throws Exception {
	public ResponseEntity<String> uploadTextoCuadratura() throws Exception {
		File doc = new File("C:\\claudio\\trabajo-extra\\ddl.txt");

		BufferedReader obj = new BufferedReader(new FileReader(doc));

		// String colores = "rojo,amarillo,verde,azul,morado,marrón";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		for (String line = obj.readLine(); line != null; line = obj.readLine()) {
			LOGGER.info("xx " + line);
			TblWms objeto = null;

			String separador = Pattern.quote("|");
			String[] arrayColores = line.split(separador);
			LOGGER.info("arrayColores.length " + arrayColores.length);
			// En este momento tenemos un array en el que cada elemento es un color.

			//for (int i = 0; i < arrayColores.length; i++) {
			int i = 0;
			if (arrayColores !=null) {
				objeto = new TblWms();

				LOGGER.info("ooo " + arrayColores[i]);

				objeto.setNroCarga(Integer.parseInt(arrayColores[i]));
				objeto.setCreateDate(arrayColores[i + 1]);
				objeto.setFacilityCode(arrayColores[i + 2]);
				objeto.setCompanyCode(arrayColores[i + 3]);
				objeto.setItemAlternate(arrayColores[i + 4]);
				objeto.setItemPartA(arrayColores[i + 5]);
				objeto.setItemPartB(arrayColores[i + 6]);
				objeto.setItemPartC(arrayColores[i + 7]);
				objeto.setItemPartD(arrayColores[i + 8]);
				objeto.setItemPartE(arrayColores[i + 9]);
				objeto.setItemPartF(arrayColores[i + 10]);
				objeto.setHierarchy1Code(arrayColores[i + 11]);
				objeto.setHierarchy2Code(arrayColores[i + 12]);
				objeto.setHierarchy3Code(arrayColores[i + 13]);
				objeto.setHierarchy4Code(arrayColores[i + 14]);
				objeto.setHierarchy5Code(arrayColores[i + 15]);
				objeto.setBatchNbr(arrayColores[i + 16]);
				objeto.setPrePackCode(arrayColores[i + 17]);
				objeto.setPrePackRatio(Integer.parseInt(arrayColores[i + 18]));
				objeto.setPrePackUnits(Integer.parseInt(arrayColores[i + 19]));
				objeto.setOblpnTotal(Integer.parseInt(arrayColores[i + 20]));
				objeto.setActiveTotal(Integer.parseInt(arrayColores[i + 21]));
				objeto.setActiveAllocated(Integer.parseInt(arrayColores[i + 22]));
				objeto.setActiveAllocatedLockcode(Integer.parseInt(arrayColores[i + 23]));
				objeto.setActiveAvailable(Integer.parseInt(arrayColores[i + 24]));
				objeto.setActiveLockcode(Integer.parseInt(arrayColores[i + 25]));
				objeto.setIblpnTotal(Integer.parseInt(arrayColores[i + 26]));
				objeto.setIblpnAllocated(Integer.parseInt(arrayColores[i + 27]));
				objeto.setIblpnAllocatedLockcode(Integer.parseInt(arrayColores[i + 28]));

				objeto.setIblpnAvailable(Integer.parseInt(arrayColores[i + 29]));
				objeto.setIblpnNotverified(Integer.parseInt(arrayColores[i + 30]));
				objeto.setIblpnLockcode(Integer.parseInt(arrayColores[i + 31]));
				objeto.setIblpnLost(Integer.parseInt(arrayColores[i + 32]));
				objeto.setTotalAllocated(Integer.parseInt(arrayColores[i + 33]));
				objeto.setTotalAvailable(Integer.parseInt(arrayColores[i + 34]));
				objeto.setTotalInventory(Integer.parseInt(arrayColores[i + 35]));
				objeto.setFourWallInventory(Integer.parseInt(arrayColores[i + 36]));
				objeto.setOpenOrderQty(Integer.parseInt(arrayColores[i + 37]));
				objeto.setLockCode1(arrayColores[i + 38]);
				objeto.setLockCodeQty1(Integer.parseInt(arrayColores[i + 39]));
				objeto.setLockCode2(arrayColores[i + 40]);
				objeto.setLockCodeQty2(Integer.parseInt(arrayColores[i + 41]));
				objeto.setLockCode3(arrayColores[i + 42]);
				objeto.setLockCodeQty3(Integer.parseInt(arrayColores[i + 43]));

				objeto.setLockCode4(arrayColores[i + 44]);
				objeto.setLockCodeQty4(Integer.parseInt(arrayColores[i + 45]));
				objeto.setLockCode5(arrayColores[i + 46]);
				objeto.setLockCodeQty5(Integer.parseInt(arrayColores[i + 47]));
				objeto.setLockCode6(arrayColores[i + 48]);
				objeto.setLockCodeQty6(Integer.parseInt(arrayColores[i + 49]));

				objeto.setLockCode7(arrayColores[i + 50]);
				objeto.setLockCodeQty7(Integer.parseInt(arrayColores[i + 51]));
				objeto.setLockCode8(arrayColores[i + 52]);
				objeto.setLockCodeQty8(Integer.parseInt(arrayColores[i + 53]));
				objeto.setLockCode9(arrayColores[i + 54]);
				objeto.setLockCodeQty9(Integer.parseInt(arrayColores[i + 55]));
				objeto.setLockCode10(arrayColores[i + 56]);
				objeto.setLockCodeQty10(Integer.parseInt(arrayColores[i + 57]));

				if (arrayColores[i + 58] != null) {
					Date date = formatter.parse(arrayColores[i + 58]);
					objeto.setDownloadDate1(date);
				} else {
					objeto.setDownloadDate1(null);
				}

				objeto.setErrorCode(Integer.parseInt(arrayColores[i + 59]));
				objeto.setObservacionError(arrayColores[i + 60]);
				objeto.setFlgTipo(Integer.parseInt(arrayColores[i + 61]));

			}
		}
		return new ResponseEntity<String>("Stringss", HttpStatus.OK);
	}
}
