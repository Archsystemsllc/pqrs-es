package com.archsystemsinc.ipms.poi.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;

import com.archsystemsinc.ipms.sec.model.Questions;


public class FillManager {

	/**
	 * Fills the report with content
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 * @param datasource
	 *            the data source
	 */
	
	/*
	 * 
	 * Fill PSV EP Status To Date Report into Excel sheet
	 * basicInfo data should be loaded into the report
	 */
	
	public static void fillQuestionAndAnswerReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex, Set<String> entityNames,TreeMap<Long, Questions> questionsFinalMap,
			final TreeMap<String, String> questionAndAnswerFinalMap, String reportType) {
		
		
			
			// Row offset
			startRowIndex += 2;
			//final List<QuestionAndAnswerReport> questionAndAnswerList = (List) questionAndAnswerFinalMap;
			// Create cell style for the body
			final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
					.createCellStyle();
			final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
					.createCellStyle();
			
			final CreationHelper createHelper = worksheet.getWorkbook()
					.getCreationHelper();

			bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
			bodyCellTextStyle.setWrapText(true);

			bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
					.getFormat("m/d/yy"));
			Long IdCellValue = null;
			
			// Create body
	             
	        int rowIndex = startRowIndex;
	        worksheet.autoSizeColumn(startColIndex);
	     
	        for(Map.Entry<Long, Questions> rowEntry : questionsFinalMap.entrySet()){
			    //questionsFinalMap.put(entry.getKey(), entry.getValue());
	        	final HSSFRow row = worksheet.createRow((short) rowIndex + 1);
				
				final HSSFCell cell1 = row.createCell(startColIndex + 0);
				final HSSFCell cell2 = row.createCell(startColIndex + 1);
				
				cell1.getRow().setHeight((short)1000);
				cell1.setCellValue(rowEntry.getValue().getQuestionDescription());			
				cell1.setCellStyle(bodyCellTextStyle);	
				
				cell2.getRow().setHeight((short)1000);
				cell2.setCellValue(rowEntry.getValue().getQuestionCategory().getName());			
				cell2.setCellStyle(bodyCellTextStyle);
				
				rowIndex++;
				int columnIndex = 2;
				for(String entityName: entityNames){
					
					String answer =  questionAndAnswerFinalMap.get(entityName + "_" + rowEntry.getKey());
					String cellValue = "";
					
					final HSSFCell cell3 = row.createCell(startColIndex + columnIndex);
					
					
					if(answer==null || answer.equalsIgnoreCase("")) {
						cellValue = "Question_NA";
					} else {				
						
						cellValue = answer;
						
					}
					cell3.setCellValue(cellValue);			
					cell3.setCellStyle(bodyCellTextStyle);
					columnIndex++;
				}
			} 
	}
	
	public static void fillEpInfoData(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource) {
		// Row offset
		startRowIndex += 2;
		LinkedHashMap<String, String> columnListMap = (LinkedHashMap<String, String>) datasource.get(0);
		Set<String>  columnListMapKeySet=  columnListMap.keySet();
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_LEFT);
		
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		int rowIndex = startRowIndex;
		for(String keyValue: columnListMapKeySet) {
			
			final HSSFRow row = worksheet.createRow((short) rowIndex + 1);
			
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			
			cell1.getRow().setHeight((short)500);
			cell1.setCellValue(keyValue);			
			cell1.setCellStyle(bodyCellTextStyle);	
			
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.getRow().setHeight((short)500);
			cell2.setCellValue(columnListMap.get(keyValue));			
			cell2.setCellStyle(bodyCellTextStyle);
			
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.getRow().setHeight((short)500);
			cell3.setCellValue("");
			cell3.setCellStyle(bodyCellDateStyle);
						
			rowIndex++;			
		}		
	}	
}
