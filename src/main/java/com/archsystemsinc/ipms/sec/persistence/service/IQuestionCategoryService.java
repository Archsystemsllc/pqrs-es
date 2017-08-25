package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;

public interface IQuestionCategoryService extends IService<QuestionCategory> {
	

	QuestionCategory findByName(final String name);

	List<QuestionCategory> findByInvited(String name);
	
	QuestionCategory findByIdAndRecordStatus(long id, int recordStatus);

}
