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
import com.archsystemsinc.ipms.persistence.search.SurveyEntityMappingSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;
import com.archsystemsinc.ipms.sec.persistence.dao.ISurveyEntityMappingJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.ISurveyEntityMappingService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;

/**
 * search service for entities of a survey using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class SurveyEntityMappingServiceImpl extends AbstractService<SurveyEntityMapping>
implements ISurveyEntityMappingService {

	@Autowired
	ISurveyEntityMappingJpaDAO dao;

	public SurveyEntityMappingServiceImpl() {
		super(SurveyEntityMapping.class);
	}

	// API

	// search

	/*public List<SurveyEntityMapping> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<SurveyEntityMapping> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], SurveyEntityMapping.class);

		Specifications<SurveyEntityMapping> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], SurveyEntityMapping.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll();
	}*/

	// search
	 
		@Override
		public List< SurveyEntityMapping > search( SurveyEntityMapping surveyEntityMapping ){
			Specifications< SurveyEntityMapping > specifications = null;
			
			if(surveyEntityMapping.getPqrsEntityType() == null) {
				specifications = Specifications.where(SurveyEntityMappingSpecifications.searchByYear(surveyEntityMapping.getYearSurvey()))
						.and(SurveyEntityMappingSpecifications.searchByRecordStatus(1));
			} else {
				specifications = Specifications.where(SurveyEntityMappingSpecifications.searchByEntityType(surveyEntityMapping.getPqrsEntityType()))
						.and(SurveyEntityMappingSpecifications.searchByYear(surveyEntityMapping.getYearSurvey()))
						.and(SurveyEntityMappingSpecifications.searchByRecordStatus(1));
			}
			
			 /*= Specifications.where(SurveyEntityMappingSpecifications.searchByEntityType(surveyEntityMapping.getPqrsEntityType()))
																.and(SurveyEntityMappingSpecifications.searchByYear(surveyEntityMapping.getYearSurvey()))
																.and(SurveyEntityMappingSpecifications.searchByRecordStatus(com.archsystemsinc.ipms.util.GenericConstants.ACTIVE_INTEGER))
																.and(SurveyEntityMappingSpecifications.searchBySurveyCompleteFlag(com.archsystemsinc.ipms.util.GenericConstants.ACTIVE_INTEGER));*/
			
			return getDao().findAll( specifications );
		}
	@Override
	protected final ISurveyEntityMappingJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<SurveyEntityMapping> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, SurveyEntityMapping.class);
	}



	@Override
	protected JpaSpecificationExecutor<SurveyEntityMapping> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	


	}

	
	@Override
	@Transactional( readOnly = true )
	public List<SurveyEntityMapping> findBySurveyAndRecordStatus(
			Survey survey, int recordStatus) {
		return dao.findBySurveyAndRecordStatus(survey,recordStatus);
	}

	@Override
	@Transactional( readOnly = true )
	public SurveyEntityMapping findByPqrsEntityAndSurveyAndRecordStatus(
			PqrsEntity pqrsEntity, Survey survey, int recordStatus) {
		return dao.findByPqrsEntityAndSurveyAndRecordStatus(pqrsEntity, survey,recordStatus);
	}

	@Override
	public List<SurveyEntityMapping> findByPqrsEntityAndRecordStatus(PqrsEntity pqrsEntity, int recordStatus) {
		return dao.findByPqrsEntityAndRecordStatus(pqrsEntity,recordStatus);
	}

	@Override
	public SurveyEntityMapping findByIdAndRecordStatus(long id, int recordStatus) {
		return dao.findByIdAndRecordStatus(id,recordStatus);
	}

	@Override
	public List<SurveyEntityMapping> findByPqrsEntity(PqrsEntity pqrsEntity) {
		return dao.findByPqrsEntity(pqrsEntity);
	}

	@Override
	public List<SurveyEntityMapping> findByPqrsEntityAndSurvey(
			PqrsEntity pqrsEntity, Survey survey) {
		return dao.findByPqrsEntityAndSurvey(pqrsEntity, survey);
	}

	@Override
	public List<SurveyEntityMapping> findBySurvey(Survey survey) {
		return dao.findBySurvey(survey);
	}

	@Override
	public List<SurveyEntityMapping> findAllByRecordStatus(int recordStatus) {
		return dao.findAllByRecordStatus(recordStatus);
	}

	
}
