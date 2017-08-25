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
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.persistence.dao.IQuestionCategoryJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionCategoryService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for question category using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class QuestionCategoryServiceImpl extends AbstractService<QuestionCategory>
implements IQuestionCategoryService {

	@Autowired
	IQuestionCategoryJpaDAO dao;

	public QuestionCategoryServiceImpl() {
		super(QuestionCategory.class);
	}

	// API

	// search

	@Override
	public List<QuestionCategory> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<QuestionCategory> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], QuestionCategory.class);

		Specifications<QuestionCategory> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
				.resolveConstraint(constraints[i], QuestionCategory.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	

	// Spring

	@Override
	protected final IQuestionCategoryJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<QuestionCategory> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, QuestionCategory.class);
	}

	@Override
	protected JpaSpecificationExecutor<QuestionCategory> getSpecificationExecutor() {
		return dao;
	}


	@Override
	@Transactional( readOnly = true )
	public QuestionCategory findByName(final String name) {
		return dao.findByName( name );
	}
	@Override
	public List<QuestionCategory> findByInvited(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionCategory findByIdAndRecordStatus(long id, int recordStatus) {
		return dao.findByIdAndRecordStatus( id, recordStatus );
	}

	

}
