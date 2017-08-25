package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityResponse;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.QuestionType;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.persistence.dao.IPqrsEntityResponseJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityResponseService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for pqrs entity responses of a survey using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class PqrsEntityResponseServiceImpl extends AbstractService<PqrsEntityResponse>
implements IPqrsEntityResponseService {

	@Autowired
	IPqrsEntityResponseJpaDAO dao;
	
	// Spring

	@Override
	protected final IPqrsEntityResponseJpaDAO getDao() {
		return dao;
	}

	public PqrsEntityResponseServiceImpl() {
		super(PqrsEntityResponse.class);
	}

	// API

	// search

	@Override
	public List<PqrsEntityResponse> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<PqrsEntityResponse> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], PqrsEntityResponse.class);

		Specifications<PqrsEntityResponse> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], PqrsEntityResponse.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	

	@Override
	public Specification<PqrsEntityResponse> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, PqrsEntityResponse.class);
	}

	@Override
	protected JpaSpecificationExecutor<PqrsEntityResponse> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public PqrsEntityResponse findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PqrsEntityResponse findOne(PqrsEntityResponse PqrsEntityResponse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional( readOnly = true )
	public List<PqrsEntityResponse> findByPqrsEntityAndRecordStatus(PqrsEntity pqrsEntity, int recordStatus) {
		return dao.findByPqrsEntityAndRecordStatus(pqrsEntity, recordStatus);
	}
	
	@Override
	@Transactional( readOnly = true )
	public List<PqrsEntityResponse> findByPqrsEntityAndSurveyAndRecordStatus(
			PqrsEntity pqrsEntity, Survey survey, int recordStatus) {
		return dao.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey, recordStatus);
	}

	@Override
	@Transactional( readOnly = true )
	public List<PqrsEntityResponse> findByPqrsEntityAndSurveyAndQuestionCategoryAndRecordStatus(
			PqrsEntity pqrsEntity, Survey survey,
			QuestionCategory questionCategory, int recordStatus) {
		return dao.findByPqrsEntityAndSurveyAndQuestionCategoryAndRecordStatus(pqrsEntity, survey, questionCategory, recordStatus);
	}

	@Override
	public PqrsEntityResponse findByIdAndRecordStatus(long id, int recordStatus) {
		return dao.findByIdAndRecordStatus(id, recordStatus);
	}

	@Override
	public PqrsEntityResponse findByQuestion(Questions question) {
		// TODO Auto-generated method stub
		return dao.findByQuestion(question);
	}

	@Override
	public List<PqrsEntityResponse> findByQuestionAndRecordStatus(
			Questions question, int recordStatus) {
		// TODO Auto-generated method stub
		return dao.findByQuestionAndRecordStatus(question,recordStatus);
	}
}
