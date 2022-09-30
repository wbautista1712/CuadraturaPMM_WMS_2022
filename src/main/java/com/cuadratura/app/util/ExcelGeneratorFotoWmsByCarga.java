package com.cuadratura.app.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cuadratura.app.oracle.dto.FotoWmsByCargaDto;
import com.cuadratura.app.oracle.dto.FotoWmsDto;

public class ExcelGeneratorFotoWmsByCarga {

	private List<FotoWmsByCargaDto> studentList;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelGeneratorFotoWmsByCarga(List<FotoWmsByCargaDto> studentList) {
		this.studentList = studentList;
		workbook = new XSSFWorkbook();

	}

	private void writeHeader() {
		sheet = workbook.createSheet("FotoWmsByCarga");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "getIdcargaWms", style);
		createCell(row, 1, "getOrgLvlChild", style);
		createCell(row, 2, "getFechaCarga", style);
		createCell(row, 3, "getHoraCarga", style);
		createCell(row, 4, "getNroCarga", style);
		createCell(row, 5, "getCreateDate", style);
		createCell(row, 6, "getFacilityCode", style);
		createCell(row, 7, "getCompanyCode", style);

	}

	private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (valueOfCell instanceof Integer) {
			cell.setCellValue((Integer) valueOfCell);
		} else if (valueOfCell instanceof Long) {
			cell.setCellValue((Long) valueOfCell);
		} else if (valueOfCell instanceof String) {
			cell.setCellValue((String) valueOfCell);
		} else {
			cell.setCellValue((Boolean) valueOfCell);
		}
		cell.setCellStyle(style);
	}

	private void write() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for (FotoWmsByCargaDto record : studentList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, record.getIdcargaWms(), style);
			createCell(row, columnCount++, record.getOrgLvlChild(), style);
			createCell(row, columnCount++, record.getFechaCarga(), style);
			createCell(row, columnCount++, record.getHoraCarga(), style);
			createCell(row, columnCount++, record.getNroCarga(), style);
			createCell(row, columnCount++, record.getCreateDate(), style);
			createCell(row, columnCount++, record.getFacilityCode(), style);
			createCell(row, columnCount++, record.getCompanyCode(), style);

		}
	}

	public void generateExcelFile(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
