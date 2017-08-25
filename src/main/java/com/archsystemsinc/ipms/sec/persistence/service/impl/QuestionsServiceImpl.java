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
import com.archsystemsinc.ipms.persistence.search.QuestionsSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.QuestionCategory;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.dao.ICreateQuestionJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IQuestionsService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;

/**
 * search service for question using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class QuestionsServiceImpl extends AbstractService<Questions> implements IQuestionsService {

	@Autowired
	ICreateQuestionJpaDAO dao;

	public QuestionsServiceImpl() {
		super(Questions.class);
	}

	// API

	// search

	@Override
	public List< Questions > search( Questions question ){
		Specifications< Questions > specifications;
		if(question.getPqrsEntityType() == null) {
			specifications = Specifications.where(QuestionsSpecifications.searchByYear(question.getYearSurvey()))
					.and(QuestionsSpecifications.searchByRecordStatus(1));
		} else {
			specifications = Specifications.where(QuestionsSpecifications.searchByEntityType(question.getPqrsEntityType()))
					.and(QuestionsSpecifications.searchByYear(question.getYearSurvey()))
					.and(QuestionsSpecifications.searchByRecordStatus(1));
		}
		
		return getDao().findAll( specifications );
	}

	// Spring

	@Override
	protected final ICreateQuestionJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Questions> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, Questions.class);
	}



	@Override
	protected JpaSpecificationExecutor<Questions> getSpecificationExecutor() {
		return getDao();
	}

	@Override
	public Questions findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Questions CreateQuestion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Questions findOne(Questions questionName) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<Questions> findQuestionsByQuestionCategoryAndRecordStatus(
			QuestionCategory questionCategory, int recordStatus) {
		return dao.findQuestionsByQuestionCategoryAndRecordStatus(questionCategory, recordStatus);
	}
	
	@Override
	public List<Questions> findQuestionsByQuestionCategoryAndPqrsEntityTypeAndYearSurveyAndRecordStatus(QuestionCategory questionCategory,PqrsEntityType pqrsEntityType,YearSurvey yearSurvey, int recordStatus){
		return dao.findQuestionsByQuestionCategoryAndPqrsEntityTypeAndYearSurveyAndRecordStatus(questionCategory,pqrsEntityType,yearSurvey,recordStatus);
	}


	@Override
	public List<Questions> findByPqrsEntityTypeAndRecordStatus(final PqrsEntityType pqrsEntityType, int recordStatus) {
		// TODO Auto-generated method stub
		return getDao().findByPqrsEntityTypeAndRecordStatus(pqrsEntityType,recordStatus);
	}
	

	@Override
	public List<Questions> findByYearSurveyAndRecordStatus(final YearSurvey yearSurvey, int recordStatus) {
		// TODO Auto-generated method stub
		return getDao().findByYearSurveyAndRecordStatus(yearSurvey,recordStatus);
	}
	
	@Override
	public Questions findByIdAndRecordStatus(long id, int recordStatus) {
		// TODO Auto-generated method stub
		return getDao().findByIdAndRecordStatus(id,recordStatus);
	}	
}
