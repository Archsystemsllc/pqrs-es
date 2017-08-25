package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;

public interface ISurveyQuestionMappingJpaDAO extends JpaRepository<SurveyQuestionMapping, Long>,
JpaSpecificationExecutor<SurveyQuestionMapping> {
	
	List<SurveyQuestionMapping> findBySurveyAndRecordStatus(Survey survey, int recordStatus);	
	
	List<SurveyQuestionMapping> findBySurveyAndQuestionCategoryAndRecordStatus(Survey survey, QuestionCategory questionCategory, int recordStatus);	
	
	List<SurveyQuestionMapping> findBySurveyAndQuestionCategory(Survey survey, QuestionCategory questionCategory);	
	
	SurveyQuestionMapping findByIdAndRecordStatus(long id, int recordStatus);
	
	//added to search question mapped to survey
	List<SurveyQuestionMapping> findByQuestionAndRecordStatus(Questions question, int recordStatus);	
}
