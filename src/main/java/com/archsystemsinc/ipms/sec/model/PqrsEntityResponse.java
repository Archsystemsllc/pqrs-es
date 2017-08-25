package com.archsystemsinc.ipms.sec.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Transient;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * persistence domain model for PqrsEntityResponse
 * 
 * captures entity response for each question in a survey
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("entity_response")
@Table(name = "entity_response")
public class PqrsEntityResponse implements INameableEntity, Comparable<PqrsEntityResponse>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entity_response_id")
	private Long id;
	
	/** pqrs entity that takes the survey*/
	@ManyToOne
	@JoinColumn(name = "entity_id")
	private PqrsEntity pqrsEntity;
	
	/** survey*/
	@ManyToOne
	@JoinColumn(name = "survey_number_id")	
	private Survey survey;	

	/** survey question*/
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Questions question;
	
	/** question category*/
	@ManyToOne
	@JoinColumn(name = "question_category_id")
	private QuestionCategory questionCategory;
	
	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answers answer;
	
	/** pqrs entity response for a question in a survey */
	@Column(name = "answer_remarks", nullable = true)
	private String answerRemarks;
	
	/** uploaded file response*/
	@Column(name = "uploaded_filecontent", nullable = true)
	@Lob @Basic(fetch = FetchType.LAZY)
	private byte[] uploadedFileContent;
	
	/** uploaded file name*/
	@Column(name = "uploaded_filename", nullable = true)
	private String uploadedFileName;
	
	/** uploaded file type*/
	@Column(name = "uploaded_filetype", nullable = true)
	private String uploadedFileType;
	
	@Column(name = "created_by",nullable = true)
	private String createdBy;
	
	@Column(name = "updated_by",nullable = true)
	private String updatedBy;
	
	@Column(name = "created_date", nullable = true)
	@OrderBy("created_date asc")
	private Date createdDate;
	
	@Column(name = "updated_date", nullable = true)
	@OrderBy("updated_date asc")
	private Date updatedDate;	
	
	@Column(name = "record_status",nullable = true)
	private int recordStatus;
	

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public byte[] getUploadedFileContent() {
		return uploadedFileContent;
	}

	public void setUploadedFileContent(byte[] uploadedFileContent) {
		this.uploadedFileContent = uploadedFileContent;
	}

	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public String getUploadedFileType() {
		return uploadedFileType;
	}

	public void setUploadedFileType(String uploadedFileType) {
		this.uploadedFileType = uploadedFileType;
	}

	public Long getId() {
		return id;
	}

	public void setId1(Long id) {
		this.id = id;
	}

	public PqrsEntity getPqrsEntity() {
		return pqrsEntity;
	}

	public void setPqrsEntity(PqrsEntity pqrsEntity) {
		this.pqrsEntity = pqrsEntity;
	}
	
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public Answers getAnswer() {
		return answer;
	}

	public void setAnswer(Answers answer) {
		this.answer = answer;
	}

	public String getAnswerRemarks() {
		return answerRemarks;
	}

	public void setAnswerRemarks(String answerRemarks) {
		this.answerRemarks = answerRemarks;
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
	
	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(PqrsEntityResponse o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
