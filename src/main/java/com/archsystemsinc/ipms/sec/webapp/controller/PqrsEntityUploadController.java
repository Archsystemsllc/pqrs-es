/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.archsystemsinc.ipms.sec.webapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityFileUpload;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;

import com.archsystemsinc.ipms.sec.model.QuestionsFileUpload;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;

import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls administrator on uploading Excel PQRS Entities file
 * 
 * @author 
 * @since
 */
@Controller
public class PqrsEntityUploadController  extends AbstractController<PqrsEntity> {

	
	@Autowired
	private IPqrsEntityService service;

	@Autowired
	private IPqrsEntityTypeService pqrsEntityTypeService;
	
	@Autowired
	private IYearService yearSurveyService;
	
	
	public PqrsEntityUploadController() {
		super(PqrsEntity.class);
	}
	
	
	/**
	 * upload questions in excel file to questions table
	 * 
	 * @param model
	 *     contains information about active navigation tab, upload success message, 
	 *     error message, pqrs entity file upload and question file upload information for rendering 
	 * @param pqrsEntityFileUpload
	 *     multi-part file model
	 * @param principal
	 *     current user credentials
	 * @param result
	 *     data validation information
	 * @param request
	 *     contains request parameter upload
	 * @return
	 *     file upload page
	 * @throws InvalidFormatException
	 *     file format goes wrong
	 */
	@RequestMapping(value = "/admin/pqrsentityupload", method = RequestMethod.POST)
	public String epInfoUploadPost(final Model model,
			PqrsEntityFileUpload pqrsEntityFileUpload, final Principal principal,
			final BindingResult result, final HttpServletRequest request) throws InvalidFormatException {

		String returnView = "filesupload";
		String active = "filesupload";
        model.addAttribute("navigationstatus", active);
		int totalNumberOfRows = 0;
		int totalEpRowsCreatedOrUpdated = 0;
		ArrayList<Object> returnObjects = null;
	

		if (request.getParameter("Upload") != null) {
			if (result.hasErrors()) {
				return "filesupload";
			}
		}

		try {
			
			if (pqrsEntityFileUpload.getpqrsEntityFile() != null) {
				
				Workbook pqrsEntityFileWorkbook = WorkbookFactory.create(pqrsEntityFileUpload.getpqrsEntityFile().getInputStream());
				Sheet pqrsEntityFileSheet = pqrsEntityFileWorkbook.getSheetAt(0);
				Iterator<Row> pqrsEntityFileRowIterator = pqrsEntityFileSheet.rowIterator();
                int pqrsEntityFileRowCount = pqrsEntityFileSheet.getPhysicalNumberOfRows();
				totalNumberOfRows = pqrsEntityFileRowCount - 1;
				String stringResult = "";

			
				
				//long yearId =  2;

				while (pqrsEntityFileRowIterator.hasNext()) 
				{
					Row pqrsEntityFileRow = (Row) pqrsEntityFileRowIterator.next();
					
					returnObjects = new ArrayList<Object>();
					if (pqrsEntityFileRow.getRowNum() > 0 && pqrsEntityFileRow.getRowNum() <= pqrsEntityFileRowCount)
					{
						System.out.println("ROW - " + pqrsEntityFileRow.getRowNum());
						Iterator<Cell> iterator = pqrsEntityFileRow.cellIterator();
						PqrsEntity pqrsEntity = new PqrsEntity();						
						PqrsEntityType pqrsEntityTypeTemp;
						YearSurvey yearSurveyTemp;
						while (iterator.hasNext()) 
						{
							Cell hssfCell = (Cell) iterator.next();
							int cellIndex = hssfCell.getColumnIndex();
							
							switch (cellIndex) 
							{
							
							case 1:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntity.setName(stringResult);				                    
				                    System.out.println("pqrs entity name: " + stringResult);
				                  
				                    break;
								
								}
								break;
								
							case 2:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntity.setAlternateName(stringResult);				                    
				                    break;	
								
								}
								break;
	
							case 3:
								switch (hssfCell.getCellType()) 
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntityTypeTemp = pqrsEntityTypeService.findByName(stringResult);
				                    pqrsEntity.setPqrsEntityType(pqrsEntityTypeTemp);
				                    pqrsEntity.setEntityTypeId(pqrsEntityTypeTemp.getId());				                    
				                    break;
								
								}
								break;
							case 4:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntity.setEmailAddresses(stringResult);				                    
				                    break;	
								
								}
								break;
							case 5:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntity.setContactNumbers(stringResult);				                    
				                    break;	
				                //added for all numeric cases with no other character in between    
                                case Cell.CELL_TYPE_NUMERIC:	
				                	
				                    stringResult= Integer.toString((int)hssfCell.getNumericCellValue());
				                    pqrsEntity.setContactNumbers(stringResult);				                    
				                    break;	
								
								}
								break;
							case 6:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntity.setComments(stringResult);				                    
				                    
				                    YearSurvey yearSurvey = new YearSurvey();
				                    yearSurvey.setId(GenericConstants.OY2_YEAR_ID);
				                    pqrsEntity.setYearSurvey(yearSurvey);
				                    pqrsEntity.setYearId(yearSurvey.getId());
				                    pqrsEntity.setCreatedBy("mobeena");
				                    pqrsEntity.setRecordStatus(GenericConstants.ACTIVE_INT);
				                    service.create(pqrsEntity);
				                    model.addAttribute("entityuploadsuccess","success.save.entities");
				                    break;

								}
								
								break;
								
							
							}


						}
						
						
					}
 
				}

			}
			
		} catch (Exception e) {
			System.out.println("Exception in Pqrs Entity Upload page: " + e.getMessage());
			model.addAttribute("entityuploaderror","error.save.entities");
			//return null;
		}
		
		
		model.addAttribute("questionsFileUpload", new QuestionsFileUpload());
		model.addAttribute("pqrsEntityFileUpload", new PqrsEntityFileUpload());

		return returnView;

	}

	/**
	 * @return
	 *    service template method
	 */
	@Override
	protected IService<PqrsEntity> getService() {
		return service;
	}
}
