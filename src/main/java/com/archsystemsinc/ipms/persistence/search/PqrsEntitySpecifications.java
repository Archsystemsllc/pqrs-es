package com.archsystemsinc.ipms.persistence.search;

import java.util.Date;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.PqrsEntity;
import com.archsystemsinc.ipms.sec.model.PqrsEntity_;

import com.archsystemsinc.ipms.sec.model.YearSurvey;

public final class PqrsEntitySpecifications {

	private PqrsEntitySpecifications() {
		throw new AssertionError();
	}
	
	// API
	public static Specification<PqrsEntity> searchByEntityType(final PqrsEntityType pqrsEntityType) {
		return new Specification<PqrsEntity>() {
			@Override
			public final Predicate toPredicate(final Root<PqrsEntity> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(pqrsEntityType != null) {
					final Predicate matchingPqrsEntityByPqrsEntityType = builder.equal(root.get(PqrsEntity_.pqrsEntityType), pqrsEntityType);
					return matchingPqrsEntityByPqrsEntityType;
				} else return null;			
			}
		};
	}	
	
	public static Specification<PqrsEntity> searchByYear(final YearSurvey yearSurvey) {
		return new Specification<PqrsEntity>() {
			@Override
			public final Predicate toPredicate(final Root<PqrsEntity> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(yearSurvey != null) {
					final Predicate matchingPqrsEntityByYear = builder.equal(root.get(PqrsEntity_.yearSurvey), yearSurvey);						
					return matchingPqrsEntityByYear;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<PqrsEntity> searchByCreatedDate(final Date createdDate) {
			return new Specification<PqrsEntity>() {
				@Override
				public final Predicate toPredicate(final Root<PqrsEntity> root,
						final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					if(createdDate != null ) {
						final Predicate matchingPqrsEntityByCreatedDate = builder.lessThan(root.get(PqrsEntity_.createdDate), createdDate );
						return matchingPqrsEntityByCreatedDate;
					} else 
						return null;				
				}
			};
	}	
	
	public static Specification<PqrsEntity> searchByRecordStatus(final Integer recordStatus) {
		return new Specification<PqrsEntity>() {
			@Override
			public final Predicate toPredicate(final Root<PqrsEntity> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					final Predicate matchingPqrsEntityByRecordStatus = builder.equal(root.get(PqrsEntity_.recordStatus), recordStatus);
					return matchingPqrsEntityByRecordStatus;
							
			}
		};
	}
}