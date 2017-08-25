package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Answers;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.persistence.dao.IAnswersJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IAnswersService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for answers using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class AnswersServiceImpl extends AbstractService<Answers>
implements IAnswersService {

	@Autowired
	IAnswersJpaDAO dao;

	public AnswersServiceImpl() {
		super(Answers.class);
	}

	// API

	// search

	@Override
	public List<Answers> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Answers> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Answers.class);

		Specifications<Answers> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Answers.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// Spring

	@Override
	protected final IAnswersJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Answers> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, Answers.class);
	}



	@Override
	protected JpaSpecificationExecutor<Answers> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	


	}

	@Override
	public Answers findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answers findOne(Answers questionName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Answers> findCurrentQuestionAnswer(Questions question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answers findByIdAndRecordStatus(long id, int recordStatus) {
		return dao.findByIdAndRecordStatus(id,recordStatus);
	}
}
