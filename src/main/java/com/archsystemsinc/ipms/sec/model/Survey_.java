package com.archsystemsinc.ipms.sec.model;


import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * static meta model properties for Survey meta data access
 * no need of instantiation.
 * 
 * @author 
 * @since
 */
@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Survey.class)
public abstract class Survey_ {

	public static volatile SingularAttribute<Survey, Long> id;
	public static volatile SingularAttribute<Survey, String> categoryName;
	public static volatile SingularAttribute<Survey, String> surveyName;
	public static volatile SingularAttribute<Survey, PqrsEntityType> pqrsEntityType;
	public static volatile SingularAttribute<Survey, YearSurvey> yearSurvey;
	public static volatile SingularAttribute<Survey, Integer> recordStatus;
	public static volatile SingularAttribute<Survey, Date> createdDate;
}
