package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Answers;

public interface IAnswersJpaDAO extends JpaRepository<Answers, Long>,
JpaSpecificationExecutor<Answers> {
	Answers findByIdAndRecordStatus(long id, int recordStatus);
}