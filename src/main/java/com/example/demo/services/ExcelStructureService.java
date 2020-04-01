package com.example.demo.services;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IncidenceRepository;
import com.example.demo.dao.TaskRepository;

@Service
public class ExcelStructureService {
	
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	IncidenceRepository incidenceRepository;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	IncidenceService incidenceService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TypeService typeService;
	
	public void createFont(Font headerFont, String color) {
		headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        if(color=="white")
        	headerFont.setColor(IndexedColors.WHITE.getIndex());
        else
        	headerFont.setColor(IndexedColors.BLACK.getIndex());

	}
	
	public void setCellStyle(CellStyle cellStyle, String color, Font headerFont) {
		if(color=="red")
			cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		else if(color=="black")
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		else
			cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setFont(headerFont);
	}
	
	public void setHeader(HashMap<Integer, String> columnsName, Row headerRow, CellStyle headerCellStyle, CellStyle headerCellStyleGreen) {
		for(int i = 0; i < columnsName.size(); i++) {
    		Cell cell = headerRow.createCell(i);
        	if(i >= 1 && i <= 7) {
        		cell.setCellValue(columnsName.get(i));
        		cell.setCellStyle(headerCellStyle);	   
        	}else {
        		cell.setCellValue(columnsName.get(i));
        		cell.setCellStyle(headerCellStyleGreen);	 
        	}
        }	
	}
	
	public void setHeader(HashMap<Integer, String> columnsName, Row headerRow, int start, int end, CellStyle cellStyle1, CellStyle cellstyle2) {
		for(int i = 0; i < columnsName.size(); i++) {
    		Cell cell = headerRow.createCell(i);
        	if(i >= start && i <= end) {
        		cell.setCellValue(columnsName.get(i));
        		cell.setCellStyle(cellStyle1);	   
        	}else {
        		cell.setCellValue(columnsName.get(i));
        		cell.setCellStyle(cellstyle2);	 
        	}
        }	
	}
	
	public void setMergedCells(Sheet sheet, int start, int end, Row row, CellStyle cellStyle, String content) {
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),start,end));
        Cell mergedCell = row.createCell(start);
        mergedCell.setCellValue(content);
		mergedCell.setCellStyle(cellStyle);
	}
	
	public void setHeader2(HashMap<Integer, String> columnsName, Row header2, CellStyle headerCellStyle, CellStyle headerCellStyleGreen) {
		for(int i = 0; i < columnsName.size(); i++) {
	        Cell cell = header2.createCell(i);
	    	if(i >= 1 && i <= 7)
	    		cell.setCellStyle(headerCellStyle);		
	    	else
	    		cell.setCellStyle(headerCellStyleGreen);		
	    }
	}
	
	public void seedAdditionalCells(List<Row> row, HashMap<Integer,String> names) {
		row.get(11).createCell(21).setCellValue(names.get(3));
		row.get(11).createCell(22).setCellValue(names.get(9));
		row.get(11).createCell(23).setCellValue(names.get(10));
		row.get(11).createCell(24).setCellValue(names.get(11));
		row.get(11).createCell(25).setCellValue(names.get(12));
		row.get(11).createCell(26).setCellValue(names.get(17));
		row.get(11).createCell(27).setCellValue(names.get(18));	
		
		row.get(0).createCell(21).setCellValue("HERRAMIENTA_TESTING");
		row.get(0).createCell(22).setCellValue("ALTA");
		row.get(0).createCell(23).setCellValue("BLOQUEANTE");
		row.get(0).createCell(24).setCellValue("SI");
		row.get(0).createCell(25).setCellValue("CRITICA");
		row.get(0).createCell(26).setCellValue("En análisis");
		row.get(0).createCell(27).setCellValue("SI");

		row.get(1).createCell(21).setCellValue("PEER_REVIEW_CODIGO");
		row.get(1).createCell(22).setCellValue("MEDIA");
		row.get(1).createCell(23).setCellValue("ALTO");
		row.get(1).createCell(24).setCellValue("NO");
		row.get(1).createCell(25).setCellValue("ALTA");
		row.get(1).createCell(26).setCellValue("En ejecución");
		row.get(1).createCell(27).setCellValue("NO");

		row.get(2).createCell(21).setCellValue("PEER_REVIEW_DF");
		row.get(2).createCell(22).setCellValue("BAJA");
		row.get(2).createCell(23).setCellValue("MEDIO");
		row.get(2).createCell(25).setCellValue("MEDIA");
		row.get(2).createCell(26).setCellValue("Entregada");

		row.get(3).createCell(21).setCellValue("PEER_REVIEW_DT");
		row.get(3).createCell(23).setCellValue("BAJO");
		row.get(3).createCell(25).setCellValue("BAJA");
		row.get(3).createCell(26).setCellValue("Entrega Rechazada");
		
		row.get(4).createCell(21).setCellValue("PEER_REVIEW_EJECUCION_PRUEBAS");
		row.get(4).createCell(25).setCellValue("MUY_BAJA");
		row.get(4).createCell(26).setCellValue("Cerrada");
		
		row.get(5).createCell(21).setCellValue("PRUEBAS_FUNCIONALES");
		row.get(5).createCell(26).setCellValue("Desestimada");
		
		row.get(6).createCell(21).setCellValue("PRUEBAS_INTEGRADAS");
		row.get(6).createCell(26).setCellValue("Cancelada");
		
		row.get(7).createCell(21).setCellValue("PRUEBAS_UNITARIAS");
		row.get(7).createCell(26).setCellValue("Aplazada");
		
		row.get(8).createCell(21).setCellValue("REVISION_ENTREGA");
		row.get(9).createCell(21).setCellValue("PRUEBAS_SEGURIDAD");
		row.get(10).createCell(21).setCellValue("PRUEBAS_CRUZADAS");
	}
	
	
}