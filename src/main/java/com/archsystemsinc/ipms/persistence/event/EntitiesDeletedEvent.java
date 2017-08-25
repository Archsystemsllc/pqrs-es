package com.archsystemsinc.ipms.persistence.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.google.common.base.Preconditions;

/**
 * This event is fired after all entities are deleted.
 */
public final class EntitiesDeletedEvent< T extends IEntity > extends ApplicationEvent{
	
	private final Class< T > clazz;
	
	@Autowired
	private Emailer emailer;
	
	public EntitiesDeletedEvent( final Object sourceToSet, final Class< T > clazzToSet ){
		super( sourceToSet );
		
		Preconditions.checkNotNull( clazzToSet );
		clazz = clazzToSet;
	}
	
	// API
	
	public final Class< T > getClazz(){
		return clazz;
	}
	
}
