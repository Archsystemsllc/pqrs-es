package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public interface IYearSurveyJpaDAO extends JpaRepository<YearSurvey, Long>,JpaSpecificationExecutor<YearSurvey> {

	YearSurvey findByName( final String name );
}