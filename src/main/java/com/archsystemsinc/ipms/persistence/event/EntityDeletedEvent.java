package com.archsystemsinc.ipms.persistence.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.google.common.base.Preconditions;

/**
 * This event should be fired when entity is updated.
 */
public final class EntityDeletedEvent< T extends IEntity > extends ApplicationEvent{
	
	private final Class< INameableEntity > clazz;
	private final INameableEntity entity;
	private Emailer emailer;
	
	public EntityDeletedEvent( final Object sourceToSet, final Class< T > clazzToSet, final T entityToSet,final Emailer emailer2 ){
		super( sourceToSet );
		emailer = emailer2;
		Preconditions.checkNotNull( clazzToSet );
		clazz = (Class<INameableEntity>) clazzToSet;
		
		Preconditions.checkNotNull( entityToSet );
		entity = (INameableEntity) entityToSet;
		
		//emailer.sendEmail(entity, "Deleted");
	}
	
	// API
	
	public final Class< INameableEntity > getClazz(){
		return clazz;
	}
	
	public final INameableEntity getEntity(){
		return Preconditions.checkNotNull( entity );
	}
	
}
