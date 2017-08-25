package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.archsystemsinc.ipms.common.EvaluateValidation;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.common.SearchValidation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * persistence domain model for PQRS Entity
 * defines pqrs entity 
 * 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("Entity")
@Table(name = "entity")
public class PqrsEntity implements INameableEntity, Comparable<PqrsEntity>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entity_id")
	private Long id;
	
	/**name of entity*/
	@Column(name = "name", nullable = true)
	@NotBlank(groups={EvaluateValidation.class})
	private String name;
	
	/** entity contact number*/
	@Column(name = "contact_numbers", nullable = true)
	private String contactNumbers;
	
	@Column(name = "email_addresses", nullable = true)
	private String emailAddresses;
	
	/** type of entity*/
	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	private PqrsEntityType pqrsEntityType;
	
	/** year of entity*/
	@ManyToOne
	@JoinColumn(name = "year_id")
	private YearSurvey yearSurvey;
	
	/** entity point of contact*/	
	@Column(name = "alternate_name", nullable = true)
	private String alternateName;
	
	
	@Column(name = "survey_id")
	private Long surveyId;
	
	@Column(name = "comments", nullable = true)
	private String comments;
	
	@Column(name = "created_by",nullable = true)
	private String createdBy;
	
	@Column(name = "updated_by",nullable = true)
	private String updatedBy;
	
	@Column(name = "created_date", nullable = true)
	@OrderBy("created_date asc")
	private Date createdDate;
	
	@Column(name = "updated_date", nullable = true)
	@OrderBy("updated_date asc")
	private Date updatedDate;
	
	/**group validation for transient field*/
	@Transient
	@NotNull(groups={EvaluateValidation.class})
	private Long entityTypeId;
	
	/**group validation for transient field*/
	@Transient
	@NotNull(groups={EvaluateValidation.class, SearchValidation.class})
	private Long yearId;	
	
	/** On delete its value is set to '0' and data can not be accessed. It is soft delete*/
	@Column(name = "record_status",nullable = true)
	private int recordStatus;


	public int getRecordStatus() {
		return recordStatus;
	}



	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}



	public Long getEntityTypeId() {
		return entityTypeId;
	}



	public void setEntityTypeId(Long entityTypeId) {
		this.entityTypeId = entityTypeId;
	}



	public Long getYearId() {
		return yearId;
	}



	public void setYearId(Long yearId) {
		this.yearId = yearId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	
	public PqrsEntityType getPqrsEntityType() {
		return pqrsEntityType;
	}



	public void setPqrsEntityType(PqrsEntityType pqrsEntityType) {
		this.pqrsEntityType = pqrsEntityType;
	}



	


	public String getAlternateName() {
		return alternateName;
	}



	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}



	public String getContactNumbers() {
		return contactNumbers;
	}



	public void setContactNumbers(String contactNumbers) {
		this.contactNumbers = contactNumbers;
	}



	public String getEmailAddresses() {
		return emailAddresses;
	}



	public void setEmailAddresses(String emailAddresses) {
		this.emailAddresses = emailAddresses;
	}



	public Long getSurveyId() {
		return surveyId;
	}



	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
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



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String getName() {
		return name;
	}



	@Override
	public int compareTo(PqrsEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}



	public YearSurvey getYearSurvey() {
		return yearSurvey;
	}



	public void setYearSurvey(YearSurvey yearSurvey) {
		this.yearSurvey = yearSurvey;
	}	
	
	
}
