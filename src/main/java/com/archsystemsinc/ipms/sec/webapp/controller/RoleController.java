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

import javax.validation.Valid;

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
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.persistence.service.IPrivilegeService;
import com.archsystemsinc.ipms.sec.persistence.service.IRoleService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

/**
 * controls administrator on creating, assigning privilege, editing, deleting and searching Role information
 * 
 * @author 
 * @since
 */
@Controller
public class RoleController extends AbstractController<Role> {

	public RoleController() {
		super(Role.class);
	}

	private final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	private IRoleService service;

	@Autowired
	private IPrivilegeService privilegeService;

	/**
	 * binds string to date format 
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

		/*// Custom Change for roles
		binder.registerCustomEditor(Set.class, "privileges",
				new CustomCollectionEditor(Set.class) {
			@Override
			protected Object convertElement(final Object element) {
				if (element != null) {
					final Long id = new Long((String) element);
					final Privilege privilege = privilegeService
							.findOne(id);
					return privilege;
				}
				return null;
			}
		});*/
	}

	/**
	 * generates list of roles
	 * 
	 * @param model
	 *    stores list of roles
	 * @return
	 *    view page for list of roles
	 */
	@RequestMapping(value = "/roles")
	public String role(final Model model) {
		final List<Role> roles = service.findAll();
		model.addAttribute("roles", roles);
		return "roles";
	}

	/**
	 * generates details of a role
	 * @param id
	 *     role id
	 * @param model
	 *     stores role data of a specified id
	 * @return
	 *     view page for role details
	 */
	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
	public String role(@PathVariable("id") final Long id, final Model model) {
		final Role role = service.findOne(id);
		model.addAttribute("role", role);
		return "role";
	}

	/**
	 * generates an empty form to add new role
	 * 
	 * @param model
	 *     stores empty role data
	 * @return
	 *     view page for add role form
	 */
	@RequestMapping(value = "/new-role", method = RequestMethod.GET)
	public String newRole(final Model model) {
		final Role role = new Role();
		model.addAttribute("role", role);
		model.addAttribute("referenceData", referenceData());
		return "rolesadd";
	}

	/**
	 * generates details of a role to edit
	 * @param id
	 *     role id
	 * @param model
	 *     stores role data of a specified id
	 * @return
	 *     edit view page for role details
	 */
	@RequestMapping(value = "/edit-role/{id}", method = RequestMethod.GET)
	public String editRole(@PathVariable("id") final Long id,
			final Model model) {
		final Role role = service.findOne(id);
		final Set<Privilege> priviliges = role.getPrivileges();
		List<Long> privilegesIdsList = new ArrayList<Long>();
		for(Privilege privilege: priviliges) {
			privilegesIdsList.add(privilege.getId());			
		}
		role.setPrivilegesIds(privilegesIdsList);
		model.addAttribute("role", role);
		model.addAttribute("referenceData", referenceData());
		return "rolesedit";
	}

	/**
	 * adds new role to repository and page is redirected to list of roles
	 * 
	 * @param role
	 *     stores new role data
	 * @param result
	 *     data validation result for new role 
	 * @param model
	 * @param redirectAttributes
	 *     stores success message to list of roles page
	 * @return
	 *     redirect to list of roles page
	 * @see
	 *     public String role(final Model model)
	 */
	@RequestMapping(value = "/new-role", method = RequestMethod.POST)
	public String addRole(@Valid @ModelAttribute("role") final Role role,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {

		String returnView = "";

		if (result.hasErrors()) {
			returnView = "rolesadd";
			model.addAttribute("role", role);
			model.addAttribute("referenceData", referenceData());
		} else {
			final Set<Privilege> priviligesToSet = new HashSet<Privilege>();			

			for (Long privilegeId: role.getPrivilegesIds()) {
				priviligesToSet.add(privilegeService.findOne(privilegeId));
			}
			role.setPrivileges(priviligesToSet);
			service.create(role);			
			//model.addAttribute("success","success.role.created");
			//returnView = "forward:roles";
			redirectAttributes.addFlashAttribute("success","success.role.created");
			returnView = "redirect:roles";
		}		
		return returnView;
	}
	
	/**
	 * making updates on repository for edited role
	 * 
	 * @param role
	 *     edited role data
	 * @param result
	 *     validation data
	 * @param redirectAttributes
	 *     stores success message to list of roles page
	 * @return
	 *     redirect to list of roles page
	 * @see
	 *     public String role(final Model model)
	 */
	@RequestMapping(value = "/edit-role", method = RequestMethod.POST)
	public String updateRole(@Valid @ModelAttribute("role") final Role role,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes) {
		String returnView = "";
		// using name as long --bad idea

		if (result.hasErrors()) {
			returnView = "rolesedit";
			model.addAttribute("role", role);
			model.addAttribute("referenceData", referenceData());
		} else {
			final Set<Privilege> priviligesToSet = new HashSet<Privilege>();			

			for (Long privilegeId: role.getPrivilegesIds()) {
				priviligesToSet.add(privilegeService.findOne(privilegeId));
			}
			role.setPrivileges(priviligesToSet);
			service.update(role);
			//model.addAttribute("success","success.role.updated");
			redirectAttributes.addFlashAttribute("success","success.role.updated");
			returnView = "redirect:roles";
		}		
		return returnView;
	}
	
	/**
	 * deletes (soft delete) a role from repository
	 * 
	 * @param id
	 *     role id to be deleted
	 * @param returnPage
	 *     stores return page 
	 * @param model
	 *     stores success message
	 * @return
	 *     forwards request list of roles page or to the specified return page
	 */
	@RequestMapping(value = "/delete-role/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable("id") final Long id, @PathVariable("returnPage") final String returnPage,
			final Model model) {
		String returnView = "";
		service.delete(id);
		model.addAttribute("success","success.role.deleted");
		if(returnPage.equalsIgnoreCase("")) {
			returnView = "forward:roles"; 
		} else {
			returnView = "forward:" + returnPage;	
		}			
		return returnView;
	}

	/**
	 * stores map of all privileges in the repository for reference purposes
	 * @return
	 */
	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final List<Privilege> list = privilegeService.findAll();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("privilegeList", pList);

		return referenceData;
	}

	@Override
	protected IService<Role> getService() {
		return service;
	}

}
