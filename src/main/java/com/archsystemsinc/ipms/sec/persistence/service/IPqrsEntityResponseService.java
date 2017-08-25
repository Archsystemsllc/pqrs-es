package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityResponse;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;


public interface IPqrsEntityResponseService extends IService<PqrsEntityResponse> {

	PqrsEntityResponse findByName(final String name);

	PqrsEntityResponse findOne(final PqrsEntityResponse pqrsEntityResponse);
	
	PqrsEntityResponse findByQuestion(final Questions question);
	
	List<PqrsEntityResponse> findByPqrsEntityAndRecordStatus(final PqrsEntity pqrsEntity, int recordStatus);
	
	List<PqrsEntityResponse> findByPqrsEntityAndSurveyAndRecordStatus(final PqrsEntity pqrsEntity, final Survey survey, int recordStatus);
	
	List<PqrsEntityResponse> findByPqrsEntityAndSurveyAndQuestionCategoryAndRecordStatus(final PqrsEntity pqrsEntity, final Survey survey, final QuestionCategory questionCategory, int recordStatus);
	
	PqrsEntityResponse findByIdAndRecordStatus(long id, int recordStatus);
	
	List<PqrsEntityResponse> findByQuestionAndRecordStatus(final Questions question, int recordStatus);
	
}
