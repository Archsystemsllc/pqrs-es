package com.archsystemsinc.ipms.sec.model;

import java.util.HashMap;
import java.util.TreeMap;

import javax.persistence.Transient;

import com.archsystemsinc.ipms.common.INameableEntity;

/**
 * transient domain model for QuestionAndAnswerReport 
 * relates entity, question, answer remarks and pqrsEntityResponse with the entity
 * 
 * @see PqrsEntityResponse
 * 
 * @author 
 * @since
 */
public class QuestionAndAnswerReport implements INameableEntity, Comparable<QuestionAndAnswerReport> {
	

	@Transient
	private String surveyName;
	
	@Transient
	private Long questionId;
	
	@Transient
	private String questionDescription;
	
	@Transient
	private String entityName;
	
	@Transient
	private String yearName;
	
	@Transient
	private String questionCategory;
	
	@Transient
	private String answerRemarks;
	
	@Transient
	private TreeMap<Long, Questions> questionsMap;
	
	@Transient
	private QuestionAndAnswerReport insideQuestionAndAnswerReport;
	
	@Transient
	private Questions questions;
	
	@Transient
	private PqrsEntityResponse pqrsEntityResponse;
	
	public TreeMap<Long, Questions> getQuestionsMap() {
		return questionsMap;
	}

	public void setQuestionsMap(TreeMap<Long, Questions> questionsMap) {
		this.questionsMap = questionsMap;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public PqrsEntityResponse getPqrsEntityResponse() {
		return pqrsEntityResponse;
	}

	public void setPqrsEntityResponse(PqrsEntityResponse pqrsEntityResponse) {
		this.pqrsEntityResponse = pqrsEntityResponse;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}

	public String getAnswerRemarks() {
		return answerRemarks;
	}

	public void setAnswerRemarks(String answerRemarks) {
		this.answerRemarks = answerRemarks;
	}
	
	

	public QuestionAndAnswerReport getInsideQuestionAndAnswerReport() {
		return insideQuestionAndAnswerReport;
	}

	public void setInsideQuestionAndAnswerReport(
			QuestionAndAnswerReport insideQuestionAndAnswerReport) {
		this.insideQuestionAndAnswerReport = insideQuestionAndAnswerReport;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(QuestionAndAnswerReport o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
