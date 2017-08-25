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
import com.archsystemsinc.ipms.sec.model.PqrsEntityFileUpload;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.QuestionType;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.QuestionsFileUpload;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.service.IAnswersService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionCategoryService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionsService;
import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;


/**
 * controls administrator on uploading Excel questions file
 * 
 * @author 
 * @since
 */
@Controller
public class QuestionsUploadController  extends AbstractController<Questions> {

	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private IQuestionsService service;
	
	@Autowired
	private IAnswersService answersService;
	
	@Autowired
	private IQuestionTypeService questionTypeService;
	
	@Autowired
	private IQuestionCategoryService questionCategoryService;
	
	@Autowired
	private IPqrsEntityTypeService pqrsEntityTypeService;
	
	@Autowired
	private IYearService yearSurveyService;
	
	
	public QuestionsUploadController() {
		super(Questions.class);
	}
	
	// Upload the questions file to the questions table
	
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
	@RequestMapping(value = "/admin/questionsupload", method = RequestMethod.POST)
	public String epInfoUploadPost(final Model model,
			QuestionsFileUpload questionsFileUpload, final Principal principal,
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
			
			if (questionsFileUpload.getQuestionsFile() != null) {
				
				Workbook questionsFileWorkbook = WorkbookFactory.create(questionsFileUpload.getQuestionsFile().getInputStream());
				Sheet questionsFileSheet = questionsFileWorkbook.getSheetAt(0);
				Iterator<Row> questionsFileRowIterator = questionsFileSheet.rowIterator();
                int questionsFileRowCount = questionsFileSheet.getPhysicalNumberOfRows();
				totalNumberOfRows = questionsFileRowCount - 1;
				String stringResult = "";

			
				
				//long yearId =  2;

				while (questionsFileRowIterator.hasNext()) 
				{
					Row questionsFileRow = (Row) questionsFileRowIterator.next();
					
					returnObjects = new ArrayList<Object>();
					//Conditional logic changed from if (questionsFileRow.getRowNum() > 0 && questionsFileRow.getRowNum() < 229)
					//to accommodate file with any number of rows
					if (questionsFileRow.getRowNum() > 0 && questionsFileRow.getRowNum() <= questionsFileRowCount)
					{
						System.out.println("ROW - " + questionsFileRow.getRowNum());
						Iterator<Cell> iterator = questionsFileRow.cellIterator();
						Questions questions = new Questions();
						QuestionType questionTypeTemp;
						QuestionCategory questionCategoryTemp;
						PqrsEntityType pqrsEntityTypeTemp;
						
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
				                    questions.setQuestionDescription(stringResult);
				                    System.out.println("question description" + stringResult);
				                  
				                    break;
								
								}
								break;
								
							case 2:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    questions.setAnswerIdString(stringResult);
				                    break;	
								
								}
								break;
	
							case 3:
								switch (hssfCell.getCellType()) 
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    questionTypeTemp = questionTypeService.findByName(stringResult);
				                    questions.setQuestionType(questionTypeTemp);
				                    questions.setQuestionTypeId(questionTypeTemp.getId());
				                    break;
								
								}
								break;
							case 4:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    questionCategoryTemp = questionCategoryService.findByName(stringResult);
				                    questions.setQuestionCategory(questionCategoryTemp);
				                    questions.setQuestionCategoryId(questionCategoryTemp.getId());
				                    break;
								
								}
								break;
							case 5:
								switch (hssfCell.getCellType())
								{
								
				                case Cell.CELL_TYPE_STRING:	
				                	
				                    stringResult=hssfCell.getStringCellValue();
				                    pqrsEntityTypeTemp = pqrsEntityTypeService.findByName(stringResult);
				                    questions.setPqrsEntityType(pqrsEntityTypeTemp);
				                    questions.setEntityTypeId(pqrsEntityTypeTemp.getId()); 
				                    
				                    YearSurvey yearSurvey = new YearSurvey();
				                    yearSurvey.setId(GenericConstants.OY2_YEAR_ID);
				                    questions.setYearSurvey(yearSurvey);
				                    questions.setYearId(yearSurvey.getId());
				                    questions.setCreatedBy("mobeena");
				                    questions.setRecordStatus(GenericConstants.ACTIVE_INT);
				                    service.create(questions);
				                    model.addAttribute("questionuploadsuccess","success.save.questions");
				                    break;

								}
								
								break;
								
							
							}


						}
						
						
					}
 
				}

			}
			
		} catch (Exception e) {
			System.out.println("Exception in Questions Upload page: " + e.getMessage());			
			model.addAttribute("questionuploaderror","error.save.questions");			
		}
		
		
		model.addAttribute("pqrsEntityFileUpload", new PqrsEntityFileUpload()); //added
		model.addAttribute("questionsFileUpload", new QuestionsFileUpload());

		return returnView;

	}

	/**
	 * @return
	 *    service template method
	 */
	@Override
	protected IService<Questions> getService() {
		return service;
	}
}
