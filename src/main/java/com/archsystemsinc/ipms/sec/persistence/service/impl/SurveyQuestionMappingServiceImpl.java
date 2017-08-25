package com.archsystemsinc.ipms.sec.persistence.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

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
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyQuestionMapping;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.dao.ISurveyQuestionMappingJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyQuestionMappingService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for questions of a survey using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class SurveyQuestionMappingServiceImpl extends AbstractService<SurveyQuestionMapping>
implements ISurveyQuestionMappingService {

	@Autowired
	ISurveyQuestionMappingJpaDAO dao;

	public SurveyQuestionMappingServiceImpl() {
		super(SurveyQuestionMapping.class);
	}

	// API

	// search

	@Override
	public List<SurveyQuestionMapping> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<SurveyQuestionMapping> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], SurveyQuestionMapping.class);

		Specifications<SurveyQuestionMapping> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], SurveyQuestionMapping.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll();
	}

	@Override
	protected final ISurveyQuestionMappingJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<SurveyQuestionMapping> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, SurveyQuestionMapping.class);
	}



	@Override
	protected JpaSpecificationExecutor<SurveyQuestionMapping> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyQuestionMapping> findBySurveyAndRecordStatus(Survey survey, int recordStatus) {
		return dao.findBySurveyAndRecordStatus(survey,recordStatus);
	}

	@Override
	public List<SurveyQuestionMapping> findBySurveyAndQuestionCategoryAndRecordStatus(
			Survey survey, QuestionCategory questionCategory, int recordStatus) {		
		return dao.findBySurveyAndQuestionCategoryAndRecordStatus(survey, questionCategory,recordStatus);
	}

	@Override
	public SurveyQuestionMapping findByIdAndRecordStatus(long id,
			int recordStatus) {
		return dao.findByIdAndRecordStatus(id,recordStatus);
	}

	@Override
	public List<SurveyQuestionMapping> findBySurveyAndQuestionCategory(
			Survey survey, QuestionCategory questionCategory) {
		return dao.findBySurveyAndQuestionCategory(survey, questionCategory);
	}

	//method added to search question mapped to survey
	@Override
	public List<SurveyQuestionMapping> findByQuestionAndRecordStatus(Questions question, int recordStatus) {
		// TODO Auto-generated method stub
		return dao.findByQuestionAndRecordStatus(question, recordStatus);
	}
	
}
