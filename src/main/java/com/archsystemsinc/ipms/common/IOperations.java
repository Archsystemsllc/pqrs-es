package com.archsystemsinc.ipms.common;

import java.util.List;


/**
 * this interface presents abstract CRUD methods for IEntity sub type objects
 * 
 * @author
 *
 * @param <T>
 *     objects of IEntity sub type
 */
public interface IOperations< T extends IEntity >{
	
	// get
	
	T findOne( final long id );
	
	List< T > findAll();
	
	// create
	
	T create( final T resource );
	
	// update
	
	void update( final T resource );
	
	// delete
	
	void delete( final long id );
	
	void deleteAll();
	
}
