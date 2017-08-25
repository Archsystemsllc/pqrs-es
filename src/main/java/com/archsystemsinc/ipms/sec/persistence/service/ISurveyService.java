package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.YearSurvey;


public interface ISurveyService extends IService<Survey> {

	List<Survey> findByPqrsEntityTypeAndRecordStatus ( final PqrsEntityType pqrsEntityType, int recordStatus );
	
	List<Survey> findByYearSurveyAndRecordStatus( final YearSurvey yearSurvey, int recordStatus );
	
	Survey findByIdAndRecordStatus(long id, int recordStatus);
	
	List<Survey> search( final Survey survey );
}

