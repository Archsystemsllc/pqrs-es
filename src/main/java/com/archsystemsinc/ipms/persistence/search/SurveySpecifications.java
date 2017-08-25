package com.archsystemsinc.ipms.persistence.search;

import java.util.Date;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.Survey_;
import com.archsystemsinc.ipms.sec.model.Survey;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public final class SurveySpecifications {

	private SurveySpecifications() {
		throw new AssertionError();
	}
	
	// API
	public static Specification<Survey> searchByEntityType(final PqrsEntityType pqrsEntityType) {
		return new Specification<Survey>() {
			@Override
			public final Predicate toPredicate(final Root<Survey> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(pqrsEntityType != null) {
					final Predicate matchingSurveyByPqrsEntityType = builder.equal(root.get(Survey_.pqrsEntityType), pqrsEntityType);
					return matchingSurveyByPqrsEntityType;
				} else return null;			
			}
		};
	}	
	
	public static Specification<Survey> searchByYear(final YearSurvey yearSurvey) {
		return new Specification<Survey>() {
			@Override
			public final Predicate toPredicate(final Root<Survey> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(yearSurvey != null) {
					final Predicate matchingSurveyByYear = builder.equal(root.get(Survey_.yearSurvey), yearSurvey);						
					return matchingSurveyByYear;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<Survey> searchByCreatedDate(final Date createdDate) {
			return new Specification<Survey>() {
				@Override
				public final Predicate toPredicate(final Root<Survey> root,
						final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					if(createdDate != null ) {
						final Predicate matchingSurveyByCreatedDate = builder.lessThan(root.get(Survey_.createdDate), createdDate );
						return matchingSurveyByCreatedDate;
					} else 
						return null;				
				}
			};
	}	
	
	public static Specification<Survey> searchByRecordStatus(final Integer recordStatus) {
		return new Specification<Survey>() {
			@Override
			public final Predicate toPredicate(final Root<Survey> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					final Predicate matchingSurveyByRecordStatus = builder.equal(root.get(Survey_.recordStatus), recordStatus);
					return matchingSurveyByRecordStatus;
							
			}
		};
	}
}