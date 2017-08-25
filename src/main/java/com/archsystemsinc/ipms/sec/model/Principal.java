package com.archsystemsinc.ipms.sec.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


/**
 * persistence domain model for Principal  
 * defines users (principal) credentials, roles and privileges
 * in this scope users represents for all users who have access to the admin page using any of roles and privileges
 * 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@Table(name = "principal")
public class Principal implements INameableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "principal_id")
	private Long id;

	/** name of user*/
	@Column(unique = true, nullable = false)
	@NotEmpty
	private String name;

	/** user password*/
	@Column(nullable = false)
	@Size(min = 4, max = 10)
	private String password;

	/** user name*/
	@Column(nullable = false)
	private String username;

	/** user email */
	@Column(nullable = true)
	@Email
	private String email;

	@Transient
	@Size(min = 4, max = 10)
	private String confirmPassword;

	/** sets of privileges of a principal*/
	@Transient
	private Set<Privilege> privileges;
	
	@Transient
	private List<Long> rolesList;
	
	@Column(name = "record_status",nullable = true)
	private int recordStatus;	
	
	/** sets roles of a principal*/
	//@formatter:off
	@ManyToMany( cascade = { CascadeType.REMOVE },fetch = FetchType.LAZY )
	@JoinTable(joinColumns = { @JoinColumn(name = "principal_id", referencedColumnName = "principal_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	@XStreamImplicit
	private Set< Role > roles;
		
	

	public Principal(){
		super();
	}

	public Principal( final String nameToSet, final String passwordToSet, final Set< Role > rolesToSet ){
		super();

		name = nameToSet;
		password = passwordToSet;
		roles = rolesToSet;
	}

	// API	

	@Override
	public Long getId() {
		return id;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Override
	public void setId(final Long idToSet) {
		id = idToSet;
	}

	

	@Override
	public String getName(){
		return name;
	}

	public void setName( final String nameToSet ){
		name = nameToSet;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword( final String passwordToSet ){
		password = passwordToSet;
	}

	public Set< Role > getRoles(){
		return roles;
	}

	public void setRoles( final Set< Role > rolesToSet ){
		roles = rolesToSet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username1) {
		username = username1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email1) {
		email = email1;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword1) {
		confirmPassword = confirmPassword1;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(final Set<Privilege> privileges1) {
		privileges = privileges1;
	}

	public List<Long> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Long> rolesList) {
		this.rolesList = rolesList;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		return result;
	}

	@Override
	public boolean equals( final Object obj ){
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		final Principal other = (Principal) obj;
		if( name == null ){
			if( other.name != null )
				return false;
		}
		else if( !name.equals( other.name ) )
			return false;
		return true;
	}

	@Override
	public String toString(){
		return new ToStringBuilder( this ).append( "id", id ).append( "name", name ).toString();
	}	
}
