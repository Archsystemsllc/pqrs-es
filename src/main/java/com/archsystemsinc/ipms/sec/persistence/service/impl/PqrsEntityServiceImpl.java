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
import com.archsystemsinc.ipms.persistence.search.PqrsEntitySpecifications;
import com.archsystemsinc.ipms.persistence.search.SurveySpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Answers;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.YearSurvey;
import com.archsystemsinc.ipms.sec.persistence.dao.IAnswersJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IPqrsEntityJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IAnswersService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for pqrs entity using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class PqrsEntityServiceImpl extends AbstractService<PqrsEntity>
implements IPqrsEntityService {

	@Autowired
	IPqrsEntityJpaDAO dao;

	public PqrsEntityServiceImpl() {
		super(PqrsEntity.class);
	}

	// API

	// search

	@Override
	public List<PqrsEntity> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<PqrsEntity> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], PqrsEntity.class);

		Specifications<PqrsEntity> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], PqrsEntity.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}
	
	@Override
	public List< PqrsEntity > search( PqrsEntity pqrsEntity ){
		Specifications< PqrsEntity > specifications;
		if(pqrsEntity.getPqrsEntityType() == null) {
			specifications = Specifications.where(PqrsEntitySpecifications.searchByYear(pqrsEntity.getYearSurvey()))
					.and(PqrsEntitySpecifications.searchByRecordStatus(1));
		} else {
			specifications = Specifications.where(PqrsEntitySpecifications.searchByEntityType(pqrsEntity.getPqrsEntityType()))
					.and(PqrsEntitySpecifications.searchByYear(pqrsEntity.getYearSurvey()))
					.and(PqrsEntitySpecifications.searchByRecordStatus(1));
		}
		
		return getDao().findAll( specifications );
	}


	// Spring

	@Override
	protected final IPqrsEntityJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<PqrsEntity> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, PqrsEntity.class);
	}



	@Override
	protected JpaSpecificationExecutor<PqrsEntity> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	


	}

	@Override
	public PqrsEntity findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PqrsEntity findOne(PqrsEntity pqrsEntity) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@Override
	public List<PqrsEntity> findByPqrsEntityTypeAndRecordStatus(final PqrsEntityType pqrsEntityType, int recordStatus) {
		
		return getDao().findByPqrsEntityTypeAndRecordStatus(pqrsEntityType, recordStatus);
	}
	
	@Override
	public List<PqrsEntity> findByPqrsEntityTypeAndYearSurveyAndRecordStatus ( final PqrsEntityType pqrsEntityType, final YearSurvey yearSurvey, int recordStatus ) {
		
		return getDao().findByPqrsEntityTypeAndYearSurveyAndRecordStatus(pqrsEntityType, yearSurvey, recordStatus);
	}
	

	@Override
	public List<PqrsEntity> findByYearSurveyAndRecordStatus(final YearSurvey yearSurvey, int recordStatus) {
		
		return getDao().findByYearSurveyAndRecordStatus(yearSurvey, recordStatus);
	}

	@Override
	public PqrsEntity findByIdAndRecordStatus(long id, int recordStatus) {
		
		return getDao().findByIdAndRecordStatus(id, recordStatus);
	}
}
