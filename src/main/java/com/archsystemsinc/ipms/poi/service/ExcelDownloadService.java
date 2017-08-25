package com.archsystemsinc.ipms.poi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.sec.model.QuestionAndAnswerReport;
import com.archsystemsinc.ipms.sec.model.Questions;



/**
 * Service for processing Apache POI-based reports
 * 
 * @author Abdul Nissar
 */
@Service
@Transactional
public class ExcelDownloadService {

	private static Logger logger = Logger.getLogger("ExcelDownloadService");

	

	/**
	 * Processes the download for Excel format. It does the following steps:
	 * 
	 * <pre>
	 * 1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	// @SuppressWarnings("unchecked")
	
	public void fillExportQuestionAndAnswerReport(final HttpServletResponse response,Set<String> entityNames, TreeMap<Long, Questions> questionsFinalMap,
			final TreeMap<String, String> questionAndAnswerFinalMap, String entityType,  String reportName, String reportType)
					throws ClassNotFoundException {
		
			logger.debug("Downloading Excel report");
			String sheetName = reportType;
			//String[] coloumnNames={"Entity Names"};
				// 1. Create new workbook
				final HSSFWorkbook workbook = new HSSFWorkbook();

				// 2. Create new worksheet
				final HSSFSheet worksheet = workbook.createSheet(sheetName);

				// 3. Define starting indices for rows and columns
				final int startRowIndex = 0;
				final int startColIndex = 0;

				// 4. Build layout
				// Build title, date, and column headers
								
				Layouter.buildReport(worksheet, startRowIndex, startColIndex,
						entityNames, entityType, reportName );

				// 5. Fill report
				FillManager.fillQuestionAndAnswerReport(worksheet, startRowIndex, startColIndex,entityNames, questionsFinalMap,
						questionAndAnswerFinalMap, reportName);

				// 6. Set the response properties
				final String fileName = reportName + "_Report.xls";
				response.setHeader("Content-Disposition", "inline; filename="
						+ fileName);
				// Make sure to set the correct content type
				response.setContentType("application/vnd.ms-excel");

				// 7. Write to the output stream
				ExcelWriter.write(response, workbook);

	}
	


	private void buildHeader(final HSSFSheet sheet, final String[] colNames)
	{
		// Create cell style for the headers
		final HSSFCellStyle headerCellStyle = Layouter.createHeaderCellStyle(sheet);
		final HSSFRow header =  sheet.createRow(0);
		for(int i=0; i<colNames.length;i++)
		{
			final Cell cell = header.createCell(i);
			cell.setCellValue(colNames[i]);
			cell.setCellStyle(headerCellStyle);
		}
	}
}
