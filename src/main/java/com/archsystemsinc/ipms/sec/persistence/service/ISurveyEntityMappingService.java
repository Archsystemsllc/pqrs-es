package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;


public interface ISurveyEntityMappingService extends IService<SurveyEntityMapping> {



	
	
	List<SurveyEntityMapping> findBySurveyAndRecordStatus(Survey survey, int recordStatus);
	
	List<SurveyEntityMapping> findBySurvey(Survey survey);
	
	List<SurveyEntityMapping> findByPqrsEntityAndRecordStatus(PqrsEntity pqrsEntity, int recordStatus);
	
	List<SurveyEntityMapping> findAllByRecordStatus(int recordStatus);
	
	List<SurveyEntityMapping> findByPqrsEntity(PqrsEntity pqrsEntity);
	
	List<SurveyEntityMapping> findByPqrsEntityAndSurvey(PqrsEntity pqrsEntity, Survey survey);	
	
	SurveyEntityMapping findByPqrsEntityAndSurveyAndRecordStatus(PqrsEntity pqrsEntity, Survey survey, int recordStatus);
	
	SurveyEntityMapping findByIdAndRecordStatus(long id, int recordStatus);	
	
	List<SurveyEntityMapping> search( final SurveyEntityMapping surveyEntityMapping );
}

