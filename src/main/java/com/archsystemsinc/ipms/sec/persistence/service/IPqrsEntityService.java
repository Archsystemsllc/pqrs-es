package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.YearSurvey;


public interface IPqrsEntityService extends IService<PqrsEntity> {

	PqrsEntity findByName(final String name);

	PqrsEntity findOne(final PqrsEntity PqrsEntityResponse);
	
    List<PqrsEntity> findByPqrsEntityTypeAndRecordStatus ( final PqrsEntityType pqrsEntityType, int recordStatus );
    
    List<PqrsEntity> findByPqrsEntityTypeAndYearSurveyAndRecordStatus ( final PqrsEntityType pqrsEntityType, final YearSurvey yearSurvey, int recordStatus );
	
	List<PqrsEntity> findByYearSurveyAndRecordStatus( final YearSurvey yearSurvey, int recordStatus );
	
	PqrsEntity findByIdAndRecordStatus(long id, int recordStatus);
	
	List<PqrsEntity> search( final PqrsEntity pqrsEntity );
	
}
