package com.archsystemsinc.ipms.sec.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * model for QuestionsFileUpload
 * defining properties, getters and setters for file upload
 * 
 * users who have access to the admin page and have the right role and privilege 
 * can upload questions in excel format
 * 
 * @author 
 * @since
 */
public class QuestionsFileUpload {

	private CommonsMultipartFile epChartFile;
	
	private CommonsMultipartFile epDobFile;
	
	private CommonsMultipartFile questionsFile;	
	
	private Long entityTypeId;	

	public Long getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(Long entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public CommonsMultipartFile getQuestionsFile() {
		return questionsFile;
	}

	public void setQuestionsFile(CommonsMultipartFile questionsFile) {
		this.questionsFile = questionsFile;
	}

	public CommonsMultipartFile getEpChartFile() {
		return epChartFile;
	}

	public void setEpChartFile(CommonsMultipartFile epChartFile) {
		this.epChartFile = epChartFile;
	}

	public CommonsMultipartFile getEpDobFile() {
		return epDobFile;
	}

	public void setEpDobFile(CommonsMultipartFile epDobFile) {
		this.epDobFile = epDobFile;
	}	
}
