package com.archsystemsinc.ipms.sec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * static meta model properties for Principal meta data access
 * no need of instantiation.
 * 
 * @author 
 * @since
 */
@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel( Principal.class )
public abstract class Principal_{

	public static volatile SingularAttribute< Principal, Long > id;
	public static volatile SetAttribute< Principal, Role > roles;
	public static volatile SingularAttribute< Principal, String > name;
	public static volatile SingularAttribute< Principal, String > password;
	
}
