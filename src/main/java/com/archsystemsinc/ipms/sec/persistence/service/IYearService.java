package com.archsystemsinc.ipms.sec.persistence.service;
import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.YearSurvey;


public interface IYearService extends IService<YearSurvey> {

	YearSurvey findByName(final String name);	
}
