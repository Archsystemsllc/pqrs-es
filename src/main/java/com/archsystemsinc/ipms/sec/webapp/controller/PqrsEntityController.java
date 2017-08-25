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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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
import com.archsystemsinc.ipms.sec.model.PqrsEntityResponse;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityResponseService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyEntityMappingService;
import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls administrator on creating, editing, deleting and searching PQRS Entities
 * 
 * @author 
 * @since
 */
@Controller
public class PqrsEntityController extends AbstractController<PqrsEntity> {

	
	
	@Autowired
	private IPqrsEntityService service;
	
	@Autowired
	private IPqrsEntityTypeService pqrsEntityTypeService;
	
	@Autowired
	private IPqrsEntityResponseService pqrsEntityResponseService;
	
	@Autowired
	private ISurveyEntityMappingService surveyEntityMappingService;
	
	@Autowired
	protected Emailer emailer;
	
	@Autowired
	private IYearService yearSurveyService;
	
	public PqrsEntityController() {
		super(PqrsEntity.class);
	}

	private final Log log = LogFactory.getLog(PqrsEntityController.class);
	

	// Search method 
	// method = RequestMethod.POST is avoided to serve as multiple entry point
	/**
	 *  validates search criteria and generates search results for entities
	 *  
	 * @param model
	 *      contains reference data, pqrs entities and active page information
	 * @param result
	 *      contains validation data 
	 * @return
	 *      list of entities page
	 */
	@RequestMapping(value = "/admin/entity/search")
	public String search(
			@Validated({SearchValidation.class}) @ModelAttribute("entity") PqrsEntity pqrsEntity, BindingResult result,
			final Model model) {
		log.debug("Enter search");
		
		if(result.hasErrors()) {				
			String active = "entityadmin";
	        model.addAttribute("navigationstatus", active);
			model.addAttribute("referenceData", referenceData());
            return "pqrsentitys";
        }
		try {
			PqrsEntityType pqrsEntityTypeTemp = pqrsEntityTypeService.findOne(pqrsEntity.getEntityTypeId());
			YearSurvey yearSurveyTemp = yearSurveyService.findOne(pqrsEntity.getYearId());
			
			pqrsEntity.setYearSurvey(yearSurveyTemp);
			pqrsEntity.setPqrsEntityType(pqrsEntityTypeTemp);
			
			//adding active navigation
			String active = "entityadmin";
	        model.addAttribute("navigationstatus", active);
			//
			
			List<PqrsEntity> entityList = service.search(pqrsEntity);
			
			Collections.sort(entityList);
			final ArrayList<PqrsEntity> entityArrayList = new ArrayList<PqrsEntity>(
					entityList);
			model.addAttribute("entities", entityArrayList);
			model.addAttribute("referenceData", referenceData(null));
			model.addAttribute("entity", pqrsEntity);
			
			log.debug("viewSurveys: Exit");
			
		} catch (Exception e) {
			log.error("Error in search:" + e.getMessage());
			model.addAttribute("failure", "failure.epinfo.search");
			model.addAttribute("errorMessage",e.getMessage());
		}
		log.debug("Exit search");
		return "pqrsentitys";
			
	}
	
	/**
	 * search entities for default hard coded year
	 * @param model
	 *      contains reference data, list of pqrs entities, entity type and active page information to be rendered  
	 * @param principal
	 *      current user information
	 * @return
	 *      list of pqrs entities 
	 */
	
	@RequestMapping(value = "/admin/pqrsentitys")
	public String pqrsEntitys(final Model model,
			final java.security.Principal principal) {
		String active = "entityadmin";
        model.addAttribute("navigationstatus", active);
        
        YearSurvey yearSurvey = yearSurveyService.findOne(GenericConstants.OY2_YEAR_ID);  // Hard coded year
		PqrsEntityType pqrsEntityTypeTemp = null; //pqrsEntityTypeService.findOne(1);
		PqrsEntity pqrsEntity = new PqrsEntity();
		pqrsEntity.setYearSurvey(yearSurvey);
		pqrsEntity.setPqrsEntityType(pqrsEntityTypeTemp);
		
		pqrsEntity.setYearId(GenericConstants.OY2_YEAR_ID);
		final List<PqrsEntity> pqrsEntitys = service.search(pqrsEntity);
		Collections.sort(pqrsEntitys);
		final ArrayList<PqrsEntity> pqrsEntitysArrayList = new ArrayList<PqrsEntity>(
			pqrsEntitys);
		model.addAttribute("entities", pqrsEntitysArrayList);
		
		
		model.addAttribute("referenceData", referenceData());
		
		model.addAttribute("entity", pqrsEntity);
		return "pqrsentitys";		
	}	
	
	
	/**
	 * display details of an entity
	 * 
	 * @param id
	 *     pqrs entity id
	 * @param model
	 *      contains reference data for year and entity type selection list 
	 *      contains pqrs entity data and active page information to be rendered  
	 * @return
	 *      pqrs entity detail view page
	 */
	@RequestMapping(value = "/admin/pqrsentity/{id}", method = RequestMethod.GET)
	public String findPqrsEntity(@PathVariable("id") final Long id,
			final Model model) {
		final PqrsEntity pqrsEntity = service.findByIdAndRecordStatus(id, GenericConstants.ACTIVE_INT);
		String active = "entityadmin";
        model.addAttribute("navigationstatus", active);
        pqrsEntity.setEntityTypeId(pqrsEntity.getPqrsEntityType().getId());	
		pqrsEntity.setYearId(pqrsEntity.getYearSurvey().getId());
		model.addAttribute("pqrsEntity", pqrsEntity);
		model.addAttribute("referenceData", referenceData());
		return "pqrsentity";
	}
	
	/**
	 * display form input for new entity 
	 * @param model
	 *       contains reference data for year, entity type list and active page information to be rendered  
	 * @return
	 *     new pqrs entity adding page
	 */	
	@RequestMapping(value = "/admin/new-pqrsentity", method = RequestMethod.GET)
	public String addPqrsEntityGet(final Model model) {
		PqrsEntity pqrsEntity = null ;
		String active = "entityadmin";
        model.addAttribute("navigationstatus", active);
		model.addAttribute("referenceData", referenceData(pqrsEntity));
        pqrsEntity = new PqrsEntity();
        model.addAttribute("pqrsEntity", pqrsEntity);
		return "pqrsentityadd";
	}	

	/**
	 * 
	 * @param pqrsResponseId
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/download/{pqrsResponseId}")
	public String download(@PathVariable("pqrsResponseId")
			Long pqrsResponseId, HttpServletResponse response) {
		
		PqrsEntityResponse pqrsEntityResponse = pqrsEntityResponseService.findOne(pqrsResponseId);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"Test file\"");
			OutputStream out = response.getOutputStream();
			response.setContentType("txt");
                        
                       byte[] buffer = pqrsEntityResponse.getUploadedFileContent();
                        InputStream is = new ByteArrayInputStream(buffer);
			IOUtils.copy(is, out);
			out.flush();
			out.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return null;
	}
	

	/**
	 * display details of an entity on editing
	 * @param id
	 *     pqrs entity id
	 * @param model
	 *     contains reference data for year and entity type selection list 
	 *     contains pqrs entity data and active page information to be rendered  
	 * @return
	 *     pqrs entity detail view page 
	 */
	@RequestMapping(value = "/admin/edit-pqrsentity/{id}", method = RequestMethod.GET)
	public String editPqrsEntity(@PathVariable("id") final Long id,
			final Model model) {
		final PqrsEntity pqrsEntity = service.findByIdAndRecordStatus(id, GenericConstants.ACTIVE_INT);	
		String active = "entityadmin";
        model.addAttribute("navigationstatus", active);
		pqrsEntity.setEntityTypeId(pqrsEntity.getPqrsEntityType().getId());	
		pqrsEntity.setYearId(pqrsEntity.getYearSurvey().getId());
		model.addAttribute("pqrsEntity", pqrsEntity);
		model.addAttribute("referenceData", referenceData());
		return "pqrsentityedit";
	}
	
	/**
	 * making updates on editing pqrs entity
	 * 
	 * @param pqrsEntity
	 *      edited entity data
	 * @param result
	 *      validation data
	 * @param model
	 *      contains reference data for year and entity type selection list when data is invalid
	 *      contains pqrs entity data and active page information to be rendered when data is invalid
	 * @param redirectAttributes
	 *      success information message carrier on redirect request to entities list
	 * @return
	 *      reidrect request to pqrs entitys get request
	 * @see
	 *     public String pqrsEntitys(final Model model,final java.security.Principal principal) {
	 */
	@RequestMapping(value = "/admin/edit-pqrsentity", method = RequestMethod.POST)
	public String updatePqrsEntity(
			@Validated({ EvaluateValidation.class }) @ModelAttribute("pqrsEntity") final PqrsEntity pqrsEntity,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {
		String returnView = "";
		String active = "entityadmin";		
        model.addAttribute("navigationstatus", active);
        PqrsEntity oldpqrsEntity=null;
			
		if (result.hasErrors()) {
			returnView = "pqrsentityedit";
			model.addAttribute("pqrsentity", pqrsEntity);
			model.addAttribute("referenceData", referenceData());
			result.getAllErrors();
		} else {
			
			try{
				PqrsEntityType pqrsEntityType = pqrsEntityTypeService.findByIdAndRecordStatus(pqrsEntity.getEntityTypeId(),GenericConstants.ACTIVE_INT);
				YearSurvey yearSurvey = yearSurveyService.findOne(pqrsEntity.getYearId());
				
				pqrsEntity.setYearSurvey(yearSurvey);
				pqrsEntity.setPqrsEntityType(pqrsEntityType);	
				pqrsEntity.setName(pqrsEntity.getName());
				pqrsEntity.setRecordStatus(GenericConstants.ACTIVE_INT);
				
				
				oldpqrsEntity = service.findByIdAndRecordStatus(pqrsEntity.getId(),GenericConstants.ACTIVE_INT);
                
				oldpqrsEntity.setEntityTypeId(oldpqrsEntity.getPqrsEntityType().getId());
				oldpqrsEntity.setYearId( oldpqrsEntity.getYearSurvey().getId());
                
				//Check if there is a change in source or year
				if(!oldpqrsEntity.getEntityTypeId().equals(pqrsEntity.getEntityTypeId())  || !oldpqrsEntity.getYearId().equals(pqrsEntity.getYearId())){
					pqrsEntity.setId(null);
					service.create(pqrsEntity);
					redirectAttributes.addFlashAttribute("success", "success.copy.entity");
				}else{
					service.update(pqrsEntity);
					redirectAttributes.addFlashAttribute("success", "success.edit.entity");
				}				
				
				returnView = "redirect:pqrsentitys";
				
			}catch(Exception e){
				log.error("Error in entity search:" + e.getMessage());				
			}
			
		} 	
		return returnView;
	}
	
	
	/**
	 * deletes pqrs entity if there is no survey attached to it 
	 * When there are surveys attached to the pqrs entity 
	 * it first need to be detached before deletion and
	 * user is forwarded to edit pqrs entity page    
	 * 
	 * @param entityId
	 *      entity id
	 * @param model     
	 * @param request
	 *      contains request data url
	 * @param redirectAttributes
	 *      contains success information message or error carrier on redirect request to entities list
	 *      contains error message and list of surveys attached to the question 
	 * @return
	 *      redirects to list of pqrs entity on success
	 *      redirects to edit entity if there are attached surveys on the entity
	 */
	@RequestMapping(value = "/admin/delete-pqrsentity/{entityId}", method = RequestMethod.GET)
	public String deleteSurveyGet(@PathVariable("entityId") final Long entityId,final Model model,HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		
		log.debug("deleteEntityGet: Enter");	
		String returnView = "";		
		StringBuffer requestUrl;
		requestUrl = request.getRequestURL();
		
		int appIndex = requestUrl.indexOf("app");
		String finalRequestUrl = requestUrl.substring(0, appIndex+4);
		
		try {
			final PqrsEntity entity = service.findOne(entityId);
			
			List<SurveyEntityMapping> surveyEntityMappings = surveyEntityMappingService.findByPqrsEntityAndRecordStatus(entity, GenericConstants.ACTIVE_INT);
			
			if(surveyEntityMappings.size() > 0){
				redirectAttributes.addFlashAttribute("error", "error.delete.entity");
				redirectAttributes.addFlashAttribute("entitySurveysMap",referenceData(surveyEntityMappings,finalRequestUrl));
				returnView = "redirect:../edit-pqrsentity/" + entityId;
				
			}else{
				entity.setRecordStatus(GenericConstants.IN_ACTIVE_INT);
				entity.setEntityTypeId(entity.getPqrsEntityType().getId());
				entity.setYearId(entity.getYearSurvey().getId());
				service.update(entity);			
				redirectAttributes.addFlashAttribute("success", "success.delete.entity");
				//returnView = "forward:../questions";	
				returnView = "redirect:../pqrsentitys";	
			}
		} catch (Exception e) {
			log.error("Exception in editEntity:"+ e.getMessage());			
		}		
		log.debug("deleteEntityGet: Exit");
			
		return returnView;
	}	
	
	
		
	/**
	 * generates new pqrs entity
	 * 
	 * @param pqrsEntity
	 *      data for new pqrs entity
	 * @param result
	 *      data validation result on error
	 * @param model
	 * @param redirectAttributes
	 *      carries success message 
	 * @return
	 *      redirects request to list of pqrsEntitys
	 * @
	 */
	
	@RequestMapping(value = "/admin/new-pqrsentity", method = RequestMethod.POST)
	public String addPqrsEntity(@Validated({ EvaluateValidation.class }) @ModelAttribute("pqrsEntity") PqrsEntity pqrsEntity,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {
		String returnView = "";
		String active = "entityadmin";
        model.addAttribute("navigationstatus", active);
			
		if (result.hasErrors()) {
			returnView = "pqrsentityadd";
			model.addAttribute("pqrsEntity", pqrsEntity);
			model.addAttribute("referenceData", referenceData());
			result.getAllErrors();
		} else {			
			
			PqrsEntityType pqrsEntityType = pqrsEntityTypeService.findByIdAndRecordStatus(pqrsEntity.getEntityTypeId(),GenericConstants.ACTIVE_INT);
			pqrsEntity.setPqrsEntityType(pqrsEntityType);
			
			YearSurvey yearSurvey = yearSurveyService.findOne(pqrsEntity.getYearId());
			pqrsEntity.setYearSurvey(yearSurvey);
			pqrsEntity.setRecordStatus(GenericConstants.ACTIVE_INT);
			service.create(pqrsEntity);
			//returnView = "forward:pqrsentitys";
			redirectAttributes.addFlashAttribute("success", "success.save.entity");// added
			returnView = "redirect:pqrsentitys";
		} 		
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
	
	/**
	 * keeps reference data for list of years and entity types
	 * @param pqrsEntity     
	 * @return
	 *     map of years and entity types
	 */
	protected Map referenceData(PqrsEntity pqrsEntity) {
		final Map referenceData = new HashMap();		
				
		final List<PqrsEntityType> pqrsEntityTypelist = pqrsEntityTypeService.findAll();
		final Map<Integer, String> pqrsEntityTypeList1 = new LinkedHashMap<Integer, String>();
		for(PqrsEntityType pqrsEntityType: pqrsEntityTypelist) {
			pqrsEntityTypeList1.put(pqrsEntityType.getId().intValue(), pqrsEntityType.getName());
		}
		
		referenceData.put("pqrsEntityTypeList", pqrsEntityTypeList1);
		
		final List<YearSurvey> yearSurveylist = yearSurveyService.findAll();
		final Map<Integer, String> yearSurveyList = new LinkedHashMap<Integer, String>();
		for(YearSurvey yearSurvey: yearSurveylist) {
			yearSurveyList.put(yearSurvey.getId().intValue(), yearSurvey.getName());
		}
		
		referenceData.put("yearSurveyList", yearSurveyList);
		
	
		return referenceData;
	}
	
	/**
	 * keeps reference data for list of years and entity types
	 *      
	 * @return
	 *     map of years and entity types
	 */
	protected Map referenceData() {
		final Map referenceData = new HashMap();		
				
		final List<PqrsEntityType> pqrsEntityTypelist = pqrsEntityTypeService.findAll();
		final Map<Integer, String> pqrsEntityTypeList = new LinkedHashMap<Integer, String>();
		for(PqrsEntityType pqrsEntityType: pqrsEntityTypelist) {
			pqrsEntityTypeList.put(pqrsEntityType.getId().intValue(), pqrsEntityType.getName());
		}
		
		referenceData.put("pqrsEntityTypeList", pqrsEntityTypeList);
		
		final List<YearSurvey> yearSurveylist = yearSurveyService.findAll();
		final Map<Integer, String> yearSurveyList = new LinkedHashMap<Integer, String>();
		for(YearSurvey yearSurvey: yearSurveylist) {
			yearSurveyList.put(yearSurvey.getId().intValue(), yearSurvey.getName());
		}
		
		referenceData.put("yearSurveyList", yearSurveyList);
		
	
		return referenceData;
	}
	
	/**
	 * map of attached surveys 
	 * 
	 * @param surveyEntityMappings
	 *     list of survey entity mapping
	 * @param finalRequestUrl
	 *     base request url to get access to the attached survey
	 * @return
	 *      map of survey name to entity
	 */   
	protected Map referenceData(List<SurveyEntityMapping> surveyEntityMappings,String finalRequestUrl) {
		log.debug("referenceData: Enter");
		final Map referenceData = new HashMap();
		Map<String, String> attachedSurveyNameLinkMap = null;
		
		
		if(surveyEntityMappings != null && surveyEntityMappings.size() > 0) {			
			attachedSurveyNameLinkMap = new LinkedHashMap<String, String>();
			
			for(SurveyEntityMapping surveyEntityMapping : surveyEntityMappings){				
				attachedSurveyNameLinkMap.put(surveyEntityMapping.getSurvey().getSurveyName(),
						finalRequestUrl + "admin/edit-survey/" + surveyEntityMapping.getSurvey().getId());
			}	
		}
		
		referenceData.put("attachedSurveyNameLinkMap",attachedSurveyNameLinkMap);		
		log.debug("referenceData: Exit");
		return referenceData;
	}
}
