package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.YearSurvey;


public interface IQuestionsService extends IService<Questions> {

	Questions findByName(final String name);

	void add(Questions CreateQuestion);
	
	Questions findOne(final Questions questionName);
	
	
	List<Questions> findQuestionsByQuestionCategoryAndRecordStatus(QuestionCategory questionCategory, int recordStatus);
	
	List<Questions> findQuestionsByQuestionCategoryAndPqrsEntityTypeAndYearSurveyAndRecordStatus(QuestionCategory questionCategory,PqrsEntityType pqrsEntityType,YearSurvey yearSurvey, int recordStatus);
	
    List<Questions> findByPqrsEntityTypeAndRecordStatus ( final PqrsEntityType pqrsEntityType, int recordStatus );
	
	List<Questions> findByYearSurveyAndRecordStatus( final YearSurvey yearSurvey, int recordStatus );
	
	Questions findByIdAndRecordStatus(long id, int recordStatus);
	
	List<Questions> search( final Questions question );
}
