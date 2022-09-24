package com.cuadratura.app.util;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.cuadratura.app.oracle.dto.UsuarioDto;

public class ExcelGeneradorUsuario 
{
	private List<UsuarioDto> lista;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelGeneradorUsuario(List<UsuarioDto> usuarioList) {
		//this.usua = usuarioList;
		workbook = new XSSFWorkbook();

	}
	
	
}
