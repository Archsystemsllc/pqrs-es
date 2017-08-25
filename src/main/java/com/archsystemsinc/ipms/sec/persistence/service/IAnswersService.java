package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Answers;
import com.archsystemsinc.ipms.sec.model.Questions;


public interface IAnswersService extends IService<Answers> {

	Answers findByName(final String name);

	Answers findOne(final Answers answers);
	
	List<Answers> findCurrentQuestionAnswer(Questions question);
	
	Answers findByIdAndRecordStatus(long id, int recordStatus);
}
