package com.archsystemsinc.ipms.persistence.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.google.common.base.Preconditions;

public final class EntityCreatedEvent< T extends Serializable > extends ApplicationEvent{
	private final Class< INameableEntity > clazz;
	private final INameableEntity entity;
	
	private Emailer emailer = null;

	public EntityCreatedEvent( final Object sourceToSet, final Class<T> clazz2, final T persistedEntity, final Emailer emailer2 ){
		super( sourceToSet );
		emailer = emailer2;
		Preconditions.checkNotNull( clazz2 );
		clazz = (Class<INameableEntity>) clazz2;
		   
		Preconditions.checkNotNull( persistedEntity );
		entity = (INameableEntity) persistedEntity;
		
		//emailer.sendEmail(entity, "Created");
		
	}
	
	// API

	public final Class< INameableEntity > getClazz(){
		return clazz;
	}
	
	public final INameableEntity getEntity(){
		return Preconditions.checkNotNull( entity );
	}
	
}
