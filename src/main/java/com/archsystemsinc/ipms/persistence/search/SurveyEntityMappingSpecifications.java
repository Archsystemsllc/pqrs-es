package com.archsystemsinc.ipms.persistence.search;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping;
import com.archsystemsinc.ipms.sec.model.SurveyEntityMapping_;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public final class SurveyEntityMappingSpecifications {

	private SurveyEntityMappingSpecifications() {
		throw new AssertionError();
	}
	
	// API
	public static Specification<SurveyEntityMapping> searchByEntityType(final PqrsEntityType pqrsEntityType) {
		return new Specification<SurveyEntityMapping>() {
			@Override
			public final Predicate toPredicate(final Root<SurveyEntityMapping> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(pqrsEntityType != null) {
					final Predicate matchingEPBySource = builder.equal(root.get(SurveyEntityMapping_.pqrsEntityType), pqrsEntityType);
					return matchingEPBySource;
				} else return null;			
			}
		};
	}	
	
	public static Specification<SurveyEntityMapping> searchByYear(final YearSurvey yearSurvey) {
		return new Specification<SurveyEntityMapping>() {
			@Override
			public final Predicate toPredicate(final Root<SurveyEntityMapping> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(yearSurvey != null) {
					final Predicate matchingEPByYear = builder.equal(root.get(SurveyEntityMapping_.yearSurvey), yearSurvey);						
					return matchingEPByYear;
				} else 
					return null;
			}
		};
	}	
	
	
	public static Specification<SurveyEntityMapping> searchByRecordStatus(final Integer recordStatus) {
		return new Specification<SurveyEntityMapping>() {
			@Override
			public final Predicate toPredicate(final Root<SurveyEntityMapping> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					final Predicate matchingEPByRecordStatus = builder.equal(root.get(SurveyEntityMapping_.recordStatus), recordStatus);
					return matchingEPByRecordStatus;
							
			}
		};
	}
	
	public static Specification<SurveyEntityMapping> searchBySurveyCompleteFlag(final Integer surveyCompleteFlag) {
		return new Specification<SurveyEntityMapping>() {
			@Override
			public final Predicate toPredicate(final Root<SurveyEntityMapping> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					final Predicate matchingEPByRecordStatus = builder.equal(root.get(SurveyEntityMapping_.surveyCompleteFlag), surveyCompleteFlag);
					return matchingEPByRecordStatus;
							
			}
		};
	}
	
	

}
