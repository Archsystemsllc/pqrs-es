package com.archsystemsinc.
ipms.sec.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.poi.service.ExcelDownloadService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityResponse;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionAndAnswerReport;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.service.IAnswersService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityResponseService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionsService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyEntityMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyQuestionMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyService;
import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls administrator on creating, and searching Report information
 * 
 * @author 
 * @since
 */
@Controller
public class ReportsController extends AbstractController<PqrsEntity>  {
	
	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private IPqrsEntityService pqrsEntityService;
	
	@Autowired
	private IAnswersService answersService;
	
	@Autowired
	private IPqrsEntityTypeService pqrsEntityTypeService;
	
	@Autowired
	private IPqrsEntityResponseService pqrsEntityResponseService;
	
	@Autowired
	private ISurveyEntityMappingService surveyEntityMappingService;
		
	@Autowired
	private ISurveyService surveyService;
	
	@Autowired
	private ISurveyQuestionMappingService surveyQuestionMappingService;
	
	@Autowired
	private IYearService yearSurveyService;
	
	
	@Autowired
	private IQuestionsService questionService;
	
	@Autowired
	private ExcelDownloadService excelDownloadService;
	

	private final Log log = LogFactory.getLog(ReportsController.class);

	
	public ReportsController() {
		super(PqrsEntity.class);
	}
	
    /**
     * generates pdf report
     * 
     * @param modelAndView
     *     stores list of pqrs entities
     * @return
     *     pdf report
     */      
	@RequestMapping(value = "/htmlreport1")
    public ModelAndView generateHtmlReport(ModelAndView modelAndView){
 
        logger.debug("--------------generate HTML report----------");
 
        Map<String,Object> parameterMap = new HashMap<String,Object>();
 
        final List<PqrsEntity> pqrsEntitys = pqrsEntityService.findAll();
 
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(pqrsEntitys);
 
        parameterMap.put("datasource", JRdataSource);
 
        //xlsReport bean has been declared in the jasper-views.xml file
        modelAndView = new ModelAndView("htmlReport", parameterMap);
 
        return modelAndView;
 
    }//generatePdfReport
	

	/**
	 * generates list of pqrs entity to survey mapping with the survey status information
	 * 
	 * @param surveyEntityMapping
	 *     stores null value for default search query
	 *     stores selected source type and year on search query      
	 * @param model
	 *     stores list of survey entity mapping, selected survey entity map and reference data 
	 *     for year and entity type options for rendering
	 * @return
	 *     view page for list of reports on entity survey status
	 */
	@RequestMapping(value = "/admin/groupreport-pqrsentity")
	public String groupReportPqrsEntityGet(@ModelAttribute("surveyentitymapping") SurveyEntityMapping surveyEntityMapping, final Model model) {
		
		String active = "reports";
        model.addAttribute("navigationstatus", active);
        
        YearSurvey yearSurvey = null;
		PqrsEntityType pqrsEntityTypeTemp = null; 
		try {
			if (surveyEntityMapping.getEntityTypeId() == null && surveyEntityMapping.getYearId() == null) {
				surveyEntityMapping = new SurveyEntityMapping();
				yearSurvey = yearSurveyService.findOne(GenericConstants.OY2_YEAR_ID);  // Hard coded year
				surveyEntityMapping.setYearId(GenericConstants.OY2_YEAR_ID);
			} else {				
				if(surveyEntityMapping.getYearId() == null){					
					yearSurvey = yearSurveyService.findOne(GenericConstants.OY2_YEAR_ID);
					pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(surveyEntityMapping.getEntityTypeId());
				}else if (surveyEntityMapping.getEntityTypeId() == null){
					yearSurvey = yearSurveyService.findOne(surveyEntityMapping.getYearId());
				}else{
					yearSurvey = yearSurveyService.findOne(surveyEntityMapping.getYearId());
					pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(surveyEntityMapping.getEntityTypeId());
				}
			}				
			surveyEntityMapping.setYearSurvey(yearSurvey);
			surveyEntityMapping.setPqrsEntityType(pqrsEntityTypeTemp);
			
			final List<SurveyEntityMapping> surveyEntityMappinglist = surveyEntityMappingService.search(surveyEntityMapping);
			
			final ArrayList<SurveyEntityMapping> surveyEntityMappingArraylist = new ArrayList<SurveyEntityMapping>(
					 surveyEntityMappinglist);
			model.addAttribute("SurveyEntityMappinglist", surveyEntityMappingArraylist);
			model.addAttribute("surveyentitymapping", surveyEntityMapping);
			model.addAttribute("referenceData", referenceData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "groupreport-pqrsentity";	
	}
	
	/*
	 * All the reports are placed in this report page.
	 */
	/**
	 * generates reports page with group and Q&A report options
	 * 
	 * @param model
	 *     stores list of entity with their surveys
	 * @return
	 *     group and Q&A report options page
	 */
	
	@RequestMapping(value = "/admin/allreport")
	public String allReportPqrsEntityGet(final Model model) {
		
		String active = "reports";
        model.addAttribute("navigationstatus", active);
		final List<SurveyEntityMapping> surveyEntityMappinglist = surveyEntityMappingService.findAllByRecordStatus(GenericConstants.ACTIVE_INT);
		final ArrayList<SurveyEntityMapping> surveyEntityMappingArraylist = new ArrayList<SurveyEntityMapping>(
				 surveyEntityMappinglist);
		model.addAttribute("SurveyEntityMappinglist", surveyEntityMappingArraylist);
	    System.out.println("inside the group report");
		return "allreport";
	}
	
	/*
	 * 
	 * This is the get method for Question & Answer report
	 */
	
	/**
	 * generates download page for Question and answer report
	 * 
	 * @param model
	 *  stores list of survey entity mapping, survey entity map and reference data 
	 *     for year and entity source options for rendering
	 * @return
	 *     download page
	 */
	
	@RequestMapping(value = "/admin/questionandanswer")
	public String questionAndAnswerGet(final Model model) {
		
		String active = "reports";
        model.addAttribute("navigationstatus", active);
		System.out.println("inside the questionAndAnswerGet");
	    SurveyEntityMapping surveyEntityMapping = new SurveyEntityMapping();
	    model.addAttribute("surveyEntityMapping", surveyEntityMapping);
        model.addAttribute("navigationstatus", active);
		model.addAttribute("referenceData", referenceData());
		return "questionandanswer-report";
	}
	
	/*
	 * Question & Answer Report
	 * 
	 * sends stream of data and message through session
	 * 
	 */
	/**
	 * generates download file for all report of Question and answer entities under the selected year and entity 
	 * source type
	 * 
	 * @param surveyEntityMapping
	 *     stores selected year and entity source type
	 * @param model
	 *     stores question and answer list
	 * @param response
	 *     stores stream of data and redirect uri for validation error
	 * @param session
	 *     stores temporary validation error message
	 * @param request
	 *     stores request uri
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/questionandanswerreport/search", method = RequestMethod.POST)
	public void questionAndAnswerReportPost(
			@ModelAttribute("surveyEntityMapping") SurveyEntityMapping surveyEntityMapping,
			final Model model, HttpServletResponse response, HttpSession session,HttpServletRequest request){
		log.info("Enter Electronic Survey Question & Answer Report");
		PqrsEntityType pqrsEntityTypeTemp = null;
		YearSurvey yearSurveyTemp = null;
		//List<SurveyEntityMapping> surveyEntityMappingFinalList = new ArrayList<QuestionAndAnswerReport>();
		List<SurveyEntityMapping> surveyEntityMappingList = null;
		
		List<QuestionAndAnswerReport> questionAndAnswerFinalList = new ArrayList<QuestionAndAnswerReport>();
		
		TreeMap<String, String> questionAndAnswerFinalMap = new TreeMap<String, String>();
		
		ArrayList<PqrsEntityResponse> pqrsEntityResponses = null;		
		Map<Long, PqrsEntityResponse> pqrsResponseMap = null;
		String returnView = "";
		String entityType = "";
		ArrayList<HashMap<Long,Questions>> testObject = new ArrayList<HashMap<Long,Questions>>();
		TreeMap<Long, Questions> questionsFinalMap = new TreeMap<Long, Questions>();
		TreeMap <Long, Questions> questionBankMap = null;
		Set<String> entityNames = new HashSet<String>();
		Questions questionTemp = null;
		try {			
			Long EntityId = surveyEntityMapping.getEntityTypeId();
			Long yearId =  surveyEntityMapping.getYearId();
			
			/*
			 * checks validity of input values
			 */			
			if (EntityId != null && yearId != null) {
				pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(EntityId);
				surveyEntityMapping.setPqrsEntityType(pqrsEntityTypeTemp);
				entityType = pqrsEntityTypeTemp.getName();
				yearSurveyTemp = yearSurveyService.findOne(yearId);
				surveyEntityMapping.setYearSurvey(yearSurveyTemp);
				surveyEntityMappingList = surveyEntityMappingService.search(surveyEntityMapping);
			}
			
			if(surveyEntityMappingList  != null) {
			
			for (SurveyEntityMapping surveyEntityMappingTemp : surveyEntityMappingList) {
				questionBankMap = new TreeMap<Long, Questions> ();
				pqrsEntityResponses = (ArrayList<PqrsEntityResponse>) pqrsEntityResponseService.
							findByPqrsEntityAndSurveyAndRecordStatus(surveyEntityMappingTemp.getPqrsEntity(), surveyEntityMappingTemp.getSurvey(), GenericConstants.ACTIVE_INT);		
				
				if (pqrsEntityResponses != null && pqrsEntityResponses.size() > 0) {

					pqrsResponseMap = new LinkedHashMap<Long, PqrsEntityResponse>();
					
					for (PqrsEntityResponse pqrsEntityResponseTemp : pqrsEntityResponses) {
						pqrsResponseMap.put(pqrsEntityResponseTemp.getQuestion()
								.getId(), pqrsEntityResponseTemp);
					}
				}
				
					
				List<SurveyQuestionMapping> surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndRecordStatus(surveyEntityMappingTemp.getSurvey(), GenericConstants.ACTIVE_INT);
					
					for (SurveyQuestionMapping surveyQuestionMapping : surveyQuestionMappings) {

						Questions question = surveyQuestionMapping.getQuestion();				
								
						question.setEntityTypeId(surveyEntityMappingTemp.getPqrsEntity().getId());
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

						
						questionBankMap.put(question.getId(), question);
					}
					
					
					for(Map.Entry<Long, Questions> entry : questionBankMap.entrySet()){
						questionTemp = new Questions();
						questionTemp = entry.getValue();
						questionsFinalMap.put(entry.getKey(), questionTemp);
					    String answerList = "";
						if(questionTemp.getFirstAnswerList() != null) {
							answerList = "Answer 1:" + questionTemp.getFirstAnswerList() + ":::";
						}
						if(questionTemp.getSecondAnswerList() != null) {
							answerList = answerList + "Answer 2:" + questionTemp.getSecondAnswerList() + ":::";						
						}
						if(questionTemp.getThirdAnswerList() != null) {
							answerList = answerList +  "Answer 3:" + questionTemp.getThirdAnswerList() ;
						}
						
						questionAndAnswerFinalMap.put(surveyEntityMappingTemp.getPqrsEntity().getName() + "_" + entry.getKey(), answerList);	
					}
					questionBankMap = null;
					entityNames.add(surveyEntityMappingTemp.getPqrsEntity().getName());
					
				} 
			
			model.addAttribute("questionAndAnswerFinalList", questionAndAnswerFinalList);			
			excelDownloadService.fillExportQuestionAndAnswerReport(response,entityNames, questionsFinalMap, questionAndAnswerFinalMap,"Registry", GenericConstants.SURVEY_QUESTION_AND_ANSWER_REPORT, "Test");			

			}
			else{				
				session.setAttribute("fileuploaderror", "error.download.questionAndAnswerReport");
				response.sendRedirect(request.getRequestURI().substring(0, request.getRequestURI().indexOf("app") + 4)+ "admin/questionandanswer");
			}
		} catch (Exception e) {
			log.error("Error in Report:" + e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("referenceData", referenceData());
		log.info("Exit PQRS PSV EP STATUS TO-DATE REPORT");
		//return "questionandanswer-report";
	}	
	
	/**
	 * generates individual report for a requested pqrs entity and survey
	 * 
	 * @param model
	 *     stores retrieving the pre-populated (If Answers are Available) questionBankList
	 *     stores active navigation tab and survey entity mapping information
	 * @param entityId
	 *     requested entity id
	 * @param surveyId
	 *     requested survey id
	 * @return
	 *     view page report of an entity with questions and answers of a survey
	 */
	@RequestMapping(value = "/admin/viewreport-pqrsentity/{eid}/{sid}", method = RequestMethod.GET)
	public String viewReportPqrsEntityGet(final Model model, @PathVariable("eid") final Long entityId,
			@PathVariable("sid") final Long surveyId) {
		log.debug("viewReportPqrsEntityGet: Enter");
		ArrayList<PqrsEntityResponse> pqrsEntityResponses = null;		
		Map<Long, PqrsEntityResponse> pqrsResponseMap = null;
		String returnView = "";
		SurveyEntityMapping surveyEntityMapping = null;	    
	    PqrsEntity pqrsEntity = null;
	    Survey survey = null;
	    ArrayList<Questions> questionBankList = null;
	   
		try {					
			survey = surveyService.findOne(surveyId);
			pqrsEntity = pqrsEntityService.findOne(entityId);
			/*Code to retrieve the Answers for the Entity based on Survey Id*/			
			surveyEntityMapping = surveyEntityMappingService.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT );
			pqrsEntityResponses = (ArrayList<PqrsEntityResponse>) pqrsEntityResponseService.
						findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, GenericConstants.ACTIVE_INT);		
			
			if (pqrsEntityResponses != null && pqrsEntityResponses.size() > 0) {

				pqrsResponseMap = new LinkedHashMap<Long, PqrsEntityResponse>();
				
				for (PqrsEntityResponse pqrsEntityResponseTemp : pqrsEntityResponses) {
					pqrsResponseMap.put(pqrsEntityResponseTemp.getQuestion()
							.getId(), pqrsEntityResponseTemp);
				}

			} 			
			/*Setting up the Model Object for the JSP */
					
			//Retrieving the pre-populated (If Answers are Available) questionBankList for the Page
			questionBankList = getQuestionBankList(pqrsResponseMap, survey);
			/*Setting Up the Model Attributes - Start*/
			String active = "reports";
	        model.addAttribute("navigationstatus", active);			
			model.addAttribute("questionbank", questionBankList);
			model.addAttribute("surveyEntityMapping", surveyEntityMapping);
			/*Setting Up the Model Attributes - End*/
		} catch (Exception e) {
			log.error("Exception in viewReportPqrsEntityGet:"+e.getMessage());
			e.printStackTrace();
		}			
		log.debug("viewReportPqrsEntityGet: Exit");
		returnView = "viewreport-pqrsentity";
		return returnView;
	}	
	
	
	/*
	 * PQRS PSV EP STATUS TO-DATE REPORT This method helps to post the data to
	 * the excel sheet
	 * 
	 * @jsp psvrepstatustodatereport.jsp
	 * 
	 * @author mobeena
	 * 
	 * @description
	 */

/*
	@RequestMapping(value = "/ep/questionandanswerreport/xls", method = RequestMethod.GET)
	public void exportQuestionAndAnswerReport(final Model model,
			HttpServletResponse response, HttpSession session) {

		log.info("Enter PQRS PSV EP STATUS TO-DATE REPORT");

		try {
			ArrayList<Object> questionAndAnswerArrayList = (ArrayList<Object>) session
					.getAttribute("question_and_answer_report");
			//Collections.sort(epBasicInfoList);
	
			excelDownloadService.fillExportQuestionAndAnswerReport(response, questionAndAnswerArrayList,"", GenericConstants.SURVEY_QUESTION_AND_ANSWER_REPORT, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
/**
 * generates map of questions with their answers for a survey	
 * 	
 * @param pqrsResponseMap
 *     stores question and response information of a survey
 * @param survey
 *     target survey
 * @return
 *     map of questions with their answers for a survey
 */
private HashMap<Long,Questions> getQuestionBankListForReport(Map<Long, PqrsEntityResponse> pqrsResponseMap,Survey survey ) {
		
		log.debug("getQuestionBankListForReport: Enter");
			
			
			List<SurveyQuestionMapping> surveyQuestionMappings= null;
			final HashMap<Long,Questions> questionBankMap = new HashMap<Long,Questions>();

			try {			
				
				surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndRecordStatus(survey, GenericConstants.ACTIVE_INT);
				
				for (SurveyQuestionMapping surveyQuestionMapping : surveyQuestionMappings) {

					Questions question = surveyQuestionMapping.getQuestion();				
							
					
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

					
					questionBankMap.put(question.getId(), question);
					
				}
			} catch (Exception e) {
				log.error("Exception in getQuestionBankListForReport:"+e.getMessage());
				e.printStackTrace();
			}
			log.debug("getQuestionBankListForReport: Exit");
			return questionBankMap;
			
		}	
	
	
	
	
/**
 * generates list of questions with their answers for a survey	
 * 	
 * @param pqrsResponseMap
 *     stores question and response information of a survey
 * @param survey
 *     target survey
 * @return
 *     list of questions with their answers for a survey
 */
private ArrayList<Questions> getQuestionBankList(Map<Long, PqrsEntityResponse> pqrsResponseMap,Survey survey ) {
	
	log.debug("getQuestionBankList: Enter");
		
		
		//final List<Questions> questions = questionsService.findQuestionsByQuestionCategory(questionCategory);
		List<SurveyQuestionMapping> surveyQuestionMappings= null;
		final ArrayList<Questions> questionBankList = new ArrayList<Questions>();

		try {			
			surveyQuestionMappings=surveyQuestionMappingService.findBySurveyAndRecordStatus(survey, GenericConstants.ACTIVE_INT);
			
			for (SurveyQuestionMapping surveyQuestionMapping : surveyQuestionMappings) {

				Questions question = surveyQuestionMapping.getQuestion();
					
					/*if (question.getAnswerIdString() != null
							&& !question.getAnswerIdString().equalsIgnoreCase("")) {

						String answerString = question.getAnswerIdString();
						List<String> answerList = Arrays
								.asList(answerString.split(","));
						ArrayList<String> answerList1 = new ArrayList<String>();
						for (String answerOption : answerList) {					
							answerList1.add(answerOption);
						}
						question.setAnswersArrayList(answerList1);
					}*/
				
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
	 * @return
	 *    service template method
	 */
	@Override
	protected IService<PqrsEntity> getService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * stores reference data for list of survey years and source types
	 *      
	 * @return
	 *     map of years and source types
	 */
	protected Map referenceData() {
		final Map referenceData = new HashMap();
		
		
		
		
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
	

}
