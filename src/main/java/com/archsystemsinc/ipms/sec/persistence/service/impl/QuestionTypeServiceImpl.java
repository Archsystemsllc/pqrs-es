package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.QuestionType;
import com.archsystemsinc.ipms.sec.persistence.dao.IQuestionTypeJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionTypeService;

/**
 * search service for question type using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class QuestionTypeServiceImpl extends AbstractService<QuestionType>
implements IQuestionTypeService {

	@Autowired
	IQuestionTypeJpaDAO dao;

	public QuestionTypeServiceImpl() {
		super(QuestionType.class);
	}

	// API

	// search



	// find

	@Override
	@Transactional( readOnly = true )
	public QuestionType findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IQuestionTypeJpaDAO getDao() {
		return dao;
	}

	
	@Override
	protected JpaSpecificationExecutor<QuestionType> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<QuestionType> findByInvited(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
