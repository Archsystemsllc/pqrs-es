package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public interface ICreateQuestionJpaDAO extends JpaRepository<Questions, Long>, JpaSpecificationExecutor<Questions> {

	
	List<Questions> findQuestionsByQuestionCategoryAndRecordStatus(QuestionCategory questionCategory, int recordStatus);
	
	List<Questions> findQuestionsByQuestionCategoryAndPqrsEntityTypeAndYearSurveyAndRecordStatus(QuestionCategory questionCategory,PqrsEntityType pqrsEntityType,YearSurvey yearSurvey, int recordStatus);
	
    List<Questions> findByPqrsEntityTypeAndRecordStatus ( final PqrsEntityType pqrsEntityType, int recordStatus );
	
	List<Questions> findByYearSurveyAndRecordStatus( final YearSurvey yearSurvey, int recordStatus );
	
	Questions findByIdAndRecordStatus(long id, int recordStatus);
}
