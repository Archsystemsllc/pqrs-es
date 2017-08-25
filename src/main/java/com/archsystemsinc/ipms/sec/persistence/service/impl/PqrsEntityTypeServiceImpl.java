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
import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.persistence.dao.IAnswersJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.ICreateQuestionJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IPqrsEntityTypeJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IAnswersService;
import com.archsystemsinc.ipms.sec.persistence.service.IPqrsEntityTypeService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

/**
 * search service for pqrs entity type using different search criteria
 * 
 * @author 
 * @since
 */
@Service
@Transactional
public class PqrsEntityTypeServiceImpl extends AbstractService<PqrsEntityType>
implements IPqrsEntityTypeService {

	@Autowired
	IPqrsEntityTypeJpaDAO dao;

	public PqrsEntityTypeServiceImpl() {
		super(PqrsEntityType.class);
	}

	// API

	// search

	@Override
	public List<PqrsEntityType> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<PqrsEntityType> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], PqrsEntityType.class);

		Specifications<PqrsEntityType> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], PqrsEntityType.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// Spring

	@Override
	protected final IPqrsEntityTypeJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<PqrsEntityType> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, PqrsEntityType.class);
	}



	@Override
	protected JpaSpecificationExecutor<PqrsEntityType> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PqrsEntityType findByName(String name) {
		
		return getDao().findByName( name );
	}

	@Override
	public PqrsEntityType findOne(PqrsEntityType pqrsEntityType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PqrsEntityType findByIdAndRecordStatus(long id, int recordStatus) {
		return getDao().findByIdAndRecordStatus( id, recordStatus );
	}

	
}
