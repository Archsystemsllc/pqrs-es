package com.archsystemsinc.ipms.sec.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * model for PqrsEntityFileUpload
 * 
 * defines a file upload of pqrs entities in excel format
 * 
 * @author 
 * @since
 */
public class PqrsEntityFileUpload {
	
	/** entity file data */
	private CommonsMultipartFile pqrsEntityFile;	
	
	
	private Long entityTypeId;

	public CommonsMultipartFile getpqrsEntityFile() {
		return pqrsEntityFile;
	}

	public void setpqrsEntityFile(CommonsMultipartFile pqrsEntityFile) { //EntitiesFile
		this.pqrsEntityFile = pqrsEntityFile;
	}

	public Long getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(Long entityTypeId) {
		this.entityTypeId = entityTypeId;
	}	

	
}
