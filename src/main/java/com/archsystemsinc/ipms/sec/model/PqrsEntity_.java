package com.archsystemsinc.ipms.sec.model;


import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * static meta model properties for PQRS Entity meta data access
 * no need of instantiation.
 * 
 * @author 
 * @since
 */
@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(PqrsEntity.class)
public abstract class PqrsEntity_ {

	public static volatile SingularAttribute<PqrsEntity, Long> id;
	public static volatile SingularAttribute<PqrsEntity, PqrsEntityType> pqrsEntityType;
	public static volatile SingularAttribute<PqrsEntity, YearSurvey> yearSurvey;
	public static volatile SingularAttribute<PqrsEntity, Integer> recordStatus;
	public static volatile SingularAttribute<PqrsEntity, Date> createdDate;
}
