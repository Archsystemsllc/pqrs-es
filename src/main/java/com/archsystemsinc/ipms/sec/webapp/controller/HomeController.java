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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;


/**
 * 
 * controls home pages for entities and administrators 
 * 
 * @author 
 * @since 
 *
 */
@Controller
public class HomeController {

	

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IPqrsEntityService pqrsEntityService;	

	private final Log log = LogFactory.getLog(HomeController.class);
	
	/**
	 * home page for administrator
	 * @param model
	 *        contains reference data and active page information for administrator
	 * @param principal
	 *        contains credentials of authenticated user
	 * @return
	 *        home view page for administrator
	 */

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String home(final Model model, final Principal principal) {
		
		log.debug("home: Enter");
        try{
		//
        	String active = "dashboard";
            model.addAttribute("navigationstatus", active);
        	
		model.addAttribute("referenceData", referenceData());
        
       
        model.addAttribute("view", "dashboard");
      
        }catch(Exception e) {
          e.printStackTrace();
          log.error("Error Occured in home:" + e.getMessage());
        }
        log.debug("home: Exit");
		return "dashboard";
	}

	
	/**
	 *  home page for entity(user) page
	 *  
	 * @param model
	 *       contains reference data, survey id, pqrs entity and active page information of entity to be rendered
	 * @param uid
	 *       contains id of entity 
	 * @param surveyId
	 *       contains id of survey
	 * @return
	 *       home view page for requested entity
	 */
	@RequestMapping(value = "/userhomepage/{uid}/{sid}")
	public String userHomePage(final Model model, @PathVariable("uid") final Long uid,
			@PathVariable("sid") final Long surveyId) {
		log.debug("userHomePage: Enter");
		String returnView = "userhomepage";
        try{
		
        PqrsEntity pqrsEntity = pqrsEntityService.findByIdAndRecordStatus(uid, GenericConstants.ACTIVE_INT);
		model.addAttribute("surveyId", surveyId);	
		
		model.addAttribute("pqrsEntity", pqrsEntity);
		model.addAttribute("referenceData", referenceData()); 
        model.addAttribute("view", "userhomepage");
        String active = "homepage";
        model.addAttribute("navigationstatus", active);
        
        if(pqrsEntity.getPqrsEntityType().getId() == GenericConstants.REGISTRY_LONG) {
        	returnView = "registryhomepage";
		} else if(pqrsEntity.getPqrsEntityType().getId() == GenericConstants.EHR_DSV_LONG ) {
			returnView = "ehrdsvhomepage";
		} else if(pqrsEntity.getPqrsEntityType().getId() == GenericConstants.EHR_DIRECT_LONG ) {
			returnView = "ehrdsvhomepage";
		} else if(pqrsEntity.getPqrsEntityType().getId() == GenericConstants.CLAIMS_LONG ) {
			returnView = "claimshomepage";		
		} else if(pqrsEntity.getPqrsEntityType().getId() == GenericConstants.GPRO_LONG ) {
			returnView = "gprohomepage";		
		} else if(pqrsEntity.getPqrsEntityType().getId() == GenericConstants.QCDR_LONG ) {
			returnView = "qcdrhomepage";		
		}
      
        }catch(Exception e)
        {
          e.printStackTrace();
          log.error("Error Occured in userHomePage:" + e.getMessage());
        }
        log.debug("userHomePage: Exit");
		return returnView;
	}
	
	/**
	 * help page 
	 * @param model
	 *        contains reference data, survey id, pqrs entity data and active page information of help
	 * @param uid
	 *        contains id of entity 
	 * @param surveyId
	 *        contains id of survey
	 * @return
	 *        help view page
	 */
	@RequestMapping(value = "/help/{uid}/{sid}", method = RequestMethod.GET)
	public String help(final Model model, @PathVariable("uid") final Long uid,
			@PathVariable("sid") final Long surveyId) {
		log.debug("help: Enter");
        try{
		
        PqrsEntity pqrsEntity = pqrsEntityService.findByIdAndRecordStatus(uid, GenericConstants.ACTIVE_INT);
 		
     	model.addAttribute("pqrsEntity", pqrsEntity);
     	model.addAttribute("surveyId", surveyId);
   	    String active = "help";
   	    model.addAttribute("navigationstatus", active);
		
		model.addAttribute("referenceData", referenceData()); 
        model.addAttribute("view", "help");
        
        } catch(Exception e) {
          e.printStackTrace();
          log.error("Error Occured in help:" + e.getMessage());
        }
        log.debug("help: Exit");
		return "help";
	}
	
	/**
	 * reports view page
	 * 
	 * @param model
	 * @return
	 *      reports view page
	 */
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reports(final Model model) {
		return "reports";
	}

	/**
	 * home page for administrator
	 * @param currentUser
	 *       current user credentials
	 * @param model
	 * @return
	 *      dashboard view page
	 */
	@RequestMapping("/")
	public String initialHome(final Principal currentUser, final Model model) {
		log.debug("initialHome: Enter");
		log.debug("initialHome: Exit");
		
		return "dashboard";
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

	
		final Map<Integer, String> prgList = new LinkedHashMap<Integer, String>();
		log.debug("referenceData: Exit");
		return referenceData;
	}

}
