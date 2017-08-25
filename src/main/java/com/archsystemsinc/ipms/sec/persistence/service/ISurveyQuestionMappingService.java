package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;


public interface ISurveyQuestionMappingService extends IService<SurveyQuestionMapping> {

	List<SurveyQuestionMapping> findBySurveyAndRecordStatus(Survey survey, int recordStatus);	
	
	List<SurveyQuestionMapping> findBySurveyAndQuestionCategoryAndRecordStatus(Survey survey, QuestionCategory questionCategory, int recordStatus);	
	
	List<SurveyQuestionMapping> findBySurveyAndQuestionCategory(Survey survey, QuestionCategory questionCategory);	
	
	SurveyQuestionMapping findByIdAndRecordStatus(long id, int recordStatus);
	
	List<SurveyQuestionMapping> findByQuestionAndRecordStatus(Questions question, int recordStatus);	
}

