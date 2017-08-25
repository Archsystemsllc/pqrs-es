
package com.archsystemsinc.ipms.sec.webapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.QuestionsList;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionCategoryService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionsService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyEntityMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyQuestionMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyService;
import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls administrator on creating, editing, deleting, email and searching of Survey information
 * 
 * @author 
 * @since
 */
@Controller
public class SurveyAdminController extends AbstractController<Survey> {

	@Autowired
	private ISurveyService service;
	
	@Autowired
	private IPqrsEntityService pqrsEntityService;
	
	@Autowired
	private ISurveyEntityMappingService surveyEntityMappingService;
	
	@Autowired
	private ISurveyQuestionMappingService surveyQuestionMappingService;
	
	@Autowired
	private IQuestionCategoryService questionCategoryService;
	
	@Autowired
	private IQuestionsService questionsService;
	
	@Autowired
	private IPqrsEntityTypeService pqrsEntityTypeService;
	
	@Autowired
	private IYearService yearSurveyService;
	
	
	@Autowired
	protected Emailer emailer;
	
	public SurveyAdminController() {
		super(Survey.class);
	}


	private final Log log = LogFactory.getLog(SurveyAdminController.class);


	@Override
	protected IService<Survey> getService() {
		return service;
	}
	
	
	/**
	 *  validates search criteria and generates list of surveys 
	 *  
	 * @param model
	 *      stores reference data, questions and active page information
	 *      reference data has pqrs entity source types and list of survey years      
	 * @param result
	 *      stores validation result 
	 * @return
	 *      list of surveys page or search query error notification message
	 */
	@RequestMapping(value = "/admin/survey/search")
	public String search(
				@Validated({SearchValidation.class}) @ModelAttribute("Survey") Survey survey, BindingResult result,
				final Model model) {
			log.debug("Enter search");
			
			if(result.hasErrors()) {				
				String active = "surveyadmin";
				model.addAttribute("navigationstatus", active);				
				model.addAttribute("referenceData", referenceData(null));
	            return "surveys";
	        }
			
			try {
				
				PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(survey.getEntityTypeId());
				
				YearSurvey yearSurveyTemp = yearSurveyService.findOne(survey.getYearId());
				
				survey.setYearSurvey(yearSurveyTemp);
				survey.setPqrsEntityType(pqrsEntityTypeTemp);
				
				//adding active navigation
				String active = "surveyadmin";
				model.addAttribute("navigationstatus", active);
				//
				
				List<Survey> surveyList = service.search(survey);
				
				Collections.sort(surveyList);
				final ArrayList<Survey> surveyArrayList = new ArrayList<Survey>(
						surveyList);
				model.addAttribute("surveys", surveyArrayList);
				model.addAttribute("referenceData", referenceData(null));
				model.addAttribute("Survey", survey);	
				log.debug("viewSurveys: Exit");
				
			} catch (Exception e) {
				log.error("Error in search:" + e.getMessage());
				model.addAttribute("failure", "failure.epinfo.search");
				model.addAttribute("errorMessage",e.getMessage());
			}
			log.debug("Exit search");
			return "surveys";			
		}

	/**
	 * generates send email page with survey details and entities mapped to the survey 
	 * 
	 * @param model
	 *     stores survey assigned entities and survey details 
	 * @param surveyId
	 *     id of target survey
	 * @return
	 *     view page of survey with assigned entities
	 */
	@RequestMapping(value = "/admin/sendemail/{surveyid}", method = RequestMethod.GET)
	public String sendEmailGetMethod(final Model model,
			@PathVariable("surveyid") final Long surveyId) {
		log.debug("sendEmailGetMethod: Enter");
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);
		HashMap<Long,String> surveyAssignedList = null;
		SurveyEntityMapping surveyEntityMapping = new SurveyEntityMapping();
        
        Survey survey = null;
		try {
			survey = service.findOne(surveyId);
			List<SurveyEntityMapping> surveyEntityMappings = 
					surveyEntityMappingService.findBySurveyAndRecordStatus(survey, GenericConstants.ACTIVE_INT);
			surveyAssignedList = new LinkedHashMap<Long,String>();
			for(SurveyEntityMapping surveyEntityMappingTemp: surveyEntityMappings) {
				surveyAssignedList.put(surveyEntityMappingTemp.getPqrsEntity().getId(), 
						surveyEntityMappingTemp.getPqrsEntity().getName());
			}
		} catch (Exception e) {
			log.error("Exception in sendEmailGetMethod:"+e.getMessage());
		}
        
        model.addAttribute("surveyAssignedList", surveyAssignedList);
        model.addAttribute("survey", survey);
        model.addAttribute("surveyEntityMapping", surveyEntityMapping);
        log.debug("sendEmailGetMethod: Exit");
		return "sendemail";
	}
	
	/**
	 * sends email for all entities mapped to the survey
	 * 
	 * @param model
	 *     stores active navigation bar information and success message to be rendered after email is sent
	 * @param surveyEntityMappingTemp
	 *     stores email subject and its content to be sent
	 * @param request
	 *     stores survey id
	 * @return
	 *     forwards request to surveys list
	 */
	@RequestMapping(value = "/admin/sendemail", method = RequestMethod.POST)
	public String sendEmail(final Model model, 
			@ModelAttribute("surveyEntityMapping") SurveyEntityMapping surveyEntityMappingTemp, HttpServletRequest request ) {
		log.debug("sendEmail: Enter");
		Long surveyId = null;
		Survey survey = null;
		PqrsEntity pqrsEntity = null;
		String active = "surveyadmin";
       
        try {
			String subject = surveyEntityMappingTemp.getEmailSubject();
			String body = surveyEntityMappingTemp.getEmailContent();
			surveyId = Long.valueOf(request.getParameter("surveyId"));
			survey = service.findOne(surveyId);
			pqrsEntity = pqrsEntityService.findOne(surveyEntityMappingTemp.getSurveyEntityId());
			SurveyEntityMapping surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);
			
			String[] toEmail = pqrsEntity.getEmailAddresses().split(",");
			surveyEntityMapping.setEmailContent(body);
			surveyEntityMapping.setEmailSubject(subject);
			
			String[] bcc ={"nahamad@archsystemsinc.com","mmohammed@archsystemsinc.com"};
			
			String replyTo ="surveyadmin@archsystemsinc.com";
			
			emailer.sendMail(toEmail, subject, body, null, bcc, replyTo);
			surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
			surveyEntityMappingService.update(surveyEntityMapping);
		} catch (Exception e) {
			log.error("Exception in sendEmail:"+e.getMessage());
		}
        model.addAttribute("navigationstatus", active);
        model.addAttribute("success", "email.sent");
        log.debug("sendEmail: Enter");
        return "forward:surveys";
	}
	
	/**
	 * searches surveys for the default hard coded year
	 * 
	 * @param model
	 *      stores reference data of source type and year options as well as list of surveys 
	 *      and active page information to be rendered 	
	 * @return
	 *      view page for list of surveys 
	 */
	@RequestMapping(value = "/admin/surveys")
	public String viewSurveys(final Model model) {
		log.debug("viewSurveys: Enter");
		String active = "surveyadmin";
        model.addAttribute("navigationstatus", active);
        
        YearSurvey yearSurvey = yearSurveyService.findOne(GenericConstants.OY2_YEAR_ID);  // Hard coded year
		PqrsEntityType pqrsEntityTypeTemp = null; //pqrsEntityTypeService.findOne(1);
		Survey survey = new Survey();
		survey.setYearSurvey(yearSurvey);
		survey.setPqrsEntityType(pqrsEntityTypeTemp);
		
		survey.setYearId(GenericConstants.OY2_YEAR_ID);
		
		final List<Survey> surveys = service.search(survey);
		Collections.sort(surveys);
		final ArrayList<Survey> surveysArrayList = new ArrayList<Survey>(
				surveys);
		model.addAttribute("surveys", surveysArrayList);
		
		model.addAttribute("referenceData", referenceData(null));
		
		model.addAttribute("Survey", survey);
		log.debug("viewSurveys: Exit");
		return "surveys";		
	}
	
	/**
	 * generates an empty form to add new survey
	 * 
	 * @param model
	 *     stores empty survey data object
	 * @return
	 *     view page for add survey form
	 */
	@RequestMapping(value = "/admin/new-survey", method = RequestMethod.GET)
	public String addSurveyGet(final Model model) {
		Survey survey = null;
		log.debug("addSurveyGet: Enter");
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);
		model.addAttribute("referenceData", referenceData(survey));
		survey = new Survey();
		model.addAttribute("survey", survey);
		
		log.debug("addSurveyGet: Exit");
		return "surveyadd";
	}	
	
	/**
	 * generates details of a survey to edit
	 * 
	 * @param surveyId
	 *     survey id
	 * @param model
	 *     stores survey data of a specified id
	 * @return
	 *     edit view page for survey details
	 */
	@RequestMapping(value = "/admin/edit-survey/{surveyid}", method = RequestMethod.GET)
	public String editSurveyGet(final Model model,@PathVariable("surveyid") final Long surveyId,HttpServletRequest request) {
		
		log.debug("editSurveyGet: Enter");	
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);
		Survey survey = service.findOne(surveyId);
		survey.setEntityTypeId(survey.getPqrsEntityType().getId());
		survey.setYearId(survey.getYearSurvey().getId());
		model.addAttribute("survey", survey);
		model.addAttribute("referenceData", referenceData(survey));
		StringBuffer requestUrl;
		requestUrl = request.getRequestURL();
		int appIndex = requestUrl.indexOf("app");
		String finalRequestUrl = requestUrl.substring(0, appIndex+4);
		finalRequestUrl = finalRequestUrl + "userhomepage/";
		model.addAttribute("finalRequestUrl", finalRequestUrl);
		log.debug("editSurveyGet: Exit");
		return "surveyedit";
	}	
	
	/**
	 * adds new survey to repository and page is redirected to list of surveys
	 * 
	 * @param role
	 *     stores new survey data
	 * @param result
	 *     data validation result for new survey 
	 * @param model
	 *     stores survey data and reference data on validation error
	 *     and final request url information to be rendered
	 * @param redirectAttributes
	 *     stores success message to list of surveys page
	 * @param request
	 *     stores request url
	 * @param session
	 * @return
	 *     redirect to list of surveys page on save survey
	 *     redirect to view assign questions page on add questions
	 * @see
	 *     public String surveys(final Model model)
	 *     public String viewSurveyAssignmentQuestions()
	 */
	@RequestMapping(value = "/admin/new-survey", method = RequestMethod.POST)
	public String addSurvey(@Validated({ EvaluateValidation.class }) @ModelAttribute("survey") Survey survey,
			final BindingResult result,final Model model,HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		log.debug("addSurvey: Enter");
		
		String returnView = "";
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);	
		
		if (result.hasErrors()) {			
			returnView = "surveyadd";
			model.addAttribute("survey", survey);
			model.addAttribute("referenceData", referenceData(null));			
		}else {
		
		PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(survey.getEntityTypeId());
		YearSurvey yearSurveyTemp = yearSurveyService.findOne(survey.getYearId());
		survey.setPqrsEntityType(pqrsEntityTypeTemp);
		survey.setYearSurvey(yearSurveyTemp);
		StringBuffer requestUrl;
		requestUrl = request.getRequestURL();
		int appIndex = requestUrl.indexOf("app");
		String finalRequestUrl = requestUrl.substring(0, appIndex+4);
		finalRequestUrl = finalRequestUrl + "userhomepage/";
		model.addAttribute("finalRequestUrl", finalRequestUrl);
		try {
				survey.setRecordStatus(GenericConstants.ACTIVE_INT);
				service.create(survey);	
				
				SurveyEntityMapping surveyEntityMapping = null;
				if(survey.getEntitySelectedList() != null) {
					
					
					String [] selectedEntitys = survey.getEntitySelectedList().split(",");
					for(String entity: selectedEntitys) {
						surveyEntityMapping = new SurveyEntityMapping();
						surveyEntityMapping.setSurvey(survey);					
						PqrsEntity pqrsEntityTemp = pqrsEntityService.findOne(Long.valueOf(entity));
						surveyEntityMapping.setErxParticipationFlag("no1");
						surveyEntityMapping.setSurveyCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setTrainingCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setDataHandlingCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setQaCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setErxCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setFeedbackCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setSurveyStatus(GenericConstants.SURVEY_READY_STATUS);
						surveyEntityMapping.setPqrsEntity(pqrsEntityTemp);
						surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);
						surveyEntityMappingService.create(surveyEntityMapping);
					}			
				}
			
			if (request.getParameter("btnAction") != null) {
				String buttonAction = request.getParameter("btnAction");
				
				if(buttonAction.equalsIgnoreCase("AddQuestions")) {
					returnView = "redirect:viewassignquestion/"+survey.getId()+"/1";
				} else if(buttonAction.equalsIgnoreCase("SaveSurvey")) {					
					redirectAttributes.addFlashAttribute("success", "success.save.survey");					
					returnView = "redirect:surveys";
				}
			}
		} catch (Exception e) {
			log.error("Exception in addSurvey:"+e.getMessage());
		}
		
		}
		log.debug("addSurvey: Exit");
		return returnView;
		
	}	
	
	/**
	 * making updates on repository for edited survey
	 * if either year or source type or both of the survey is changed 
	 * it creates a new copy of survey for the specified year and source type
	 * 
	 * @param survey
	 *     edited survey data
	 * @param result
	 *     validation data
	 * @param redirectAttributes
	 *     stores success message to list of survey page
	 * @param request
	 *     stores request url and button action information	     
	 * @param session
	 * @return
	 *     redirect to list of surveys page
	 * @see
	 *     public String surveys(final Model model)
	 */
	@RequestMapping(value = "/admin/edit-survey", method = RequestMethod.POST)
	public String editSurvey(@Validated({ EvaluateValidation.class }) @ModelAttribute("survey") Survey survey,
			final BindingResult result,final Model model,HttpServletRequest request,HttpSession session,final RedirectAttributes redirectAttributes) {	
		log.debug("editSurvey: Enter");
		String returnView = "";
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);	
		Survey oldsurvey = null;
		SurveyEntityMapping surveyEntityMapping = null;
		List <SurveyEntityMapping> surveyEntityMappingList = null;
		HashMap<Integer,Integer> entitySelectedMap = null;
		HashMap<Long,SurveyEntityMapping> entityAvailableMap = null;
		
		if (result.hasErrors()) {			
			returnView = "surveyedit";
			model.addAttribute("survey", survey);
			model.addAttribute("referenceData", referenceData(survey));			
		}else {
		PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(survey.getEntityTypeId());
		YearSurvey yearSurveyTemp = yearSurveyService.findOne(survey.getYearId());
		survey.setPqrsEntityType(pqrsEntityTypeTemp);
		survey.setYearSurvey(yearSurveyTemp);
		StringBuffer requestUrl;
		requestUrl = request.getRequestURL();
		int appIndex = requestUrl.indexOf("app");
		String finalRequestUrl = requestUrl.substring(0, appIndex+4);
		finalRequestUrl = finalRequestUrl + "userhomepage/";
		model.addAttribute("finalRequestUrl", finalRequestUrl);
		boolean backButtonFlag = false;
		boolean surveyNewCopy = false;
		
		if (request.getParameter("btnAction") != null) {
			String buttonAction = request.getParameter("btnAction");			
			
			if(buttonAction.equalsIgnoreCase("BackButton")) {			
				backButtonFlag = true;
			} 
		}
		
		if(!backButtonFlag) {			
			survey.setRecordStatus(GenericConstants.ACTIVE_INT);
			
			//added to create new copy
			oldsurvey = service.findByIdAndRecordStatus(survey.getId(),GenericConstants.ACTIVE_INT);            
			oldsurvey.setEntityTypeId(oldsurvey.getPqrsEntityType().getId());
			oldsurvey.setYearId( oldsurvey.getYearSurvey().getId());
            
			//Check if there is a change in source or year
			if(!oldsurvey.getEntityTypeId().equals(survey.getEntityTypeId())  || !oldsurvey.getYearId().equals(survey.getYearId())){
				survey.setId(null);
				survey.setEntitySelectedList(null);
				survey = service.create(survey);				
				surveyNewCopy = true;
			}else{
				service.update(survey);				
			}
			
			surveyEntityMappingList = surveyEntityMappingService.findBySurvey(survey);			
			if(surveyEntityMappingList !=null && surveyEntityMappingList.size() >0) {				 
				entityAvailableMap = new LinkedHashMap<Long,SurveyEntityMapping>();
				for(SurveyEntityMapping surveyEntityMappingTemp: surveyEntityMappingList) {
					entityAvailableMap.put(surveyEntityMappingTemp.getPqrsEntity().getId(), surveyEntityMappingTemp);
				}
			}
			
			if(survey.getEntitySelectedList() == null && surveyEntityMappingList !=null && surveyEntityMappingList.size() >0) {
				for(SurveyEntityMapping surveyEntityMappingTemp: surveyEntityMappingList) {
					surveyEntityMappingTemp.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
					surveyEntityMappingService.update(surveyEntityMappingTemp);
				}
			}			
			
			if(survey.getEntitySelectedList() != null) {			
				String [] selectedEntitys = survey.getEntitySelectedList().split(",");
				entitySelectedMap = new LinkedHashMap<Integer,Integer>();
				for(String selectedEntity: selectedEntitys) {
					entitySelectedMap.put(Integer.valueOf(selectedEntity), Integer.valueOf(selectedEntity));
				}				
				for(String selectedEntity: selectedEntitys) {
					PqrsEntity pqrsEntityTemp = pqrsEntityService.findOne(Long.valueOf(selectedEntity));					
			
					surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntityTemp, survey, GenericConstants.ACTIVE_INT);
					if(surveyEntityMapping == null) {
						surveyEntityMapping = new SurveyEntityMapping();
						surveyEntityMapping.setSurvey(survey);					
						surveyEntityMapping.setErxParticipationFlag("no1");
						surveyEntityMapping.setSurveyCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setTrainingCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setDataHandlingCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setQaCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setErxCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setFeedbackCompleteFlag(GenericConstants.IN_ACTIVE_FLAG);
						surveyEntityMapping.setSurveyStatus(GenericConstants.SURVEY_READY_STATUS);
						surveyEntityMapping.setPqrsEntity(pqrsEntityTemp);
						surveyEntityMapping.setPqrsEntityType(pqrsEntityTypeTemp);
						surveyEntityMapping.setYearSurvey(yearSurveyTemp);
						surveyEntityMapping.setRecordStatus(GenericConstants.ACTIVE_INT);						
						surveyEntityMappingService.create(surveyEntityMapping);
					} else {
						Integer selectedValueTemp = entitySelectedMap.get(Integer.valueOf(selectedEntity));
						if(selectedValueTemp == null) {
							surveyEntityMapping.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
							surveyEntityMappingService.update(surveyEntityMapping);
						}
					}
				}	
				
				if(entityAvailableMap != null && entityAvailableMap.size()>0) {
					for(Long entityMappingId: entityAvailableMap.keySet()) {
						Integer selectedValueTemp = entitySelectedMap.get(entityMappingId.intValue());
						if(selectedValueTemp == null) {
							SurveyEntityMapping surveyEntityMappingTemp = entityAvailableMap.get(entityMappingId);
							
							//surveyEntityMapping.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
							surveyEntityMappingTemp.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
							surveyEntityMappingService.update(surveyEntityMappingTemp);
						}
					}
				}				
			}
		} 
		
		if (request.getParameter("btnAction") != null) {
			String buttonAction = request.getParameter("btnAction");
			
			if(buttonAction.equalsIgnoreCase("AddQuestions")) {
				returnView = "redirect:viewassignquestion/"+survey.getId()+"/1";
			} else if(buttonAction.equalsIgnoreCase("UpdateSurvey")) {				
				if(surveyNewCopy){
					redirectAttributes.addFlashAttribute("success", "success.copy.survey");
				}else{
					redirectAttributes.addFlashAttribute("success", "success.update.survey");
				}				
				returnView = "redirect:surveys";
			}
		}
		
		}
		
		log.debug("editSurvey: Exit");
		return returnView;		
	}	
	
	
	/**
	 * deletes (soft delete) a survey from repository
	 * 
	 * @param surveyId
	 *     survey id to be deleted 
	 * @param model
	 *     stores success message
	 * @return
	 *     redirects to list of surveys page
	 */	
	@RequestMapping(value = "/admin/delete-survey/{surveyid}", method = RequestMethod.GET)
	public String deleteSurveyGet(final Model model,@PathVariable("surveyid") final Long surveyId,HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		
		log.debug("deleteSurveyGet: Enter");	
		String returnView = "";		
		
		try {
			final Survey survey = service.findOne(surveyId);		
			survey.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
			survey.setEntityTypeId(survey.getPqrsEntityType().getId());			
			survey.setYearId(survey.getYearSurvey().getId());
			service.update(survey);	
			redirectAttributes.addFlashAttribute("success", "success.delete.survey");
			//returnView = "forward:surveys";	
			returnView = "redirect:../surveys";	
		} catch (Exception e) {
			log.error("Exception in deleteSurvey:"+ e.getMessage());
			System.out.println("Exception in deleteSurvey: " + e.getMessage()); 
			e.printStackTrace();
		}		
		log.debug("deleteSurveyGet: Exit");
			
		return returnView;
	}
	
	/**
	 * generates assign question page of a survey
	 * 
	 * @param model
	 *     stores active navigation tab, questions related to the survey,
	 * @param surveyId
	 *     survey id of the survey to include questions to
	 * @param categoryId
	 *     category id of question
	 * @return
	 *     view page of a check list of questions related to the survey year, survey source type and 
	 *     question category type
	 */
	@RequestMapping(value = "/admin/viewassignquestion/{surveyid}/{categoryId}")
	public String viewSurveyAssignmentQuestions(final Model model,
			@PathVariable("surveyid") final Long surveyId,@PathVariable("categoryId") final Long categoryId) {
		log.debug("viewSurveyAssignmentQuestions: Enter");
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);
		String returnView = "";		
		QuestionCategory questionCategory = null;
		List<Questions> questionBankList = null;
		List<Questions> questionBankFinalList = null;
		List<SurveyQuestionMapping> questionsSelectedList = null;
		Survey survey = null;		
		HashMap<Long, Questions> questionsSelectedMap = null;
	    
		try {			
			questionCategory = questionCategoryService.findOne(categoryId);			
			survey = service.findOne(surveyId);
			PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(survey.getPqrsEntityType().getId());
			YearSurvey yearSurveyTemp = yearSurveyService.findOne(survey.getYearSurvey().getId());
			survey.setPqrsEntityType(pqrsEntityTypeTemp);
			survey.setYearSurvey(yearSurveyTemp);
			questionBankList = questionsService.findQuestionsByQuestionCategoryAndPqrsEntityTypeAndYearSurveyAndRecordStatus(questionCategory,pqrsEntityTypeTemp,yearSurveyTemp, GenericConstants.ACTIVE_INT);
			
			questionsSelectedList = surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(survey, questionCategory, GenericConstants.ACTIVE_INT);
			
			if (questionsSelectedList != null && questionsSelectedList.size() > 0) {

				questionsSelectedMap = new LinkedHashMap<Long, Questions>();
				
				for (SurveyQuestionMapping surveyQuestionMapping : questionsSelectedList) {
					questionsSelectedMap.put(surveyQuestionMapping.getQuestion()
							.getId(), surveyQuestionMapping.getQuestion());
				}

			} 		
			questionBankFinalList = new ArrayList<Questions>();
			if(questionBankList != null) {
				for(Questions question: questionBankList) {
					
					Questions questionTemp = null;
					if(questionsSelectedMap != null) {
						questionTemp = questionsSelectedMap.get(question.getId());
					}					
					if(questionTemp!=null) {
						question.setQuestionSelected(true);
						questionBankFinalList.add(question);
					} else {
						questionBankFinalList.add(question);
					}					
				}
			}
			
			model.addAttribute("questionbank", questionBankFinalList);
			model.addAttribute("surveyid", surveyId);
			QuestionsList questionsList = new QuestionsList();
			model.addAttribute("questionsList", questionsList);
			
			System.out.println(model);
			if(categoryId == 1 ) {
				returnView = "assigntraining";
			} else if(categoryId ==2 ) {
				returnView = "assigndatahandling";
			} else if(categoryId ==3 ) {
				returnView = "assignqa";
			} else if(categoryId ==4 ) {
				returnView = "assignerx";
			} else if(categoryId ==5 ) {		
				returnView = "assignfeedback";
			} 

		} catch (Exception e) {
			log.error("Error in viewSurveyAssignmentQuestions:" + e.getMessage());
			System.out.println("Error Message: " + e.getMessage());
		}		
		log.debug("viewSurveyAssignmentQuestions: Exit");
		return returnView;
		
	}
	
	/**
	 * assigns selected questions of a particular question category to the survey
	 * 
	 * @param model
	 *     stores success message
	 * @param questionsList
	 *     stores questions related to the survey and question  
	 *     for the particular year and source type of survey and question under the selected category
	 * @param result
	 * @param surveyId
	 *     survey id
	 * @param categoryId
	 *     category id
	 * @param session
	 * @return
	 *     forwards request to view assign question page
	 * @see
	 *    public String viewSurveyAssignmentQuestions()
	 */
	@RequestMapping(value = "/admin/saveassignquestion/{surveyid}/{categoryId}")
	public String saveSurveyAssignmentQuestions(final Model model, @ModelAttribute("questionsList") final QuestionsList questionsList,final BindingResult result,
			@PathVariable("surveyid") final Long surveyId,@PathVariable("categoryId") final Long categoryId,HttpSession session) {
		log.debug("saveSurveyAssignmentQuestions: Enter");
		String returnView = "";
		String active = "surveyadmin";
		model.addAttribute("navigationstatus", active);
		List<SurveyQuestionMapping> questionsDbList = null;
		HashMap<Long, SurveyQuestionMapping> questionsDbMap = null;
		Survey survey = null;
		QuestionCategory questionCategory = null;
		
		try {
			
			model.addAttribute("surveyid", surveyId);
			
			model.addAttribute("questionsList", questionsList);
			
			questionCategory = questionCategoryService.findOne(categoryId);
			survey = service.findOne(surveyId);
			
			questionsDbList = surveyQuestionMappingService.findBySurveyAndQuestionCategory(survey, questionCategory);
			
			if (questionsDbList != null && questionsDbList.size() > 0) {

				questionsDbMap = new LinkedHashMap<Long, SurveyQuestionMapping>();
				
				for (SurveyQuestionMapping surveyQuestionMappingTemp : questionsDbList) {
					questionsDbMap.put(surveyQuestionMappingTemp.getQuestion()
							.getId(), surveyQuestionMappingTemp);
				}

			}
			if (questionsList.getQuestionsList() !=null && questionsList.getQuestionsList().size() >= 0) {
				
				for(Questions question: questionsList.getQuestionsList()) {
					
					if(question.isQuestionSelected()) {
						SurveyQuestionMapping surveyQuestionMappingTemp = null;
						if(questionsDbMap != null) {
							surveyQuestionMappingTemp = questionsDbMap.get(question.getId());
						}
						
						if(surveyQuestionMappingTemp == null) {
							surveyQuestionMappingTemp = new SurveyQuestionMapping();
							surveyQuestionMappingTemp.setSurvey(survey);					
							Questions questionTemp2 = questionsService.findOne(question.getId());
							surveyQuestionMappingTemp.setQuestion(questionTemp2);
							surveyQuestionMappingTemp.setQuestionCategory(questionTemp2.getQuestionCategory());
							surveyQuestionMappingTemp.setRecordStatus(GenericConstants.ACTIVE_INT);
							surveyQuestionMappingService.create(surveyQuestionMappingTemp);
						} else {						
								surveyQuestionMappingTemp.setRecordStatus(GenericConstants.ACTIVE_INT);
								surveyQuestionMappingService.update(surveyQuestionMappingTemp);						
						}					
						
					} else {
						SurveyQuestionMapping surveyQuestionMappingTemp = null;
						if(questionsDbMap != null) {
							surveyQuestionMappingTemp = questionsDbMap.get(question.getId());
						}
						
						if(surveyQuestionMappingTemp != null) {
							surveyQuestionMappingTemp.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
							surveyQuestionMappingService.update(surveyQuestionMappingTemp);
						}
					}					
				}				
			}	
			
			if(categoryId == 1 ) {
				model.addAttribute("success", "success.assignquestions.page1");
				returnView = "forward:/app/admin/viewassignquestion/" + surveyId +"/2";
			} else if(categoryId ==2 ) {
				model.addAttribute("success", "success.assignquestions.page2");
				returnView = "forward:/app/admin/viewassignquestion/" + surveyId +"/3";
			} else if(categoryId ==3 ) {
				model.addAttribute("success", "success.assignquestions.page3");
				returnView = "forward:/app/admin/viewassignquestion/" + surveyId +"/4";
			} else if(categoryId ==4 ) {
				model.addAttribute("success", "success.assignquestions.page4");
				returnView = "forward:/app/admin/viewassignquestion/" + surveyId +"/5";
				questionsDbList = surveyQuestionMappingService.findBySurveyAndQuestionCategoryAndRecordStatus(survey, questionCategory, GenericConstants.ACTIVE_INT);
				List<SurveyEntityMapping> selectedEntitys = surveyEntityMappingService.findBySurveyAndRecordStatus(survey, GenericConstants.ACTIVE_INT);
				if (questionsDbList != null && questionsDbList.size() > 0) {					
					
					for(SurveyEntityMapping surveyEntityMapping: selectedEntitys) {
						surveyEntityMapping.setErxParticipationFlag("yes1");
						surveyEntityMappingService.update(surveyEntityMapping);							
					}
				} else {
					for(SurveyEntityMapping surveyEntityMapping: selectedEntitys) {
						surveyEntityMapping.setErxParticipationFlag("no1");
						surveyEntityMappingService.update(surveyEntityMapping);	
					}
				}
			} else if(categoryId ==5 ) {		
				model.addAttribute("success", "success.assignquestions.page5");
				returnView = "forward:/app/admin/viewassignquestion/" + surveyId +"/5";
			} 

		} catch (Exception e) {
			log.error("Error in saveSurveyAssignmentQuestions:" + e.getMessage());
			System.out.println("Error in save survey assignment question:" + e.getMessage());
		}		
		log.debug("saveSurveyAssignmentQuestions: Exit");
		return returnView;
		
	}
	
	/**
	 * stores reference data of the entities related to survey based on the year and source type of the survey
	 * It also stores selected entities for the survey.
	 * 
	 * @param survey
	 *      target survey 
	 * @return
	 *      map  of related data to a survey
	 */
	protected Map referenceData(Survey survey) {
		log.debug("referenceData: Enter");
		final Map referenceData = new HashMap();		
		Map<Long, String> pqrsEntitySelectedMap = null;
		Map<Long, String> pqrsEntityMap = null;
		
		if(survey != null) {
			
			final List<PqrsEntity> pqrsEntityList = pqrsEntityService.findByPqrsEntityTypeAndYearSurveyAndRecordStatus(survey.getPqrsEntityType(), survey.getYearSurvey(), GenericConstants.ACTIVE_INT);
					pqrsEntityMap = new LinkedHashMap<Long, String>();
					for(PqrsEntity pqrsEntity: pqrsEntityList) {
						pqrsEntityMap.put(pqrsEntity.getId(), pqrsEntity.getName());
			}	
			
			final List<SurveyEntityMapping> pqrsEntitySelectedList = surveyEntityMappingService.findBySurveyAndRecordStatus(survey, GenericConstants.ACTIVE_INT);
			pqrsEntitySelectedMap = new LinkedHashMap<Long, String>();
			for(SurveyEntityMapping surveyEntityMapping: pqrsEntitySelectedList) {
				pqrsEntitySelectedMap.put(surveyEntityMapping.getPqrsEntity().getId(),surveyEntityMapping.getPqrsEntity().getName());
				pqrsEntityMap.remove(surveyEntityMapping.getPqrsEntity().getId());
			}	
			
		}
		
		referenceData.put("pqrsEntitySelectedMap", pqrsEntitySelectedMap);
		
		referenceData.put("pqrsEntityMap", pqrsEntityMap);
		
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
		
		
		log.debug("referenceData: Exit");
		return referenceData;
	}
}
