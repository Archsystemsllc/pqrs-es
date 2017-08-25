package com.archsystemsinc.ipms.sec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * static meta model properties for Role meta data access
 * no need of instantiation.
 * 
 * @author 
 * @since
 */
@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel( Role.class )
public abstract class Role_{
	
	public static volatile SingularAttribute< Role, Long > id;
	public static volatile SingularAttribute< Role, String > name;
	public static volatile SetAttribute< Role, Privilege > privileges;
	
}
