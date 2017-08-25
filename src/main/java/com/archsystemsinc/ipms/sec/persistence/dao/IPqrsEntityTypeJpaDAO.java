package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.PqrsEntityType;

public interface IPqrsEntityTypeJpaDAO extends JpaRepository<PqrsEntityType, Long>,
JpaSpecificationExecutor<PqrsEntityType> {

	PqrsEntityType findByName( final String name );
	
	PqrsEntityType findByIdAndRecordStatus(long id, int recordStatus);
	
}