package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;


public interface IPqrsEntityTypeService extends IService<PqrsEntityType> {

	PqrsEntityType findByName(final String name);

	PqrsEntityType findOne(final PqrsEntityType pqrsEntityType);
	
	PqrsEntityType findByIdAndRecordStatus(long id, int recordStatus);
	
}
