package com.archsystemsinc.ipms.sec.webapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.sec.model.PqrsEntityFileUpload;
import com.archsystemsinc.ipms.sec.model.QuestionsFileUpload;

/**
 * controls file upload functionality for entity and questions file upload
 * 
 * @author Grmahun Redda
 * @since 22/02/2017
 */
@Controller
public class FilesUploadController {
	
	/** 
	 * Displays file upload page 
	 * 
	 * @param model
	 *      contains  pqrs entity file upload data object, questions file upload data object and active page information 
	 * @param session
	 * @return 
	 *     filesupload view page
	 */
	@RequestMapping(value = "/admin/filesupload", method = RequestMethod.GET)
	public String documentUploadGet(final Model model, HttpSession session) {
		
		/*added to highlight file upload navigation when it is active*/
		String active = "filesupload";
        model.addAttribute("navigationstatus", active);
        
        model.addAttribute("pqrsEntityFileUpload", new PqrsEntityFileUpload());         
		model.addAttribute("questionsFileUpload", new QuestionsFileUpload());
		
		return "filesupload";
	}

}
