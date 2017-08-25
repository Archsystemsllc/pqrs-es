package com.archsystemsinc.ipms.sec.model;


import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * static meta model properties for Questions meta data access
 * no need of instantiation.
 * 
 * @author 
 * @since
 */
@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Questions.class)
public abstract class Questions_ {

	public static volatile SingularAttribute<Questions, Long> id;
	public static volatile SingularAttribute<Questions, String> categoryName;
	public static volatile SingularAttribute<Questions, String> name;
	public static volatile SingularAttribute<Questions, PqrsEntityType> pqrsEntityType;
	public static volatile SingularAttribute<Questions, YearSurvey> yearSurvey;
	public static volatile SingularAttribute<Questions, Integer> recordStatus;
	public static volatile SingularAttribute<Questions, Date> createdDate;
}
