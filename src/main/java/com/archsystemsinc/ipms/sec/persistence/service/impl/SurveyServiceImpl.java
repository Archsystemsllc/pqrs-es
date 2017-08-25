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
import com.archsystemsinc.ipms.persistence.search.SurveySpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.dao.ISurveyJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for survey using different search criteria 
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class SurveyServiceImpl extends AbstractService<Survey>
implements ISurveyService {

	@Autowired
	ISurveyJpaDAO dao;

	public SurveyServiceImpl() {
		super(Survey.class);
	}

	// API

	// search

	@Override
	public List<Survey> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Survey> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Survey.class);

		Specifications<Survey> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Survey.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll();
	}
	
	@Override
	public List< Survey > search( Survey survey ){
		Specifications< Survey > specifications;
		if(survey.getPqrsEntityType() == null) {
			specifications = Specifications.where(SurveySpecifications.searchByYear(survey.getYearSurvey()))
					.and(SurveySpecifications.searchByRecordStatus(1));
		} else {
			specifications = Specifications.where(SurveySpecifications.searchByEntityType(survey.getPqrsEntityType()))
					.and(SurveySpecifications.searchByYear(survey.getYearSurvey()))
					.and(SurveySpecifications.searchByRecordStatus(1));
		}
		
		return getDao().findAll( specifications );
	}

	@Override
	protected final ISurveyJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Survey> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, Survey.class);
	}

	@Override
	protected JpaSpecificationExecutor<Survey> getSpecificationExecutor() {
		return getDao();
	}

	@Override
	public List<Survey> findByPqrsEntityTypeAndRecordStatus(final PqrsEntityType pqrsEntityType, int recordStatus) {
		
		return getDao().findByPqrsEntityTypeAndRecordStatus(pqrsEntityType,recordStatus);
	}
	
	@Override
	public List<Survey> findByYearSurveyAndRecordStatus(final YearSurvey yearSurvey, int recordStatus) {
		
		return getDao().findByYearSurveyAndRecordStatus(yearSurvey, recordStatus);
	}

	@Override
	public Survey findByIdAndRecordStatus(long id, int recordStatus) {
		
		return getDao().findByIdAndRecordStatus(id, recordStatus);
	}	
}
