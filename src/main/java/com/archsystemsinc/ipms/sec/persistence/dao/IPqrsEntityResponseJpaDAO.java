package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityResponse;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;

public interface IPqrsEntityResponseJpaDAO extends JpaRepository<PqrsEntityResponse, Long>,
JpaSpecificationExecutor<PqrsEntityResponse> {
	
	List<PqrsEntityResponse> findByPqrsEntityAndRecordStatus(final PqrsEntity pqrsEntity, int recordStatus);
	
	List<PqrsEntityResponse> findByPqrsEntityAndSurveyAndRecordStatus(final PqrsEntity pqrsEntity, final Survey survey, int recordStatus);
	
	List<PqrsEntityResponse> findByPqrsEntityAndSurveyAndQuestionCategoryAndRecordStatus(final PqrsEntity pqrsEntity, final Survey survey, final QuestionCategory questionCategory, int recordStatus);
	
	PqrsEntityResponse findByIdAndRecordStatus(long id, int recordStatus);
	
	PqrsEntityResponse findByQuestion(final Questions question);
	
	List<PqrsEntityResponse> findByQuestionAndRecordStatus(final Questions question, int recordStatus);
}