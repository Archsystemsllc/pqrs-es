package com.archsystemsinc.ipms.persistence.search;

import java.util.Date;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.PqrsEntityType;
import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.Questions_;
import com.archsystemsinc.ipms.sec.model.YearSurvey;

public final class QuestionsSpecifications {

	private QuestionsSpecifications() {
		throw new AssertionError();
	}
	
	// API
	public static Specification<Questions> searchByEntityType(final PqrsEntityType pqrsEntityType) {
		return new Specification<Questions>() {
			@Override
			public final Predicate toPredicate(final Root<Questions> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(pqrsEntityType != null) {
					final Predicate matchingQuestionByPqrsEntityType = builder.equal(root.get(Questions_.pqrsEntityType), pqrsEntityType);
					return matchingQuestionByPqrsEntityType;
				} else return null;			
			}
		};
	}	
	
	public static Specification<Questions> searchByYear(final YearSurvey yearSurvey) {
		return new Specification<Questions>() {
			@Override
			public final Predicate toPredicate(final Root<Questions> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(yearSurvey != null) {
					final Predicate matchingQuestionByYear = builder.equal(root.get(Questions_.yearSurvey), yearSurvey);						
					return matchingQuestionByYear;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<Questions> searchByCreatedDate(final Date createdDate) {
			return new Specification<Questions>() {
				@Override
				public final Predicate toPredicate(final Root<Questions> root,
						final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					if(createdDate != null ) {
						final Predicate matchingQuestionByCreatedDate = builder.lessThan(root.get(Questions_.createdDate), createdDate );
						return matchingQuestionByCreatedDate;
					} else 
						return null;				
				}
			};
	}	
	
	public static Specification<Questions> searchByRecordStatus(final Integer recordStatus) {
		return new Specification<Questions>() {
			@Override
			public final Predicate toPredicate(final Root<Questions> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					final Predicate matchingQuestionByRecordStatus = builder.equal(root.get(Questions_.recordStatus), recordStatus);
					return matchingQuestionByRecordStatus;
							
			}
		};
	}
}