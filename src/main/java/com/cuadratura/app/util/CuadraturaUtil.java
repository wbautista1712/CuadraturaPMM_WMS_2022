package com.cuadratura.app.util;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;

public abstract class CuadraturaUtil {
	protected static final Log logger = LogFactory.getLog(CuadraturaUtil.class);

	public static long abs(long a) {
		return (a < 0) ? -a : a;
	}

	public static String evaluaCeldaExcel(Cell cell) {
		String valorRetorno = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case BOOLEAN:
				valorRetorno = Boolean.toString(cell.getBooleanCellValue());
				return valorRetorno;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					valorRetorno = formatter.format(cell.getDateCellValue());
					return valorRetorno;
				} else {
					DataFormatter formato = new DataFormatter();
					valorRetorno = formato.formatCellValue(cell);
					return valorRetorno;
				}
			case STRING:
				valorRetorno = cell.getStringCellValue();
				return valorRetorno;
			case BLANK:
				return valorRetorno;
			case ERROR:
				valorRetorno = String.valueOf(cell.getErrorCellValue());
				return valorRetorno;
			case FORMULA:
				valorRetorno = String.valueOf(cell.getNumericCellValue());
				return valorRetorno;
			default:
				return valorRetorno;
			}
		} else {
			return valorRetorno;
		}
	}

}
