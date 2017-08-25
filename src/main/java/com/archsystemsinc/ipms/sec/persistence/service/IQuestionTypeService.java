package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.QuestionType;

public interface IQuestionTypeService extends IService<QuestionType> {
	

	QuestionType findByName(final String name);

	List<QuestionType> findByInvited(String name);

}