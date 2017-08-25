package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Answers;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public interface ISurveyJpaDAO extends JpaRepository<Survey, Long>,JpaSpecificationExecutor<Survey> {
	
	List<Survey> findByPqrsEntityTypeAndRecordStatus ( final PqrsEntityType pqrsEntityType, int recordStatus );
	
	List<Survey> findByYearSurveyAndRecordStatus( final YearSurvey yearSurvey, int recordStatus );
	
	Survey findByIdAndRecordStatus(long id, int recordStatus);
	
}
