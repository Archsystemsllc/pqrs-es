package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.QuestionType;

public interface IQuestionTypeJpaDAO extends JpaRepository<QuestionType, Long>,
JpaSpecificationExecutor<QuestionType> {

	QuestionType findByName(final String name);
}

