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
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.dao.IYearSurveyJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IYearService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for year of a survey using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class YearSurveyServiceImpl extends AbstractService<YearSurvey>
implements IYearService {

	@Autowired
	IYearSurveyJpaDAO dao;

	public YearSurveyServiceImpl() {
		super(YearSurvey.class);
	}

	// API

	// search

	@Override
	public List<YearSurvey> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<YearSurvey> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], YearSurvey.class);

		Specifications<YearSurvey> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], YearSurvey.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// Spring

	@Override
	protected final IYearSurveyJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<YearSurvey> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, YearSurvey.class);
	}



	@Override
	protected JpaSpecificationExecutor<YearSurvey> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YearSurvey findByName(String name) {
		System.out.println("it entered year survey");
		// TODO Auto-generated method stub
		return getDao().findByName( name );
	}
	
}
