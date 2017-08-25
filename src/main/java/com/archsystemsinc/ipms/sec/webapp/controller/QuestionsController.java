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


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.common.EvaluateValidation;
import com.archsystemsinc.ipms.common.SearchValidation;
import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.QuestionType;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionCategoryService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionsService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyQuestionMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls administrator on creating, editing, deleting and searching Question information
 * 
 * @author 
 * @since
 */
@Controller
public class QuestionsController extends AbstractController<Questions> {

	@Autowired
	private IQuestionsService service;
	
	@Autowired
	private IQuestionTypeService questionTypeService;
	
	@Autowired
	private IQuestionCategoryService questionCategoryService;
	
	@Autowired
	private IPqrsEntityTypeService pqrsEntityTypeService;
	
	@Autowired
	private IYearService yearSurveyService;
	
	@Autowired
	private ISurveyQuestionMappingService surveyQuestionMappingService;
	
	
	public QuestionsController() {
		super(Questions.class);
	}
	
	private final Log log = LogFactory.getLog(QuestionsController.class);

	/**
	 *  validates search criteria and generates search results for questions
	 *  
	 * @param model
	 *      stores reference data, questions and active page information
	 * @param result
	 *      stores validation result 
	 * @return
	 *      list of questions page
	 */
	@RequestMapping(value = "/admin/questions/search")
	public String search(
			@Validated({SearchValidation.class}) @ModelAttribute("question") Questions question, BindingResult result, HttpServletRequest request, //@Valid @ModelAttribute("CreateQuestion") Questions question, BindingResult result,
			final Model model) {
		log.debug("Enter search");
		
		//It only validates year of question
		if(result.hasErrors()) {				
			String active = "questionadmin";
	        model.addAttribute("navigationstatus", active);	
			model.addAttribute("referenceData", referenceData());
            return "questions";
        }
		
		try {
			
			System.out.println(request.getParameter("entityTypeId"));
			System.out.println(request.getParameter("yearId"));
			
			PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(question.getEntityTypeId());
			YearSurvey yearSurveyTemp = yearSurveyService.findOne(question.getYearId());
			
			question.setYearSurvey(yearSurveyTemp);
			question.setPqrsEntityType(pqrsEntityTypeTemp);
			
			//adding active navigation
			String active = "questionadmin";
	        model.addAttribute("navigationstatus", active);
			//
			
			final List<Questions> questionsList = service.search(question);
			Collections.sort(questionsList);
			final ArrayList<Questions> questionsArrayList = new ArrayList<Questions>(
					questionsList);
			model.addAttribute("questions", questionsArrayList);
				
			model.addAttribute("referenceData", referenceData());
			model.addAttribute("question", question);
			 	
			log.debug("viewQuestions: Exit");
			
		} catch (Exception e) {
			log.error("Error in search:" + e.getMessage());
			model.addAttribute("failure", "failure.epinfo.search");
			model.addAttribute("errorMessage",e.getMessage());
		}
		log.debug("Exit search");
		return "questions";
	}
	
	/**
	 * search questions for the default hard coded year
	 * @param model
	 *      stores reference data, list of questions and active page information to be rendered 	
	 * @return
	 *      list of questions 
	 */
	@RequestMapping(value = "/admin/questions")
	public String questions(final Model model) {
		
		//Hard Coded Year to 2015
		YearSurvey yearSurvey = yearSurveyService.findOne(GenericConstants.OY2_YEAR_ID);  // Hard coded year
		PqrsEntityType pqrsEntityTypeTemp = null; //pqrsEntityTypeService.findOne(1);
		Questions question = new Questions();
		question.setYearSurvey(yearSurvey);
		question.setPqrsEntityType(pqrsEntityTypeTemp);
		question.setYearId(GenericConstants.OY2_YEAR_ID);
		String active = "questionadmin";
        model.addAttribute("navigationstatus", active);
		//final List<Questions> questions = service.findAll();
		
        final List<Questions> questions = service.search(question);
		Collections.sort(questions);
		final ArrayList<Questions> questionsArrayList = new ArrayList<Questions>(
				questions);
		model.addAttribute("questions", questionsArrayList);
		model.addAttribute("referenceData", referenceData());
		model.addAttribute("question", question);

		return "questions";
	}
	
	/**
	 * display details of a question
	 * 
	 * @param id
	 *      question id
	 * @param model
	 *      stores reference data of years and source type options list 
	 *      stores pqrs entity data and active page information to be rendered  
	 * @return
	 *      question detail view page
	 */		
	@RequestMapping(value = "/admin/question/{id}", method = RequestMethod.GET)
	public String viewQuestion(@PathVariable("id") final Long id,
			final Model model) {
		String active = "questionadmin";
        model.addAttribute("navigationstatus", active);
		final Questions question = service.findOne(id);
		
		model.addAttribute("question", question);
		model.addAttribute("referenceData", referenceData());
		return "question";
	}
	
	/**
	 * display form input for new question 
	 * @param model
	 *     stores reference data for year, source type options and active page information to be rendered  
	 * @return
	 *     new question adding page
	 */	
	@RequestMapping(value = "/admin/new-question", method = RequestMethod.GET)
	public String addQuestionGetMethod(final Model model, 
			QuestionCategory QuestionCategory) {
		
		String active = "questionadmin";
        model.addAttribute("navigationstatus", active);
		final Questions question = new Questions();
		model.addAttribute("question", question);
		model.addAttribute("referenceData", referenceData());
		return "questionadd";
	}
	
	/**
	 * display details of a question
	 * 
	 * @param id
	 *      question id
	 * @param model
	 *      stores reference data of years and source type options list 
	 *      stores pqrs entity data and active page information to be rendered  
	 * @return
	 *      question detail view page
	 */		
	@RequestMapping(value = "/admin/edit-question/{id}", method = RequestMethod.GET)
	public String updateQuestionGetMethod(@PathVariable("id") final Long id,
			final Model model) {
		//final Questions question = service.findOne(id);		
		final Questions question;
		String active = "questionadmin";
        model.addAttribute("navigationstatus", active);
		try {
			question = service.findByIdAndRecordStatus(id, GenericConstants.ACTIVE_INT);
			
			question.setQuestionCategoryId(question.getQuestionCategory().getId());
			question.setQuestionTypeId(question.getQuestionType().getId());
			question.setEntityTypeId(question.getPqrsEntityType().getId());
			question.setYearId(question.getYearSurvey().getId());
			
			model.addAttribute("question", question);
			model.addAttribute("referenceData", referenceData());	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error in edit question:" + e.getMessage());
		}		
		return "questionedit";
	}
	
	/**
	 * making updates on repository for edited question
	 * 
	 * @param questions
	 *      edited question data
	 * @param result
	 *      validation data
	 * @param model
	 *      stores list of years and source type options,
	 *      question and active page information to be rendered if input data is invalid
	 * @param redirectAttributes
	 *      success information message carrier to list of questions page
	 * @return
	 *      redirect to list of questions page
	 * @see
	 *     public String questions(final Model model) 
	 */
	@RequestMapping(value = "/admin/edit-question", method = RequestMethod.POST)
	public String updateQuestion(@Validated({ EvaluateValidation.class }) @ModelAttribute("question") final Questions question,
			    final BindingResult result, final Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		log.debug("Enter edit question");
		String returnView = "";
		String active = "questionadmin";		
        model.addAttribute("navigationstatus", active);
        Questions oldQuestion = null;
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd"); //format it as per your requirement
		
		if (result.hasErrors()) {
			//question edit display
			returnView = "questionedit"; 
			model.addAttribute("question", question); 
			model.addAttribute("referenceData", referenceData());
			result.getAllErrors();
		} else {
			
			try{
				QuestionCategory questionCategoryTemp = questionCategoryService.findOne(question.getQuestionCategoryId());
				QuestionType questionTypeTemp = questionTypeService.findOne(question.getQuestionTypeId());
				
				PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(question.getEntityTypeId());
				YearSurvey yearSurveyTemp = yearSurveyService.findOne(question.getYearId());
				
				
				question.setYearSurvey(yearSurveyTemp);
				question.setPqrsEntityType(pqrsEntityTypeTemp);
				question.setQuestionCategory(questionCategoryTemp);
				question.setQuestionType(questionTypeTemp);
				
				question.setUpdatedDate(currentDate.getTime());				
				question.setRecordStatus(GenericConstants.ACTIVE_INT);
				question.setQuestionDescription(question.getQuestionDescription());		
				
                oldQuestion = service.findByIdAndRecordStatus(question.getId(),GenericConstants.ACTIVE_INT);
                
                oldQuestion.setEntityTypeId(oldQuestion.getPqrsEntityType().getId());
                oldQuestion.setYearId( oldQuestion.getYearSurvey().getId());
                
				//Check if there is a change in source or year
                if(!oldQuestion.getEntityTypeId().equals(question.getEntityTypeId())  || !oldQuestion.getYearId().equals(question.getYearId())){
					question.setId(null);
					service.create(question);
					redirectAttributes.addFlashAttribute("success", "success.copy.question");
				}else{
					service.update(question);
					redirectAttributes.addFlashAttribute("success", "success.edit.question");
				}		
				
				returnView = "redirect:questions";				
				
			}catch(Exception e){
				log.error("Error in edit question:" + e.getMessage());				
			}			
			
		} 
		
		log.debug("Exit edit question");
		return returnView;
	}
	
	/**
	 * deletes question if there is no survey attached to it 
	 * When there are surveys attached to the question 
	 * it first need to be detached before deletion accordingly 
	 * user is forwarded to edit question page    
	 * 
	 * @param questionId
	 *      question id
	 * @param model     
	 * @param request
	 *      stores request data url
	 * @param redirectAttributes
	 *      stores success information message or error carrier on redirect request to questions list
	 *      stores error message and list of surveys attached to the question 
	 * @return
	 *      redirects to list of questions on success
	 *      redirects to edit question if there are attached surveys on the question
	 */
	@RequestMapping(value = "/admin/delete-question/{questionId}", method = RequestMethod.GET)
	public String deleteSurveyGet(@PathVariable("questionId") final Long questionId,final Model model,HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		
		log.debug("deleteQuestionGet: Enter");	
		String returnView = "";			
		StringBuffer requestUrl;
		requestUrl = request.getRequestURL();
		
		int appIndex = requestUrl.indexOf("app");
		String finalRequestUrl = requestUrl.substring(0, appIndex+4);
		
		try {
			final Questions question = service.findOne(questionId);		
			
			List<SurveyQuestionMapping> surveyQuestionMappings = surveyQuestionMappingService.findByQuestionAndRecordStatus(question, GenericConstants.ACTIVE_INT);
			
			if(surveyQuestionMappings.size() > 0){		
				redirectAttributes.addFlashAttribute("error", "error.delete.question");	
				redirectAttributes.addFlashAttribute("questionSurveysMap",referenceData(surveyQuestionMappings,finalRequestUrl));
				returnView = "redirect:../edit-question/" + questionId;
			}else{
				question.setEntityTypeId(question.getPqrsEntityType().getId());
				question.setQuestionCategoryId(question.getQuestionCategory().getId());
				question.setQuestionTypeId(question.getQuestionType().getId());
				question.setYearId(question.getYearSurvey().getId());
				question.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
				service.update(question);			
				redirectAttributes.addFlashAttribute("success", "success.delete.question");	
				returnView = "redirect:../questions";
			}
			
		} catch (Exception e) {
			log.error("Exception in deleteQuestion:"+ e.getMessage());
			System.out.println("Exception in deleteQuestion: " + e.getMessage()); 
			e.printStackTrace();
		}		
		log.debug("deleteQuestionGet: Exit");
			
		return returnView;
	}
		
	
	/**
	 * creates new question entry
	 * 
	 * @param pqrsEntity
	 *      data for new question
	 * @param result
	 *      data validation result on error
	 * @param model
	 * @param redirectAttributes
	 *      carries success message 
	 * @return
	 *      redirects request to list of questions
	 * 
	 */
	@RequestMapping(value = "/admin/new-question", method = RequestMethod.POST)
	public String addQuestion(@Validated({ EvaluateValidation.class }) @ModelAttribute("question") Questions question,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {
		
		log.debug("newQuestionPost: Enter");
		String returnView = "";
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd"); //format it as per your requirement
		String active = "questionadmin";
        model.addAttribute("navigationstatus", active);
        
			if (result.hasErrors()) {
				returnView = "questionadd";
				model.addAttribute("Question", question);
				model.addAttribute("referenceData", referenceData());
				result.getAllErrors();
			} else {
				QuestionCategory questionCategoryTemp = questionCategoryService.findOne(question.getQuestionCategoryId());
				QuestionType questionTypeTemp = questionTypeService.findOne(question.getQuestionTypeId());
				PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(question.getEntityTypeId());
				YearSurvey yearSurveyTemp = yearSurveyService.findOne(question.getYearId());
				
				question.setYearSurvey(yearSurveyTemp);
				question.setPqrsEntityType(pqrsEntityTypeTemp);
				question.setQuestionCategory(questionCategoryTemp);
				question.setQuestionType(questionTypeTemp);
				question.setCreatedDate(currentDate.getTime());
				question.setRecordStatus(GenericConstants.ACTIVE_INT);
				question = service.create(question);
				
				// returnView is changed from returnView = "forward:questions" to returnView = "redirect:questions";
				// to prevent multiple submission on refresh
				
				redirectAttributes.addFlashAttribute("success", "success.save.question");
				returnView = "redirect:questions";
			} 
		
			log.debug("newQuestionPost: Exit");
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
	
	/**
	 * stores reference data for list of years and source types
	 *      
	 * @return
	 *     map of years and source types
	 */
	protected Map referenceData() {
		final Map referenceData = new HashMap();
		
		final List<QuestionCategory> questionCategorylist = questionCategoryService.findAll();
		final Map<Integer, String> questionCategoryList = new LinkedHashMap<Integer, String>();
		
		for (int i = 0; i < questionCategorylist.size(); i++) {
			questionCategoryList.put(questionCategorylist.get(i).getId().intValue(), questionCategorylist.get(i).getName());
		}
		referenceData.put("questionCategoryList", questionCategoryList);
				
		final List<QuestionType> questionTypelist = questionTypeService.findAll();
		final Map<Integer, String> questionTypeList = new LinkedHashMap<Integer, String>();
		
		for (int i = 0; i < questionTypelist.size(); i++) {
			questionTypeList.put(questionTypelist.get(i).getId().intValue(), questionTypelist.get(i).getName());
		}
		referenceData.put("questionTypeList", questionTypeList);
		
		
		final List<PqrsEntityType> pqrsEntityTypeList = pqrsEntityTypeService.findAll();
		final Map<Long, String> pqrsEntityTypeMap = new LinkedHashMap<Long, String>();
		for(PqrsEntityType pqrsEntityType: pqrsEntityTypeList) {
			pqrsEntityTypeMap.put(pqrsEntityType.getId(), pqrsEntityType.getName());
		}	
		
		referenceData.put("pqrsEntityTypeMap", pqrsEntityTypeMap);
		
		final List<YearSurvey> yearSurveyList = yearSurveyService.findAll();
		final Map<Long, String> yearSurveyMap = new LinkedHashMap<Long, String>();
		for(YearSurvey yearSurvey: yearSurveyList) {
			yearSurveyMap.put(yearSurvey.getId(), yearSurvey.getName());
		}	
		
		referenceData.put("yearSurveyMap", yearSurveyMap);
	
		return referenceData;
	}
	
	//reference data for attached surveys
	/**
	 * map of attached surveys 
	 * 
	 * @param surveyQuestionMappings
	 *     list of survey question mapping
	 * @param finalRequestUrl
	 *     base request url to get access to the attached surveys
	 * @return
	 *     map of survey name to question
	 */  
	protected Map referenceData(List<SurveyQuestionMapping> surveyQuestionMappings,String finalRequestUrl) {
		log.debug("referenceData: Enter");
		final Map referenceData = new HashMap();
		Map<String, String> attachedSurveyNameLinkMap = null;
		
		
		if(surveyQuestionMappings != null && surveyQuestionMappings.size() > 0) {			
			attachedSurveyNameLinkMap = new LinkedHashMap<String, String>();
			
			for(SurveyQuestionMapping surveyQuestionMapping : surveyQuestionMappings){				
				attachedSurveyNameLinkMap.put(surveyQuestionMapping.getSurvey().getSurveyName(),
						finalRequestUrl + "admin/edit-survey/" + surveyQuestionMapping.getSurvey().getId());
			}	
		}
		
		referenceData.put("attachedSurveyNameLinkMap",attachedSurveyNameLinkMap);		
		log.debug("referenceData: Exit");
		return referenceData;
	}
	
}
