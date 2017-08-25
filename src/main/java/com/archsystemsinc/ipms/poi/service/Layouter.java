package com.archsystemsinc.ipms.poi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.archsystemsinc.ipms.util.GenericConstants;


/**
 * Builds the report layout.
 * 
 * @author Abdul Nissar
 */
public class Layouter {

	private static Logger logger = Logger.getLogger("Layouter");

	/**
	 * Builds the report layout.
	 * <p>
	 * This doesn't have any data yet. This is your template.
	 */
	public static void buildReport(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex,
			final Set<String> coloumnNames, String entityName , String reportName) {
		// Set column widths
		for (short coloumnIndex = 0; coloumnIndex < coloumnNames.size() + 1; coloumnIndex++) {
			worksheet.setColumnWidth(coloumnIndex, 15000);
			/*if(reportType.equalsIgnoreCase(GenericConstants.SURVEY_QUESTION_AND_ANSWER_REPORT)) {
				if(coloumnIndex == 0 ) {
					worksheet.setColumnWidth(coloumnIndex, 15000);
				}
				
			}*/
			
		}
		// Build the title and date headers
		buildTitle(worksheet, startRowIndex, startColIndex, reportName);
		// Build the column headers
		buildHeaders(worksheet, startRowIndex, startColIndex, reportName, coloumnNames);
	}

	/**
	 * Builds the report title and the date header
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 */
	public static void buildTitle(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex, String reportName) {
		// Create font style for the report title
		final Font fontTitle = worksheet.getWorkbook().createFont();
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeight((short) 280);

		// Create cell style for the report title
		final HSSFCellStyle cellStyleTitle = worksheet.getWorkbook()
				.createCellStyle();
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setWrapText(true);
		cellStyleTitle.setFont(fontTitle);
		
		if(reportName.equalsIgnoreCase(GenericConstants.SURVEY_QUESTION_AND_ANSWER_REPORT)) {
			
			// Create report title
			final HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
			rowTitle.setHeight((short) 500);
			final HSSFCell cellTitle = rowTitle.createCell(startColIndex);
			cellTitle.setCellValue(" Questions and Answers Report for Entity:" );
			cellTitle.setCellStyle(cellStyleTitle);

			// Create merged region for the report title
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

			// Create date header
			final HSSFRow dateTitle = worksheet
					.createRow((short) startRowIndex + 1);
			final HSSFCell cellDate = dateTitle.createCell(startColIndex);
			cellDate.setCellValue("Description: This sheet contains Questions and Answers");
			
		} 
	}

	/**
	 * Builds the column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 */
	public static void buildToDateHeader(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex,
			final String reportType,final String[] coloumnNames) {

		// Create cell style for the headers
		final HSSFCellStyle headerCellStyle = createHeaderCellStyle(worksheet);

		// Create the column headers
		final HSSFRow rowHeader = worksheet
				.createRow((short) startRowIndex + 2);
		rowHeader.setHeight((short) 500);
		List<String> coloumList = Arrays.asList(coloumnNames);
		
		for(int i=0;i<coloumList.size();i++){
			
			final HSSFCell cell1= rowHeader.createCell(startColIndex + i);
			cell1.setCellValue(coloumList.get(i));
			System.out.println(coloumList.get(i) + "column name");
			cell1.setCellStyle(headerCellStyle);
			
		}  		
			
	}

	public static HSSFCellStyle createHeaderCellStyle(final HSSFSheet worksheet)	{
		final HSSFCellStyle headerCellStyle = worksheet.getWorkbook()
				.createCellStyle();
		// Create font style for the headers
		final Font font = worksheet.getWorkbook().createFont();
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL);

		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return headerCellStyle;
	}
	
	/**
	 * Builds To-Date Report column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 */
	public static void buildHeaders(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex,
			final String reportName, Set<String> coloumnNames) {

		// Create cell style for the headers
		final HSSFCellStyle headerCellStyle = createHeaderCellStyle(worksheet);

		// Create the column headers
		worksheet.autoSizeColumn(startColIndex);
		final HSSFRow rowHeader = worksheet
				.createRow((short) startRowIndex + 2);
		
		rowHeader.setHeight((short) 1500);
		
		if (reportName.equalsIgnoreCase(GenericConstants.SURVEY_QUESTION_AND_ANSWER_REPORT)) {
			int index = 0;
			int index2 = 1;
			final HSSFCell cell1 = rowHeader.createCell(startColIndex + index);
			cell1.setCellValue("Questions");
			cell1.setCellStyle(headerCellStyle);
			
			final HSSFCell cell2 = rowHeader.createCell(startColIndex + index2);
			cell2.setCellValue("Category");
			cell2.setCellStyle(headerCellStyle);
			
			index ++;
			index2 ++;
			
			for(String headerName: coloumnNames) {
				final HSSFCell cell3 = rowHeader.createCell(startColIndex + index2);
				cell3.setCellValue(headerName);
				cell3.setCellStyle(headerCellStyle);
				index2 ++;
			}			
		}  
	}

}