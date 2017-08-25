package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * persistence domain model for answer
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("Answers")
@Table(name = "answers")
public class Answers implements INameableEntity, Comparable<Answers> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answerid")
	private Long id;
	
	@Column(name = "answername", nullable = true)
	private String answerName;
	
	@Column(nullable = true)
	private String createdBy;
	
	@Column(nullable = true)
	private String updatedBy;
	
	@Column(name = "createddate", nullable = true)
	@OrderBy("createddate asc")
	private Date createdDate;
	
	@Column(name = "updateddate", nullable = true)
	@OrderBy("updateddate asc")
	private Date updatedDate;
	
	/** On delete its value is set to '0' and data can not be accessed. It is soft delete*/
	@Column(name = "record_status",nullable = true)
	private int recordStatus;	
	
	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Answers o) {
		// TODO Auto-generated method stub
		return 0;
	}
}