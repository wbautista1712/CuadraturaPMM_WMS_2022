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

import com.cuadratura.app.oracle.dto.FotoPmmDto;

public class ExcelGeneratorFotoPmm {

	private List<FotoPmmDto> studentList;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelGeneratorFotoPmm(List<FotoPmmDto> studentList) {
		this.studentList = studentList;
		workbook = new XSSFWorkbook();

	}

	private void writeHeader() {
		sheet = workbook.createSheet("Student");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "IDCARGA_PMM", style);
		createCell(row, 1, "FECHA FOTO", style);
		createCell(row, 2, "HORA FOTO", style);
		createCell(row, 3, "FECHA CARGA", style);
		createCell(row, 4, "HORA CARGA", style);
		createCell(row, 5, "CANT. REGISTROS", style);
		createCell(row, 6, "USUARIO", style);
		createCell(row, 7, "ARCHIVO", style);
		createCell(row, 8, "ESTADO", style);
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
		for (FotoPmmDto record : studentList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, record.getIdCarga_Pmm(), style);
			createCell(row, columnCount++, record.getFecha_Foto(), style);
			createCell(row, columnCount++, record.getHora_Foto(), style);
			createCell(row, columnCount++, record.getFecha_Carga(), style);
			createCell(row, columnCount++, record.getHora_Carga(), style);
			createCell(row, columnCount++, record.getRegistros(), style);
			createCell(row, columnCount++, record.getUsuario(), style);
			createCell(row, columnCount++, record.getNombre_Archivo(), style);
			createCell(row, columnCount++, record.getEstado(), style);
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
