package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.QuestionCategory;



public interface IQuestionCategoryJpaDAO extends JpaRepository<QuestionCategory, Long>,
JpaSpecificationExecutor<QuestionCategory> {

	QuestionCategory findByName(final String name);
	
	QuestionCategory findByIdAndRecordStatus(long id, int recordStatus);
}

