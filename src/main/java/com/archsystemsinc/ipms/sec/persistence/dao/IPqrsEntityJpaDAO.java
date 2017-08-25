package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public interface IPqrsEntityJpaDAO extends JpaRepository<PqrsEntity, Long>, JpaSpecificationExecutor<PqrsEntity> {
	
	List<PqrsEntity> findByPqrsEntityTypeAndRecordStatus (final PqrsEntityType pqrsEntityType, int recordStatus);
	
	List<PqrsEntity> findByYearSurveyAndRecordStatus (final YearSurvey yearSurvey, int recordStatus);
	
	PqrsEntity findByIdAndRecordStatus(long id, int recordStatus);
	
	List<PqrsEntity> findByPqrsEntityTypeAndYearSurveyAndRecordStatus ( final PqrsEntityType pqrsEntityType, final YearSurvey yearSurvey, int recordStatus );

}