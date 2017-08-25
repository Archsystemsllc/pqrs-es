package com.archsystemsinc.ipms.sec.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * persistence domain model for Survey 
 * it defines survey name, year, and source 
 * 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("Survey")
@Table(name = "survey")
public class Survey<SurveyEntityType> implements INameableEntity, Comparable<Survey> {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "surveyid")
	private Long id;	

	@Column(name = "surveyname", nullable = true)
	@NotBlank(groups={EvaluateValidation.class})
	private String surveyName;
	
	@Column(name = "survey_description", nullable = true)
	private String surveyDescription;
	
	@Column(nullable = true)
	private String createdBy;
	
	@Column(nullable = true)
	private String updatedBy;

	@Column(name= "entity_type_id", insertable = false, updatable = false)
	private Long entity_type_id;
	
	@Column(name = "createddate", nullable = true)
	@OrderBy("createddate asc")
	private Date createdDate;
	
	@Column(name = "updateddate", nullable = true)
	@OrderBy("updateddate asc")
	private Date updatedDate;	
	
	@ManyToOne
	@JoinColumn(name = "survey_year_id")
	private YearSurvey yearSurvey;
	
	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	private PqrsEntityType pqrsEntityType;

		
	@Transient
	private String entityList;
	
	@Transient
	private String entitySelectedList;
	
	@Transient
    @NotNull(groups={EvaluateValidation.class})
	private Long entityTypeId;
	
	@Transient
	@NotNull(groups={EvaluateValidation.class, SearchValidation.class})
	private Long yearId;
	
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

	public String getEntityList() {
		return entityList;
	}

	public void setEntityList(String entityList) {
		this.entityList = entityList;
	}

	public String getEntitySelectedList() {
		return entitySelectedList;
	}

	public void setEntitySelectedList(String entitySelectedList) {
		this.entitySelectedList = entitySelectedList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}	

	public String getSurveyDescription() {
		return surveyDescription;
	}

	public void setSurveyDescription(String surveyDescription) {
		this.surveyDescription = surveyDescription;
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
	public int compareTo(Survey o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Long getEntity_type_id() {
		return entity_type_id;
	}

	public void setEntity_type_id(Long entity_type_id) {
		this.entity_type_id = entity_type_id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public YearSurvey getYearSurvey() {
		return yearSurvey;
	}

	public void setYearSurvey(YearSurvey yearSurvey) {
		this.yearSurvey = yearSurvey;
	}

	public PqrsEntityType getPqrsEntityType() {
		return pqrsEntityType;
	}

	public void setPqrsEntityType(PqrsEntityType pqrsEntityType) {
		this.pqrsEntityType = pqrsEntityType;
	}	
	
}
