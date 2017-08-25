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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityResponse;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.QuestionsList;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;
import com.archsystemsinc.ipms.sec.persistence.service.IAnswersService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityResponseService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionCategoryService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionsService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyEntityMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyQuestionMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.sec.webapp.controller.validator.SurveyValidator;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls entities (users) on accessing, editing, validating and submitting  survey
 * 
 * @author 
 * @since
 */
@Controller
public class SurveyEntityController extends
		AbstractController<PqrsEntityResponse> {
	
	@Autowired
	private SurveyValidator surveyValidator;

	@Autowired
	private IQuestionsService questionsService;

	@Autowired
	private IQuestionCategoryService questionCategoryService;

	@Autowired
	private IPqrsEntityResponseService service;

	@Autowired
	private IPqrsEntityService pqrsEntityService;
	
	@Autowired
	private ISurveyService surveyService;
	
	@Autowired
	private ISurveyEntityMappingService surveyEntityMappingService;
	
	@Autowired
	private ISurveyQuestionMappingService surveyQuestionMappingService;
	
	@Autowired
	private IAnswersService answersService;

	@Autowired
	private DefaultLobHandler defaultLobHandler;
	
	
	@InitBinder("questionsList")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(surveyValidator);
    
	}

	public SurveyEntityController() {
		super(PqrsEntityResponse.class);
	}

	private final Log log = LogFactory.getLog(SurveyEntityController.class);

	@Override
	protected IService<PqrsEntityResponse> getService() {
		return service;
	}	

	/**
	 * generates question and answer pages according to tab selection or tab order that presents data for each question
	 * category
	 * 
	 * @param tabid
	 *     id of a tab(question category)
	 * @param model
	 *     stores pqrs entity, survey mapping data, questions and survey id to be rendered
	 * @param entityId
	 *     id of entity
	 * @param surveyId
	 *     id of survey
	 * @param request
	 * @return
	 *     view page based on user (entity) tab selection
	 */
	@RequestMapping(value = "/view-usersurvey/{uid}/{sid}/{tabid}")
	public String viewUserSurvey(@PathVariable("tabid") final Integer tabid,
			final Model model, @PathVariable("uid") final Long entityId,
			@PathVariable("sid") final Long surveyId, HttpServletRequest request) {
		
		log.debug("viewUserSurvey: Enter");

		ArrayList<PqrsEntityResponse> pqrsEntityResponses = null;		
		Map<Long, PqrsEntityResponse> pqrsResponseMap = null;
		String returnView = "";
		QuestionCategory questionCategory = null;
	    PqrsEntity pqrsEntity = null;
	    Survey survey = null;
	    QuestionsList questionsListModel = null;
	    ArrayList<Questions> questionBankList = null;
	    SurveyEntityMapping surveyEntityMapping = null;	    
	    Integer categoryId = null;	    
		try {
			categoryId = tabid - 1;
			survey = surveyService.findByIdAndRecordStatus(surveyId, GenericConstants.ACTIVE_INT);
			pqrsEntity = pqrsEntityService.findByIdAndRecordStatus(entityId, GenericConstants.ACTIVE_INT);
			/*Code to retrieve the Answers for the Entity based on Survey Id*/
			
			if(tabid == 7) {				
				pqrsEntityResponses = (ArrayList<PqrsEntityResponse>) service.
						findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			} else {
				questionCategory = questionCategoryService.findOne(categoryId);
				pqrsEntityResponses = (ArrayList<PqrsEntityResponse>) service.findByPqrsEntityAndSurveyAndQuestionCategoryAndRecordStatus
						(pqrsEntity, survey, questionCategory, GenericConstants.ACTIVE_INT);
			}
			
			
			surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			if (pqrsEntityResponses != null && pqrsEntityResponses.size() > 0) {

				pqrsResponseMap = new LinkedHashMap<Long, PqrsEntityResponse>();
				
				for (PqrsEntityResponse pqrsEntityResponseTemp : pqrsEntityResponses) {
					pqrsResponseMap.put(pqrsEntityResponseTemp.getQuestion()
							.getId(), pqrsEntityResponseTemp);
				}

			} 			
			/*Setting up the Model Object for the JSP */
			questionsListModel = new QuestionsList();
			
			/* Navigation Based on tabId(Question Category Id)*/
			if (pqrsEntityResponses == null || pqrsEntityResponses.size() == 0) {
					Date surveyStartDate = new Date();
					surveyEntityMapping.setSurveyStartDate(surveyStartDate);
					surveyEntityMapping.setSurveyStatus(GenericConstants.SURVEY_IN_PROGRESS_STATUS);
					
					surveyEntityMapping = validateQuestionsAvaiabilityForSurvey(surveyEntityMapping);
					surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
					surveyEntityMappingService.update(surveyEntityMapping);
				
			}
			
			if(tabid == GenericConstants.TRAINING_INTEGER ) {				
				returnView = "traininginformation";
			} else if(tabid ==GenericConstants.DATA_HANDLING_INTEGER ) {
				returnView = "pqrsdatahandling";
			} else if(tabid ==GenericConstants.QA_INTEGER ) {
				returnView = "qualityassurance";
			} else if(tabid ==GenericConstants.ERX_INTEGER ) {
				returnView = "erx";
			} else if(tabid ==GenericConstants.FEEDBACK_INTEGER ) {			
				returnView = "feedback";
			} else if(tabid ==GenericConstants.REVIEW_INTEGER ) {
				
				if(surveyEntityMapping.getiAgreeFlag()!=null && surveyEntityMapping.getiAgreeFlag().equalsIgnoreCase("1")) {
					surveyEntityMapping.setiAgreeFlagBoolean(true);
				}		
				returnView = "viewuserreport";
			}
			
			//Retrieving the pre-populated (If Answers are Available) questionBankList for the Page
			questionBankList = getQuestionBankList(categoryId, pqrsResponseMap, survey);
			/*Setting Up the Model Attributes - Start*/
			String active = "usersurvey";
		    model.addAttribute("navigationstatus", active);
			model.addAttribute("pqrsEntity", pqrsEntity);
			model.addAttribute("surveyEntityMapping", surveyEntityMapping);
			model.addAttribute("questionsList", questionsListModel);
			model.addAttribute("surveyId", surveyId);
			model.addAttribute("referenceData", referenceData());
			model.addAttribute("questionbank", questionBankList);
			/*Setting Up the Model Attributes - End*/
		} catch (Exception e) {
			log.error("Exception in viewUserSurvey:"+e.getMessage());
			e.printStackTrace();
		}			
		log.debug("viewUserSurvey: Enter");
		return returnView;		
	}	
	
	/**
	 * sets complete flag of a question category for an entity survey 
	 * if there are no mappings to the survey for the given question category
	 * @param surveyEntityMapping
	 *     survey entity mapping data object
	 * @return
	 *     survey entity mapping data object
	 */
	private SurveyEntityMapping validateQuestionsAvaiabilityForSurvey(SurveyEntityMapping surveyEntityMapping) {
		
		
		QuestionCategory questionCategory = null;
		List<SurveyQuestionMapping> surveyQuestionMappings = null;
		
		for(int index=1; index<=5;index++) {
			
			switch(index) {
			
			case 1: questionCategory = questionCategoryService.findOne(index);
					surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(surveyEntityMapping.getSurvey(), questionCategory, GenericConstants.ACTIVE_INT);
					if(surveyQuestionMappings.size() == 0) {
						surveyEntityMapping.setTrainingCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}
					break;
			case 2: questionCategory = questionCategoryService.findOne(index);
					surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(surveyEntityMapping.getSurvey(), questionCategory, GenericConstants.ACTIVE_INT);
					if(surveyQuestionMappings.size() == 0) {
						surveyEntityMapping.setDataHandlingCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}
					break;
			case 3:questionCategory = questionCategoryService.findOne(index);
					surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(surveyEntityMapping.getSurvey(), questionCategory, GenericConstants.ACTIVE_INT);
					if(surveyQuestionMappings.size() == 0) {
						surveyEntityMapping.setQaCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}
					break;
			case 4:questionCategory = questionCategoryService.findOne(index);
					surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(surveyEntityMapping.getSurvey(), questionCategory, GenericConstants.ACTIVE_INT);
					if(surveyQuestionMappings.size() == 0) {
						surveyEntityMapping.setErxCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}
					break;
			case 5:questionCategory = questionCategoryService.findOne(index);
					surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(surveyEntityMapping.getSurvey(), questionCategory, GenericConstants.ACTIVE_INT);
					if(surveyQuestionMappings.size() == 0) {
						surveyEntityMapping.setFeedbackCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}
					break;
			}
			
		}
		
		return surveyEntityMapping;
	}
	
	/**
	 * generates survey page of an entity for a particular survey 
	 * 
	 * @param model
	 *     stores access status, active navigation menu, survey id
	 * @param surveyEntityMappingTemp
	 *     stores erx participation flag
	 * @param entityId
	 *     entity id
	 * @param surveyId
	 *     survey id
	 * @param request
	 *     stores erx participation flag that shows whether the entity had participated in erx or not
	 * @return
	 *     a corporate information view page for survey entry form is active. Depending on the status of the survey for an entity 
	 *     the page can be a starting page, survey in progress page or completed survey information  
	 */
	@RequestMapping(value = "/new-usersurvey/{uid}/{sid}")
	public String addPqrsEntityResponseGetMethod(final Model model,@ModelAttribute("surveyEntityMapping") SurveyEntityMapping surveyEntityMappingTemp,
			@PathVariable("uid") final Long entityId,
			@PathVariable("sid") final Long surveyId, HttpServletRequest request) {
		
		log.debug("addPqrsEntityResponseGetMethod: Enter");
		ArrayList<PqrsEntityResponse> pqrsEntityResponses = null;		
		Survey survey = null;		
		PqrsEntity pqrsEntity = null;
		SurveyEntityMapping surveyEntityMapping = null;
		    
		try {			
			
			survey = surveyService.findOne(surveyId);
			pqrsEntity = pqrsEntityService.findOne(entityId);
			
			/*Code to retrieve the Answers for the Entity based on Survey Id*/
			pqrsEntityResponses = (ArrayList<PqrsEntityResponse>) service.
					findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			if (request.getParameter("erxParticipationFlagAttribute") != null) {
				surveyEntityMapping.setErxParticipationFlag(surveyEntityMappingTemp.getErxParticipationFlag());
				pqrsEntity.setRecordStatus(GenericConstants.ACTIVE_INT);
				pqrsEntityService.update(pqrsEntity);
			}
			
			
			if (pqrsEntityResponses != null && pqrsEntityResponses.size() > 0) {
				
				model.addAttribute("initialAccess", false);				

			} else {
				
				model.addAttribute("initialAccess", true);
			}
			
			if(surveyEntityMapping == null) {
				surveyEntityMapping = new SurveyEntityMapping();
				model.addAttribute("success","survey.noteligible");
			}
			
			/*Displaying the home pages dynamically*/
			
			/*Setting Up the Model Attributes - Start*/
			String active = "usersurvey";
		   model.addAttribute("navigationstatus", active);
			model.addAttribute("surveyId", surveyId);
			model.addAttribute("referenceData", referenceData());
			model.addAttribute("pqrsEntity", pqrsEntity);
			model.addAttribute("surveyEntityMapping", surveyEntityMapping);
			/*Setting Up the Model Attributes - End*/
		} catch (Exception e) {
			log.error("Exception in addPqrsEntityResponseGetMethod:"+e.getMessage());
			e.printStackTrace();
		}	
		log.debug("addPqrsEntityResponseGetMethod: Enter");
		return "corporateinformation";

	}	
	
	
	/**
	 * stores entity response of a survey and generate view page of the survey for each question category 
	 * based on tab selection
	 * 
	 * @param model
	 *     stores list of questions of a survey, success message, entity data and survey id 
	 * @param tabid
	 *     active tab id of a selected question category
	 * @param questionsList
	 *     list of questions on selected category
	 * @param result
	 *     contains data validation information
	 * @param request
	 *     has entity id, survey id, page name and button action information
	 * @return
	 *     depending on the entity preference actions the next category tab or entity user home page is displayed
	 */
	@RequestMapping(value = "/addpqrsentityresponses/{tabid}", method = RequestMethod.POST)
	public String addPqrsEntityResponse(
			final Model model, @PathVariable("tabid") final Long tabid,
			@Valid @ModelAttribute("questionsList") final QuestionsList questionsList,
			final BindingResult result, HttpServletRequest request) {
		log.debug("addPqrsEntityResponse: Enter");
		String returnView = "";
		returnView = "";
		PqrsEntityResponse pqrsEntityResponse = null;
		Long entityId = null;
		Map<Long, PqrsEntityResponse> pqrsResponseMap = null;
		PqrsEntity pqrsEntity = null;
		QuestionCategory questionCategory = null;
		List<PqrsEntityResponse> pqrsEntityResponses = null;
		Long surveyId = null;
		Survey survey = null;
		MultipartFile uploadedFile = null;
		ByteArrayOutputStream outputStreamBuffer = null;
		String uploadedFileType = "";
		String uploadedFileName = "";
		SurveyEntityMapping surveyEntityMapping = null;
		try {

			entityId = Long.valueOf(request.getParameter("entityId"));
			surveyId = Long.valueOf(request.getParameter("surveyId"));
			pqrsEntity = pqrsEntityService.findOne(entityId);
			survey = surveyService.findOne(surveyId);
			surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			
			if(questionsList.getQuestionsList() != null) {
				
				questionCategory = questionCategoryService.findOne(questionsList
						.getQuestionsList().get(0).getQuestionCategoryId());
				pqrsEntityResponses = service.findByPqrsEntityAndSurveyAndQuestionCategoryAndRecordStatus(pqrsEntity, survey, questionCategory, GenericConstants.ACTIVE_INT);
						
				if (pqrsEntityResponses != null && pqrsEntityResponses.size() > 0) {

					pqrsResponseMap = new LinkedHashMap<Long, PqrsEntityResponse>();

					for (PqrsEntityResponse pqrsEntityResponseTemp : pqrsEntityResponses) {
						pqrsResponseMap.put(pqrsEntityResponseTemp.getQuestion()
								.getId(), pqrsEntityResponseTemp);
					}

				}			
				
				/*Code to get the answers from Model Object -Start*/
				for (Questions question : questionsList.getQuestionsList()) {

					if (pqrsResponseMap != null) {
						pqrsEntityResponse = pqrsResponseMap.get(question.getId());
					}

					String providedAnswer = getFinalAnswer(question);
					
					/*Code to get the answers from Model Object -End*/
					
					/*File Upload Code - Start */
					uploadedFile = question.getUploadFile();
					outputStreamBuffer = null;
					if(uploadedFile != null && !uploadedFile.isEmpty()) {
						InputStream inputStream = uploadedFile.getInputStream();
						uploadedFileName = uploadedFile.getOriginalFilename();
						outputStreamBuffer = new ByteArrayOutputStream();
						uploadedFile = question.getUploadFile();
						uploadedFileType = uploadedFile.getContentType();
				        int nRead;
				        byte[] data = new byte[16384];

				        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				        	outputStreamBuffer.write(data, 0, nRead);
				        }

				        outputStreamBuffer.flush();
					}		       
			        
			        /*File Upload Code - End */

					if (pqrsEntityResponse == null) {
						PqrsEntityResponse pqrsEntityResponseTemp = new PqrsEntityResponse();
						QuestionCategory questionCategoryTemp = questionCategoryService
								.findOne(question.getQuestionCategoryId());
						Questions questionTemp = questionsService.findOne(question
								.getId());
						pqrsEntityResponseTemp.setQuestion(questionTemp);
						pqrsEntityResponseTemp.setPqrsEntity(pqrsEntity);
						pqrsEntityResponseTemp
								.setQuestionCategory(questionCategoryTemp);
						pqrsEntityResponseTemp.setAnswerRemarks(providedAnswer);
						pqrsEntityResponseTemp.setSurvey(survey);
						if(outputStreamBuffer != null) {
							pqrsEntityResponseTemp.setUploadedFileContent(outputStreamBuffer.toByteArray());
							pqrsEntityResponseTemp.setUploadedFileName(uploadedFileName);
							pqrsEntityResponseTemp.setUploadedFileType(uploadedFileType);
						}
						pqrsEntityResponseTemp.setRecordStatus(GenericConstants.ACTIVE_INT);
						service.create(pqrsEntityResponseTemp);
						if(pqrsResponseMap == null) {
							pqrsResponseMap = new LinkedHashMap<Long, PqrsEntityResponse>();
						}
						pqrsResponseMap.put(questionTemp.getId(), pqrsEntityResponseTemp);
					} else {
						pqrsEntityResponse.setAnswerRemarks(providedAnswer);
						if(outputStreamBuffer != null) {
							pqrsEntityResponse.setUploadedFileContent(outputStreamBuffer.toByteArray());
							pqrsEntityResponse.setUploadedFileName(uploadedFileName);
							pqrsEntityResponse.setUploadedFileType(uploadedFileType);
						}
						pqrsEntityResponse.setRecordStatus(GenericConstants.ACTIVE_INT);
						service.update(pqrsEntityResponse);
						pqrsResponseMap.put(question.getId(), pqrsEntityResponse);
					}
					
				}
				
			} 

			model.addAttribute("questionsList", questionsList);
			
			
			/*Code for navigation of the page, depending on the btnAction request parameter*/
			String pageName = "";
			boolean errorResultFlag = result.hasErrors();
			
			if (request.getParameter("pageName") != null) {
				pageName = request.getParameter("pageName");
				if (pageName.equalsIgnoreCase("Training")) {
					if (errorResultFlag) {
						surveyEntityMapping.setTrainingCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);			
					}  else {
						surveyEntityMapping.setTrainingCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}					
				} else if (pageName.equalsIgnoreCase("DataHandling")) {						
					if (errorResultFlag) {
						surveyEntityMapping.setDataHandlingCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);			
					}  else {
						surveyEntityMapping.setDataHandlingCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}						
				} else if (pageName.equalsIgnoreCase("Qa")) {					
					if (errorResultFlag) {
						surveyEntityMapping.setQaCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);			
					}  else {
						surveyEntityMapping.setQaCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}					
				} else if (pageName.equalsIgnoreCase("Erx")) {
					if (errorResultFlag) {
						surveyEntityMapping.setErxCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);			
					}  else {
						surveyEntityMapping.setErxCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}						
				} else if (pageName.equalsIgnoreCase("Feedback")) {
					if (errorResultFlag) {
						surveyEntityMapping.setFeedbackCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);			
					}  else {
						surveyEntityMapping.setFeedbackCompleteFlag(GenericConstants.ACTIVE_FLAG);
					}						
				}			
				surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
				surveyEntityMappingService.update(surveyEntityMapping);
			}
			
			returnView = "forward:/app/userhomepage/" + entityId + "/" + surveyId;	
						
			if (request.getParameter("btnAction") != null) {
				String buttonAction = request.getParameter("btnAction");
				
					if(buttonAction.contains("Exit")) {
						
						model.addAttribute("success", "success.saveexit.page");
						returnView = "forward:/app/userhomepage/" + entityId + "/" + surveyId;						
						
					} else if(buttonAction.contains("Next")) {
						
						if (pageName.equalsIgnoreCase("Training")) {
							
							model.addAttribute("success", "success.savenext.page1");
							returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabid;

						} else if (pageName.equalsIgnoreCase("DataHandling")) {
							
							model.addAttribute("success", "success.savenext.page2");
							returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabid;
						} else if (pageName.equalsIgnoreCase("Qa")) {
							if (surveyEntityMapping.getErxParticipationFlag().equalsIgnoreCase("no1")) {
								Long tabidTemp = tabid + 1;
								returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabidTemp;
							} else {
								returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabid;
							}
							model.addAttribute("success", "success.savenext.page3");

						} else if (pageName.equalsIgnoreCase("Erx")) {
							
							model.addAttribute("success", "success.savenext.page4");
							returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabid;
							
						} else if (pageName.equalsIgnoreCase("Feedback")) {
												
							model.addAttribute("success", "success.savenext.page5");
							returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabid;							
						}			
						surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
						surveyEntityMappingService.update(surveyEntityMapping);						
					} 
					
			} else {
				if(tabid == 1) {
					returnView = "forward:/app/new-usersurvey/"+ entityId + "/" + surveyId ;
				} else {
					returnView = "forward:/app/view-usersurvey/"+ entityId + "/" + surveyId +"/" + tabid;
					                     
				}
				
			}
			model.addAttribute("pqrsEntity", pqrsEntity);
			model.addAttribute("surveyId", surveyId);
		} catch (Exception e) {
			log.error("Exception in addPqrsEntityResponse:"+e.getMessage());
			e.printStackTrace();
		}
		log.debug("addPqrsEntityResponse: Exit");
		return returnView;
	}
	
	
	/**
	 * All questions of a survey have to be answered before it is submitted 
	 * If survey is not complete it generates view page of left out questions
	 * 
	 * on completion of survey it updates survey entity information and
	 * generates view page of corporate information 
	 * 
	 * @param model
	 *     stores success message on survey complete
	 *     stores error message on left out questions
	 * @param surveyEntityMappingTemp
	 *     has agreement status information
	 * @param result
	 *     data validation information
	 * @param request
	 *     has entity id, survey id, button action and page parameter data
	 * @return
	 *     on survey completion it forwards to corporate information page of the survey and other tabs are deactivated
	 *     if it is not completed it forwards to incomplete questions review submit page
	 */
	@RequestMapping(value = "/submitsurvey", method = RequestMethod.POST)
	public String submitSurvey(final Model model,  
			@ModelAttribute("surveyEntityMapping") SurveyEntityMapping surveyEntityMappingTemp,
			final BindingResult result, HttpServletRequest request) {
		Long surveyId = null;
		Long entityId = null;
		PqrsEntity pqrsEntity = null;
		Survey survey = null;
		SurveyEntityMapping surveyEntityMapping = null;
		String returnView = "";
		
		try {
			Date surveyEndDate = new Date();
			entityId = Long.valueOf(request.getParameter("entityId"));
			surveyId = Long.valueOf(request.getParameter("surveyId"));
			pqrsEntity = pqrsEntityService.findOne(entityId);
			survey = surveyService.findOne(surveyId);
			surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			if(surveyEntityMappingTemp.isiAgreeFlagBoolean()) {
				surveyEntityMapping.setiAgreeFlag(GenericConstants.ACTIVE_FLAG);
				surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
				surveyEntityMappingService.update(surveyEntityMapping);
			} 
			if (request.getParameter("btnAction") != null 
					|| request.getParameter("pageName") != null) {
				String buttonAction = "";
				if (request.getParameter("btnAction") != null) {
					buttonAction = request.getParameter("btnAction");
				} else {
					buttonAction = request.getParameter("pageName");
				}
				
			returnView = "forward:view-usersurvey/"+ entityId + "/" + surveyId+"/7";
			if (buttonAction.contains("Submit")) {		
					 
					 if((surveyEntityMapping.getTrainingCompleteFlag()!=null && surveyEntityMapping.getTrainingCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
								(surveyEntityMapping.getDataHandlingCompleteFlag()!=null && surveyEntityMapping.getDataHandlingCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
										(surveyEntityMapping.getQaCompleteFlag()!=null && surveyEntityMapping.getQaCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
												(surveyEntityMapping.getErxParticipationFlag().equalsIgnoreCase("yes1") && surveyEntityMapping.getErxCompleteFlag()!=null && surveyEntityMapping.getErxCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
														(surveyEntityMapping.getFeedbackCompleteFlag()!=null && surveyEntityMapping.getFeedbackCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG))) {
							
						 	surveyEntityMapping.setSurveyCompleteFlag(GenericConstants.ACTIVE_FLAG);
						 	surveyEntityMapping.setSurveyStatus(GenericConstants.SURVEY_COMPLETE_STATUS);
						 	surveyEntityMapping.setSurveyEndDate(surveyEndDate);
						 	surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
						 	surveyEntityMappingService.update(surveyEntityMapping);
							returnView = "forward:new-usersurvey/" + entityId + "/" + surveyId;
							model.addAttribute("success", "success.submitsurvey.page6");
							
						} else if((surveyEntityMapping.getTrainingCompleteFlag()!=null && surveyEntityMapping.getTrainingCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
								(surveyEntityMapping.getDataHandlingCompleteFlag()!=null && surveyEntityMapping.getDataHandlingCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
								(surveyEntityMapping.getQaCompleteFlag()!=null && surveyEntityMapping.getQaCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) &&
										(surveyEntityMapping.getErxParticipationFlag().equalsIgnoreCase("no1")) &&
												(surveyEntityMapping.getFeedbackCompleteFlag()!=null && surveyEntityMapping.getFeedbackCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG))) {
									surveyEntityMapping.setSurveyEndDate(surveyEndDate);
									surveyEntityMapping.setSurveyCompleteFlag(GenericConstants.ACTIVE_FLAG);
									surveyEntityMapping.setSurveyStatus(GenericConstants.SURVEY_COMPLETE_STATUS);
									surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
									surveyEntityMappingService.update(surveyEntityMapping);
									returnView = "forward:new-usersurvey/" + entityId + "/" + surveyId;
									model.addAttribute("success", "success.submitsurvey.page6");
						}	else {
							StringBuffer errorString= new StringBuffer();
							errorString.append("Please complete the questions in following category (s) and save the response(s) before submitting the survey: " );
							if(surveyEntityMapping.getTrainingCompleteFlag()==null || !surveyEntityMapping.getTrainingCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) {
								errorString.append("Training Information Category,");
							}
							if(surveyEntityMapping.getDataHandlingCompleteFlag()==null || !surveyEntityMapping.getDataHandlingCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) {
							errorString.append("Data Handling Category,");
							}
							if(surveyEntityMapping.getQaCompleteFlag()==null || !surveyEntityMapping.getQaCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) {
								errorString.append("Quality Assurance Category,");
							}
							if(surveyEntityMapping.getErxParticipationFlag().equalsIgnoreCase("yes1") && 
									(surveyEntityMapping.getErxCompleteFlag()==null || !surveyEntityMapping.getErxCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG))) {
								errorString.append("eRx Category,");
							}
							if(surveyEntityMapping.getFeedbackCompleteFlag()==null || !surveyEntityMapping.getFeedbackCompleteFlag().equalsIgnoreCase(GenericConstants.ACTIVE_FLAG)) {
								errorString.append("Feedback Category,");
							}
							
							String finalErrorString = errorString.substring(0, errorString.length()-1)+".";
							model.addAttribute("errorMsg", finalErrorString);
							returnView = "forward:view-usersurvey/"+ entityId + "/" + surveyId+"/7";							
						}

				 	}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnView;
		
	}
	
	/**
	 * generates a string of answers to a question with delimiters based on type of question
	 * 
	 * @param question
	 *     a target question
	 * @return
	 *     string of answers to a question
	 */
	private String getFinalAnswer(Questions question) {
		StringBuffer providedAnswers = new StringBuffer();
		int arrayListIndex = 0;
		if (question.getFirstAnswerList() != null) {
			if(question.getQuestionTypeId() == 6 ||  question.getQuestionTypeId() == 7) {
				for (String singleFirstAnswer : question
						.getFirstAnswerList()) {
					providedAnswers.append(singleFirstAnswer.trim());
					providedAnswers
							.append(GenericConstants.IN_ANSWER_SEPERATOR);
				}
			} else {
				arrayListIndex = 0;
				for (String singleFirstAnswer : question
						.getFirstAnswerList()) {
					providedAnswers.append(singleFirstAnswer.trim());
					arrayListIndex ++;
					if (arrayListIndex != question
						.getFirstAnswerList().size()) {
						providedAnswers
						.append(GenericConstants.COMMA_STRING);
					}
					
				}
			}
			
			providedAnswers
					.append(GenericConstants.ANSWER_SEPERATOR);
		} else {
			providedAnswers.append(GenericConstants.NULL_STRING
					+ GenericConstants.ANSWER_SEPERATOR);
		}
		if (question.getSecondAnswerList() != null && question.getSecondAnswerList().size() > 0) {
			arrayListIndex = 0;
			for (String singleSecondAnswer : question
					.getSecondAnswerList()) {
				providedAnswers.append(singleSecondAnswer.trim());
				arrayListIndex++;
				if (arrayListIndex != question
						.getSecondAnswerList().size()) {
					providedAnswers
						.append(GenericConstants.COMMA_STRING);
				}
			}
			
			providedAnswers
					.append(GenericConstants.ANSWER_SEPERATOR);
		} else {
			providedAnswers.append(GenericConstants.NULL_STRING
					+ GenericConstants.ANSWER_SEPERATOR);
		}

		if (question.getThirdAnswerList() != null && question.getThirdAnswerList().size() > 0) {
			arrayListIndex = 0;
			for (String singleThirdAnswer : question
					.getThirdAnswerList()) {
				providedAnswers.append(singleThirdAnswer.trim());
				arrayListIndex++;
				if (arrayListIndex != question
						.getThirdAnswerList().size()) {
					providedAnswers
						.append(GenericConstants.COMMA_STRING);
				}
			}
			

		} else {
			providedAnswers.append(GenericConstants.NULL_STRING);
		}
		
		return providedAnswers.toString();
	}
	
	
/**
 * generates array of questions with its answers and remarks mapped to a survey of a given year, 
 * source type and question category of an entity
 * 
 * @param questionCategoryId
 *      id of question category
 * @param pqrsResponseMap
 *      has previous data for pqrs entity responses of all questions in a given survey
 * @param survey
 *      has survey information
 * @return
 *      array list of questions with their list of answers for the specified entity
 */
private ArrayList<Questions> getQuestionBankList(Integer questionCategoryId,Map<Long, PqrsEntityResponse> pqrsResponseMap,Survey survey ) {
	
	log.debug("getQuestionBankList: Enter");
		
		QuestionCategory questionCategory = null;
		//final List<Questions> questions = questionsService.findQuestionsByQuestionCategory(questionCategory);
		List<SurveyQuestionMapping> surveyQuestionMappings= null;
		final ArrayList<Questions> questionBankList = new ArrayList<Questions>();

		try {			
			
			if(questionCategoryId == 6) {
				surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndRecordStatus(survey, GenericConstants.ACTIVE_INT);
			} else {
				questionCategory = questionCategoryService.findOne(questionCategoryId);
				surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(survey, questionCategory, GenericConstants.ACTIVE_INT);
			}
			questionCategory = questionCategoryService.findOne(questionCategoryId);
			for (SurveyQuestionMapping surveyQuestionMapping : surveyQuestionMappings) {

				Questions question = surveyQuestionMapping.getQuestion();
					
					if (question.getAnswerIdString() != null
							&& !question.getAnswerIdString().equalsIgnoreCase("")) {

						String answerString = question.getAnswerIdString();
						List<String> answerList = Arrays
								.asList(answerString.split(","));
						ArrayList<String> answerList1 = new ArrayList<String>();
						for (String answerOption : answerList) {					
							answerList1.add(answerOption);
						}
						question.setAnswersArrayList(answerList1);
					}
				
				if (pqrsResponseMap != null) {
					PqrsEntityResponse pqrsEntityResponseTemp = pqrsResponseMap
							.get(question.getId());
					if (pqrsEntityResponseTemp != null) {
						String[] answersTemp;
						if (pqrsEntityResponseTemp.getAnswerRemarks() != null) {
							answersTemp = pqrsEntityResponseTemp.getAnswerRemarks()
									.split(GenericConstants.ANSWER_SEPERATOR);
							ArrayList<String> tempAnswerArrayList = null;
							if(answersTemp[0]==null || answersTemp[0].equalsIgnoreCase("") || answersTemp[0].equalsIgnoreCase("null")) {
								question.setFirstAnswerList(null);
							} else {
								String[] inAnswersTemp = answersTemp[0].split(GenericConstants.IN_ANSWER_SEPERATOR);
								tempAnswerArrayList = new ArrayList<String>();
								for(int index=0;index<inAnswersTemp.length;index++) {
									tempAnswerArrayList.add(inAnswersTemp[index]);
								}
								question.setFirstAnswerList(tempAnswerArrayList);
							}
							if(answersTemp[1]==null || answersTemp[1].equalsIgnoreCase("") || answersTemp[1].equalsIgnoreCase("null")) {
								question.setSecondAnswerList(null);
							} else {
								String[] inAnswersTemp = answersTemp[1].split(GenericConstants.IN_ANSWER_SEPERATOR);
								tempAnswerArrayList = new ArrayList<String>();
								for(int index=0;index<inAnswersTemp.length;index++) {
									tempAnswerArrayList.add(inAnswersTemp[index]);
								}
								question.setSecondAnswerList(tempAnswerArrayList);
							}
							if(answersTemp[2]==null || answersTemp[2].equalsIgnoreCase("") || answersTemp[2].equalsIgnoreCase("null")) {
								question.setThirdAnswerList(null);
							} else {
								String[] inAnswersTemp = answersTemp[2].split(GenericConstants.IN_ANSWER_SEPERATOR);
								tempAnswerArrayList = new ArrayList<String>();
								for(int index=0;index<inAnswersTemp.length;index++) {
									tempAnswerArrayList.add(inAnswersTemp[index]);
								}
								question.setThirdAnswerList(tempAnswerArrayList);
							}							
						}		
						
						if(pqrsEntityResponseTemp.getUploadedFileContent()!=null && pqrsEntityResponseTemp.getUploadedFileContent().length>0) {
							question.setUploadFileFlag(true);
							question.setUploadFileName(pqrsEntityResponseTemp.getUploadedFileName());							
							question.setUploadFileId(pqrsEntityResponseTemp.getId());
							
						}
					}
				}
				questionBankList.add(question);
			}
		} catch (Exception e) {
			log.error("Exception in getQuestionBankList:"+e.getMessage());
			e.printStackTrace();
		}
		log.debug("getQuestionBankList: Exit");
		return questionBankList;
		
	}	

/**
 * uploads a file as part of response (answer)
 * 
 * @param pqrsResponseId
 *      id of file uploaded
 * @param response
 *      stores file name
 * @return
 *      null
 */
@RequestMapping("/previewfile/{pqrsResponseId}")
public String previewFile(@PathVariable("pqrsResponseId")
		Long pqrsResponseId, HttpServletResponse response) {
	
	PqrsEntityResponse pqrsEntityResponse = null;
	try {
		pqrsEntityResponse = service.findOne(pqrsResponseId);
		response.setHeader("Content-Disposition", "infile;filename=\""+ pqrsEntityResponse.getUploadedFileName()+"\"");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		response.setContentType(pqrsEntityResponse.getUploadedFileType());
                    
                   byte[] buffer = pqrsEntityResponse.getUploadedFileContent();
                    InputStream is = new ByteArrayInputStream(buffer);
		IOUtils.copy(is, servletOutputStream);
		servletOutputStream.flush();
		servletOutputStream.close();
	
	} catch (IOException e) {
		e.printStackTrace();
	} 
	
	
	return null;
}

/**
 * contain reference data
 * 
 * @return
 *     empty reference data
 */
protected Map referenceData() {
		log.debug("referenceData: Enter");
		final Map referenceData = new HashMap();
		log.debug("referenceData: Exit");
		return referenceData;
 }

}
