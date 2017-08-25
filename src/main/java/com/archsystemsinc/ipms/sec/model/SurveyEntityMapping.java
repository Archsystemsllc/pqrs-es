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
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * persistence domain model for SurveyEntityMapping
 * it relates pqrs entity with its survey  
 * 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("survey_entity_mapping")
@Table(name = "survey_entity_mapping")
public class SurveyEntityMapping implements INameableEntity, Comparable<SurveyEntityMapping> {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;	
	
	/** survey */
	@ManyToOne
	@JoinColumn(name = "surveyid", nullable = true)
	private Survey survey;
	
	/** pqrs entity taking survey*/
	@ManyToOne
	@JoinColumn(name = "entity_id", nullable = true)
	private PqrsEntity pqrsEntity;
	
	@Column(name = "survey_status", nullable = true)
	private String surveyStatus;
	
	@Column(name = "survey_start_date", nullable = true)
	private Date surveyStartDate;
	
	@Column(name = "survey_end_date", nullable = true)
	private Date surveyEndDate;	
	
	@Column(name = "record_status",nullable = true)
	private int recordStatus;	
	
	/** pqrs entity erx participation status flag*/
	@Column(name = "erx_participation_flag", nullable = true)
	private String erxParticipationFlag;
	
	/** pqrs entity survey completion status flag*/
	@Column(name = "survey_complete", nullable = true)
	private String surveyCompleteFlag="0";
	
	/** pqrs entity response certification agreement flag*/
	@Column(name = "i_agree_flag", nullable = true)
	private String iAgreeFlag="0";
	
	/** pqrs entity training questioner completion status flag*/
	@Column(name = "training_flag", nullable = true)
	private String trainingCompleteFlag="0";
	
	/** pqrs entity data handling questioner completion status flag*/
	@Column(name = "datahandling_flag", nullable = true)
	private String dataHandlingCompleteFlag="0";
	
	/** pqrs entity quality assurance completion status flag*/
	@Column(name = "qa_flag", nullable = true)
	private String qaCompleteFlag="0";
	
	/** pqrs entity erx questioner completion status flag*/
	@Column(name = "erx_flag", nullable = true)
	private String erxCompleteFlag="0";
	
	/** pqrs entity feedback questioner completion status flag*/
	@Column(name = "feedback_flag", nullable = true)
	private String feedbackCompleteFlag="0";
	
	/** */
	@Column(name = "email_content", nullable = true)
	private String emailContent;
	
	@Column(nullable = true)
	private String createdBy;
	
	@Column(nullable = true)
	private String updatedBy;
	
	@Column(name = "createddate", nullable = true)
	@OrderBy("createddate asc")
	private Date createdDate;
	
	@Column(name = "updateddate", nullable = true)
	@OrderBy("updateddate asc")
	private Date updatedDate;
	
	// Columns added on 5/29/2015
	
	/** pqrs entity type taking the survey*/
	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	private PqrsEntityType pqrsEntityType;
	
	/** year of survey*/
	@ManyToOne
	@JoinColumn(name = "year_id")
	private YearSurvey yearSurvey;
	
	
	@Transient
	private Long entityTypeId;
	
	@Transient
	private Long yearId;
	
	// Columns added on 5/29/2015
	
	
	@Transient
	private boolean iAgreeFlagBoolean;
	
	@Transient
	private Long surveyEntityId;
	
	@Column(name = "email_subject", nullable = true)
	private String emailSubject;
	
	
	
	
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

	public PqrsEntityType getPqrsEntityType() {
		return pqrsEntityType;
	}

	public void setPqrsEntityType(PqrsEntityType pqrsEntityType) {
		this.pqrsEntityType = pqrsEntityType;
	}

	public YearSurvey getYearSurvey() {
		return yearSurvey;
	}

	public void setYearSurvey(YearSurvey yearSurvey) {
		this.yearSurvey = yearSurvey;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public Long getSurveyEntityId() {
		return surveyEntityId;
	}

	public void setSurveyEntityId(Long surveyEntityId) {
		this.surveyEntityId = surveyEntityId;
	}

	public boolean isiAgreeFlagBoolean() {
		return iAgreeFlagBoolean;
	}

	public void setiAgreeFlagBoolean(boolean iAgreeFlagBoolean) {
		this.iAgreeFlagBoolean = iAgreeFlagBoolean;
	}

	public String getErxParticipationFlag() {
		return erxParticipationFlag;
	}

	public void setErxParticipationFlag(String erxParticipationFlag) {
		this.erxParticipationFlag = erxParticipationFlag;
	}

	public String getSurveyCompleteFlag() {
		return surveyCompleteFlag;
	}

	public void setSurveyCompleteFlag(String surveyCompleteFlag) {
		this.surveyCompleteFlag = surveyCompleteFlag;
	}

	public String getiAgreeFlag() {
		return iAgreeFlag;
	}

	public void setiAgreeFlag(String iAgreeFlag) {
		this.iAgreeFlag = iAgreeFlag;
	}

	public String getTrainingCompleteFlag() {
		return trainingCompleteFlag;
	}

	public void setTrainingCompleteFlag(String trainingCompleteFlag) {
		this.trainingCompleteFlag = trainingCompleteFlag;
	}

	public String getDataHandlingCompleteFlag() {
		return dataHandlingCompleteFlag;
	}

	public void setDataHandlingCompleteFlag(String dataHandlingCompleteFlag) {
		this.dataHandlingCompleteFlag = dataHandlingCompleteFlag;
	}

	public String getQaCompleteFlag() {
		return qaCompleteFlag;
	}

	public void setQaCompleteFlag(String qaCompleteFlag) {
		this.qaCompleteFlag = qaCompleteFlag;
	}

	public String getErxCompleteFlag() {
		return erxCompleteFlag;
	}

	public void setErxCompleteFlag(String erxCompleteFlag) {
		this.erxCompleteFlag = erxCompleteFlag;
	}

	public String getFeedbackCompleteFlag() {
		return feedbackCompleteFlag;
	}

	public void setFeedbackCompleteFlag(String feedbackCompleteFlag) {
		this.feedbackCompleteFlag = feedbackCompleteFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public PqrsEntity getPqrsEntity() {
		return pqrsEntity;
	}

	public void setPqrsEntity(PqrsEntity pqrsEntity) {
		this.pqrsEntity = pqrsEntity;
	}

	public String getSurveyStatus() {
		return surveyStatus;
	}

	public void setSurveyStatus(String surveyStatus) {
		this.surveyStatus = surveyStatus;
	}

	public Date getSurveyStartDate() {
		return surveyStartDate;
	}

	public void setSurveyStartDate(Date surveyStartDate) {
		this.surveyStartDate = surveyStartDate;
	}

	public Date getSurveyEndDate() {
		return surveyEndDate;
	}

	public void setSurveyEndDate(Date surveyEndDate) {
		this.surveyEndDate = surveyEndDate;
	}

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
	public int compareTo(SurveyEntityMapping o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
