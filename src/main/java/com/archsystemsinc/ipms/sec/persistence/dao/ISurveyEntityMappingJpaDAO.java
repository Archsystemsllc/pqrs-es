package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;

public interface ISurveyEntityMappingJpaDAO extends JpaRepository<SurveyEntityMapping, Long>,
JpaSpecificationExecutor<SurveyEntityMapping> {
	
	

	List<SurveyEntityMapping> findBySurveyAndRecordStatus(Survey survey, int recordStatus);
	
	List<SurveyEntityMapping> findBySurvey(Survey survey);
	
	List<SurveyEntityMapping> findByPqrsEntityAndRecordStatus(PqrsEntity pqrsEntity, int recordStatus);
	
	List<SurveyEntityMapping> findAllByRecordStatus(int recordStatus);
	
	List<SurveyEntityMapping> findByPqrsEntity(PqrsEntity pqrsEntity);
	
	List<SurveyEntityMapping> findByPqrsEntityAndSurvey(PqrsEntity pqrsEntity, Survey survey);
	
	SurveyEntityMapping findByPqrsEntityAndSurveyAndRecordStatus(PqrsEntity pqrsEntity, Survey survey, int recordStatus);
	
	SurveyEntityMapping findByIdAndRecordStatus(long id, int recordStatus);
	
}
