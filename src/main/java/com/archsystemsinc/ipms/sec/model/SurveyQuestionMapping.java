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
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * persistence domain model for SurveyEntityMapping
 * it relates pqrs entity with its questioner  
 * 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("survey_question_mapping")
@Table(name = "survey_question_mapping")
public class SurveyQuestionMapping implements INameableEntity, Comparable<SurveyQuestionMapping> {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;	  

	/** survey linked with the question*/
	@ManyToOne
	@JoinColumn(name = "surveyid", nullable = true)
	private Survey survey;
	
	/** question selected in the survey*/
	@ManyToOne
	@JoinColumn(name = "questionid", nullable = true)
	private Questions question;
	
	/** category of the question selected in the survey*/
	@ManyToOne
	@JoinColumn(name = "question_category_id")
	private QuestionCategory questionCategory;
	
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
	
	/** year of survey*/
	@ManyToOne
	@JoinColumn(name = "survey_year_id")
	private YearSurvey yearSurvey;
	
	/** pqrs entity taking questioner of the survey */
	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	private PqrsEntity pqrsEntity;
	
	@Column(name = "record_status",nullable = true)
	private int recordStatus;

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
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

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
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
	public int compareTo(SurveyQuestionMapping o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public YearSurvey getYearSurvey() {
		return yearSurvey;
	}

	public void setYearSurvey(YearSurvey yearSurvey) {
		this.yearSurvey = yearSurvey;
	}

	

	public PqrsEntity getPqrsEntity() {
		return pqrsEntity;
	}

	public void setPqrsEntity(PqrsEntity pqrsEntity) {
		this.pqrsEntity = pqrsEntity;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
