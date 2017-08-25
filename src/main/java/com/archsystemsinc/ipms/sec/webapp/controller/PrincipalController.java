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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.validation.Valid;

import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.persistence.service.IPrivilegeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IRoleService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;


/**
 * controls creating, editing, deleting and retrieving principal information
 * 
 * @author 
 * @since
 */
@Controller
public class PrincipalController extends AbstractController<Principal> {

	public PrincipalController() {
		super(Principal.class);
	}

	private final Log log = LogFactory.getLog(PrincipalController.class);

	@Autowired
	private IPrincipalService service;

	@Autowired
	private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    /**
     * date format binder
     *  binds input string date to string object
     */
	@Override
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				GenericConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setLenient(false);
		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));		
	}

	/**
	 * 
	 * @param model
	 *     contains list of principals to render in principals view page
	 * @return
	 *     view page for list of principals
	 */
	@RequestMapping(value = "/principals")
	public String principal(final Model model) {
		final List<Principal> principals = service.findAll();
		model.addAttribute("principals", principals);
		return "principals";
	}
	
	/**
	 * details of a principal with a given id
	 * 
	 * @param id
	 *     id of a principal
	 * @param model
	 *     carries principal data in the response 
	 * @return
	 *     view jsp page for principal details
	 */
	@RequestMapping(value = "/principal/{id}", method = RequestMethod.GET)
	public String principal(@PathVariable("id") final Long id, final Model model) {
		final Principal principal = service.findOne(id);
		model.addAttribute("principal", principal);
		return "principal";
	}

	/**
	 * form input data for new principal 
	 * 
	 * @param model
	 *     contains empty principal data object and reference data that have list of roles 
	 *     it is attached to the response
	 * @return
	 *     rendering page for adding principal
	 */
	@RequestMapping(value = "/new-principal", method = RequestMethod.GET)
	public String newPrincipal(final Model model) {
		final Principal principal = new Principal();
		model.addAttribute("principal", principal);
		model.addAttribute("referenceData", referenceData());
		return "principalsadd";
	}

	/**
	 * display details of principal on editing
	 *  
	 * @param id
	 *     id of a principal
	 * @param model
	 *     contains reference data for role list
	 * @return
	 *      principal detail view page
	 */
	@RequestMapping(value = "/edit-principal/{id}", method = RequestMethod.GET)
	public String editPrincipal(@PathVariable("id") final Long id,
			final Model model) {
		final Principal principal = service.findOne(id);
		final Set<Role> roles = principal.getRoles();
		List<Long> rolesList = new ArrayList<Long>();
		for(Role role: roles) {
			rolesList.add(role.getId());			
		}
		principal.setRolesList(rolesList);
		model.addAttribute("principal", principal);
		model.addAttribute("referenceData", referenceData());
		return "principalsedit";
	}

	/**
	 * making updates on editing principal
	 * 
	 * @param principal
	 *     edited principal data
	 * @param result
	 *     data validation
	 * @param model
	 *     contains reference data of role set and principal data on response when data is invalid
	 *     contains success message when data is valid
	 *@param redirectAttributes
	 *     carries success message on redirected request
	 * @return
	 *     redirects to list of principals
	 * @see
	 *     public String principal(final Model model)
	 */
	@RequestMapping(value = "/new-principal", method = RequestMethod.POST)
	public String addPrincipal(
			@Valid @ModelAttribute("principal") final Principal principal,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {

		String returnView = "";

		if (result.hasErrors()) {
			returnView = "principalsadd";
			model.addAttribute("principal", principal);
			model.addAttribute("referenceData", referenceData());
		} else {
			final Set<Role> rolesToSet = new HashSet<Role>();			

			for (Long roleId: principal.getRolesList()) {
				rolesToSet.add(roleService.findOne(roleId));
			}
			principal.setRoles(rolesToSet);
			service.create(principal);
			//model.addAttribute("success", "success.principal.created");
			//returnView = "forward:principals";
			redirectAttributes.addFlashAttribute("success", "success.principal.created");
			returnView = "redirect:principals";
		}		
		return returnView;
	}

	/**
	 * making updates on edit principal
	 * 
	 * @param principal
	 *     edited principal data
	 * @param result
	 *     validation data
	 * @param model
	 * @param redirectAttributes
	 *     carries success message on redirected request
	 * @return
	 *    redirects to list of principals
	 * @see 
	 *    public String principal(final Model model)
	 */
	@RequestMapping(value = "/edit-principal", method = RequestMethod.POST)
	public String updatePrincipal(
			@Valid @ModelAttribute("principal") final Principal principal,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {
		String returnView = "";
		// using name as long --bad idea

		if (result.hasErrors()) {
			returnView = "principalsedit";
			model.addAttribute("principal", principal);
			model.addAttribute("referenceData", referenceData());
		} else {
			final Set<Role> rolesToSet = new HashSet<Role>();			

			for (Long roleId: principal.getRolesList()) {
				rolesToSet.add(roleService.findOne(roleId));
			}
			principal.setRoles(rolesToSet);
			principal.setRecordStatus(GenericConstants.ACTIVE_INT);
			service.update(principal);
			//returnView = "forward:principals";
			//model.addAttribute("success","success.principal.updated");				
			redirectAttributes.addFlashAttribute("success","success.principal.updated");
			returnView = "redirect:principals";
		}		
		return returnView;
	}
	
	/**
	 *  map of role to privileges 
	 *  
	 * @param id
	 *     id of role
	 * @param model
	 * @return
	 *     a map of survey name to privileges 
	 */
	@RequestMapping(value = "/getPrivOfRole/{id}", method = RequestMethod.POST)
	public Map<Integer, String> gerPrivilegesOfRole(@PathVariable("id") final Long id, final Model model) {
		final Role role = roleService.findOne(id);
		final List<Privilege> list = (List<Privilege>) role.getPrivileges();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		return pList;
	}
	
	/**
	 * deletes principal of a given id
	 * 
	 * @param id
	 *     id of a principal
	 * @param returnPage
	 *     return page on delete success
	 * @param model
	 *     contains success message on delete
	 * @return
	 *     a view page rendered by return page or principals
	 */
	@RequestMapping(value = "/delete-principal/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable("id") final Long id, @PathVariable("returnPage") final String returnPage,
			final Model model) {
		String returnView = "";
		service.delete(id);
		model.addAttribute("success","success.principal.deleted");
		if(returnPage.equalsIgnoreCase("")) {
			returnView = "forward:principals"; 
		} else {
			returnView = "forward:" + returnPage;	
		}			
		return returnView;
	}

	/**
	 * 
	 * @return
	 *    list of roles
	 */
	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Role> rList = roleService.findAll();
       
        referenceData.put("roleCompleteList", rList);
        
		return referenceData;
	}

	/**
	 * @return
	 *    service template method
	 */
	@Override
	protected IService<Principal> getService() {
		return service;
	}
}
