package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * persistence domain model of PqrsEntityType 
 *  
 * defines source of survey, question and entity type of PQRS entity 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("PqrsEntityType")
@Table(name = "entity_type")
public class PqrsEntityType implements INameableEntity{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entity_type_id")
	private Long id;
	
	/** source type (entity type) name*/
	@Column(nullable = true)
	private String name;
	

	@Column(name = "createdby",nullable = true)
	private String createdBy;
	
	@Column(name = "updatedby",nullable = true)
	private String updatedBy;
	
	@Column(name = "createddate", nullable = true)
	@OrderBy("createddate asc")
	private Date createdDate;
	
	@Column(name = "updateddate", nullable = true)
	@OrderBy("updateddate asc")
	private Date updatedDate;
	
	@Column(name = "record_status",nullable = true)
	private int recordStatus;
	
	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
    
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
